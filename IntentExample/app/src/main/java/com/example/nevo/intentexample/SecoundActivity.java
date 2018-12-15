package com.example.nevo.intentexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SecoundActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound);

        EditText fname,lname;
        Intent i = getIntent();

        fname = (EditText)findViewById(R.id.edit1);
        lname = (EditText)findViewById(R.id.edit2);

        sp = getSharedPreferences("MyFile",MODE_PRIVATE);

        if(i!=null) {
            SharedPreferences.Editor editor = sp.edit();
            Bundle b = i.getExtras();

            fname.setText(b.getString("fname"));
            lname.setText(b.getString("lname"));

            fname.setText(sp.getString("fname", "not found"));
            lname.setText(sp.getString("lname", "not found"));
        }
    }
}
