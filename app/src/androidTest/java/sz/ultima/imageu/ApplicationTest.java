package sz.ultima.imageu;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import sz.ultima.imageu.interfaces.VolleyRequestOnResultListener;
import sz.ultima.imageu.utils.MaVolleyRequest;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test_String () {
        String test = "https://api.imgur.com/3/gallery/t/peace/1";

        MaVolleyRequest.getInstance(getContext()).GetMethodRequest (test, null, new VolleyRequestOnResultListener() {
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
    }

}