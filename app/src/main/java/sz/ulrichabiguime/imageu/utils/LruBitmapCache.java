package sz.ulrichabiguime.imageu.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */




// this is the class that helps me to not get a OOM OutOfMemoryException
public class LruBitmapCache extends LruCache<String, Bitmap>
        implements ImageLoader.ImageCache {

    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }

    public LruBitmapCache(Context ctx) {

        this(getCacheSize(ctx));
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            return value.getAllocationByteCount();
        }
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1){
            return value.getByteCount();
        }
        return value.getRowBytes()*value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

    // Returns a cache size equal to approximately three screens worth of images.
    public static int getCacheSize(Context ctx) {
        final DisplayMetrics displayMetrics = ctx.getResources().
                getDisplayMetrics();
        final int screenWidth = displayMetrics.widthPixels;
        final int screenHeight = displayMetrics.heightPixels;
        // 4 bytes per pixel
        final int screenBytes = screenWidth * screenHeight * 4;

        return screenBytes * 3;
    }
}
