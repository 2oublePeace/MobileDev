package com.emiryan.mobiledev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Student> listStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listStudents = ServiceLocator.getInstance().getListStudents();

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerView, ListFragment.class, null)
                .commit();
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
}