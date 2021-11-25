package com.emiryan.mobiledev.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.emiryan.mobiledev.utils.CustomAdapter;
import com.emiryan.mobiledev.R;
import com.emiryan.mobiledev.utils.ServiceLocator;
import com.emiryan.mobiledev.entities.Student;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    Button buttonSearch;
    String operation;
    String field;
    EditText editText;
    List<Student> list;
    List<Student> filteredList;
    Spinner fieldSpinner;
    Spinner operatorSpinner;
    ListView searchListView;
    CustomAdapter adapter;

    public SearchFragment() {
        super(R.layout.fragment_search);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initialize(view);

        buttonSearch.setOnClickListener(v -> {
            field = fieldSpinner.getSelectedItem().toString();
            operation = operatorSpinner.getSelectedItem().toString();


            switch (operation) {
                case "Equals" :
                    adapter.clear();
                    Field privateStringField = null;
                    try {
                        privateStringField = Student.class.getDeclaredField(field.toLowerCase());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    privateStringField.setAccessible(true);

                    for(Student student : list) {
                        try {
                            String value = privateStringField.get(student).toString();
                            String searchValue = editText.getText().toString();
                            if(!searchValue.isEmpty() && value.compareTo(searchValue) == 0) {
                                adapter.add(student);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "Greater" :
                    adapter.clear();
                    privateStringField = null;
                    try {
                        privateStringField = Student.class.getDeclaredField(field.toLowerCase());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    privateStringField.setAccessible(true);

                    for(Student student : list) {
                        try {
                            String value = privateStringField.get(student).toString();
                            String searchValue = editText.getText().toString();
                            if(!searchValue.isEmpty() && value.compareTo(searchValue) > 0) {
                                adapter.add(student);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "Less" :
                    adapter.clear();
                    privateStringField = null;
                    try {
                        privateStringField = Student.class.getDeclaredField(field.toLowerCase());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    privateStringField.setAccessible(true);

                    for(Student student : list) {
                        try {
                            String value = privateStringField.get(student).toString();
                            String searchValue = editText.getText().toString();
                            if(!searchValue.isEmpty() && value.compareTo(searchValue) < 0) {
                                adapter.add(student);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
            }
        });
    }

    private void initialize(View view) {
        operation = "";
        field = "";

        String[] fields = { "Name", "Age", "Checked"};
        String[] operators = { "Equals", "Greater", "Less"};

        list = ServiceLocator.getInstance().getListStudents();
        filteredList = new ArrayList<>();

        buttonSearch = view.findViewById(R.id.searchButtonSearch);
        fieldSpinner = view.findViewById(R.id.fieldSpinner);
        operatorSpinner = view.findViewById(R.id.operatorSpinner);
        editText = view.findViewById(R.id.editTextValue);
        searchListView = view.findViewById(R.id.searchListView);

        ArrayAdapter<String> fieldAdapter = new ArrayAdapter(view.getContext(),
                android.R.layout.simple_spinner_item, fields);
        ArrayAdapter<String> operatorAdapter = new ArrayAdapter(view.getContext(),
                android.R.layout.simple_spinner_item, operators);
        adapter = new CustomAdapter(view.getContext(), R.layout.list_item, filteredList,
                searchListView);

        fieldAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operatorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fieldSpinner.setAdapter(fieldAdapter);
        operatorSpinner.setAdapter(operatorAdapter);

        searchListView.setAdapter(adapter);
    }
}