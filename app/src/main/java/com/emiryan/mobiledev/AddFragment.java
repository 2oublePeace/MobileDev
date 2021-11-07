package com.emiryan.mobiledev;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initialize(view);

        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String age = editTextAge.getText().toString();
            boolean checked = checkBox.isChecked();

            if(!name.isEmpty() && !age.isEmpty()) {
                Bundle student = new Bundle();
                student.putSerializable("studentKey",
                        new Student(name, Integer.parseInt(age), checked));
                getParentFragmentManager().setFragmentResult("studentKey", student);

                Toast.makeText(view.getContext(), "Добавлено",Toast.LENGTH_LONG).show();
                reset();
            } else {
                Toast.makeText(view.getContext(), "Заполните поля",Toast.LENGTH_LONG).show();
            }
        });

        buttonBack.setOnClickListener(v -> getParentFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, ListFragment.class, null)
                .commit());
    }

    private void initialize(View view) {
        buttonAdd = view.findViewById(R.id.addButtonAdd);
        buttonBack = view.findViewById(R.id.addButtonBack);
        checkBox = view.findViewById(R.id.addCheckBox);
        editTextName = view.findViewById(R.id.addEditTextName);
        editTextAge = view.findViewById(R.id.addEditTextAge);
    }

    private void reset() {
        editTextName.setText("");
        editTextAge.setText("");
        checkBox.setChecked(false);
    }

}