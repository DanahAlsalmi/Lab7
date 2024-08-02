package com.example.lab7.Service;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Model.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<Student> students = new ArrayList<>();

    //Get / Read - done
    public ArrayList<Student> getStudents() {
        return students;
    }

    //Add Student - done
    public void addStudent(Student student) {
        students.add(student);
    }

    // Update Student - done
    public boolean updateStudent(int id ,Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }


    //Delete Course - done
    public boolean deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }


    // Get a student by ID
    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Get students by name
    public ArrayList<Student> getStudentsByName(String name) {
        ArrayList<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    // Get number of students
    public int getNumberOfStudents() {
        return students.size();
    }

    // Clear all students
    public boolean clearAllStudents() {
        if (students.size() > 0) {
            students.clear();
            return true;
        }
        return false;
    }

    //Change status
    public ApiResponse updateStudentStatus(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                if ("Not Completed".equals(student.getStatus())) {
                    student.setStatus("Completed");
                    return new ApiResponse("Student status changed to Completed");
                }else {
                    return new ApiResponse("Student is already Completed.");
                }
            }
        }
        return new ApiResponse("Student with ID " + id + " not found.");
    }
}
