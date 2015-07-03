package club.cqut.collageanswer.activity;

import android.app.Activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;

/**
 * Created by Howe on 2015/7/2.
 */
@EActivity(R.layout.activity_my_answer)
public class MyAnswerActivity extends Activity {
    @ViewById
    protected HeadBackView view_head;

    @AfterViews
    protected void init(){
        view_head.setTitle("我的回答");
    }
}
