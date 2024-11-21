package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Professor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProfessorService {
    ArrayList<Professor> professors = new ArrayList<>();

    public ArrayList<Professor> getProfessors(){
        return professors;
    }

    public void addProfessor(Professor professor){
        professors.add(professor);
    }
    public boolean updateProfessor(String id, Professor professor){
        for (int i = 0; i < professors.size(); i++) {
            if(professors.get(i).getId().equals(id)){
                professors.set(i, professor);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProfessor(String id){
        for(Professor p : professors){
            if(p.getId().equals(id)){
                professors.remove(p);
                return true;
            }
        }
        return false;
    }


    // assign to department
    public void assignToDepartment(String id, String dep){
        for (int i = 0; i <professors.size() ; i++) {
            if(professors.get(i).getId().equals(id)){
                professors.get(i).setDepartment(dep);
            }
        }
    }
    // get by years of experince
    public ArrayList<Professor> getSameYears(int years){
        ArrayList<Professor> sameyears = new ArrayList<>();
        for(Professor p : professors){
            if(p.getYearsOfExperience() == years){
                sameyears.add(p);
            }
        }
        return sameyears;
    }
    // get by same major
    public ArrayList<Professor> getSameDep(String dep){
        ArrayList<Professor> samedep = new ArrayList<>();
        for(Professor p : professors){
            if(p.getDepartment().equalsIgnoreCase(dep)){
                samedep.add(p);
            }
        }
        return samedep;
    }
}
