package com.emiryan.mobiledev;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class AddFragment extends Fragment {

    Button buttonAdd;
    Button buttonSelectAll;
    Button buttonUnselectAll;
    Button buttonSend;
    EditText editText;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> customAdapter;

    public AddFragment() {
        super(R.layout.fragment_add);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initialize(view);

        listView = (ListView) getView().findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(
                (parent, v, position, id) -> customAdapter.notifyDataSetChanged()
        );

        buttonAdd.setOnClickListener(v -> {
            listView.setItemChecked(list.size(), true);
            customAdapter.add(editText.getText().toString());
        });

        buttonSelectAll.setOnClickListener(v -> {
            for (int i = 0; i < list.size(); i++) {
                listView.setItemChecked(i, true);
            }
        });

        buttonUnselectAll.setOnClickListener(v -> {
            for (int i = 0; i < list.size(); i++) {
                listView.setItemChecked(i, false);
            }
        });

        buttonSend.setOnClickListener(v -> {
            Intent i = new Intent(getActivity().getBaseContext(), MainActivity.class);

            i.putExtra("NAME_KEY", "Vova");
            i.putExtra("YEAR_KEY", 23);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .remove(this)
                    .commit();
        });
    }

    private void initialize(View view) {
        buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonSelectAll = view.findViewById(R.id.buttonSelectAll);
        buttonUnselectAll = view.findViewById(R.id.buttonUnselectAll);
        buttonSend = view.findViewById(R.id.buttonToast);
        editText = (EditText) getView().findViewById(R.id.editText);
        list = new ArrayList<>();
        customAdapter = new CustomAdapter(getContext(), R.layout.item_list, list);
    }

    private void printSelectedItems(View view)  {

        SparseBooleanArray parsedList = listView.getCheckedItemPositions();
        StringBuilder checkedItemsList = new StringBuilder();

        for(int i = 0; i < parsedList.size(); i++) {
            if(parsedList.valueAt(i) == true){
                String text = (String) listView.getItemAtPosition(i);
                checkedItemsList = checkedItemsList.append(" " + text);
            }
        }
        Toast.makeText(getContext(), "Selected items are: " + checkedItemsList.toString(), Toast.LENGTH_LONG).show();
    }

}