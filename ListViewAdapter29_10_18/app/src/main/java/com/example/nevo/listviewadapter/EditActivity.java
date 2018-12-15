package com.example.nevo.listviewadapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class EditActivity extends AppCompatActivity {

    Button btnOk,btnPic;
    ImageView img;
    EditText edName,edSize;

    final int REQUEST_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edName = findViewById(R.id.edName);
        edSize = findViewById(R.id.edSize);
        img = findViewById(R.id.img);
        btnOk = findViewById(R.id.btnOk);
        btnPic = findViewById(R.id.btnPic);

        Intent i = getIntent();
        final Bundle b = i.getExtras();
        edName.setText(b.getString("name"));
        edSize.setText(b.getString("size"));

        byte[]imgArr = b.getByteArray("image");
        img.setImageBitmap(BitmapFactory.decodeByteArray(imgArr,0,imgArr.length));

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,REQUEST_IMAGE);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("name",edName.getText().toString());
                i.putExtra("size",edSize.getText().toString());

                Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                i.putExtra("image",stream.toByteArray());

                i.putExtra("pos",b.getInt("pos"));
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            Bundle b = data.getExtras();
            img.setImageBitmap((Bitmap)b.get("data"));
        }
    }
}
