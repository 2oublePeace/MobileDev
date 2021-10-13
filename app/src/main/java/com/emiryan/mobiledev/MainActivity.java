package com.emiryan.mobiledev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editTextName = findViewById(R.id.editTextName);
        list = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, list);
        listView.setAdapter(adapter);
    }

    public void editTextChanged(View view) {
        adapter.add(editTextName.getText().toString());
    }
}