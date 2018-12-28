package com.example.nevo.testapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    TextView tvTime;
    Button btnStart, btnStop, btnReset;

    Boolean isPaused;
    int count;
    MyTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = findViewById(R.id.tvTime);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new MyTask();
                task.execute(60*60);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.cancel(true);
                tvTime.setText("00:00");
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
            }
        });

        isPaused = false;
    }

    private class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... integers) {
            for(count = 1; count <= integers[0]; count++){
                try{
                    if(isCancelled())
                        return "didn't done";
                    while(isPaused);
                    publishProgress(count);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "done!";
        }

        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            Toast.makeText(MainActivity.this, "starting...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
 //           super.onProgressUpdate(values);
            //Toast.makeText(MainActivity.this, "running..." + i, Toast.LENGTH_SHORT).show();
            tvTime.setText(String.format("%02d:%02d",(int)(count/60), (int)(count % 60)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        task.cancel(false);
    }
}
