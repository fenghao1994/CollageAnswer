package club.cqut.collageanswer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.Header;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.activity.AttestActivity_;
import club.cqut.collageanswer.activity.AttestEditActivity_;
import club.cqut.collageanswer.activity.EditActivity_;
import club.cqut.collageanswer.activity.HomeActivity_;
import club.cqut.collageanswer.activity.LoginActivity_;
import club.cqut.collageanswer.activity.MyAnswerActivity_;
import club.cqut.collageanswer.activity.MyFocusActivity_;
import club.cqut.collageanswer.activity.MyQuestionActivity_;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.model.User;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * 我的信息fragment
 * Created by fenghao on 2015/6/29.
 */

@EFragment(R.layout.fragment_mine)
public class MineFragment extends Fragment {

    @ViewById
    protected LinearLayout mine_layout;
    @ViewById
    protected HeadBackView view_head;

    @Pref
    protected UserInfo_ userInfo;
    User user;

    @AfterViews
    protected void init(){
        getCurrentUser();
    }

    @Click(R.id.go_attest)
    protected void goAttestEdit(){
        if (userInfo.realName().get() != null && !userInfo.realName().get().equals("")
                && userInfo.stuNumber().get() != null && !userInfo.stuNumber().equals("") ){
            Intent intent = new Intent(getActivity(), AttestActivity_.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getActivity(), AttestEditActivity_.class);
            startActivity(intent);
        }
    }

    @Click(R.id.my_question)
    protected void goMyQuestion(){
        Intent intent = new Intent(getActivity(), MyQuestionActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.my_answer)
    protected void goMyAnswer(){
        Intent intent = new Intent(getActivity(), MyAnswerActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.my_friends)
    protected void goMyFriends(){
        Intent intent = new Intent(getActivity(), MyFocusActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.load_out)
    protected void loadOut(){
        HttpClient.post(getActivity(), HttpUrl.POST_LOGIN_OUT, null, new BaseJsonHttpResponseHandler(getActivity()) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                userInfo.edit().name().put("").email().put("").stuNumber().put("").id().put(-1).realName().put("").token().put("").apply();
                Intent intent = new Intent(getActivity(), HomeActivity_.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), "未知错误", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), HomeActivity_.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 得到当前用户的信息
     */
    public void getCurrentUser(){

        RequestParams params = new RequestParams();
        params.put("access_token", userInfo.token().get().toString());

        HttpClient.get(getActivity(), HttpUrl.GET_CURRENT_USER, params, new BaseJsonHttpResponseHandler(getActivity()) {
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
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), "得到当前用户失败---" + statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }


}
