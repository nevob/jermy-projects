package com.example.nevo.asynctask_15_10_18;

import android.opengl.Visibility;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    int count;
    MyTask task;
    TextView txtProg;
    boolean isPaused;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progBar);
        txtProg = (TextView)findViewById(R.id.txtProg);
        isPaused = false;
        task = new MyTask();
        task.execute(100);
    }

    private class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... integers) {
            progressBar.setMax(integers[0]);
            for(count = 1; count <= integers[0]; count++){
                try{
                    while(isPaused)
                        Thread.sleep(3000);
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
            //super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
            //Toast.makeText(MainActivity.this, "running..." + i, Toast.LENGTH_SHORT).show();
            txtProg.setText("Running..." + count);
            progressBar.setProgress(values[0]);
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
