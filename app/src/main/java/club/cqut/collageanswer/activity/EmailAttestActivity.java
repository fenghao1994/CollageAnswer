package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;

/**
 * Created by Howe on 2015/7/2.
 */
@EActivity(R.layout.activity_email_attest)
public class EmailAttestActivity extends Activity{

    @ViewById
    protected HeadBackView view_head;

    @ViewById
    protected EditText school_email;

    @Pref
    protected UserInfo_ userInfo;

    protected RequestParams params = null;
    private TextView textView;

    @AfterViews
    public void init(){
        view_head.setTitle("校园邮箱认证");
        textView = view_head.getRightText();
        view_head.showRightText();
        textView.setText("提交");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });
    }

    /**
     * 验证校园邮箱正确性
     */
    public void checkInput(){
        String email = school_email.getText().toString();

        //分割邮箱
        String[] format = email.split("\\.");
        String[] company = format[0].split("@");

        if(company[1].equals("cqut")){
            if(format[1].equals("edu") && format[2].equals("cn")){
                alertDialog();
            }
        } else if(format[1].equals("cqut") && format[2].equals("edu") && format[3].equals("cn")){
            alertDialog();
        } else {
            school_email.setError("邮箱错误");
        }
    }

    /**
     * 弹窗确认
     */
    public void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(EmailAttestActivity.this);
        builder.setTitle("请确认");
        builder.setMessage("邮箱提交后不可修改");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                postEmail();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * 提交邮箱
     */
    public void postEmail(){
        params = new RequestParams();
        params.put("user_id", userInfo.id().get());
        params.put("school_email", school_email.getText().toString());

        HttpClient.get(this, HttpUrl.GET_EMAIL_APPROVE, params, new BaseJsonHttpResponseHandler(this) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getApplication(), "邮箱已提交", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplication(), HomeActivity_.class);
                startActivity(intent.putExtra("mine",4));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplication(), statusCode + "---" + responseString, Toast.LENGTH_LONG).show();
            }
        });
    }
}
