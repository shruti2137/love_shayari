package com.example.love_shayari.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.love_shayari.Activitys.shayary;
import com.example.love_shayari.R;
import com.example.love_shayari.config;

public class expand_adapter extends BaseAdapter {

    shayary shayary;
    public expand_adapter(shayary shayary) {
        this.shayary=shayary;
    }

    @Override
    public int getCount() {
        return config.gradients.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(shayary).inflate(R.layout.expand_item,parent,false);

        return convertView;
    }
}
