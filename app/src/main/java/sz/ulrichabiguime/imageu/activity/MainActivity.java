package sz.ulrichabiguime.imageu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import sz.ulrichabiguime.imageu.R;
import sz.ulrichabiguime.imageu.interfaces.VolleyRequestOnResultListener;
import sz.ulrichabiguime.imageu.utils.MaVolleyRequest;


public class MainActivity extends FragmentActivity {


    String authLink = "https://api.imgur.com/";
    String pinConfirmation = "https://api.imgur.com/oauth2/token";
    //    String test = "https://api.imgur.com/3/image/8ABRUYt";
//    String test = "https://api.imgur.com/3/gallery/random/random/1";
//    https://api.imgur.com/oauth2  http://api.imgur.com/#overview
    String test = "https://api.imgur.com/3/gallery/t/peace/1";
    /*
    parameters will be.
    client_id	required	Your Application ID
    client_secret	required	The client_secret for the application
    grant_type	required	Must be: pin
    pin	required	The users pin that they entered into your application
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // send a VolleyGetRequest that send back a page.
        // look for input_id=pin inside the page. get it's value
        // and send it back to

        MaVolleyRequest.getInstance(getApplicationContext()).GetMethodRequest (test, null, new VolleyRequestOnResultListener() {

            @Override
            public void onSucces(String result) {
                Log.d("VVVV ", result);
                // I get the json file here...
                //
            }

            @Override
            public void onFailure(String error) {
            }
        });

        /*
        * authorization header is a request to be sent with post
        * POST /test.php HTTP/1.1
           Accept: application/json
User-Agent: Apache-HttpClient/4.1 (java 1.5)
Host: myhost.com
Authorization: Basic YnxpcYRlc3RwMTulHGhlSGs=
        *
        * */
    }


    public String getAllXML(String xmlc){

        String str = "";

        //For file source
        //Resources res = activity.getResources();
        //XmlResourceParser xpp = res.getXml(R.xml.test);


        try {
            //For String source
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlc));

            xpp.next();
            int eventType = xpp.getEventType();

            while (xpp.getEventType()!=XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType()==XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("input")) {
//                           <input class="left" id="pin" name="pin" type="text" readonly="readonly" value="8822a5dd8e" />
                        str += "\ninput : "+xpp.getFeature("value");
                    }
                }
                xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void seePic(View view) {

        Intent intent = new Intent(this, ShowPicActivity.class);
        startActivity(intent);
    }
}
