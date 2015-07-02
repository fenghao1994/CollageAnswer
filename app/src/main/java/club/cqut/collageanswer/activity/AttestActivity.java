package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;

/**
 * 认证页面
 * Created by Leero on 2015/7/2.
 */
@EActivity(R.layout.activity_attest)
public class AttestActivity extends Activity{

    @ViewById
    protected HeadBackView view_head;
    @ViewById
    protected ImageView email_attest, friends_attest, manager_attest;

    @AfterViews
    public void init(){

    }

    /**
     *跳转到邮箱认证
     */
    @Click(R.id.email_attest)
    protected void goEmailAttest(){
        Intent intent = new Intent(this, EmailAttestActivity_.class);
        startActivity(intent);
    }

    /**
     * 相互认证
     */
    @Click(R.id.friends_attest)
    protected void goFriendAttest(){
        Intent intent = new Intent(this, FriendsAttestActivity_.class);
        startActivity(intent);
    }

    /**
     * 管理员认证
     */
    @Click(R.id.manager_attest)
    protected void goManagerAttest(){

    }

}
