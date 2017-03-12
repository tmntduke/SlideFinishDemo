package tmnt.example.slidefinishdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tmnt on 2017/3/12.
 */

public class SecondActivity extends Activity {

    private SlideFinishLayout mSlideFinishLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_lay);
        mSlideFinishLayout = (SlideFinishLayout) findViewById(R.id.lay);

        mSlideFinishLayout.setOnSlideFinishListener(new SlideFinishLayout.OnSlideFinishListener() {
            @Override
            public void slideFinish() {
                SecondActivity.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_out_right);
    }
}
