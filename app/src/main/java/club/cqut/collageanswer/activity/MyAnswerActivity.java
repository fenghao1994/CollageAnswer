package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.Header;
import org.codehaus.jackson.type.TypeReference;

import java.io.Serializable;
import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.adapter.MyAnswerAdapter;
import club.cqut.collageanswer.adapter.QuestionItemAdapter;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.model.Question;
import club.cqut.collageanswer.model.QuestionAnswer;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * Created by Howe on 2015/7/2.
 */
@EActivity(R.layout.activity_my_answer)
public class MyAnswerActivity extends Activity {
    @ViewById
    protected HeadBackView view_head;
    @ViewById
    protected PullToRefreshListView listview;

    @Pref
    protected UserInfo_ userInfo;

    public final String REFRESH = "0";//表示下拉刷新
    public final String LOADMORE = "1";//表示上拉刷新
    public String type = REFRESH;
    public RequestParams params = null;
    public MyAnswerAdapter adapter = null;
    public String page = "1";//当前的页数

    @AfterViews
    protected void init(){
        view_head.setTitle("我的回答");

        firstIn();
        adapter = new MyAnswerAdapter(this);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        initListView();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuestionAnswer question = adapter.list.get(position - 1);
                Intent intent = new Intent(getApplication(), AllAnswerActivity_.class);
                intent.putExtra("question", (Serializable)question);
                startActivity(intent);
            }
        });

        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            //下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                params = new RequestParams();
                params.put("user_id", userInfo.id().get());
                type = REFRESH;
                refresh();
            }

            //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                type = LOADMORE;
                params = new RequestParams();
                params.put("user_id", userInfo.id().get());
                params.put("page", Integer.parseInt(page) + 1);
                refresh();
            }
        });
        listview.setAdapter(adapter);
    }

    /**
     * 初始化pulltorefresh
     */
    private void initListView(){
        ILoadingLayout startLabels = listview.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("向下拉进行刷新！！！");
        startLabels.setRefreshingLabel("正在刷新！！！");
        startLabels.setReleaseLabel("放开进行刷新！！！");

        ILoadingLayout endLabels = listview.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("向上拉加载更多！！！");
        endLabels.setRefreshingLabel("正在加载！！！");
        endLabels.setReleaseLabel("放开进行加载！！！");
    }

    /**
     * 第一次进入
     */
    public void firstIn(){
        //第一次进来的时候直接进行刷新
        params = new RequestParams();
        params.put("user_id", userInfo.id().get());
        type = REFRESH;
        refresh();
    }

    /**
     * 刷新数据
     */
    public void refresh(){

        HttpClient.get(this, HttpUrl.GET_MYANSWER, params, new BaseJsonHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), "错误--" + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                List<QuestionAnswer> questions_answer = JacksonMapper.parseToList(responseString, new TypeReference<List<QuestionAnswer>>() {
                });
                page = headers[8].getValue();
                if (type == REFRESH) {
                    if(questions_answer != null && questions_answer.size() != 0){
                        adapter.addNewQA(questions_answer);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    if (questions_answer.size() == 0) {
                        Toast.makeText(getApplication(), "没有更多数据！", Toast.LENGTH_LONG).show();
                    }else{
                        adapter.addOldQA(questions_answer);
                        adapter.notifyDataSetChanged();
                    }
                }

                listview.onRefreshComplete();

            }
        });
    }
}
