package com.example.lab7.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Instructor {
    private int id;
    private String name;
    private String email;
//    private List<Course> courses;

}
