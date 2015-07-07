package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.Header;
import org.w3c.dom.Text;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;

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

    @Pref
    protected UserInfo_ userInfo;

    TextView textView;


    @AfterViews
    protected void init(){
        view_head.setTitle("描述你的问题");
        textView = view_head.getRightText();
        view_head.showRightText();
        textView.setText("下一步");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCommit();
            }
        });
    }


    /**
     * 下一步
     */
    protected void clickCommit(){
        boolean flag = true;

        if(userInfo.id().get() == -1){
            Toast.makeText(getApplication(), "提问题前,请先登录！", Toast.LENGTH_LONG).show();
            Intent t = new Intent(this, LoginActivity_.class);
            startActivity(t);
            return;
        }

        if(question_title.getText().length() < 10){
            Toast.makeText(getApplication(), "问题标题不能少于10个字", Toast.LENGTH_LONG).show();
            flag = false;
        }
        if(question_content.getText().length() < 20){
            Toast.makeText(getApplication(), "问题内容不能少于20个字", Toast.LENGTH_LONG).show();
            flag = false;
        }

        if( flag){
            Intent intent = new Intent(this, QuestionCommit_.class);
            intent.putExtra("title", question_title.getText().toString());
            intent.putExtra("content", question_content.getText().toString());
            startActivity(intent);
        }
    }



}
