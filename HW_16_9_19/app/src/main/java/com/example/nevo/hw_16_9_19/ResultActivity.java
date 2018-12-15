package com.example.nevo.hw_16_9_19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        EditText result = (EditText)findViewById(R.id.result);
        result.setText(getIntent().getExtras().getString("result"));
    }
}
