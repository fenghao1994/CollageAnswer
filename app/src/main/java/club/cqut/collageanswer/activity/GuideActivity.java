package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;
import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.customview.GuideImage;
import club.cqut.collageanswer.customview.GuideImage_;
import club.cqut.collageanswer.preferences.UserInfo_;

/**
 * Created by fenghao on 2015/6/27.
 */
@EActivity(R.layout.activity_guide)
public class GuideActivity extends Activity {
    @ViewById
    protected ViewPager viewPager;
    @Pref
    protected UserInfo_ userInfo;
    private List<View> views;

    @AfterViews
    protected void init(){
        userInfo.edit().guide().put(true).apply();

        views = new ArrayList<>(2);
        GuideImage imageView = GuideImage_.build(this);
        imageView.setImageRes(R.mipmap.bootstrap_1);
        views.add(imageView);

        imageView = GuideImage_.build(this);
        imageView.setImageRes(R.mipmap.bootstrap_2);
        views.add(imageView);

        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views.get(position));
                return views.get(position);
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == views.size() - 1){
                    //3秒后进入
                    finishActivity();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Background
    protected void finishActivity(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.finish();
    }
}
