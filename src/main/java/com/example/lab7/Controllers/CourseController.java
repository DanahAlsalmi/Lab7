package com.example.lab7.Controllers;


import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Model.Course;
import com.example.lab7.Service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;


    //Get - Done
    @GetMapping("/get")
    private ResponseEntity getCourse() {
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    //Add - done
    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        courseService.addCourse(course);
        return ResponseEntity.status(201).body(new ApiResponse("Course added successfully"));
    }

    //Update - done
    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable int id ,@Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdated = courseService.updateCourse(id, course);
        if (isUpdated) {
            return ResponseEntity.status(201).body(new ApiResponse("Course updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }

    //Delete - done
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable int id) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("Course deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }

    // Get courses by name
    @GetMapping("/{title}")
    public ResponseEntity getCoursesByTitle(@PathVariable String title) {
        if (courseService.getCoursesByTitle(title)!=null){
            return ResponseEntity.status(200).body(courseService.getCoursesByTitle(title));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Course not found"));
    }

    // Get the total number of courses
    @GetMapping("/count")
    public ResponseEntity getNumberOfCourses() {
        return ResponseEntity.status(200).body("Number of course: "+courseService.getNumberOfCourses());
    }

    // Clear all courses
    @DeleteMapping("/clear")
    public ResponseEntity clearAllCourses() {
        boolean isDeleted = courseService.clearAllCourses();
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("Course deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Already No courses"));
    }
}
