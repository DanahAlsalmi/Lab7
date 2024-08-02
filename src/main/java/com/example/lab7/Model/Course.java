package com.example.lab7.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Course {

    private int id;
    @NotEmpty(message = "Title is required")
    private String title;
    @NotEmpty(message = "Description is required")
    private String description;
//    private List<Student> students=new ArrayList<>();
//    private List<Instructor> instructors=new ArrayList<>();
}
