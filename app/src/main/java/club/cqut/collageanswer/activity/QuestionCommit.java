package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.Header;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;

/**
 * 提交问题
 * Created by fenghao on 2015/7/4.
 */
@EActivity(R.layout.activity_question_commit)
public class QuestionCommit extends Activity {

    @ViewById
    protected HeadBackView backview;
    @ViewById
    protected CheckBox label_1, label_2, label_3, label_4, label_5, label_6;
    @Pref
    protected UserInfo_ userInfo;

    public TextView commit;
    public String label = "";
    String questionTitle;
    String questionContent;

    @AfterViews
    protected void init(){
        Intent intent = getIntent();
        questionTitle = intent.getStringExtra("title");
        questionContent = intent.getStringExtra("content");
        backview.setTitle("为你的问题添加标题");
        backview.showRightText();
        commit = backview.getRightText();
        commit.setText("提交");
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitDialot();
            }
        });
    }


    /**
     * 多选框是否被选择
     */
    public void isCheckBoxChecked(){
        if( label_1.isChecked()){
            label += label_1.getText().toString() + ",";
        }
        if( label_2.isChecked()){
            label += label_2.getText().toString() + ",";
        }
        if( label_3.isChecked()){
            label += label_3.getText().toString() + ",";
        }
        if( label_4.isChecked()){
            label += label_4.getText().toString() + ",";
        }
        if( label_5.isChecked()){
            label += label_5.getText().toString() + ",";
        }
        if( label_6.isChecked()){
            label += label_6.getText().toString() + ",";
        }
        if( !label.equals("")){
            label = label.substring(0, label.length() - 1);
        }
    }

    public void commitQuestion(){

        RequestParams params = new RequestParams();
        params.put("user_id", userInfo.id().get());
        params.put("content", questionContent);
        params.put("title", questionTitle);
        params.put("label", label);

        HttpClient.post(this, HttpUrl.POST_ONE_QUESTION, params, new BaseJsonHttpResponseHandler(this) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getApplication(), "提交成功", Toast.LENGTH_LONG).show();
                backActivity();
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), "提交失败--" + statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }
    /**
     * 提交问题成功后回到
     */
    public void backActivity(){
        this.finish();
        startActivity( new Intent(this, HomeActivity_.class));
    }



    public void commitDialot(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认提交？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isCheckBoxChecked();
                commitQuestion();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
