package free.hindi.aarti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
        setContentView(R.layout.activity_main);
        StartAppAd.showSlider(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent;
        switch (id){
            case R.id.action_more_apps:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:AASA"));
                startActivity(intent);
                break;
            case R.id.action_rate_us:
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=free.hindi.aarti"));
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
