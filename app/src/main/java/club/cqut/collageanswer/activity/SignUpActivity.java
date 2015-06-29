package club.cqut.collageanswer.activity;

import android.app.Activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;

/**
 * 注册页面
 * Created by fenghao on 2015/6/29.
 */
@EActivity(R.layout.activity_sign)
public class SignUpActivity extends Activity{

    @ViewById
    protected HeadBackView view_head;

    @AfterViews
    protected void init(){
        view_head.setTitle("注册");
    }
}
