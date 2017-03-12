package tmnt.example.slidefinishdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmnt on 2017/3/12.
 */

public class ThirdActivity extends Activity {

    private ListView mListView;
    private List<String> mStringList;
    private SlideFinishLayout mSlideFinishLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_lay);

        mListView = (ListView) findViewById(R.id.list);
        mSlideFinishLayout = (SlideFinishLayout) findViewById(R.id.lay);

        mStringList = new ArrayList<>();
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");
        mStringList.add("tom");

        ListViewAdapter adapter = new ListViewAdapter(this, mStringList);
        mListView.setAdapter(adapter);

        mSlideFinishLayout.setOnSlideFinishListener(new SlideFinishLayout.OnSlideFinishListener() {
            @Override
            public void slideFinish() {
                ThirdActivity.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_out_right);
    }
}
