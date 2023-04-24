package com.example.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student implements Comparable<Student> {
    private String id;
    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty studentCondition;
    private SimpleIntegerProperty year;
    private SimpleIntegerProperty np;
    private HashMap<String, List<Double>> allPoints = new HashMap<>();
    private HashMap<String, StudentCondition> groupStatuses = new HashMap<>();

    public String getId() {
        return id;
    }


    public Student(String id, String name, String surname, int year, int np) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.year = new SimpleIntegerProperty(year);
        this.np = new SimpleIntegerProperty(np);
    }

    @Override
    public int compareTo(Student student) {
        return this.surname.getValue().compareTo(student.getSurname());
    }


    public void setStudentCondition(StudentCondition studentCondition) {
        this.studentCondition = new SimpleStringProperty(studentCondition.toString());
    }

    public void setAllPoints(HashMap<String, List<Double>> allPoints) {
        this.allPoints = allPoints;
    }

    public String getName() {
        return name.getValue();
    }

    public String getSurname() {
        return surname.getValue();
    }

    public StudentCondition getStudentCondition() {
        return StudentCondition.valueOf(studentCondition.getValue());
    }

    public int getYear() {
        return year.getValue();
    }

    public int getNp() {
        return np.getValue();
    }

    public HashMap<String, List<Double>> getAllPoints() {
        return allPoints;
    }

    public List<Double> getPointsForClass(String className) {
        return allPoints.getOrDefault(className, new ArrayList<>());
    }

    public void joinGroup(String className) {
        ClassContainer.classMap.get(className).getStudentsList().add(this);

        this.allPoints.putIfAbsent(className, new ArrayList<>());
        setGroupStatus(className, StudentCondition.oczekujący);
    }

    public void leaveGroup(String className) {
        setGroupStatus(className, StudentCondition.oczekujący);
    }

    public double getAverage(String className) {
        return getPointsForClass(className).stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public void setPointsForClass(String value, List<Double> points) {
        allPoints.putIfAbsent(value, new ArrayList<>());
        allPoints.get(value).addAll(points);
    }

    public double getAverageOfAllSubjects() {
        double sum = 0;
        double counter = 0;
        for (var item : getAllPoints().entrySet()) {
            if (!getGroupStatuses().get(item.getKey()).equals(StudentCondition.oczekujący)) {
                sum += getAverage(item.getKey());
                counter++;
            }
        }
        return sum / counter;
    }

    public HashMap<String, StudentCondition> getGroupStatuses() {
        return groupStatuses;
    }


    public void setGroupStatus(String groupName, StudentCondition studentCondition) {
        if (this.groupStatuses.containsKey(groupName)) {

            if (studentCondition.equals(StudentCondition.wypisany)) {
                ClassContainer.classMap.get(groupName).getStudentsList().remove(this);
                this.allPoints.remove(groupName);
            }
            StudentCondition oldValue = this.groupStatuses.get(groupName);
            this.groupStatuses.replace(groupName, oldValue, studentCondition);
        } else {
            this.groupStatuses.put(groupName, studentCondition);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public void setStudentCondition(String studentCondition) {
        this.studentCondition.set(studentCondition);
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public void setNp(int np) {
        this.np.set(np);
    }

    public void setGroupStatuses(HashMap<String, StudentCondition> groupStatuses) {
        this.groupStatuses = groupStatuses;
    }
}