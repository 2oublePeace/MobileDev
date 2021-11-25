package com.emiryan.mobiledev.view;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.emiryan.mobiledev.utils.CustomAdapter;
import com.emiryan.mobiledev.R;
import com.emiryan.mobiledev.utils.ServiceLocator;
import com.emiryan.mobiledev.entity.Student;

import java.util.List;

public class DeleteFragment extends Fragment {

    Button buttonDeleteAll;
    Button buttonDeleteChecked;
    Button buttonDeleteUnchecked;
    ListView deleteListView;
    CustomAdapter customAdapter;

    public DeleteFragment() {
        super(R.layout.fragment_delete);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initialize(view);
        deleteListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        deleteListView.setAdapter(customAdapter);
        deleteListView.setOnItemClickListener(
                (parent, v, position, id) -> customAdapter.notifyDataSetChanged()
        );

        buttonDeleteAll.setOnClickListener(v -> {
            customAdapter.clear();
        });

        buttonDeleteChecked.setOnClickListener(v -> {
            SparseBooleanArray parsedList = deleteListView.getCheckedItemPositions();
            for(int i = parsedList.size() - 1; i >= 0; i--) {
                if(parsedList.valueAt(i)) {
                    customAdapter.remove(customAdapter.getItem(parsedList.keyAt(i)));
                }
            }

            for(int i = 0; i < deleteListView.getCount(); i++) {
                deleteListView.setItemChecked(i, false);
            }
        });

        buttonDeleteUnchecked.setOnClickListener(v -> {
            SparseBooleanArray parsedList = deleteListView.getCheckedItemPositions();

            for(int i = deleteListView.getCount() - 1; i >= 0; i--) {
                if(parsedList.indexOfKey(i) < 0) {
                    customAdapter.remove(customAdapter.getItem(i));
                } else if (!parsedList.valueAt(i)) {
                    customAdapter.remove(customAdapter.getItem(i));
                }
            }

            for(int i = 0; i < deleteListView.getCount(); i++) {
                deleteListView.setItemChecked(i, true);
            }
        });
    }

    private void initialize(View view) {
        buttonDeleteAll = view.findViewById(R.id.deleteButtonDeleteAll);
        buttonDeleteChecked = view.findViewById(R.id.deleteButtonDeleteChecked);
        buttonDeleteUnchecked = view.findViewById(R.id.deleteButtonDeleteUnchecked);
        deleteListView = view.findViewById(R.id.deleteListView);
        List<Student> list = ServiceLocator.getInstance().getListStudents();
        customAdapter = new CustomAdapter(view.getContext(), R.layout.list_item, list, deleteListView);
    }

}