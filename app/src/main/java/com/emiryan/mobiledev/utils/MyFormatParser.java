package com.emiryan.mobiledev.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.widget.Toast;

import com.emiryan.mobiledev.entity.Student;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyFormatParser {
    private static final String FILE_NAME = "content.txt";

    public static void saveData(Context context, List<Student> students) {
        String data = "";
        for(Student student : students) {
            data += "name:" + student.getName() + "\n";
            data += "age:" + student.getAge() + "\n";
            data += "checked:" + student.isChecked() + "\n\n";
        }

        writeData(context, data);
    }

    public static void writeData(Context context, String data) {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(data.getBytes());
        }
        catch(IOException ex) {

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

   /* public static String readData(Context context) {
        FileInputStream fin = null;
        String data = null;
        try {
            fin = context.openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            data = new String (bytes);
        }
        catch(IOException ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        return data;
    }

    public static void loadData(Context context) {
        String data = readData(context);
    }*/



    public static List<Student> loadData(Context context) {
        List<Student> students = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(context.openFileInput(FILE_NAME)))) {

            List<String> values = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty()) {
                    values.add(line.split(":")[1]);
                } else {
                    students.add(Student.builder()
                            .name(values.get(0))
                            .age(Integer.parseInt(values.get(1)))
                            .checked(Boolean.parseBoolean(values.get(2)))
                            .build());
                    values.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }
}
