package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;

/**
 * 登录页面
 * Created by fenghao on 2015/6/29.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity implements View.OnClickListener{

    @ViewById
    protected HeadBackView view_head;
    @ViewById
    protected TextView sign_in;
    @ViewById
    protected TextView try_look;

    @AfterViews
    protected void init(){
        view_head.hineLeftButton();
        view_head.setTitle("登录");
        sign_in.setOnClickListener(this);
        try_look.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent t = new Intent();
        switch (v.getId()){
            case R.id.sign_in:
                t.setClass(this, SignUpActivity_.class);
                startActivity(t);break;
            case R.id.try_look:
                t.setClass(this, HomeActivity_.class);
                startActivity(t);break;
            default:break;
        }
    }
}
