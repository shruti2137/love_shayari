package com.example.love_shayari.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.love_shayari.Adapters.background_adapter2;
import com.example.love_shayari.Adapters.background_adepter;
import com.example.love_shayari.Adapters.emoji_adapter;
import com.example.love_shayari.Adapters.font_adapter;
import com.example.love_shayari.R;
import com.example.love_shayari.config;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class pencil extends AppCompatActivity {
    String text;
    TextView textView,pencil;
    ImageView reload,expand,download;
    GridView gridView;
    Button backgrond,textcolor,font,emoji,textsize,share;
    LinearLayout linearLayout;
    ListView listView;
    Bitmap bitmap;
    SeekBar seekbar;

    @SuppressLint({"MissingInflatedId", "WrongThread"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencil);

        text=getIntent().getStringExtra("shayari");
        textView=findViewById(R.id.pencil);
        textView.setText(text);

        linearLayout = findViewById(R.id.linear);
        textView = findViewById(androidx.core.R.id.text);
        reload=findViewById(R.id.reload);
        expand=findViewById(R.id.expand);
        backgrond=findViewById(R.id.background);
        textcolor=findViewById(R.id.Textcolor);
        share=findViewById(R.id.share);
        font=findViewById(R.id.Font);
        emoji=findViewById(R.id.Emoji);
        textsize=findViewById(R.id.Textsize);
        textView=findViewById(R.id.pencil);
        seekbar=findViewById(R.id.seekbar);
        pencil=findViewById(R.id.pencil);
        download = findViewById(R.id.download);


        text=getIntent().getStringExtra("shayari");
        textView.setText(text);
        reload.setOnClickListener(view -> {
            int min=0;
            int max=config.gradients.length;
            int random=new Random().nextInt(max-min)+min;
            linearLayout.setBackgroundResource(config.gradients[random]);
        });

        expand.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
            bottomSheetDialog.setCancelable(false);
            View view1=LayoutInflater.from(this).inflate(R.layout.background_layout2,null);

            gridView=view1.findViewById(R.id.grid);
            background_adapter2 adapter2=new background_adapter2(this);
            gridView.setAdapter(adapter2);

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

        backgrond.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
            bottomSheetDialog.setCancelable(false);
            View view1 = LayoutInflater.from(this).inflate(R.layout.background_layout,null);

            gridView=view1.findViewById(R.id.grid);
            background_adepter adepter=new background_adepter(this);
            gridView.setAdapter(adepter);

            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    linearLayout.setBackgroundColor(getResources().getColor(config.colors[i]));
                    bottomSheetDialog.show();
                    bottomSheetDialog.setCancelable(true);

                }
            });
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon = getBitmapFromView(linearLayout);
                System.out.println("bitmap======>"+icon);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                int num=new Random().nextInt(2000);
                File f = new File(config.file.getAbsolutePath()  + "/temporary_file"+num+".jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    Toast.makeText(pencil.this, "file downloaded", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon = getBitmapFromView(linearLayout);
                System.out.println("bitmap======>"+icon);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                int num=new Random().nextInt(2000);

                File f = new File(config.file.getAbsolutePath()  + "/temporary_file"+num+".jpg");

                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    Toast.makeText(pencil.this, "file downloaded", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    share.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), f.getAbsolutePath(),"img","Identified image")));
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                startActivity(Intent.createChooser(share, "Share Image"));
            }
        });

        emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(pencil.this);
                bottomSheetDialog.setCancelable(false);
                view= LayoutInflater.from(pencil.this).inflate(R.layout.emoji_layout, null);

                listView = view.findViewById(R.id.emoji_list);
                emoji_adapter adapter = new emoji_adapter(pencil.this);

                listView.setAdapter(adapter);
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        textView.setText(config.emoji[i]+"\n"+text+"\n"+config.emoji[i]);
                        bottomSheetDialog.setCancelable(true);
                    }
                });
            }
        });


        font.setOnClickListener(new View.OnClickListener() {
            private ListAdapter adapter;

            @Override
            public void onClick(View view) {

                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(pencil.this);
                bottomSheetDialog.setCancelable(false);
                view  = LayoutInflater.from(pencil.this).inflate(R.layout.font_layout,null);

                gridView=view.findViewById(R.id.grid12);
                font_adapter fa =new font_adapter(pencil.this);
                gridView.setAdapter(fa);
                gridView.setNumColumns(config.font.length);

                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        Typeface typeface=Typeface.createFromAsset(getAssets(),config.font[i]);
                        textView.setTypeface(typeface);

                        bottomSheetDialog.setCancelable(true);
                    }
                });

            }
        });


        textsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(pencil.this);

                view=LayoutInflater.from(pencil.this).inflate(R.layout.textsize_layout,null);
                seekbar = view.findViewById(R.id.seekbar);
                seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        textView.setTextSize(seekbar.getProgress());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();
            }
        });
        textcolor.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
            bottomSheetDialog.setCancelable(false);
            View view1 = LayoutInflater.from(this).inflate(R.layout.background_layout,null);

            gridView=view1.findViewById(R.id.grid);
            background_adepter adepter=new background_adepter(this);
            gridView.setAdapter(adepter);

            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();



            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    textView.setTextColor(getResources().getColor(config.colors[i]));
                    bottomSheetDialog.show();
                    bottomSheetDialog.setCancelable(true);

                }
            });
        });
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }


}