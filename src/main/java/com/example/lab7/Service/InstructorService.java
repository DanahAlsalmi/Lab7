package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import com.example.lab7.Model.Instructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InstructorService {

    ArrayList<Instructor> instructors = new ArrayList<>();


    //Get / Read - done
    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    //Add Instructor - done
    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    // Update Instructor - done
    public boolean updateInstructor(int id ,Instructor instructor) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId() == id) {
                instructors.set(i, instructor);
                return true;
            }
        }
        return false;
    }

    //Delete Course - done
    public boolean deleteInstructor(int id) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId() == id) {
                instructors.remove(i);
                return true;
            }
        }
        return false;
    }

    // Get an instructor by ID
    public Instructor getInstructorById(int id) {
        for (Instructor instructor : instructors) {
            if (instructor.getId() == id) {
                return instructor;
            }
        }
        return null;
    }

    // Get instructors by name
    public ArrayList<Instructor> getInstructorsByName(String name) {
        ArrayList<Instructor> filteredInstructors = new ArrayList<>();
        for (Instructor instructor : instructors) {
            if (instructor.getName().equalsIgnoreCase(name)) {
                filteredInstructors.add(instructor);
            }
        }
        return filteredInstructors;
    }

    // Get number of instructors
    public int getNumberOfInstructors() {
        return instructors.size();
    }

    // Clear all instructors
    public boolean clearAllInstructors() {
        if (instructors.size() > 0) {
            instructors.clear();
            return true;
        }
        return false;
    }


}
