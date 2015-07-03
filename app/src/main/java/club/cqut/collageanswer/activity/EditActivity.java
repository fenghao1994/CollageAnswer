package club.cqut.collageanswer.activity;

import android.app.Activity;
import android.content.Intent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import club.cqut.collageanswer.R;

/**
 * Created by Howe on 2015/7/2.
 */
@EActivity(R.layout.activity_myinfo_edit)
public class EditActivity extends Activity{

    @AfterViews
    protected void init(){
        Intent intent = new Intent(this,AttestActivity_.class);
        startActivity(intent);
    }
}
