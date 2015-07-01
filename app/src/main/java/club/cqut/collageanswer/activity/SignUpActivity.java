package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * 注册页面
 * Created by fenghao on 2015/6/29.
 */
@EActivity(R.layout.activity_sign)
public class SignUpActivity extends Activity {

    @ViewById
    protected HeadBackView view_head;
    @ViewById
    protected MaterialEditText editText_username;
    @ViewById
    protected MaterialEditText editText_account;
    @ViewById
    protected MaterialEditText editText_password;
    @ViewById
    protected MaterialEditText editText_password_confirm;
    @ViewById
    protected ImageView imageView_signup;

    protected RequestParams params = null;

    boolean flag2 = true;

    @AfterViews
    protected void init(){
        view_head.setTitle("注册");

        /**
         * 验证密码是否大于8位
         */
        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText_password.getText().length() < 8) {
                    editText_password.setError("密码不能小于8位");
                    flag2 = false;
                } else {
                    flag2 = true;
                    editText_password.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText_password_confirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText_password_confirm.getText().length() < 8) {
                    editText_password_confirm.setError("密码不能小于8位");
                    flag2 = false;
                } else {
                    flag2 = true;
                    editText_password_confirm.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Click(R.id.imageView_signup)
    protected void clickSignUp(){
        checkMessage();
    }
    /**
     * 验证填写的信息的合法性
     */
    public void checkMessage(){
        boolean flag1 = true;
        if(editText_username.getText().length() == 0){
            editText_username.setError("昵称不能为空");
            flag1 = false;
        }
        if(editText_account.getText().length() == 0){
            editText_account.setError("请输入邮箱号");
            flag1 = false;
        }else if(! isEmail( editText_account.getText().toString())){
            editText_account.setError("邮箱格式错误");
            flag1 = false;
        }
        if(editText_password.getText().length() == 0 ){
            editText_password.setError("密码不能为空");
            flag1 = false;
        }else if(editText_password_confirm.getText().length() == 0 ){
            editText_password_confirm.setError("密码不能为空");
            flag1 = false;
        }else if(! editText_password.getText().toString().equals ( editText_password_confirm.getText().toString())){
            editText_password.setError("两次密码不一致");
            editText_password_confirm.setError("两次密码不一致");
            flag1 = false;
        }
        if(flag1 && flag2){
            postSignUp();
        }

    }


    /**
     * 发送注册请求
     */
    public void postSignUp(){
        params = new RequestParams();
        params.put("username", editText_username.getText().toString());
        params.put("email", editText_account.getText().toString());
        params.put("password", editText_password.getText().toString());

        HttpClient.post(this, HttpUrl.POST_SIGN_UP, params, new BaseJsonHttpResponseHandler(this){
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getApplication(), "注册成功！！！", Toast.LENGTH_LONG).show();
                finished();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Map<String, Object> map = JacksonMapper.parse(responseString);
                if(map.containsKey("email")){
                    Toast.makeText(getApplication(), "email已被注册,请更换邮箱！", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplication(), statusCode + "---" + responseString, Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /**
     * 邮箱验证
     * @param email
     * @return
     */
    public boolean isEmail(String email){
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 注册成功，跳转到登陆页面
     */
    public void finished(){
        Intent intent = new Intent(this, LoginActivity_.class);
        intent.putExtra("email", editText_account.getText().toString());
        setResult(0, intent);
        this.finish();
    }
}
