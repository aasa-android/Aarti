package free.hindi.aarti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;


public class MainActivity extends ActionBarActivity {

    String[] mAarti = {"गणेश जी की आरती","अम्बे जी की आरती","रामायण जी की आरती", "हनुमान जी की आरती", "लक्ष्मी जी की आरती",
            "कुंज बिहारी जी की आरती", "शिवजी की आरती"};
    int[] mGodImages = {R.drawable.ganeshji, R.drawable.ambeji, R.drawable.ramayanji, R.drawable.hanumanji,
            R.drawable.laxmiji, R.drawable.kunjji, R.drawable.shivaji};

    private StartAppAd startAppAd = new StartAppAd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "102735663", "203546808", true);
        StartAppAd.showSplash(this, savedInstanceState);
        setContentView(R.layout.activity_main);
        StartAppAd.showSlider(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        GridView mAartiList = (GridView) findViewById(R.id.aarti_list);

        AartiAdapter adapter = new AartiAdapter(MainActivity.this, mGodImages, mAarti);
        mAartiList.setAdapter(adapter);

        mAartiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, AartiActivity.class);
                intent.putExtra("godAarti", position);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }
}
