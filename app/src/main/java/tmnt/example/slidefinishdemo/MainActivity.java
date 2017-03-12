package tmnt.example.slidefinishdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButtonNormal = (Button) findViewById(R.id.normal_activity);
        mButtonNormal.setOnClickListener(this);

        Button mButtonAbs = (Button) findViewById(R.id.absListview_activity);
        mButtonAbs.setOnClickListener(this);

        Button mButtonScroll = (Button) findViewById(R.id.scrollview_activity);
        mButtonScroll.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent mIntent = null;
        switch (v.getId()) {
            case R.id.normal_activity:
                mIntent = new Intent(MainActivity.this, SecondActivity.class);
                break;
            case R.id.absListview_activity:
                mIntent=new Intent(MainActivity.this,ThirdActivity.class);
        }
        startActivity(mIntent);
    }
}
