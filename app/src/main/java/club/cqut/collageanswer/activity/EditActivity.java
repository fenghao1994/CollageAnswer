package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.Header;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.model.User;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * Created by Howe on 2015/7/2.
 */
@EActivity(R.layout.activity_myinfo_edit)
public class EditActivity extends Activity{

    @ViewById
    protected HeadBackView view_head;

    @ViewById
    protected EditText name, hobby, sinal;

    @ViewById
    protected RadioGroup sex;
    @ViewById
    protected RadioButton male, female;

    @Pref
    protected UserInfo_ userInfo;

    protected RequestParams params = null;
    private User user;
    TextView textView;

    @AfterViews
    protected void init(){

        getCurrentUser();

        params = new RequestParams();

        view_head.setTitle("个人信息编辑");
        view_head.showRightText();

        textView = view_head.getRightText();
        textView.setText("提交");
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(20.0f);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMess();
            }
        });
    }


    public void postMess(){
        if (male.isChecked()) {
            params.put("sex", 1);
        } else {
            params.put("sex", 0);
        }
        params.put("user_id", userInfo.id().get());
        params.put("username", name.getText());
        params.put("hobby", hobby.getText());
        params.put("user_sign", sinal.getText());

        HttpClient.post(this, HttpUrl.POST_USER_INFO, params, new BaseJsonHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), "提交失败---" + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                goMine();
            }
        });
    }

    /**
     * 得到当前用户的信息
     */
    public void getCurrentUser(){

        RequestParams params = new RequestParams();
        params.put("access_token", userInfo.token().get().toString());

        HttpClient.get(this, HttpUrl.GET_CURRENT_USER, params, new BaseJsonHttpResponseHandler(this) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                user = JacksonMapper.parse(responseString, User.class);

                //刷新用户本地数据
                if (user.getApprove() != null && !user.getApprove().equals("")) {
                    userInfo.approve().put(user.getApprove());
                } else {
                    userInfo.approve().put("");
                }
                if (user.getRealname() != null && !user.getRealname().equals("")) {
                    userInfo.realName().put(user.getRealname());
                } else {
                    userInfo.realName().put("");
                }
                if (user.getStuNum() != null && !user.getStuNum().equals("")) {
                    userInfo.stuNumber().put(user.getStuNum());
                } else {
                    userInfo.stuNumber().put("");
                }
                userInfo.edit().id().put(user.getId()).name().put(user.getUsername()).email().put(user.getEmail()).token().put(user.getToken()).apply();

                getData(user);
                Toast.makeText(getApplication(), user.getSex() + "", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), "得到当前用户失败---" + statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 跳转到我的信息
     */
    public void goMine(){
        Intent intent = new Intent(this,HomeActivity_.class);
        startActivity(intent.putExtra("mine", 4));
    }

    /**
     * 设置当前信息
     * @param user
     */
    public void getData(User user){
        name.setText(user.getUsername());
        hobby.setText(user.getHobby());
        sinal.setText(user.getUserSign());
        if (user.getSex()){
            sex.clearCheck();
            sex.check(male.getId());
        } else {
            sex.clearCheck();
            sex.check(female.getId());
        }

    }
}
