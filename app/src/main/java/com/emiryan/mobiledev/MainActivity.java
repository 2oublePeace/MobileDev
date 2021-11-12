package com.emiryan.mobiledev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        editTextName = findViewById(R.id.editTextName);

        customAdapter = new CustomAdapter<String>(this, R.layout.item_list, list);

        listView = findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(
                (parent, v, position, id) -> customAdapter.notifyDataSetChanged()
        );
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
            if(parsedList.valueAt(i)){
                String text = (String) listView.getItemAtPosition(i);
                checkedItemsList = checkedItemsList.append(" ").append(text);
            }
        }

        Toast.makeText(this, "Selected items are: " + checkedItemsList.toString(),
                Toast.LENGTH_LONG).show();
    }
}