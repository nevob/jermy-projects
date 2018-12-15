package com.example.nevo.fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    Button btnAdd, btnRemove, btnAttach, btnDetach;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MyFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.add);
        btnRemove = findViewById(R.id.delete);
        btnAttach = findViewById(R.id.attach);
        btnDetach = findViewById(R.id.detach);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment == null){
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragment = new MyFragment();
                    fragmentTransaction.add(R.id.fragmentLayout, fragment);
                    fragmentTransaction.addToBackStack("add");
                    fragmentTransaction.commit();
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment != null){
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(fragment);
                    fragmentTransaction.addToBackStack("remove");
                    fragmentTransaction.commit();
                    fragment = null;
                }
            }
        });

        btnAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment!=null) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.attach(fragment);
                    fragmentTransaction.addToBackStack("attach");
                    fragmentTransaction.commit();
                }
            }
        });

        btnDetach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment!=null) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.attach(fragment);
                    fragmentTransaction.addToBackStack("detach");
                    fragmentTransaction.commit();
                }
            }
        });

        fragment = null;
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            fragmentManager.popBackStack();
            return true;
        }
        return false;
    }
}
