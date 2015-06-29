package club.cqut.collageanswer.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import club.cqut.collageanswer.R;


/**
 * Antivity头部，带back按钮
 */
@EViewGroup(R.layout.layout_base_head)
public class HeadBackView extends RelativeLayout {

    @ViewById
    protected TextView textView_title;

    @ViewById(R.id.imageView_left)
    protected ImageView leftButton;
    @ViewById(R.id.imageView_right)
    protected ImageView rightButton;

    private Context context = null;
    /**
     * 右侧按钮显示控制
     */
    private boolean rightButtonShowFlag = false;


    public HeadBackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @AfterViews
    protected void init(){
        rightButton.setVisibility(View.GONE);
        leftButton.setImageResource(R.mipmap.arrow_left);
    }

    public ImageView getRightButton() {
        return rightButton;
    }

    /**
     * 设置右侧按钮的图标
     * @param resId
     */
    public void setImageViewRightIconRes(int resId){
        rightButton.setImageResource(resId);
    }

    /**
     * 显示右侧按钮
     */
    public void showRightButton(){
        rightButton.setVisibility(View.VISIBLE);
        rightButtonShowFlag = true;
    }

    /**
     * 隐藏左侧按钮
     */
    public void hideRightButton(){
        rightButton.setVisibility(View.GONE);
        rightButtonShowFlag = false;
    }

    /**
     * 切换右侧按钮的显示
     */
    public void switchRightButton(){
        if(rightButtonShowFlag){
            hideRightButton();
        }else{
            showRightButton();
        }
    }

    public void setTitle(CharSequence title){
        textView_title.setText(title);
    }

    public void setTitle(int stringResId){
        textView_title.setText(stringResId);
    }

    @Click(R.id.view_left_zone)
    protected void backClick(){
        ((Activity)context).finish();
    }
}
