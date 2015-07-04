package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;
import org.codehaus.jackson.type.TypeReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.adapter.QuestionItemAdapter;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.model.Question;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * 搜索问题页面
 * Created by fenghao on 2015/6/29.
 */
@EActivity(R.layout.activity_search)
public class SearchQuestionActivity extends Activity{

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

    public RequestParams params = null;
    public QuestionItemAdapter adapter = null;
    public List<Question> list;
    public String page = "1";//当前的页数
    public final String REFRESH = "0";//表示下拉刷新
    public final String LOADMORE = "1";//表示上拉刷新
    public String type = REFRESH;


    @AfterViews
    protected void init(){
        headback.setTitle( "搜索问题");
        adapter = new QuestionItemAdapter( this);
        search_question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (search_question.getText().length() != 0) {
                    search_delect.setImageResource(R.mipmap.delete);
                    listview.setEnabled(true);
                } else {
                    search_delect.setImageResource(R.mipmap.search);
                    listview.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        initListView();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Question question = adapter.list.get(position - 1);
                Intent intent = new Intent(getApplication(), AllAnswerActivity_.class);
                intent.putExtra("question", (Serializable) question);
                startActivity(intent);
            }
        });

        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            //下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                params = new RequestParams();
                params.put("title", search_question.getText());
                params.put("page", "1");
                type = REFRESH;
                searchQuestion();
            }

            //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                type = LOADMORE;
                params = new RequestParams();
                params.put("title", search_question.getText());
                params.put("page", Integer.parseInt(page) + 1);
                searchQuestion();
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

    /**
     * 点击x按钮
     */
    @Click(R.id.search_delect)
    protected void clickSearchDelect(){
        search_question.setText("");
    }

    /**
     * 点击搜索按钮
     */
    @Click(R.id.search_text)
    protected void clickSearchText(){
        if(search_question.getText().length() == 0){
            Toast.makeText(this, "请输入要搜索的关键字", Toast.LENGTH_LONG).show();
            return;
        }
        params = new RequestParams();
        params.put("title", search_question.getText());
        searchQuestion();
    }

    public void searchQuestion(){
        HttpClient.post(this, HttpUrl.POST_SEARCH_QUESTION, params, new BaseJsonHttpResponseHandler(this){
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                List<Question> questions = JacksonMapper.parseToList(responseString, new TypeReference<List<Question>>() {
                });
                page = headers[8].getValue();
                if (type == REFRESH) {
                    if(questions.size() == 0){
                        Toast.makeText(getApplication(), "暂未搜到与关键相关的问题", Toast.LENGTH_LONG).show();
                    }else{
                        adapter.addNewQuestion(questions);
                    }

                } else {
                    if(questions.size() == 0){
                        Toast.makeText(getApplication(), "没有更多数据！", Toast.LENGTH_LONG).show();
                    }
                    adapter.addOldQuestion(questions);
                }
                adapter.notifyDataSetChanged();
                listview.onRefreshComplete();
            }
        });
    }
}
