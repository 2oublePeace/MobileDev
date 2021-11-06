package com.emiryan.mobiledev;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServiceLocator {
    private static ServiceLocator instance = null;
    private List<Student> listStudents;

    private ServiceLocator() {
        listStudents = new ArrayList<>();
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    public List<Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }
}
