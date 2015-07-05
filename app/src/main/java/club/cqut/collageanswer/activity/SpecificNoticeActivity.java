package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;

/**
 * 具体通知内容
 * Created by fenghao on 2015/7/5.
 */
@EActivity(R.layout.activity_specific_notice)
public class SpecificNoticeActivity extends Activity {

    @ViewById
    protected HeadBackView headback;

    @ViewById
    protected TextView content;

    @AfterViews
    protected void init(){

        headback.setTitle("详细通知");
        Intent intent = getIntent();
        String str = intent.getStringExtra("content");
        content.setText(str);
    }
}
