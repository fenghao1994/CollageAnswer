package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.Header;

import java.util.Map;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.RoungImage.CircleImageView;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.model.Answer;
import club.cqut.collageanswer.model.Question;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * 具体回答的页面
 * Created by fenghao on 2015/7/2.
 */
@EActivity(R.layout.activity_specific_answer)
public class SpecificAnswerActivity extends Activity{

    @ViewById
    protected HeadBackView backview;
    @ViewById
    protected TextView question_title, role_name, user_sign, prise_num, answer_content;

    @ViewById
    protected CircleImageView head_image;
    @ViewById
    protected ImageView prise;

    @Pref
    protected UserInfo_ userInfo;

    Answer answer;
    boolean message;

// TODO  访问网络 判断该用户是否可以进行点赞
    @AfterViews
    protected void init(){
        backview.setTitle("详细回答");
        Intent intent = getIntent();
        Question question = (Question) intent.getSerializableExtra("question");
        answer = (Answer) intent.getSerializableExtra("answer");
        question_title.setText(question.getTitle());
        role_name.setText(answer.getUserRole());
        if( answer.getUserSign() == null || answer.getUserSign().equals("")){
            user_sign.setText("该用户很懒，没有写个性签名！");
        }else{
            user_sign.setText(answer.getUserSign());
        }
        prise_num.setText(answer.getPriseNum() + "");
        answer_content.setText(answer.getContext());
        showHeadImage(answer.getHeadImage());
        sentPrise();
    }
    @UiThread
    public void showHeadImage(String url){
        // DisplayImageOptions的初始化
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.bootstrap_1)
                .showImageOnLoading(R.mipmap.bootstrap_2)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(url, head_image, options);
    }

    @Click(R.id.prise)
    protected void clickPrise(){
        isAddPriseNum();
    }
    /**
     * 点赞后发送到服务器
     */

    public void sentPrise(){
        RequestParams params = new RequestParams();
        params.put("user_id", userInfo.id().get());
        params.put("answer_id", answer.getId());
        HttpClient.post(this, HttpUrl.POST_PRISE, params, new BaseJsonHttpResponseHandler(this){
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Map<String, Object> map = JacksonMapper.parse(responseString);
                if(map.containsValue("success")){
                    prise.setImageResource(R.mipmap.raiseprise);
                    message = true;
                }else{
                    message = false;
                    prise.setImageResource(R.mipmap.pressprise);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Map<String, Object> map = JacksonMapper.parse(responseString);
                message = false;
            }
        });
    }

    /**
     * 是否进行
     */
    public void isAddPriseNum( ){
        if ( message){
            prise.setEnabled(true);
            prise.setImageResource(R.mipmap.pressprise);
            prise_num.setText( (answer.getPriseNum() + 1) + "" );
        }else {
            prise.setEnabled(false);
        }
    }
}
