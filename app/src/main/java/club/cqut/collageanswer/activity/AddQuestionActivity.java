package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.Header;
import org.w3c.dom.Text;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;

/**
 * 问问题页面
 * Created by fenghao on 2015/6/29.
 */
@EActivity(R.layout.activity_add_question)
public class AddQuestionActivity extends Activity{

    @ViewById
    protected HeadBackView view_head;

    @ViewById
    protected EditText question_title;

    @ViewById
    protected EditText question_content;

    //点击标签按钮
    @ViewById
    protected Button btn_label;

    //提交按钮
    @ViewById
    protected Button btn_commit;

    @Pref
    protected UserInfo_ userInfo;


    @AfterViews
    protected void init(){
        view_head.setTitle("描述你的问题");
    }


    /**
     * 提交问题
     */
    @Click(R.id.btn_commit)
    protected void clickCommit(){
        boolean flag = true;

        if(userInfo.id().get() == -1){
            Toast.makeText(getApplication(), "提问题前,请先登录！", Toast.LENGTH_LONG).show();
            Intent t = new Intent(this, LoginActivity_.class);
            startActivity(t);
            return;
        }

        if(question_title.getText().length() < 10){
            Toast.makeText(getApplication(), "问题标题不能少于10个字", Toast.LENGTH_LONG).show();
            flag = false;
        }
        if(question_content.getText().length() < 20){
            Toast.makeText(getApplication(), "问题内容不能少于20个字", Toast.LENGTH_LONG).show();
            flag = false;
        }

        if( flag){
            commitQuestion();
        }
    }

    public void commitQuestion(){

        RequestParams params = new RequestParams();
        params.put("user_id", userInfo.id().get());
        params.put("content", question_content.getText());
        params.put("title", question_title.getText().toString());
        params.put("label", "");

        HttpClient.post(this, HttpUrl.POST_ONE_QUESTION, params, new BaseJsonHttpResponseHandler(this){
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getApplication(), "提交成功", Toast.LENGTH_LONG).show();
                backActivity();
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), "提交失败--" + statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 提交问题成功后回到
     */
    public void backActivity(){
        this.finish();
    }
}
