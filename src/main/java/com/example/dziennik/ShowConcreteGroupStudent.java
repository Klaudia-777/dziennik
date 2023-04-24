package com.example.dziennik;

import com.example.model.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ShowConcreteGroupStudent implements Initializable {

    @FXML
    private Text groupName;

    @FXML
    private Text grades;

    @FXML
    private Text average;

    @FXML
    private Text studentCondition;

    private String className;
    private String avg;
    private List<Double> gradesList = new ArrayList<>();
    private String studentState;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (className.equals("Dowolny")) {
            groupName.setText("");
            grades.setText("");
            average.setText("Średnia ocen ze wszystkich przedmiotów: " + avg);
            studentCondition.setText("");
        } else {
            groupName.setText("Przedmiot: " + className);
            String gradesString = gradesList.stream().map(String::valueOf).collect(Collectors.joining(", ")).toString();
            grades.setText("Oceny: " + gradesString);
            average.setText("Średnia ocen: " + avg);
            studentCondition.setText("Stan studenta: " + studentState);
        }
    }

    public void setData(String groupName, Student student) {
        if (groupName.equals("Dowolny")) {
            this.className = groupName;
            this.avg = String.valueOf(student.getAverageOfAllSubjects());
        } else {
            this.className = groupName;
            this.gradesList = student.getPointsForClass(groupName);
            this.avg = String.valueOf(student.getAverage(groupName));
            this.studentState = String.valueOf(student.getGroupStatuses().get(groupName));
        }
    }
}
