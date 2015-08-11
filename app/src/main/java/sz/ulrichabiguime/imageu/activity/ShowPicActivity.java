package sz.ulrichabiguime.imageu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import sz.ulrichabiguime.imageu.R;
import sz.ulrichabiguime.imageu.on3.MyBoruto;


public class ShowPicActivity extends Activity {


    private static final String TAG = "ShowPicActivity";
    ImageView iv_pic;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pic);
        initViews();
        // volley request to fetch the activity...
        // showing a progressbar from the start.
        String url ="http://www.mpages.co.nz/MpageMeadia/News/uegaXM8NVUezhV_s72dwCA%E6%98%A5%E6%99%9Ab.jpeg";

        // no need to upload such a big picture... ill just upload something shorter
    /*    ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                progressBar.setVisibility(View.GONE);
                iv_pic.setImageBitmap(bitmap);
                iv_pic.setVisibility(View.VISIBLE);
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                makeToast (volleyError.toString());
                finish();
            }
        });
        imageRequest.setTag(TAG);*/
//        MyBoruto.getInstance(getApplicationContext()).addToRequestQueue(imageRequest);
    MyBoruto.getInstance(getApplicationContext()).getImageLoader();
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_pic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyBoruto.getInstance(getApplicationContext()).cancelAllRequest (TAG);
    }

    // load pic function... that takes a link, and shows the pic of it.
}
