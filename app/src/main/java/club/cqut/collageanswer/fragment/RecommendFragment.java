package club.cqut.collageanswer.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.Header;
import org.codehaus.jackson.type.TypeReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.activity.AllAnswerActivity_;
import club.cqut.collageanswer.adapter.MyFragmentPagerAdapter;
import club.cqut.collageanswer.adapter.QuestionItemAdapter;
import club.cqut.collageanswer.model.Question;
import club.cqut.collageanswer.preferences.UserInfo_;
import club.cqut.collageanswer.util.http.BaseJsonHttpResponseHandler;
import club.cqut.collageanswer.util.http.HttpClient;
import club.cqut.collageanswer.util.http.HttpUrl;
import club.cqut.collageanswer.util.http.JacksonMapper;

/**
 * Created by Howe on 2015/7/3.
 */
@EFragment(R.layout.fragment_recommend)
public class RecommendFragment extends Fragment {

    @ViewById
    protected ViewPager question_viewpager;

    @ViewById
    protected PagerSlidingTabStrip tabs;

    public ArrayList<Fragment> list;

    @AfterViews
    protected void init(){

        FellowQuestionFragment_ fellowQuestionFragment = new FellowQuestionFragment_();
        MajorQuestionFragment_ majorQuestionFragment = new MajorQuestionFragment_();
        list = new ArrayList<>();
        list.add(fellowQuestionFragment);
        list.add(majorQuestionFragment);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getActivity(), getChildFragmentManager(), tabs, question_viewpager);
        adapter.setOffscreenPageLimit(2);
        adapter.addTab("专业推荐", fellowQuestionFragment);
        adapter.addTab("老乡推荐", majorQuestionFragment);

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
