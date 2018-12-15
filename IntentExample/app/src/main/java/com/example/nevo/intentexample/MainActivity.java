package com.example.nevo.intentexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    EditText fname, lname;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        fname = (EditText)findViewById(R.id.edit1);
        lname = (EditText)findViewById(R.id.edit2);

        sp = getSharedPreferences("MyFile",MODE_PRIVATE);

        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                Intent i = new Intent(MainActivity.this, SecoundActivity.class);

                i.putExtra("fname",fname.getText().toString());
                i.putExtra("fname",fname.getText().toString());

                editor.putString("fname",fname.getText().toString());
                editor.putString("lname",lname.getText().toString());
                editor.commit();

                startActivity(i);
            }
        });
    }
}
