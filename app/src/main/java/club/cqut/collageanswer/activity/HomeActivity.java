package club.cqut.collageanswer.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.fragment.MineFragment_;
import club.cqut.collageanswer.fragment.QuestionFragment;
import club.cqut.collageanswer.fragment.QuestionFragment_;
import club.cqut.collageanswer.fragment.RankFragment_;
import club.cqut.collageanswer.fragment.RecommendFragment;
import club.cqut.collageanswer.fragment.RecommendFragment_;

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
    protected TextView activity_title;
    @ViewById
    protected LinearLayout layout_title;

    @AfterViews
    public void init(){

        layout_question.setOnClickListener(this);
        layout_recommend.setOnClickListener(this);
        layout_rank.setOnClickListener(this);
        layout_mine.setOnClickListener(this);
        layout_search.setOnClickListener(this);
        activity_title.setOnClickListener(this);


        chioceFragment(0);
    }



    public void chioceFragment(int index){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        reset();
        hineFragment(fragmentTransaction);

        switch(index ){
            case 0:
                if(questionFragment == null){
                    questionFragment = new QuestionFragment_();
                    fragmentTransaction.add(R.id.content_fragment, questionFragment);
                }else{
                    fragmentTransaction.show( questionFragment);
                }
                break;
            case 1:
                if(recommendFragment == null){
                    recommendFragment = new RecommendFragment_();
                    fragmentTransaction.add( R.id.content_fragment, recommendFragment);
                }else{
                    fragmentTransaction.show( recommendFragment);
                }
                break;
            case 2:
                if(rankFragment == null){
                    rankFragment = new RankFragment_();
                    fragmentTransaction.add( R.id.content_fragment, rankFragment);
                }else{
                    fragmentTransaction.show( rankFragment);
                }
                break;
            case 3:
                if(mineFragment == null){
                    mineFragment = new MineFragment_();
                    fragmentTransaction.add( R.id.content_fragment, mineFragment);
                }else{
                    fragmentTransaction.show( mineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * 重置选项
     * 将所有的都置为默认
     */
    public void reset(){

    }

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
                break;
            case R.id.layout_recommend:
                chioceFragment(1);
                layout_search.setVisibility(View.GONE);
                layout_title.setVisibility(View.VISIBLE);
                activity_title.setText("推荐");
                break;
            case R.id.layout_rank:
                chioceFragment(2);
                layout_search.setVisibility(View.GONE);
                layout_title.setVisibility(View.VISIBLE);
                activity_title.setText("排行榜");
                break;
            case R.id.layout_mine:
                chioceFragment(3);
                layout_search.setVisibility(View.GONE);
                layout_title.setVisibility(View.VISIBLE);
                activity_title.setText("我的信息");
                break;
            case R.id.layout_search:
                Toast.makeText(getApplicationContext(), "123", Toast.LENGTH_LONG).show();
                break;
            default:break;
        }
    }
}
