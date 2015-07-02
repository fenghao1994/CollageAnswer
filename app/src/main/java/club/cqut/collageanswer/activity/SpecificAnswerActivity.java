package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.RoungImage.CircleImageView;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.model.Answer;
import club.cqut.collageanswer.model.Question;

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

// TODO  访问网络 判断该用户是否可以进行点赞
    @AfterViews
    protected void init(){
        backview.setTitle("详细回答");
        Intent intent = getIntent();
        Question question = (Question) intent.getSerializableExtra("question");
        Answer answer = (Answer) intent.getSerializableExtra("answer");

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
}
