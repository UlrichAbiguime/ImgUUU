package sz.ultima.imageu.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Ultima on 8/2/2015.
 */
public class ImageCenter {

    public static LruCache<String, Bitmap> lruCache;

    public static LruCache<String, Bitmap> getLruCache () {

        if (lruCache == null) {
//            lruCache = new LruCache<String, Bitmap>(20) ;
        }
        return lruCache;
    }

}
