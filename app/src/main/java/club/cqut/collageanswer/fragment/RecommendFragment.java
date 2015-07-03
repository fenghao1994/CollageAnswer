package club.cqut.collageanswer.fragment;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.adapter.RankItemAdapter;
import club.cqut.collageanswer.adapter.RecommendAdapter;
import club.cqut.collageanswer.model.Question;

/**
 * 推荐Fragment
 * Created by fenghao on 2015/6/29.
 */
@EFragment(R.layout.fragment_recommend)
public class RecommendFragment extends Fragment {

    @ViewById
    protected PullToRefreshListView listview;

    public List<Question> list;

    @AfterViews
    protected void init(){

        list = new ArrayList<>();

        for(int n=0; n<10; n++){
            list.add(new Question());
        }

        RecommendAdapter adapter = new RecommendAdapter(getActivity(), list);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        initListView();
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            //下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            }
            //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            }
        });
        listview.setAdapter(adapter);

    }


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
}
