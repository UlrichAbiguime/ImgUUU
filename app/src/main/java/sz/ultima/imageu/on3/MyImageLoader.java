package sz.ultima.imageu.on3;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */
public class MyImageLoader extends ImageLoader {

    public MyImageLoader(RequestQueue queue, ImageCache imageCache) {
        super(queue, imageCache);
    }

    @Override
    public ImageContainer get(String requestUrl, ImageListener imageListener, int maxWidth, int maxHeight) {
        return super.get(requestUrl, imageListener, maxWidth, maxHeight);
    }
}
