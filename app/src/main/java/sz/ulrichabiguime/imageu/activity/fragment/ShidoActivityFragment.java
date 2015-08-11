package sz.ulrichabiguime.imageu.activity.fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sz.ulrichabiguime.imageu.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class ShidoActivityFragment extends Fragment {

    public ShidoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shido, container, false);
    }
}
