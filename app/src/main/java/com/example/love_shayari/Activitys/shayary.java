package com.example.love_shayari.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.love_shayari.Adapters.background_adapter2;
import com.example.love_shayari.Adapters.expand_adapter;
import com.example.love_shayari.Adapters.shayaryadapter;
import com.example.love_shayari.R;
import com.example.love_shayari.config;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.util.Random;

public class shayary extends AppCompatActivity {

    TextView title,shayary_text;
    ImageView next,prev,copy,share,pencil,expand,reload;
    int shayaripos;
    String[] arr;
    LinearLayout linearLayout;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shayary);

            shayary_text=findViewById(R.id.shayary_text);
            title=findViewById(R.id.shayary_title);
            next=findViewById(R.id.shayary_next);
            prev=findViewById(R.id.shayary_prev);
            copy=findViewById(R.id.shayary_copy);
            share=findViewById(R.id.shayary_share);
            pencil=findViewById(R.id.shayary_pencil);
            expand=findViewById(R.id.shayary_expand);
             reload=findViewById(R.id.shayary_reload);
        linearLayout=findViewById(R.id.shayary_linear);
        gridView=findViewById(R.id.grid);

        reload.setOnClickListener(view -> {
            int min=0;
            int max=config.gradients.length;
            int random=new Random().nextInt(max-min)+min;
            linearLayout.setBackgroundResource(config.gradients[random]);
        });

        expand.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
            bottomSheetDialog.setCancelable(false);
            View view1= LayoutInflater.from(this).inflate(R.layout.expand_layout,null);

            gridView=view1.findViewById(R.id.expand_grid);
            expand_adapter adapter=new expand_adapter(this);
            gridView.setAdapter(adapter);

            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    linearLayout.setBackgroundResource(config.gradients[i]);
                    bottomSheetDialog.dismiss();
                }
            });

        });

        shayaripos=getIntent().getIntExtra("cat_position",0);
            arr=getIntent().getStringArrayExtra("arr");

            shayary_text.setText(arr[shayaripos]);
            title.setText((shayaripos+1)+"/"+ arr.length);

            copy.setOnClickListener(view -> {
                ClipboardManager clipboardManager= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText("text",arr[shayaripos]);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(shayary.this,"copied",Toast.LENGTH_SHORT).show();
            });

            share.setOnClickListener(view -> {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                String shareBody = shayary_text.getText().toString();
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "share"));
            });

            prev.setOnClickListener(view -> {
                if (shayaripos>0)
                {
                    shayaripos--;
                    shayary_text.setText(arr[shayaripos]);
                    title.setText((shayaripos+1)+"/"+arr.length);
                }
            });

            next.setOnClickListener(view -> {
                if (shayaripos<arr.length-1)
                {
                    shayaripos++;
                    shayary_text.setText(arr[shayaripos]);
                    title.setText((shayaripos+1)+"/"+arr.length);
                }
            });


            pencil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(shayary.this,pencil.class);
                    intent.putExtra("shayari",shayary_text.getText().toString());
                    startActivity(intent);
                }
            });
    }

}