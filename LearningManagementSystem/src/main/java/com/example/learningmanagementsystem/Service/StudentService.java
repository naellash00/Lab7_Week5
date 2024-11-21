package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents(){
        return students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public boolean updateStudent(String id, Student student){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getStudentID().equals(id)){
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id){
        for(Student student : students){
            if(student.getStudentID().equals(id)){
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public boolean enrollStudentInCourse(String id){
        for(Student student : students){
            if(student.getStudentID().equals(id)){
                student.setEnrolled(true);
                return true;
            }
        }
        return false;
    }

    public boolean addAbsence(String id){
        for(Student student : students){
            if(student.getStudentID().equals(id)){
                student.setNumberOfAbsence(student.getNumberOfAbsence()+1);
                return true;
            }
        }
        return false;
    }

    public String passedCourse(String id){
        for(Student student : students){
            if(student.getStudentID().equals(id)){
                if(student.getCourseGrade()>=60){
                    return "passed";
                }
                return "failed";
            }
        }
        return "not found";
    }
}
