package com.emiryan.mobiledev.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.FragmentResultListener;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.emiryan.mobiledev.R;
import com.emiryan.mobiledev.SettingsActivity;
import com.emiryan.mobiledev.utils.DBparser;
import com.emiryan.mobiledev.utils.MyFormatParser;
import com.emiryan.mobiledev.utils.ServiceLocator;
import com.emiryan.mobiledev.entities.Student;
import com.emiryan.mobiledev.utils.JSONparser;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Student> listStudents;
    boolean enabledDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        enabledDB = prefs.getBoolean("enabled", false);
        List<String> listTemp = new LinkedList<>();

        if(enabledDB) {
            LoadDBData();
        } else {
            LoadData();
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView, ListFragment.class, null)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(enabledDB) {
            DBparser.saveData(this, ServiceLocator.getInstance().getListStudents());
        } else {
            MyFormatParser.saveData(this, ServiceLocator.getInstance().getListStudents());
        }
    }

    public void buttonAdd(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, AddFragment.class, null)
                .commit();

        getSupportFragmentManager().setFragmentResultListener("studentKey", this, (requestKey, bundle) -> {
            Student student = (Student) bundle.getSerializable("studentKey");
            listStudents.add(student);
            ServiceLocator.getInstance().setListStudents(listStudents);
        });
    }

    public void buttonDelete(View view) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, DeleteFragment.class, null)
                .commit();

        getSupportFragmentManager().setFragmentResultListener("studentKey", this, (requestKey, bundle) -> {
            Student student = (Student) bundle.getSerializable("studentKey");
            listStudents.add(student);
            ServiceLocator.getInstance().setListStudents(listStudents);
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
        new Thread(() -> {
            ServiceLocator.getInstance().setListStudents(MyFormatParser.loadData(this));
            listStudents = ServiceLocator.getInstance().getListStudents();
        }).start();
    }

    private void LoadDBData() {
        ServiceLocator.getInstance().setListStudents(DBparser.loadData(this));
        listStudents = ServiceLocator.getInstance().getListStudents();
    }

    public void LoadJSONData(View view) {
        new Handler().post(() -> {
            ServiceLocator.getInstance().setListStudents(JSONparser.loadData(this));
            listStudents = ServiceLocator.getInstance().getListStudents();
            getSupportFragmentManager().setFragmentResult("listStudentsKey", new Bundle());
        });
    }

    public void setPreferences(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}