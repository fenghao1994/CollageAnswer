package club.cqut.collageanswer.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import java.util.ArrayList;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.adapter.MyFragmentPagerAdapter;

/**
 * 问题fragment
 * Created by fenghao on 2015/6/28.
 */
@EFragment(R.layout.fragment_question)
public class QuestionFragment extends Fragment{

    @ViewById
    protected ViewPager question_viewpager;

    @ViewById
    protected PagerSlidingTabStrip tabs;

    public ArrayList<Fragment> list;

    @AfterViews
    protected void init(){

        BestNewQuestionFragment_ bestNewQuestionFragment = new BestNewQuestionFragment_();
        BestHotQuestionFragment_ bestHotQuestionFragment = new BestHotQuestionFragment_();
        list = new ArrayList<>();
        list.add(bestNewQuestionFragment);
        list.add(bestHotQuestionFragment);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getActivity(), tabs, question_viewpager);
        adapter.setOffscreenPageLimit(1);
        adapter.addTab("最新问题", bestNewQuestionFragment);
        adapter.addTab("最热问题", bestHotQuestionFragment);

        adapter.build();

        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
