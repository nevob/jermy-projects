package com.example.nevo.listviewadapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lv;
    ItemAdapter itemAdapter;
    ArrayList<Item> items;

    final int REQUEST_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(new Item("hi","5.6",BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.capture)));
                Toast.makeText(MainActivity.this, "added", Toast.LENGTH_SHORT).show();
                itemAdapter.notifyDataSetChanged();
            }
        });

        lv = findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item selected = itemAdapter.getItem(i);
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("name",selected.getName());
                intent.putExtra("size",selected.getSize());

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = selected.getImage();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                intent.putExtra("image",stream.toByteArray());

                intent.putExtra("pos",i);
                startActivityForResult(intent,REQUEST_EDIT);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item selected = itemAdapter.getItem(i);
                items.remove(selected);
                itemAdapter.notifyDataSetChanged();
                return false;
            }
        });

        items = new ArrayList<>();
        items.add(new Item("hi","5.6",BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.capture)));

        itemAdapter = new ItemAdapter(this,0,0, items);
        lv.setAdapter(itemAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_EDIT && resultCode == RESULT_OK){
            Bundle b = data.getExtras();
            int pos = b.getInt("pos");
            Item item = items.get(pos);
            item.setName(b.getString("name"));
            item.setSize(b.getString("size"));
            byte[] img = b.getByteArray("image");
            item.setImage(BitmapFactory.decodeByteArray(img,0,img.length));
            itemAdapter.notifyDataSetChanged();
        }
    }
}
