package sz.ultima.imageu.on3;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import sz.ultima.imageu.utils.LruBitmapCache;

/**
 * Created by 1 on 2015/8/5.
 */
public class MyBoruto {

    // items needed
    private static MyBoruto instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private Context mCtx;

    public ImageLoader getImageLoader() {
        return imageLoader;
        // maybe each activity should have his own image loader.
        // it's own cache that will be then cut off each time the activity
        // is dead.

        // or on another foot, we only have one activity with so many pictures...
        // let's make an unillimited cache for it.

        // cache that leaves only when the activity is leaving, then
        // we just will copy the file to  localdisk just after...

        // finally,
        /*
        * i'll have to types of caches.
        * 1. for the first images that we see back there.
        * 2. for the big images, because, the user don't obviously want to see
        *   // the content of the after seeing the thumbnail.
        * 3. and beside the thumbnail is even easier to load in the layout...
        *   /// but i cannot download the whole picture, and only keep the thumbnail...
        *       come on~~~
        *
        * 4. Ill need a pull to refresh grid_view...
        *   As i still don't know how to manage it now, i'll look for a cool api.
        * */
    }

    public MyBoruto(Context ctx) {

        //
        mCtx = ctx;
        requestQueue = getRequestQueue();
        // do all the stuff about the memory

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;


        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

            //            private final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(cacheSize) ;
            private final LruBitmapCache lruCache = new LruBitmapCache(mCtx);

            @Override
            public Bitmap getBitmap(String url) {
                return lruCache.get(url);
                // it is actually to long to fetch the data from the disk in here. // so we could just open a thread
                // to manage it.
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url, bitmap);
                // we open a thread that puts it into the diskcache.
            }
        });
//        imageLoader = new LruBitmapCache(mCtx);
//        LruBitmapCache.getCacheSize(mCtx)
    }


    public synchronized static MyBoruto getInstance (Context ctx) {
        if (instance == null) {
            instance = new MyBoruto(ctx);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx);
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void cancelAllRequest(String tag) {
        requestQueue.cancelAll(tag);
    }
}
