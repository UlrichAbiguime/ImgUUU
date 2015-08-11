package sz.ulrichabiguime.imageu.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */


public class CookieStringtRequest extends StringRequest {
    private Map<String, String> mHeaders = new HashMap<String, String>();
    private static String cookie = "";
    //
    private static Boolean isTruncate = true;// ��ȡ��һ�λ�õ�cookie

    public CookieStringtRequest(int method, String url, String jsonRequest,
                                Response.Listener<String> listener, Response.ErrorListener errorListener) {

        super(method, url, listener, errorListener);
    }

    public void setCookie() {
        mHeaders.put("accept", "*/*");
        mHeaders.put("connection", "Keep-Alive");
      if (!cookie.equals(""))
           mHeaders.put("Cookie", cookie);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        mHeaders.put("Authorization", "Client-ID 9e3e207ed22e19e");
        return mHeaders;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        // TODO Auto-generated method stub

        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            Pattern pattern = Pattern.compile("Set-Cookie.*?;");
            Matcher m = pattern.matcher(response.headers.toString());
            if (m.find()) {
                cookie = m.group();
                // L.e("LOG","cookie from server "+ cookie);
            }

            return Response.success(jsonString,
                    HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }
}