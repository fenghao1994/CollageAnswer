package club.cqut.collageanswer.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.activity.AttestActivity_;
import club.cqut.collageanswer.activity.AttestEditActivity_;
import club.cqut.collageanswer.activity.EditActivity_;
import club.cqut.collageanswer.activity.HomeActivity_;
import club.cqut.collageanswer.activity.LoginActivity_;
import club.cqut.collageanswer.activity.MyAnswerActivity_;
import club.cqut.collageanswer.activity.MyFocusActivity_;
import club.cqut.collageanswer.activity.MyQuestionActivity_;
import club.cqut.collageanswer.customview.HeadBackView;
import club.cqut.collageanswer.preferences.UserInfo_;

/**
 * 我的信息fragment
 * Created by fenghao on 2015/6/29.
 */

@EFragment(R.layout.fragment_mine)
public class MineFragment extends Fragment {


    @ViewById
    protected LinearLayout mine_layout;

    @ViewById
    protected HeadBackView view_head;

    @Pref
    protected UserInfo_ userInfo;

    @AfterViews
    protected void init(){
        if( userInfo.id().get() == -1){
            mine_layout.setVisibility(View.GONE);
            Intent intent = new Intent(getActivity(), LoginActivity_.class);
            startActivity(intent);
        }/*else{
            mine_layout.setVisibility(View.VISIBLE);
        }*/
        Toast.makeText(getActivity(),"" + userInfo.id().get(), Toast.LENGTH_LONG).show();
    }


    @Click(R.id.go_attest)
    protected void goAttestEdit(){
        if (userInfo.realName().get() != null && !userInfo.realName().get().equals("")
                && userInfo.stuNumber().get() != null && !userInfo.stuNumber().equals("") ){
            Intent intent = new Intent(getActivity(), AttestActivity_.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getActivity(), AttestEditActivity_.class);
            startActivity(intent);
        }
    }

    @Click(R.id.my_question)
    protected void goMyQuestion(){
        Intent intent = new Intent(getActivity(), MyQuestionActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.my_answer)
    protected void goMyAnswer(){
        Intent intent = new Intent(getActivity(), MyAnswerActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.my_friends)
    protected void goMyFriends(){
        Intent intent = new Intent(getActivity(), MyFocusActivity_.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "start", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "destroy", Toast.LENGTH_LONG).show();
    }
}
