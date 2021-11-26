package com.emiryan.mobiledev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            list = (ArrayList<String>) savedInstanceState.getSerializable("list");
        } else {
            list = new ArrayList<>();
        }

        editTextName = findViewById(R.id.editTextName);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, list);
        customAdapter = new CustomAdapter(this, R.layout.item_list, list);

        listView = findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                customAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", list);
    }

    public void addText(View view) {
        listView.setItemChecked(list.size(), true);
        customAdapter.add(editTextName.getText().toString());
    }

    public void selectAll(View view) {
        for (int i = 0; i < list.size(); i++) {
            listView.setItemChecked(i, true);
        }
    }

    public void resetAll(View view) {
        for (int i = 0; i < list.size(); i++) {
            listView.setItemChecked(i, false);
        }
    }

    public void printSelectedItems(View view)  {

        SparseBooleanArray parsedList = listView.getCheckedItemPositions();
        StringBuilder checkedItemsList = new StringBuilder();

        for(int i = 0; i < parsedList.size(); i++) {
            if(parsedList.valueAt(i) == true){
                String text = (String) listView.getItemAtPosition(i);
                checkedItemsList = checkedItemsList.append(" " + text);
            }
        }
        Toast.makeText(this, "Selected items are: " + checkedItemsList.toString(), Toast.LENGTH_LONG).show();
    }
}