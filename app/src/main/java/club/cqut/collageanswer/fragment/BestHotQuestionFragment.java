package club.cqut.collageanswer.fragment;


import android.os.AsyncTask;
import android.support.v4.app.Fragment;
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
 * 最热问题fragment
 * Created by fenghao on 2015/6/28.
 */
@EFragment(R.layout.fragment_best_hot_question)
public class BestHotQuestionFragment extends Fragment {
    @ViewById
    protected PullToRefreshListView listview;
    public final String REFRESH = "0";//表示下拉刷新
    public final String LOADMORE = "1";//表示上拉刷新
    public String type = REFRESH;
    public RequestParams params = null;
    public QuestionItemAdapter adapter = null;
    public String page = "1";//当前的页数
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
                params = null;
                type = REFRESH;
                refresh();
            }

            //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                type = LOADMORE;
                params = new RequestParams();
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
        params = null;
        type = REFRESH;
        refresh();
    }

    /**
     * 刷新数据
     */
    public void refresh(){

        HttpClient.get(getActivity(), HttpUrl.GET_HOT_QUESTION, params, new BaseJsonHttpResponseHandler(getActivity()) {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), "错误--" + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                List<Question> questions = JacksonMapper.parseToList(responseString, new TypeReference<List<Question>>() {
                });
                page = headers[8].getValue();
                if (type == REFRESH) {
                    adapter.addNewQuestion(questions);
                } else {
                    if(questions.size() == 0){
                        Toast.makeText(getActivity(), "没有更多数据！", Toast.LENGTH_LONG).show();
                    }
                    adapter.addOldQuestion(questions);
                }
                adapter.notifyDataSetChanged();
                listview.onRefreshComplete();

            }
        });
    }
}
