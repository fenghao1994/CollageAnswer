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

import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.adapter.NoticeItemAdapter;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.model.Notice;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * 通知页面
 * Created by fenghao on 2015/7/5.
 */
@EActivity(R.layout.activity_notice)
public class NoticeActivity extends Activity {

    @ViewById
    protected HeadBackView headback;
    @ViewById
    protected PullToRefreshListView listview;

    public final String REFRESH = "0";//表示下拉刷新
    public final String LOADMORE = "1";//表示上拉刷新
    public String type = REFRESH;
    public RequestParams params = null;
    public NoticeItemAdapter adapter = null;
    public String page = "1";//当前的页数

    @Pref
    protected UserInfo_ userInfo;

    @AfterViews
    protected void init(){
        headback.setTitle("通知");
        firstIn();
        adapter = new NoticeItemAdapter(this);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        initListView();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Notice notice = adapter.list.get(position - 1);
                Intent intent = new Intent(getApplication(), SpecificNoticeActivity_.class);
                intent.putExtra("content", notice.getContent());
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

        HttpClient.get(this, HttpUrl.POST_ALL_NOTICE, params, new BaseJsonHttpResponseHandler( this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), "错误--" + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                List<Notice> notices = JacksonMapper.parseToList(responseString, new TypeReference<List<Notice>>() {
                });
                page = headers[8].getValue();
                if (type == REFRESH) {
                    adapter.addNewNotice(notices);
                    adapter.notifyDataSetChanged();
                } else {
                    if (notices.size() == 0) {
                        Toast.makeText(getApplication(), "没有更多数据！", Toast.LENGTH_LONG).show();
                    } else {
                        adapter.addOldNotice(notices);
                        adapter.notifyDataSetChanged();
                    }
                }
                listview.onRefreshComplete();
            }
        });
    }
}
