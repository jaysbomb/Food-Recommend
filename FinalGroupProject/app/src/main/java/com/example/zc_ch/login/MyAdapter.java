package com.example.zc_ch.login;

/**
 * Created by lenovo on 2017/4/11.
 */


    import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

    public class MyAdapter extends ArrayAdapter<History> {

        private int resoureId;
        private List<History> objects;
        private Context context;


        public MyAdapter(Context context, int resourceId, List<History> objects) {
            super(context, resourceId, objects);
            // TODO Auto-generated constructor stub
            this.objects = objects;
            this.context = context;

        }

        private static class ViewHolder {
            ImageView imageView;
            TextView title;
            TextView content;


        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return objects.size();
        }

        @Override
        public History getItem(int position) {
            // TODO Auto-generated method stub
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater mInflater = LayoutInflater.from(context);
                convertView = mInflater.inflate(R.layout.item, null);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.content = (TextView) convertView.findViewById(R.id.content);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            History history = objects.get(position);
            if (null != history) {
                viewHolder.imageView.setBackgroundResource(history.getPic());
                viewHolder.title.setText(history.getName());
                viewHolder.content.setText(history.getDate().toString());
            }

            return convertView;
        }
    }
