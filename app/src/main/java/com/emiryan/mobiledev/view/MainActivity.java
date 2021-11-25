package com.emiryan.mobiledev.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.View;

import com.emiryan.mobiledev.R;
import com.emiryan.mobiledev.utils.MyFormatParser;
import com.emiryan.mobiledev.utils.ServiceLocator;
import com.emiryan.mobiledev.entity.Student;
import com.emiryan.mobiledev.utils.JSONparser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Student> listStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadData();
        MyFormatParser.saveData(this, ServiceLocator.getInstance().getListStudents());

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerView, ListFragment.class, null)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyFormatParser.saveData(this, ServiceLocator.getInstance().getListStudents());
    }

    public void buttonAdd(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, AddFragment.class, null)
                .commit();

        getSupportFragmentManager().setFragmentResultListener("studentKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                Student student = (Student) bundle.getSerializable("studentKey");
                listStudents.add(student);
                ServiceLocator.getInstance().setListStudents(listStudents);
            }
        });
    }

    public void buttonDelete(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, DeleteFragment.class, null)
                .commit();

        getSupportFragmentManager().setFragmentResultListener("studentKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                Student student = (Student) bundle.getSerializable("studentKey");
                listStudents.add(student);
                ServiceLocator.getInstance().setListStudents(listStudents);
            }
        });
    }

    public void buttonEdit(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, EditFragment.class, null)
                .commit();
    }

    public void buttonSearch(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, SearchFragment.class, null)
                .commit();
    }

    private void LoadData() {
        ServiceLocator.getInstance().setListStudents(MyFormatParser.loadData(this));
        listStudents = ServiceLocator.getInstance().getListStudents();
    }

    private void LoadJSONData() {
        ServiceLocator.getInstance().setListStudents(JSONparser.loadData(this));
        listStudents = ServiceLocator.getInstance().getListStudents();
    }
}