package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;

/**
 * Created by Howe on 2015/7/3.
 */
@EActivity(R.layout.activity_attest_edit)
public class AttestEditActivity extends Activity {

    @ViewById
    protected HeadBackView view_head;
    @ViewById
    protected EditText real_name, student_no;

    TextView textView;

    @Pref
    protected UserInfo_ userInfo;

    protected RequestParams params = null;

    @AfterViews
    protected void init(){
        view_head.setTitle("认证编辑");
        view_head.showRightText();

        textView = view_head.getRightText();
        textView.setText("提交");

        real_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        student_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMess();
            }
        });
    }

    /**
     * 输入信息验证
     */
    public void checkMess(){
        if(real_name.getText().length() == 0) {
            real_name.setError("真名不能为空");
            return;
        } else if(real_name.getText().length() == 1){
            real_name.setError("请输入您的真名");
            return;
        } else if(student_no.getText().length() == 0){
            student_no.setError("学号不能为空");
            return;
        } else if(student_no.getText().length() != 11) {
            student_no.setError("请输入正确的学号");
            return;
        }

        postData();
    }


    /**
     * 发送认证数据提交请求
     */
    public void postData(){
        params = new RequestParams();
        params.put("user_id", userInfo.id().get());
        params.put("stu_num", student_no.getText().toString());
        params.put("real_name", real_name.getText().toString());

        HttpClient.post(this, HttpUrl.POST_USER_APPROVE, params, new BaseJsonHttpResponseHandler(this) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getApplication(), "数据已提交", Toast.LENGTH_SHORT).show();
                goSelection();
                userInfo.edit().realName().put(real_name.getText().toString()).stuNumber().put(student_no.getText().toString()).apply();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), statusCode + "---" + responseString, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     *  跳转到认证选择
     */
    public void goSelection(){
        Intent intent = new Intent(this, EmailAttestActivity_.class);
        startActivity(intent);
    }
}
