package sz.ultima.imageu.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import net.simonvt.menudrawer.MenuDrawer;

import sz.ultima.imageu.R;
import sz.ultima.imageu.adapters.DrawerListAdapter;
import sz.ultima.imageu.utils.CustomToast;
import sz.ultima.imageu.utils.Utils;
import sz.ultima.imageu.viewz.OverListView;

public class ShidoActivity extends FragmentActivity {

    // views
    private FrameLayout frameLayout;
    private ImageView iv_back;

    // variables
    private String[] mPlanetTitles;
    private DrawerListAdapter adap;
    private MenuDrawer mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shido);
        initViews();
        setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
        setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
        initAdapters();
        setAdapters();
//getSupportActionBar().hide();;
        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.activity_shido);
        mDrawer.setMenuView(R.layout.activity_menu_view);
        mDrawer.setOnDrawerStateChangeListener(new MenuDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == MenuDrawer.STATE_CLOSED) {
                 iv_back.setVisibility(View.VISIBLE);
                } else {
                    iv_back.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                // Do nothing
            }
        });
    }

    private void setAdapters() {
    }

    private void initAdapters() {

        String[] data = getResources().getStringArray(R.array.drawer_item);
        adap = new DrawerListAdapter(this, Utils.ArrayToList(data));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawer.isMenuVisible()) {
                    mDrawer.closeMenu(true);
                }
            }
        });
    }


    private void initViews() {
        frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        iv_back = (ImageView) findViewById(R.id.title_back); iv_back.setVisibility(View.GONE);
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

    /**
     * 设置虚拟按钮为半透明状态
     *
     * @param activity activity
     * @param colorId  颜色id
     */
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


    /**
     * 给状态栏设置颜色,针对 4.4 , 5.0 在style文件中已经配置
     * 提示:如果出现布局上移了,请确认你的activity 对应的xml根布局是否有配置 fillSystemWindow=true
     *
     * @param activity activity
     * @param colorId  颜色id,请用 getResource.getColor(xxx) 传入
     */
    public static void setStatusBarColor(Activity activity, int colorId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager manager = new SystemBarTintManager(activity);
            manager.setStatusBarTintEnabled(true);
            manager.setStatusBarTintColor(colorId);
        }
    }



}
