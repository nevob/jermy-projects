package com.example.nevo.listview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button btn;
    LayoutInflater inflater;

    int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,REQUEST_IMAGE_CAPTURE);
            }
        });
        inflater = getLayoutInflater();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bitmap pic = (Bitmap)data.getExtras().get("data");
            View v = inflater.inflate(R.layout.custom_layout,null,false);
            Item item = new Item("hi","5.0",pic);
            ((TextView)v.findViewById(R.id.tvName)).setText(item.getName());
            ((TextView)v.findViewById(R.id.tvSize)).setText(item.getSize());
            ((ImageView)v.findViewById(R.id.img)).setImageBitmap(item.getImage());
            ((LinearLayout)findViewById(R.id.layout)).addView(v);
        }
    }
}
