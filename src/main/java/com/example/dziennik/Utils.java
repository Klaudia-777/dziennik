package com.example.dziennik;

import com.example.model.Class;
import com.example.model.ClassContainer;
import com.example.model.Student;

import java.util.List;

public class Utils {

    //name and surname is temporarily used as username and password
    //assuming name and surname are unique
    public static Student getStudentByNameAndSurname(String name, String surname) {
        List<Class> allClasses = ClassContainer.classMap.values().stream().toList();
        for (var c : allClasses) {
            if (c.getStudentsList().stream().anyMatch(n -> n.getName().equals(name) && n.getSurname().equals(surname))) {
                return c.getStudentsList().stream().filter(n -> n.getName().equals(name) && n.getSurname().equals(surname)).findFirst().get();
            }
        }
        return null;
    }


}
