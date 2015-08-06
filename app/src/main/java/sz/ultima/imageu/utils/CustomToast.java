package sz.ultima.imageu.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */
public class CustomToast {
    public static void mT(Context mctx, String s) {
        Toast.makeText(mctx, s, Toast.LENGTH_SHORT).show();
    }
}
