package sz.ulrichabiguime.imageu.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

import sz.ulrichabiguime.imageu.interfaces.VolleyRequestOnResultListener;
import sz.ulrichabiguime.imageu.on3.MyBoruto;

/**
 * Created by Ultima on 8/2/2015.
 */
public class MaVolleyRequest {

/*    public static void postRequestBitmap (Context mContext, String link, VolleyRequestOnResultListener listener) {

        // request the image...

        //1- images when retrieved have to be put inside the LruCache.
    }*/

    private static MaVolleyRequest instance;
    private static MyBoruto borutoInstance;

    public static MaVolleyRequest getInstance(Context ctx) {
        if (instance == null) {
            instance = new MaVolleyRequest();
            borutoInstance = MyBoruto.getInstance(ctx);
        }
        return instance;
    }

    public void GetMethodRequest (String ulr, String params, final VolleyRequestOnResultListener listener) {

        // create a method.
        CookieStringtRequest req = new CookieStringtRequest(Request.Method.GET, ulr, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                listener.onSucces(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onFailure(volleyError.toString());
            }
        });
        req.setCookie();
        borutoInstance.addToRequestQueue(req);
    }

    public void PostMethodRequest (String ulr, final Map<String, String> params, final VolleyRequestOnResultListener listener) {

        // for the params, ill be adding them
        // create a method.
        CookieStringtRequest req = new CookieStringtRequest(Request.Method.POST, ulr, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                listener.onSucces(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onFailure(volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        req.setCookie();
        borutoInstance.addToRequestQueue(req);
    }

}
