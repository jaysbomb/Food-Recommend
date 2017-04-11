package com.example.zc_ch.login;

/**
 * Created by ZOU Zijie on 2017/4/9.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

class FoodImageAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<FoodIconItem> iconList;

    public FoodImageAdapter(Context c, List<FoodIconItem> iconList) {
        mContext = c;
        this.iconList = iconList;
    }

    public int getCount() {
        return iconList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setPadding(10, 10, 10, 10);

        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(iconList.get(position).getIconId());

        return imageView;
    }
}
