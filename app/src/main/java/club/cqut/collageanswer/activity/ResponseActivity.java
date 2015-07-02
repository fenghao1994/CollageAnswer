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

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.model.Question;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;


/**
 * 回答问题页面
 * Created by Leero on 2015/7/2.
 */
@EActivity(R.layout.activity_response)
public class ResponseActivity extends Activity {

    @ViewById
    protected HeadBackView view_head;

    @ViewById
    protected EditText answer_content;

    @ViewById
    protected Button btn_commit;

    @Pref
    protected UserInfo_ userInfo;

    Question question = null;

    @AfterViews
    public void init(){
        Intent intent = getIntent();
        question = (Question) intent.getSerializableExtra("question");
        view_head.setTitle("我来回答");
    }


    /**
     * 提交答案
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

      /*  if(userInfo.realName().get() == null){
            Toast.makeText(getApplication(), "非认证用户不能进行认证，请先认证", Toast.LENGTH_LONG).show();
            Intent t = new Intent(this, AttestActivity_.class);
            startActivity(t);
        }*/

        if(answer_content.getText().length() == 0){
            Toast.makeText(getApplication(), "请输入问题的答案", Toast.LENGTH_LONG).show();
            flag = false;
        }
        if(flag){
            commitAnswer();
        }

    }

    private void commitAnswer() {

        RequestParams params = new RequestParams();
        params.put("content", answer_content.getText());
        params.put("praise_num", 0);
        params.put("user_id", userInfo.id().get());
        params.put("user_role", "匿名");
        params.put("question_id", question.getId());

        HttpClient.post(this, HttpUrl.POST_ONE_ANSWER, params, new BaseJsonHttpResponseHandler(this){
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getApplication(), "回答问题成功！", Toast.LENGTH_LONG).show();
                backActivity();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), "回答失败---" +statusCode, Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * 提交成功回到之前的页面
     */
    public void backActivity(){
        this.finish();
    }

}
