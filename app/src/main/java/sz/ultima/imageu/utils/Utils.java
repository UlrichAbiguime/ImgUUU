package sz.ultima.imageu.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */
public class Utils {
    public static List<String> ArrayToList(String[] d) {

        List<String> data = new ArrayList<>();
        for (int i = 0; i < d.length; i++) {
            data.add(d[i]);
        }
        return data;
    }
}
