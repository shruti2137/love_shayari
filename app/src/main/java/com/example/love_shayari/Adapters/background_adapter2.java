package com.example.love_shayari.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.love_shayari.Activitys.pencil;
import com.example.love_shayari.R;
import com.example.love_shayari.config;

public class background_adapter2 extends BaseAdapter {
    pencil pencil;

    public background_adapter2(pencil pencil)
    {
        this.pencil=pencil;
    }
    @Override
    public int getCount() {
        return config.gradients.length;
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
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(pencil).inflate(R.layout.background2_item,parent,false);
        TextView textView=convertView.findViewById(R.id.view);
        textView.setBackgroundResource(config.gradients[i]);

        return convertView;
    }
}
