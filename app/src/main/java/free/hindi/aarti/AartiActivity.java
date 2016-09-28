package free.hindi.aarti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class AartiActivity extends ActionBarActivity {

    TextView aarti;
    ImageView bgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aarti);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_aarti);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int findGod = getIntent().getIntExtra("godAarti", 0);

        aarti = (TextView) findViewById(R.id.aarti);
        bgImage = (ImageView) findViewById(R.id.background_image);

        switch (findGod){
            case 0:
                SetUpAll(R.string.ganpatiji, R.string.ganeshji_title, R.drawable.ganeshji);
                break;
            case 1:
                SetUpAll(R.string.ambeji, R.string.ambeji_title, R.drawable.ambeji);
                break;
            case 2:
                SetUpAll(R.string.ramayanji, R.string.ramayanji_title, R.drawable.ramayanji);
                break;
            case 3:
                SetUpAll(R.string.hanumanji, R.string.hanumanji_title, R.drawable.hanumanji);
                break;
            case 4:
                SetUpAll(R.string.laxmiji, R.string.laxmiji_title, R.drawable.laxmiji);
                break;
            case 5:
                SetUpAll(R.string.kunjji, R.string.kunjji_title, R.drawable.kunjji);
                break;
            case 6:
                SetUpAll(R.string.shivji, R.string.shivji_title, R.drawable.shivaji);
                aarti.setText(getResources().getString(R.string.shivji));
                getSupportActionBar().setTitle(getResources().getString(R.string.shivji_title));
                bgImage.setImageResource(R.drawable.shivaji);
                break;
            default:
                break;
        }
    }

    private void SetUpAll(int aartiText, int titleText, int mImage) {
        aarti.setText(getResources().getString(aartiText));
        getSupportActionBar().setTitle(getResources().getString(titleText));
        bgImage.setImageResource(mImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_aarti, menu);
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
            case R.id.action_share:
                sharePost();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sharePost() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, aarti.getText());
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_title)));

    }
}
