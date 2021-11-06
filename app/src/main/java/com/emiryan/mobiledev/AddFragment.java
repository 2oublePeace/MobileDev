package com.emiryan.mobiledev;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class AddFragment extends Fragment {

    Button buttonAdd;
    Button buttonBack;
    EditText editTextName;
    EditText editTextAge;
    CheckBox checkBox;

    public AddFragment() {
        super(R.layout.fragment_add);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initialize(view);

        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String age = editTextAge.getText().toString();
            boolean checked = checkBox.isChecked();

            if(name != null || age != null) {
                Bundle student = new Bundle();
                student.putSerializable("studentKey",
                        new Student(name, Integer.parseInt(age), checked));
                getParentFragmentManager().setFragmentResult("studentKey", student);
            }
        });

        buttonBack.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerView, ListFragment.class, null)
                    .commit();

        });
    }

    private void initialize(View view) {
        buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonBack = view.findViewById(R.id.buttonBack);
        checkBox = view.findViewById(R.id.checkBox);
        editTextName = (EditText) getView().findViewById(R.id.editTextName);
        editTextAge = (EditText) getView().findViewById(R.id.editTextAge);
    }

}