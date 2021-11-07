package com.emiryan.mobiledev;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {

    ListView listView;
    CustomAdapter customAdapter;

    public ListFragment() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initialize(view);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(
                (parent, v, position, id) -> customAdapter.notifyDataSetChanged()
        );
    }

    private void initialize(View view) {
        listView = view.findViewById(R.id.listView);
        customAdapter = new CustomAdapter(view.getContext(), R.layout.list_item,
                ServiceLocator.getInstance().getListStudents());
    }
}