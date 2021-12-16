package com.emiryan.mobiledev.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.emiryan.mobiledev.entities.Student;
import com.emiryan.mobiledev.utils.CustomAdapter;
import com.emiryan.mobiledev.R;
import com.emiryan.mobiledev.utils.ServiceLocator;

import java.io.Serializable;
import java.util.List;

public class ListFragment extends Fragment {

    ListView listView;
    CustomAdapter customAdapter;

    public ListFragment() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            ServiceLocator.getInstance().setListStudents(
                    (List<Student>) savedInstanceState.getSerializable("students"));
        }

        initialize(view);

        getParentFragmentManager().setFragmentResultListener("listStudentsKey", this,
                (requestKey, bundle) -> initialize(view));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("students",
                (Serializable) ServiceLocator.getInstance().getListStudents());
    }

    private void initialize(View view) {
        listView = view.findViewById(R.id.listView);

        customAdapter = new CustomAdapter(view.getContext(), R.layout.list_item,
                ServiceLocator.getInstance().getListStudents(), listView);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(
                (parent, v, position, id) -> customAdapter.notifyDataSetChanged()
        );
    }
}