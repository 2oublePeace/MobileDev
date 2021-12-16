package com.emiryan.mobiledev.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.emiryan.mobiledev.db.DatabaseHelper;
import com.emiryan.mobiledev.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class DBparser {

    public static List<Student> loadData(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context.getApplicationContext());
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);

        List<Student> students = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            students.add(Student.builder()
                    .name(cursor.getString(1))
                    .age(cursor.getInt(2))
                    .checked(Boolean.parseBoolean(cursor.getString(3)))
                    .build());

            cursor.moveToNext();
        }
        cursor.close();

        return students;
    }

    public static void saveData(Context context, List<Student> students) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context.getApplicationContext());
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        db.execSQL("delete from " + DatabaseHelper.TABLE);

        for(Student student : students) {
            db.execSQL(
                "INSERT INTO " + DatabaseHelper.TABLE + " (" +
                    DatabaseHelper.COLUMN_NAME + ", " +
                    DatabaseHelper.COLUMN_AGE  + ", " +
                    DatabaseHelper.COLUMN_CHECKED +
                ") " +
                "VALUES ('" + student.getName() +
                    "', " + student.getAge()+
                    ", '" + student.isChecked() +
                "');"
            );
        }
    }
}
