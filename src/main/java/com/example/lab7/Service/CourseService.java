package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();


    //Get / Read - done
    public ArrayList<Course> getCourses() {
        return courses;
    }

    //Add Course - done
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Update Course - done
    public boolean updateCourse(int id ,Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }

    //Delete Course - done
    public boolean deleteCourse(int id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    // Get Course by ID
    public Course getCourseById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    // Get Courses by title
    public ArrayList<Course> getCoursesByTitle(String title) {
        ArrayList<Course> filteredCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTitle().equalsIgnoreCase(title)) {
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }

    // Get Number of Courses
    public int getNumberOfCourses() {
        return courses.size();
    }

    // Clear All Courses
    public boolean clearAllCourses() {
        if (courses.size() > 0) {
            courses.clear();
            return true;
        }
        return false;
    }
}
