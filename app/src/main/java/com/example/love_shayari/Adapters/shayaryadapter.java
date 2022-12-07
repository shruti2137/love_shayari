package com.example.love_shayari.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.love_shayari.Activitys.shayary;
import com.example.love_shayari.R;

public class shayaryadapter extends PagerAdapter {

    shayary s;
    String[] shayary;
    public shayaryadapter(shayary s, String[] shayary) {
        this.s=s;
        this.shayary=shayary;
    }

    @Override
    public int getCount() {
        return shayary.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view= LayoutInflater.from(s).inflate(R.layout.shayary_item,container,false);

        TextView textView=view.findViewById(R.id.shayary);

        textView.setText(shayary[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
