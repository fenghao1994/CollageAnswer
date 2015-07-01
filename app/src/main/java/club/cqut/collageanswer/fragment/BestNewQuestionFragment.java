package club.cqut.collageanswer.fragment;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;
import org.codehaus.jackson.type.TypeReference;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.adapter.QuestionItemAdapter;
import club.cqut.collageanswer.model.Question;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * 最新问题fragment页面
 * Created by fenghao on 2015/6/28.
 */
@EFragment(R.layout.fragment_best_new_question)
public class BestNewQuestionFragment extends Fragment {

    @ViewById
    protected PullToRefreshListView listview;

    public final String REFRESH = "0";//表示下拉刷新
    public final String LOADMORE = "1";//表示上拉刷新
    public String type = REFRESH;
    public RequestParams params = null;
    public QuestionItemAdapter adapter = null;
    @AfterViews
    protected void init(){
        firstIn();
        adapter = new QuestionItemAdapter(getActivity());
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        initListView();
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            //下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                type = REFRESH;
                params = new RequestParams();
                params.put("type", type);
                refresh();
            }

            //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                int id = -1;//列表中最后一项的id
                List<Question> list = adapter.getList();
                id = (list.get ( list.size() - 1)).getId();
                type = LOADMORE;
                params = new RequestParams();
                params.put("type", type);
                params.put("id", id);
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
        type = REFRESH;
        params.put("type", type);
        refresh();
    }

    /**
     * 刷新数据
     */
    public void refresh(){

        HttpClient.get(getActivity(), HttpUrl.GET_NEW_QUESTION, params, new BaseJsonHttpResponseHandler(getActivity()) {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), "错误--" + statusCode, Toast.LENGTH_LONG).show();
                Log.i("a", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                List<Question> questions = JacksonMapper.parseToList(responseString, new TypeReference<List<Question>>() {});

                Log.w("h", headers.toString());

                if (type == REFRESH) {
                    adapter.addNewQuestion(questions);
                } else {
                    adapter.addOldQuestion(questions);
                }
                adapter.notifyDataSetChanged();
                listview.onRefreshComplete();
            }
        });
    }

}
