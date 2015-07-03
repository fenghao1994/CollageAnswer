package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;

/**
 * Created by Howe on 2015/7/2.
 */
@EActivity(R.layout.activity_myinfo_edit)
public class EditActivity extends Activity{

    @ViewById
    protected HeadBackView view_head;

    @AfterViews
    protected void init(){
        view_head.setTitle("个人信息编辑");
    }

    @Click(R.id.save)
    protected void save(){
        Intent intent = new Intent(this,AttestActivity_.class);
        startActivity(intent);
    }
}
