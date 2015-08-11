package sz.ulrichabiguime.imageu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sz.ulrichabiguime.imageu.R;


/**
 * Created by UlrichAbiguime at Shenzhen.
 */
public class DrawerListAdapter extends BaseAdapter {


    // this list view will have a top view that is your image.

    List<String> data;
    Context mCtx;
    LayoutInflater inf;

    public DrawerListAdapter (Context ctx, List<String> d) {

        mCtx = ctx;
        inf = LayoutInflater.from(ctx);
        data = d;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;
        DrawerListViewHolder vh;
        if (convertView == null) {
            v = inf.inflate(R.layout.left_drawer_listitem, parent, false);
            vh = DrawerListViewHolder.makeUp(v);
        } else {
            v = convertView;
            vh = (DrawerListViewHolder) convertView.getTag();
        }
        // set the content inside the view.
        vh.tv_title.setText(getItem(position));
        v.setTag(vh);
        return v;
    }


    static class DrawerListViewHolder  {

        public ImageView iv_icon;
        public TextView tv_title;
        public static DrawerListViewHolder makeUp (View v) {
            DrawerListViewHolder vh = new DrawerListViewHolder();
//            vh.iv_icon = v.find
            vh.tv_title = (TextView) v.findViewById(R.id.tv_string_content);
            return vh;
        }
    }

}
