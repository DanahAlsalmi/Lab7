package com.example.lab7.Controllers;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Model.Instructor;
import com.example.lab7.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping("/get")
    public ResponseEntity getInstructor(){
        return ResponseEntity.status(200).body(instructorService.getInstructors());
    }

    @PostMapping("/add")
    public ResponseEntity addInstructor(@Valid @RequestBody Instructor instructor, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        instructorService.addInstructor(instructor);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added instructor"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInstructor(@PathVariable int id, @Valid @RequestBody Instructor instructor,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdated = instructorService.updateInstructor(id, instructor);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Successfully updated instructor"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Failed to update instructor"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInstructor(@PathVariable int id){
        boolean isDeleted = instructorService.deleteInstructor(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted instructor"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Failed to delete instructor"));
    }

    // Get an instructor by ID
    @GetMapping("/{id}")
    public ResponseEntity getInstructorById(@PathVariable int id) {
        return ResponseEntity.status(200).body(instructorService.getInstructorById(id));
    }

    // Get instructors by name
    @GetMapping("/name/{name}")
    public ResponseEntity getInstructorsByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(instructorService.getInstructorsByName(name));
    }

    // Get the total number of instructors
    @GetMapping("/count")
    public ResponseEntity getNumberOfInstructors() {
        return ResponseEntity.status(200).body("Numbers od instructor : "+instructorService.getNumberOfInstructors());
    }

    // Clear all instructors
    @DeleteMapping("/clear")
    public ResponseEntity clearAllInstructors() {
        if (instructorService.clearAllInstructors()){
            return ResponseEntity.status(200).body(new ApiResponse("Successfully cleared instructors"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Failed to clear instructors"));
    }

}
