package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.model.Question;


/**
 * 回答问题页面
 * Created by Leero on 2015/7/2.
 */
@EActivity(R.layout.activity_response)
public class ResponseActivity extends Activity {

    @AfterViews
    public void init(){
        Intent intent = getIntent();
        Question question = (Question) intent.getSerializableExtra("question");
    }

}
