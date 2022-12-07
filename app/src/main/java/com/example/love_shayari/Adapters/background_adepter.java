package com.example.love_shayari.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.love_shayari.Activitys.pencil;
import com.example.love_shayari.R;
import com.example.love_shayari.config;

public class background_adepter extends BaseAdapter {
    pencil pencil;

    public background_adepter(com.example.love_shayari.Activitys.pencil pencil){
        this.pencil=pencil;
    }
    @Override
    public int getCount() {
        return config.colors.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView,ViewGroup parent) {
        convertView = LayoutInflater.from(pencil).inflate(R.layout.backgrund_item,parent,false);
        LinearLayout linearLayout = convertView.findViewById(R.id.view);
        linearLayout.setBackgroundColor(pencil.getResources().getColor(config.colors[i]));
        return convertView;
    }
}
