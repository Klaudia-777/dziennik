package com.example.model;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private SimpleStringProperty groupName;
    private List<Student> studentsList = new ArrayList<>();
    private int limit;

    public Class(String groupName, List<Student> studentsList, int limit) {
        this.groupName = new SimpleStringProperty(groupName);
        this.studentsList = studentsList;
        this.limit = limit;

        for (var s : this.studentsList) {
            s.setGroupStatus(this.groupName.getValue(), StudentCondition.przepisany);
        }
    }

    public void addPointsToStudent(String studentId, List<Double> points) {
        for (var s : studentsList) {
            if (s.getId().equals(studentId)) {
                s.setPointsForClass(groupName.getValue(), points);
            }
        }

    }

    public Class(String nameGroup, int limitGroup) {
        this.groupName = new SimpleStringProperty(nameGroup);
        this.limit = limitGroup;
    }

    public void addStudent(Student student) {

        if (studentsList.size() < limit) {
            if (!studentsList.contains(student)) {
                studentsList.add(student);
            } else {
                System.out.println("Student already exist!");
            }
        } else {
            System.err.println("Can not add student!");
            System.out.println("Can not add student!");
        }
    }

    public void removeStudent(Student student) {
        studentsList.removeIf(s -> s.getSurname().equals(student.getSurname()) && s.getName().equals(student.getName()) && s.getYear() == student.getYear());
    }

    public void changeCondition(Student student, StudentCondition studentCondition) {
        student.setStudentCondition(studentCondition);
    }


    public Student search(String surname) {
        for (int j = 0; j < studentsList.size(); j++) {

            String studentSurname = studentsList.get(j).getSurname();
            if (studentSurname.compareTo(surname) == 0) {
                return studentsList.get(j);
            }
        }
        return null;
    }

    public List<Student> searchPartial(String str) {
        List<Student> matchingStudents = new ArrayList<>();
        for (int i = 0; i < studentsList.size(); i++) {
            String studentSurname = studentsList.get(i).getSurname();
            if (studentSurname.contains(str)) {
                matchingStudents.add(studentsList.get(i));
            }
        }
        return matchingStudents;
    }

    public int countByCondition(StudentCondition studentCondition) {
        int counter = 0;
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getStudentCondition().equals(studentCondition)) {


                counter = counter + 1;
            }
        }
        return counter;
    }


    public List<Student> sortByName() {
        List<Student> sortedList = studentsList;
        sortedList.sort(Student::compareTo);
        return sortedList;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public int getLimit() {
        return limit;
    }

    public String getGroupName() {
        return groupName.getValue();
    }
}
