package mikeslopsema.weather;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.request.RequestOptions;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Mike on 1/20/2018.
 */

public class CustomAdapter extends ArrayAdapter<ListItemModel> implements View.OnClickListener {
    private ArrayList<ListItemModel> mData;
    private Context mContext;

    private static class ViewHolder {
        TextView name;
        TextView title0;
        TextView title1;
        TextView title2;
        TextView title3;
        TextView title4;
        TextView title5;
        TextView title6;
        TextView title7;
        WebView webView0;
        WebView webView1;
        WebView webView2;
        WebView webView3;
        WebView webView4;
        WebView webView5;
        WebView webView6;
        WebView webView7;
    }

    public CustomAdapter(ArrayList<ListItemModel> data, Context context) {
        super(context, R.layout.row_item, data);
        mContext = context;
        mData = data;
    }

    @Override
    public void add(ListItemModel lim) {
        mData.add(lim);
        notifyDataSetChanged();
    }

    public ListItemModel get(int position) {
        return mData.get(position);
    }

    @Override
    public void onClick(View view) {
        Snackbar.make(view, "List View : " + view.getTag(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemModel lim = getItem(position);
        Log.i("MIKE", "getView : " + lim.getName());
        Log.i("MIKE", "getView : " + lim.getForecasts().toString());
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.title0 = convertView.findViewById(R.id.title0);
            viewHolder.title1 = convertView.findViewById(R.id.title1);
            viewHolder.title2 = convertView.findViewById(R.id.title2);
            viewHolder.title3 = convertView.findViewById(R.id.title3);
            viewHolder.title4 = convertView.findViewById(R.id.title4);
            viewHolder.title5 = convertView.findViewById(R.id.title5);
            viewHolder.title6 = convertView.findViewById(R.id.title6);
            viewHolder.title7 = convertView.findViewById(R.id.title7);
            viewHolder.webView0 = convertView.findViewById(R.id.webView0);
            viewHolder.webView1 = convertView.findViewById(R.id.webView1);
            viewHolder.webView2 = convertView.findViewById(R.id.webView2);
            viewHolder.webView3 = convertView.findViewById(R.id.webView3);
            viewHolder.webView4 = convertView.findViewById(R.id.webView4);
            viewHolder.webView5 = convertView.findViewById(R.id.webView5);
            viewHolder.webView6 = convertView.findViewById(R.id.webView6);
            viewHolder.webView7 = convertView.findViewById(R.id.webView7);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(lim.getName());
        viewHolder.title0.setText(lim.getTitle(0));
        viewHolder.title1.setText(lim.getTitle(1));
        viewHolder.title2.setText(lim.getTitle(2));
        viewHolder.title3.setText(lim.getTitle(3));
        viewHolder.title4.setText(lim.getTitle(4));
        viewHolder.title5.setText(lim.getTitle(5));
        viewHolder.title6.setText(lim.getTitle(6));
        viewHolder.title7.setText(lim.getTitle(7));
        viewHolder.webView0.loadUrl(lim.getImageUrl(0));
        viewHolder.webView1.loadUrl(lim.getImageUrl(1));
        viewHolder.webView2.loadUrl(lim.getImageUrl(2));
        viewHolder.webView3.loadUrl(lim.getImageUrl(3));
        viewHolder.webView4.loadUrl(lim.getImageUrl(4));
        viewHolder.webView5.loadUrl(lim.getImageUrl(5));
        viewHolder.webView6.loadUrl(lim.getImageUrl(6));
        viewHolder.webView7.loadUrl(lim.getImageUrl(7));
        // Return the completed view to render on screen
        return convertView;
    }
}
