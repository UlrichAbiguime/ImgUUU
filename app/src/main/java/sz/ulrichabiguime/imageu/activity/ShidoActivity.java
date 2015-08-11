package sz.ulrichabiguime.imageu.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import net.simonvt.menudrawer.MenuDrawer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sz.ulrichabiguime.imageu.R;
import sz.ulrichabiguime.imageu.adapters.DrawerListAdapter;
import sz.ulrichabiguime.imageu.utils.CustomToast;
import sz.ulrichabiguime.imageu.utils.Utils;
import sz.ulrichabiguime.imageu.viewz.CircularImageView;
import sz.ulrichabiguime.imageu.viewz.OverListView;

public class ShidoActivity extends FragmentActivity {

    // views
    @Bind(R.id.content_frame)   FrameLayout frameLayout;
    @Bind(R.id.title_back)   ImageView iv_back;

    // variables
    private String[] mPlanetTitles;
    private DrawerListAdapter adap;
    private MenuDrawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shido);
        ButterKnife.bind(this);
        initViews();
        setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
        setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
        initAdapters();
        setAdapters();
//getSupportActionBar().hide();;
        mDrawer = MenuDrawer.attach(this);

        mDrawer.setContentView(R.layout.activity_shido);
        mDrawer.setMenuView(R.layout.activity_menu_view);
        setUpPicImageSize (mDrawer.getMenuSize());
        mDrawer.setOnDrawerStateChangeListener(new MenuDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == MenuDrawer.STATE_CLOSED && newState != previousstate) {
//                    CustomToast.mT(ShidoActivity.this, "Drawer closed");
                    previousstate = newState;
                    iv_back.setVisibility(View.VISIBLE);
                } else if (newState == MenuDrawer.STATE_OPEN && previousstate != newState){
                    iv_back.setVisibility(View.INVISIBLE);
//                    CustomToast.mT(ShidoActivity.this, "Drawer opened");
                    previousstate = newState;
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                // Do nothing
            }
        });
    }

    int previousstate = MenuDrawer.STATE_CLOSED;

    private void initViews() {
//        iv_back.setVisibility(View.GONE);
    }

    public void aboutMe (View view) {
        CustomToast.mT(this, "我是林佳明");
    }

    public void getOut (View view) {
        if (mDrawer != null)
            mDrawer.closeMenu();
        (new android.os.Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 400);
    }

    private void setUpPicImageSize(int menuSize) {

        // get the size of the menu
        CircularImageView img = (CircularImageView) findViewById(R.id.circularone);
        // get the width of the view.
        ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
        layoutParams.width = menuSize/2;
        layoutParams.height = menuSize/2;
        img.setLayoutParams(layoutParams);
    }



    private void setAdapters() {
    }

    private void initAdapters() {

        String[] data = getResources().getStringArray(R.array.drawer_item);
        adap = new DrawerListAdapter(this, Utils.ArrayToList(data));
    }

    @OnClick(R.id.title_back)
    public void back () {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements OverListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            CustomToast.mT(ShidoActivity.this, getResources().getStringArray(R.array.drawer_item)[position]);
            mDrawer.closeMenu(true);
        }
    }


    public static void setNavigationBarColor(Activity activity, int colorId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(colorId);
        }

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager manager = new SystemBarTintManager(activity);
            manager.setNavigationBarTintEnabled(true);
            manager.setNavigationBarTintColor(colorId);
        }
    }



    public static void setStatusBarColor(Activity activity, int colorId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager manager = new SystemBarTintManager(activity);
            manager.setStatusBarTintEnabled(true);
            manager.setStatusBarTintColor(colorId);
        }
    }

}
