package com.example.nevo.sqlroom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    EditText ed;
    Button btnInsert,btnFind, btnDelete, btnNuke;
    Handler mHandler;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed = findViewById(R.id.ed);

        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User("nevo","bl");
                user.setFirstName(ed.getText().toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.userDao().insertAll(user);

                        Message message = mHandler.obtainMessage(1,"done.");
                        message.sendToTarget();
                    }
                }).start();
            }
        });

        btnFind = findViewById(R.id.btnFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int uid = db.userDao().loadUidByFirstName(ed.getText().toString());

                        Message message = mHandler.obtainMessage(2,ed.getText().toString() + " UID = " + uid);
                        message.sendToTarget();
                    }
                }).start();
            }
        });

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.userDao().deleteByName(ed.getText().toString());

                        Message message = mHandler.obtainMessage(3,"done.");
                        message.sendToTarget();
                    }
                }).start();
            }
        });

        btnNuke = findViewById(R.id.btnNuke);
        btnNuke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.userDao().nukeTable();

                        Message message = mHandler.obtainMessage(4,"done.");
                        message.sendToTarget();
                    }
                }).start();
            }
        });

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                Toast.makeText(MainActivity.this, (String)message.obj, Toast.LENGTH_SHORT).show();
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                db = Room.databaseBuilder(getApplicationContext(), DataBase.class, "User").addMigrations(MIGRATION_1_2).build();
            }
        }).start();
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
//                    + "`name` TEXT, PRIMARY KEY(`id`))");
        }
    };
}
