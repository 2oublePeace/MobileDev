package com.emiryan.mobiledev.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.emiryan.mobiledev.utils.CustomAdapter;
import com.emiryan.mobiledev.R;
import com.emiryan.mobiledev.utils.ServiceLocator;
import com.emiryan.mobiledev.entities.Student;

import java.util.List;

public class EditFragment extends Fragment {

    Button editButton;
    EditText editTextName;
    EditText editTextAge;
    CheckBox editCheckBox;
    ListView editListView;
    CustomAdapter customAdapter;

    public EditFragment() {
        super(R.layout.fragment_edit);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initialize(view);

        editListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        editListView.setAdapter(customAdapter);
        editListView.setOnItemClickListener(
                (parent, v, position, id) -> {
                    enableEditing();
                    customAdapter.notifyDataSetChanged();
                }
        );

        editButton.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String age = editTextAge.getText().toString();
            boolean checked = editCheckBox.isChecked();

            if(!name.isEmpty() && !age.isEmpty()) {
                Student student = (Student) editListView.getItemAtPosition(editListView.getCheckedItemPosition());
                student.setAge(Integer.parseInt(age));
                student.setName(name);
                student.setChecked(checked);
                reset();
                disableEditing();
                Toast.makeText(view.getContext(), "Обновлено",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(view.getContext(), "Заполните поля",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initialize(View view) {
        editButton = view.findViewById(R.id.editButtonEdit);
        editTextName = view.findViewById(R.id.editEditTextName);
        editTextAge = view.findViewById(R.id.editEditTextAge);
        editCheckBox = view.findViewById(R.id.editCheckBox);
        editListView = view.findViewById(R.id.editListView);
        disableEditing();
        List<Student> list = ServiceLocator.getInstance().getListStudents();
        customAdapter = new CustomAdapter(view.getContext(), R.layout.list_item, list, editListView);
    }

    private void enableEditing() {
        editTextName.setEnabled(true);
        editTextAge.setEnabled(true);
        editCheckBox.setEnabled(true);
    }

    private void disableEditing() {
        editTextName.setEnabled(false);
        editTextAge.setEnabled(false);
        editCheckBox.setEnabled(false);
    }

    private void reset() {
        editTextName.setText("");
        editTextAge.setText("");
        editCheckBox.setChecked(false);
    }
}