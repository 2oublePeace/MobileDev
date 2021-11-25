package com.emiryan.mobiledev.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private String name;
    private int age;
    private boolean checked;
}
