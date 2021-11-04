package com.emiryan.mobiledev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("onDestroy");
    }
    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("onStop");
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("onStart");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        System.out.println("onRestart");
    }

    public void addText(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerView, AddFragment.class, null)
                .commit();

        Intent i = getIntent();
        String name = i.getStringExtra("NAME_KEY");
        int year = i.getIntExtra("YEAR_KEY",0);
    }
}