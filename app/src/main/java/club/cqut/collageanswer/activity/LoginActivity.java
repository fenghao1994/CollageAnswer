package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.Header;
import org.json.JSONObject;

import java.util.Map;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.db.UserDB;
import club.cqut.collageanswer.model.User;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * 登录页面
 * Created by fenghao on 2015/6/29.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity{

    @ViewById
    protected HeadBackView view_head;
    @ViewById
    protected TextView sign_in;
    @ViewById
    protected TextView try_look;
    @ViewById
    protected MaterialEditText editText_account;
    @ViewById
    protected MaterialEditText editText_password;
    @ViewById
    protected ImageView imageView_login;
    @Pref
    protected UserInfo_ userInfo;
    @Bean
    protected UserDB userDB;

    protected RequestParams params = null;

    @AfterViews
    protected void init(){
        view_head.hineLeftButton();
        view_head.setTitle("登录");

        if(userInfo.id().get() != -1){
            skipActivity();
        }

        //判断密码长度不能小于8位
        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( editText_password.getText().length() < 8){
                    editText_password.setError("密码长度不能小于8位");
                }else{
                    editText_password.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 注册成功返回时自动时填写账号
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            if(data != null) {
                String email = data.getStringExtra("email");
                editText_account.setText(email);
            }
        }
    }

    /**
     * 进入注册页面
     */
    @Click(R.id.sign_in)
    protected void clickSignUp(){
        Intent t = new Intent();
        t.setClass(this, SignUpActivity_.class);
        startActivityForResult(t, 0);
    }

    /**
     * 直接进入主页面
     */
    @Click(R.id.try_look)
    protected void tryLook(){
        Intent t = new Intent();
        t.setClass(this, HomeActivity_.class);
        startActivity(t);
    }

    /**
     * 点击登陆
     */
    @Click(R.id.imageView_login)
    protected void clickLogin(){
        if( editText_password.getText().length() < 8){
            editText_password.setError("密码长度应该大于8位");
        }else{
            postLogin();
        }
    }

    /**
     * 登陆请求发送
     */
    public void postLogin(){
        params = new RequestParams();
        params.put("login", editText_account.getText().toString());
        params.put("password", editText_password.getText().toString());
        HttpClient.post(this, HttpUrl.POST_LOGIN, params, new BaseJsonHttpResponseHandler(this){
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                User user = JacksonMapper.parse(responseString, User.class);
                userInfo.edit().id().put(user.getId()).name().put(user.getUsername()).email().put(user.getEmail()).token().put(user.getToken()).apply();
                HttpClient.resetAuth(user.getToken());
                skipActivity();
                Toast.makeText(getApplication(), "登陆成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), "登陆失败 " + statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 跳转activity
     */
    public void skipActivity(){
        Intent intent = new Intent(this, HomeActivity_.class);
        startActivity(intent);
    }

}
