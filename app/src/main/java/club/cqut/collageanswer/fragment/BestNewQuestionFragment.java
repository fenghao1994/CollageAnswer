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
import club.cqut.collageanswer.adapter.QuestionItemAdapter;
import club.cqut.collageanswer.model.Question;

/**
 * 最新问题fragment页面
 * Created by fenghao on 2015/6/28.
 */
@EFragment(R.layout.fragment_best_new_question)
public class BestNewQuestionFragment extends Fragment {

    @ViewById
    protected PullToRefreshListView listview;

    public List<Question> list;

    @AfterViews
    protected void init(){

        /*
         *假数据验证
         */
        list = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            list.add(new Question());
        }
        QuestionItemAdapter adapter = new QuestionItemAdapter(getActivity(), list);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        initListView();
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            //下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new FinishRefresh().execute();
            }
            //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new FinishRefresh().execute();
            }
        });
        listview.setAdapter(adapter);
    }

    /*
     * 测试下拉上拉刷新
     */
    public class FinishRefresh extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            listview.onRefreshComplete();
        }
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
