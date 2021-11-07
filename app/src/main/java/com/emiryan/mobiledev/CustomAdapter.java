package com.emiryan.mobiledev;

import android.app.Activity;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private Context context;
    private int resource;
    private List<Student> objects;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.resource, parent, false);

        ImageView imageView = view.findViewById(R.id.icon);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewAge = view.findViewById(R.id.textViewAge);
        TextView textViewChecked = view.findViewById(R.id.textViewChecked);
        ListView listView = ((Activity) context).findViewById(R.id.listView);
        SparseBooleanArray parsedList = listView.getCheckedItemPositions();

        Student object = objects.get(position);

        textViewAge.setText(Integer.toString(object.getAge()));
        textViewName.setText(object.getName());
        textViewChecked.setText(Boolean.toString(object.isChecked()));

        if(parsedList.indexOfKey(position) >= 0) {
            if(parsedList.get(position)) {
                imageView.setImageResource(R.drawable.no);
            } else {
                imageView.setImageResource(R.drawable.yes);
            }
        } else {
            imageView.setImageResource(R.drawable.yes);
        }

        return view;
    }
}
