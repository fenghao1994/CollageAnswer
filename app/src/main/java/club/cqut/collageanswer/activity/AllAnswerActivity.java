package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;
import org.codehaus.jackson.type.TypeReference;

import java.io.Serializable;
import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.adapter.AnswerItemAdapter;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.model.Answer;
import club.cqut.collageanswer.model.Question;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * 问题的回答页面
 * Created by fenghao on 2015/7/1.
 */
@EActivity(R.layout.activity_all_answer)
public class AllAnswerActivity extends Activity {
    @ViewById
    protected HeadBackView headback;
    @ViewById
    protected TextView question_label;
    @ViewById
    protected TextView question_title;
    @ViewById
    protected TextView question_content;
    @ViewById
    protected PullToRefreshListView listview;

    public final String REFRESH = "0";//表示下拉刷新
    public final String LOADMORE = "1";//表示上拉刷新
    public String type = REFRESH;
    public RequestParams params = null;
    public AnswerItemAdapter adapter;
    public String page = "1";//当前的页数

    Question question;

    @AfterViews
    protected void init(){
        headback.setTitle("详细");
        Intent  intent = getIntent();
        question  = (Question) intent.getSerializableExtra("question");
        String label = splitLabel(question);
        question_label.setText(label);
        question_title.setText(question.getTitle());
        question_content.setText(question.getContent());
        firstIn();
        initListView();
        headback.showRightButton();
        headback.setImageViewRightIconRes(R.mipmap.bootstrap_1);
        headback.getRightButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(getApplication(), ResponseActivity_.class);
                t.putExtra("question", question);
                startActivity(t);
            }
        });

        adapter = new AnswerItemAdapter(this);
        listview.setMode(PullToRefreshBase.Mode.BOTH);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Answer answer = adapter.list.get(position - 1);
                Intent t = new Intent(getApplication(), SpecificAnswerActivity_.class);
                t.putExtra("question", question);
                t.putExtra("answer", answer);
                startActivity(t);
            }
        });

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

        HttpClient.get(this, HttpUrl.GET_ALL_ANSWERS + question.getId(), params, new BaseJsonHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), "错误--" + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                List<Answer> answers = JacksonMapper.parseToList(responseString, new TypeReference<List<Answer>>() {
                });
                page = headers[8].getValue();
                if (type == REFRESH) {
                    adapter.addNewAnswer(answers);
                } else {
                    if (answers.size() == 0) {
                        Toast.makeText(getApplication(), "没有更多数据！", Toast.LENGTH_LONG).show();
                    }
                    adapter.addOldAnswer(answers);
                }
                adapter.notifyDataSetChanged();
                listview.onRefreshComplete();

            }
        });
    }


    /**
     * 将标签进行整理
     * @param question
     * @return
     */
    public String splitLabel(Question question){
        String[] strings = {};
        String str = "";
        if( question.getLabel() != null && !question.getLabel().equals("")){
            strings = question.getLabel().split(",");
            for(int i = 0 ; i < strings.length ; i++){
                str += strings[i] + "  ";
            }
        }
        return str;
    }
}
