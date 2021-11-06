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

public class CustomAdapter<T> extends ArrayAdapter<T> {
    private LayoutInflater inflater;
    private Context context;
    private int resource;
    private List<T> objects;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.resource, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView textView = (TextView) view.findViewById(R.id.text);
        ListView listView = (ListView) ((Activity) context).findViewById(R.id.listView);
        SparseBooleanArray parsedList = listView.getCheckedItemPositions();

        T object = objects.get(position);

        textView.setText(object.toString());
        textView.setTextSize(36);

        if(parsedList.size() > 0) {
            if(position == parsedList.keyAt(position) && parsedList.get(position) == true) {
                imageView.setImageResource(R.drawable.yes);
            } else {
                imageView.setImageResource(R.drawable.no);
            }
        } else {
            imageView.setImageResource(R.drawable.yes);
        }

        return view;
    }
}
