package sz.ultima.imageu.interfaces;

import android.graphics.Bitmap;

/**
 * Created by Ultima on 8/2/2015.
 */
public interface VolleyRequestOnResultListener {

    public void onSucces (Bitmap bitmap);
    public void onFailure (String error);
}
