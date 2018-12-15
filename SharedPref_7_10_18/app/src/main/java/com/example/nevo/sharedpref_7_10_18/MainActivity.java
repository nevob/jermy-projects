package com.example.nevo.sharedpref_7_10_18;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    Button btnStore, btnUpdate, btnShow, btnRemove;
    EditText edValue, edKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("MyFile", MODE_PRIVATE);
        edKey = (EditText)findViewById(R.id.edKey);
        edValue = (EditText)findViewById(R.id.edValue);

        btnStore = (Button)findViewById(R.id.btnStore);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = edValue.getText().toString();
                String key = edKey.getText().toString();
                sp.edit().putString(key,val).commit();
                Toast.makeText(MainActivity.this, "Stored!", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = edValue.getText().toString();
                String key = edKey.getText().toString();
                sp.edit().putString(key,val).commit();
                Toast.makeText(MainActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRemove = (Button)findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = edKey.getText().toString();
                sp.edit().remove(key).commit();
                Toast.makeText(MainActivity.this, "Removed!", Toast.LENGTH_SHORT).show();
            }
        });

        btnShow = (Button)findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = edKey.getText().toString();
                String val = sp.getString(key,"not found!");
                Toast.makeText(MainActivity.this, val, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
