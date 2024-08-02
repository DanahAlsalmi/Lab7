package com.example.lab7.Controllers;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Model.Student;
import com.example.lab7.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getStudent(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable int id,@Valid @RequestBody Student student, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = studentService.updateStudent(id, student);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));

        }
        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id){
        boolean isDeleted = studentService.deleteStudent(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }


    // Get a student by ID
    @GetMapping("/{id}")
    public ResponseEntity getStudentById(@PathVariable int id) {
        return ResponseEntity.status(200).body(studentService.getStudentById(id));
    }

    // Get students by last name
    @GetMapping("/name/{name}")
    public ResponseEntity getStudentsByName(@PathVariable String name) {
        if(studentService.getStudentsByName(name).isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
        }
        return ResponseEntity.status(200).body(studentService.getStudentsByName(name));
    }

    // Get the total number of students
    @GetMapping("/count")
    public ResponseEntity getNumberOfStudents() {
        return ResponseEntity.status(200).body("Number of student : "+studentService.getNumberOfStudents());
    }

    // Clear all students
    @DeleteMapping("/clear")
    public ResponseEntity clearAllStudents() {
        if(studentService.clearAllStudents()){
            return ResponseEntity.status(200).body(new ApiResponse("Student clear successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));

    }

    @PutMapping("/status/{id}")
    public ResponseEntity updateStudentStatus(@PathVariable int id){
        return ResponseEntity.status(200).body(studentService.updateStudentStatus(id));
    }
}
