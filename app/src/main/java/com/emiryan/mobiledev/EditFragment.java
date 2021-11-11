package com.emiryan.mobiledev;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class EditFragment extends Fragment {

    Button editButton;
    ListView editListView;
    CustomAdapter customAdapter;

    public EditFragment() {
        super(R.layout.fragment_edit);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initialize(view);

        editListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        editListView.setAdapter(customAdapter);
        editListView.setOnItemClickListener(
                (parent, v, position, id) -> customAdapter.notifyDataSetChanged()
        );

        editButton.setOnClickListener(v -> {

        });

        editListView.setOnItemClickListener(
                (parent, v, position, id) -> {
                    customAdapter.notifyDataSetChanged();
                    editListView.setItemChecked(position, true);
                }
        );
    }

    private void initialize(View view) {
        editButton = view.findViewById(R.id.editButtonEdit);
        editListView = view.findViewById(R.id.editListView);
        List<Student> list = ServiceLocator.getInstance().getListStudents();
        customAdapter = new CustomAdapter(view.getContext(), R.layout.list_item, list, editListView);    }
}