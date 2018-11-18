package com.example.mira.refrigerator;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MiRa on 2015-11-27.
 */

public class MyListViewAdapter extends BaseAdapter {

    Context mContext;
    int layout;
    ArrayList<MyListViewItems> itemList;
    LayoutInflater inflater;

    public MyListViewAdapter(Context context, int layout, ArrayList<MyListViewItems> itemList){
        this.mContext = context;
        this.layout = layout;
        this.itemList = itemList;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            convertView = inflater.inflate(layout, parent, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.itemimg);
        TextView textViewName = (TextView)convertView.findViewById(R.id.itemname);
        TextView textViewBuyDay = (TextView)convertView.findViewById(R.id.itembuyday);
        TextView textViewLastDay = (TextView)convertView.findViewById(R.id.itemlastday);


        Drawable dr = convertView.getResources().getDrawable(android.R.drawable.ic_menu_camera);
        imageView.setImageDrawable(dr);
        textViewName.setText(itemList.get(position).getName());
        textViewBuyDay.setText(itemList.get(position).getBuyDay());
        textViewLastDay.setText(itemList.get(position).getLastDay());


        return convertView;
    }
}
