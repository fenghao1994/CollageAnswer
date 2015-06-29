package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;

/**
 * 问问题页面
 * Created by fenghao on 2015/6/29.
 */
@EActivity(R.layout.activity_add_question)
public class AddQuestionActivity extends Activity{

    @ViewById
    protected HeadBackView view_head;

    @ViewById
    protected EditText question_title;

    @ViewById
    protected EditText question_content;

    //点击标签按钮
    @ViewById
    protected Button btn_label;

    //提交按钮
    @ViewById
    protected Button btn_commit;


    @AfterViews
    protected void init(){
        view_head.setTitle("描述你的问题");
    }
}
