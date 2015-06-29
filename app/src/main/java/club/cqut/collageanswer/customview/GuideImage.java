package club.cqut.collageanswer.customview;

//**

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;

/* 引导页需要用的图片容器
* Created by Overlord on 2015/5/30.
* SingularityClub
*/
@EViewGroup(R.layout.layout_guide_image)
public class GuideImage extends LinearLayout {

    @ViewById
    protected ImageView imageView;

    public GuideImage(Context context) {
        super(context);
    }

    public void setImageRes(int imageResId){
//        imageView.setImageResource(imageResId);
        imageView.setBackgroundResource(imageResId);
    }
}
