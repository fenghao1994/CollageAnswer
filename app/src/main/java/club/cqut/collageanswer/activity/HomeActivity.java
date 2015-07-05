package club.cqut.collageanswer.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import club.cqut.collageanswer.fragment.MineFragment_;
import club.cqut.collageanswer.fragment.QuestionFragment;
import club.cqut.collageanswer.fragment.QuestionFragment_;
import club.cqut.collageanswer.fragment.RankFragment_;
import club.cqut.collageanswer.fragment.RecommendFragment;
import club.cqut.collageanswer.fragment.RecommendFragment_;
import club.cqut.collageanswer.model.Notice;
import club.cqut.collageanswer.model.User;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

@EActivity(R.layout.activity_home)
public class HomeActivity extends FragmentActivity implements View.OnClickListener{
    private QuestionFragment_ questionFragment;
    private RecommendFragment_ recommendFragment;
    private RankFragment_ rankFragment;
    private MineFragment_ mineFragment;
    @ViewById
    protected LinearLayout layout_question;
    @ViewById
    protected LinearLayout layout_recommend;
    @ViewById
    protected LinearLayout layout_rank;
    @ViewById
    protected LinearLayout layout_mine;
    @ViewById
    protected LinearLayout layout_search;
    @ViewById
    protected TextView activity_title, text_question, text_recommend, text_rank, text_mine;
    @ViewById
    protected LinearLayout layout_title;
    @ViewById
    protected LinearLayout add_question;
    @ViewById
    protected TextView edit, attest;

    @Pref
    protected UserInfo_ userInfo;

    @ViewById
    protected ImageView img_question, img_recommend, img_add_question, img_rank, img_mine;

    @AfterViews
    public void init(){
        layout_question.setOnClickListener(this);
        layout_recommend.setOnClickListener(this);
        layout_rank.setOnClickListener(this);
        layout_mine.setOnClickListener(this);
        layout_search.setOnClickListener(this);
        activity_title.setOnClickListener(this);
        add_question.setOnClickListener(this);
        chioceFragment(0);

        /**
         * 编辑信息后返回到mineFragment
         */
        Intent intent = getIntent();
        int fragmentId = intent.getIntExtra("mine",-1);
        if(fragmentId > 0){
            if(fragmentId == 4){
                chioceFragment(3);
                layout_search.setVisibility(View.GONE);
                layout_title.setVisibility(View.VISIBLE);
                edit.setVisibility(View.VISIBLE);
                attest.setVisibility(View.VISIBLE);
                activity_title.setText("我的信息");
            }
        }

    }

    /**
     * 初始化底部的布局
     */
    public void initImageAndText(){
        img_question.setImageResource(R.mipmap.a);
        img_recommend.setImageResource(R.mipmap.b);
        img_add_question.setImageResource(R.mipmap.c_1);
        img_rank.setImageResource(R.mipmap.d);
        img_mine.setImageResource(R.mipmap.f);
        text_question.setTextColor(getResources().getColor(R.color.bottom_black));
        text_recommend.setTextColor(getResources().getColor(R.color.bottom_black));
        text_rank.setTextColor(getResources().getColor(R.color.bottom_black));
        text_mine.setTextColor(getResources().getColor(R.color.bottom_black));

    }

    public void chioceFragment(int index){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hineFragment(fragmentTransaction);
        initImageAndText();
        switch(index ){
            case 0:
                if(questionFragment == null){
                    questionFragment = new QuestionFragment_();
                    fragmentTransaction.add(R.id.content_fragment, questionFragment);
                }else{
                    fragmentTransaction.show( questionFragment);
                }
                img_question.setImageResource(R.mipmap.a_1);
                text_question.setTextColor(getResources().getColor(R.color.bottom_blue));
                break;
            case 1:
                if(recommendFragment == null){
                    recommendFragment = new RecommendFragment_();
                    fragmentTransaction.add( R.id.content_fragment, recommendFragment);
                }else{
                    fragmentTransaction.show( recommendFragment);
                }
                img_recommend.setImageResource(R.mipmap.b_1);
                text_recommend.setTextColor(getResources().getColor(R.color.bottom_blue));
                break;
            case 2:
                if(rankFragment == null){
                    rankFragment = new RankFragment_();
                    fragmentTransaction.add( R.id.content_fragment, rankFragment);
                }else{
                    fragmentTransaction.show( rankFragment);
                }
                img_rank.setImageResource(R.mipmap.d_1);
                text_rank.setTextColor(getResources().getColor(R.color.bottom_blue));
                break;
            case 3:
                if(mineFragment == null){
                    mineFragment = new MineFragment_();
                    fragmentTransaction.add( R.id.content_fragment, mineFragment);
                }else{
                    fragmentTransaction.show( mineFragment);
                }
                img_mine.setImageResource(R.mipmap.f_1);
                text_mine.setTextColor(getResources().getColor(R.color.bottom_blue));
                break;
        }
        fragmentTransaction.commit();
    }


    /**
     * 隐藏fragment
     * @param fragmentTransaction
     */
    public void hineFragment(FragmentTransaction fragmentTransaction){
        if(questionFragment != null){
            fragmentTransaction.hide(questionFragment);
        }
        if(recommendFragment != null){
            fragmentTransaction.hide(recommendFragment);
        }
        if(rankFragment != null){
            fragmentTransaction.hide(rankFragment);
        }
        if(mineFragment != null){
            fragmentTransaction.hide(mineFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch( v.getId()){
            case R.id.layout_question:
                chioceFragment(0);
                layout_search.setVisibility(View.VISIBLE);
                layout_title.setVisibility(View.GONE);
                edit.setVisibility(View.GONE);
                attest.setVisibility(View.GONE);
                break;
            case R.id.layout_recommend:
                chioceFragment(1);
                layout_search.setVisibility(View.GONE);
                layout_title.setVisibility(View.VISIBLE);
                edit.setVisibility(View.GONE);
                attest.setVisibility(View.GONE);
                activity_title.setText("推荐");
                break;
            case R.id.add_question:
                Intent intent = new Intent(this, AddQuestionActivity_.class);
                startActivity(intent);
                break;
            case R.id.layout_rank:
                chioceFragment(2);
                layout_search.setVisibility(View.GONE);
                layout_title.setVisibility(View.VISIBLE);
                edit.setVisibility(View.GONE);
                attest.setVisibility(View.GONE);
                activity_title.setText("排行榜");
                break;
            case R.id.layout_mine:
                if(userInfo.id().get() == -1){
                    Intent t = new Intent(this, LoginActivity_.class);
                    startActivity(t);
                    return;
                }
                chioceFragment(3);
                layout_search.setVisibility(View.GONE);
                layout_title.setVisibility(View.VISIBLE);
                edit.setVisibility(View.VISIBLE);
                attest.setVisibility(View.VISIBLE);
                activity_title.setText("我的信息");
                break;
            case R.id.layout_search:
                Intent t = new Intent();
                t.setClass(this, SearchQuestionActivity_.class);
                startActivity(t);
                break;
            default:break;
        }
    }


    /**
     * 跳转到个人信息编辑
     */
    @Click(R.id.edit)
    protected void goEdit(){
        Intent intent = new Intent(this, EditActivity_.class);
        startActivity(intent);
    }


    /**
     * 跳转到认证页面
     */
    @Click(R.id.attest)
    protected void goAttest(){
        /*if (userInfo.realName().get() != null && !userInfo.realName().get().equals("")
                && userInfo.stuNumber().get() != null && !userInfo.stuNumber().equals("") ){
            Intent intent = new Intent(this, AttestActivity_.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, AttestEditActivity_.class);
            startActivity(intent);
        }*/
        Intent intent = new Intent(this, NoticeActivity_.class);
        startActivity(intent);
    }


    /**
     * 退出程序判断
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder( this);
            builder.setTitle("系统提示:");
            builder.setMessage("确定要退出吗？");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
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
        return false;
    }



}
