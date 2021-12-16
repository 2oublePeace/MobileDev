package com.emiryan.mobiledev.utils;

import android.content.Context;

import com.emiryan.mobiledev.R;
import com.emiryan.mobiledev.entities.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONparser {
    public static List<Student> loadData(Context context) {
        List<Student> listStudents = new ArrayList<>();

        try {
            String jsonText = readJSON(context, R.raw.students);
            JSONObject jsonRoot = new JSONObject(jsonText);
            JSONArray arrayStudents = jsonRoot.getJSONArray("root");

            for(int i = 0; i < arrayStudents.length(); i++) {
                listStudents.add(Student.builder()
                        .name((String) ((JSONObject) arrayStudents.get(i)).get("name"))
                        .age((int) ((JSONObject) arrayStudents.get(i)).get("age"))
                        .checked((boolean) ((JSONObject) arrayStudents.get(i)).get("checked"))
                        .build());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return listStudents;
    }

    private static String readJSON(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s = null;

        while((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }

        return sb.toString();
    }
}
