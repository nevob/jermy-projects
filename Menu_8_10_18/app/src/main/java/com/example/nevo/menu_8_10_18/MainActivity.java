package com.example.nevo.menu_8_10_18;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img1;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView)findViewById(R.id.img1);
        registerForContextMenu(img1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemToast) {
            Toast.makeText(this, "You are toasted :)", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.itemPic) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,REQUEST_IMAGE_CAPTURE);
        }
        if (item.getItemId() == R.id.itemActivity) {
            Intent i = new Intent(this,SecoundActivity.class);
            startActivity(i);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE){
            Bundle extras = data.getExtras();
            Bitmap img = (Bitmap)extras.get("data");
            img1.setImageBitmap(img);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Image Options");
        menu.add(0,v.getId(), 0, "Image Resize +");
        menu.add(0,v.getId(), 0, "Image Resize -");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals("Image Resize +")) {
            img1.setScaleX(img1.getScaleX()+1);
            img1.setScaleY(img1.getScaleY()+1);
        }
        if(item.getTitle().equals("Image Resize -")) {
            img1.setScaleX(img1.getScaleX()-1);
            img1.setScaleY(img1.getScaleY()-1);
        }
        return true;
    }
}
