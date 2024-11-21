package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public boolean updateCourse(String id, Course course){
        for (int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getID().equalsIgnoreCase(id)){
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String id){
        for(Course course : courses){
            if(course.getID().equalsIgnoreCase(id)){
                courses.remove(course);
                return true;
            }
        }
        return false;
    }

    public boolean isCourseFull(String id){
        for(Course course : courses){
            if(course.getID().equalsIgnoreCase(id)){
                if(course.getNumberOfStudents()==40){
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Course> getSameMajorCourses(String major){
        ArrayList<Course> sameMajorCourses = new ArrayList<>();
        for(Course course : courses){
            if(course.getCourseRelatedMajor().equalsIgnoreCase(major)){
                sameMajorCourses.add(course);
            }
        }
        return sameMajorCourses;
    }

    public boolean isAvailableCourse(String id){
        for(Course course : courses){
            if(course.getID().equalsIgnoreCase(id)){
                if(course.getNumberOfStudents() < 40)
                    return true;
            }
        }
        return false;
    }
}
