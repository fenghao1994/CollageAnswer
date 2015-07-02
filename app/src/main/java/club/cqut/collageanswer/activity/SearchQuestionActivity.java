package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.adapter.QuestionItemAdapter;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.model.Question;

/**
 * 搜索问题页面
 * Created by fenghao on 2015/6/29.
 */
@EActivity(R.layout.activity_search)
public class SearchQuestionActivity extends Activity implements OnClickListener{

    @ViewById
    protected EditText search_question;
    @ViewById
    protected ImageView search_delect;
    @ViewById
    protected HeadBackView headback;
    @ViewById
    protected TextView search_text;
    @ViewById
    protected PullToRefreshListView listview;

    public List<Question> list;

    @AfterViews
    protected void init(){
        search_question.setOnClickListener(this);
        search_delect.setOnClickListener(this);
        search_text.setOnClickListener(this);
        headback.setTitle("搜索问题");
        search_question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (search_question.getText().length() != 0) {
                    search_delect.setImageResource(R.mipmap.delete);
                } else {
                    search_delect.setImageResource(R.mipmap.search);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        * 测试
        * */
        list = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            list.add(new Question());
        }
        QuestionItemAdapter adapter = new QuestionItemAdapter(this);
        initListView();
        listview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new FinishRefresh().execute();
            }
        });

        listview.setAdapter(adapter);
    }

    private void initListView(){

        ILoadingLayout endLabels = listview.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("向上拉加载更多！！！");
        endLabels.setRefreshingLabel("正在加载！！！");
        endLabels.setReleaseLabel("放开进行加载！！！");
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


    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.search_text:
                Toast.makeText(this, "搜索", Toast.LENGTH_LONG).show();
                break;
            case R.id.search_delect:
                search_question.setText("");
                break;
        }
    }
}
