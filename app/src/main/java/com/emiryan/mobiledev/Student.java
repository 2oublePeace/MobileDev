package com.emiryan.mobiledev;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private boolean checked;

    public Student(String name, int age, boolean checked) {
        this.name = name;
        this.age = age;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
