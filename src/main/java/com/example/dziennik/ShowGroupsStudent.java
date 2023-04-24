package com.example.dziennik;

import com.example.model.Class;
import com.example.model.ClassContainer;
import com.example.model.Student;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ShowGroupsStudent implements Initializable {
    @FXML
    private TableView<Class> tbData;

    @FXML
    public TableColumn<Class, String> groupName;
    @FXML
    public TableColumn<Class, String> avg;
    @FXML
    public TableColumn<Class, String> state;


    private Student student;
    private ObservableList<Class> classes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupName.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        avg.setCellValueFactory((var) -> new ReadOnlyObjectWrapper(String.valueOf(
                var.getValue().getStudentsList()
                        .stream()
                        .filter(n -> n.equals(student)).findFirst().get().getAverage(var.getValue().getGroupName())
        )));
        state.setCellValueFactory((var) -> new ReadOnlyObjectWrapper(String.valueOf(
                var.getValue().getStudentsList()
                        .stream()
                        .filter(n -> n.equals(student)).findFirst().get().getGroupStatuses().get(var.getValue().getGroupName())
        )));

        tbData.setItems(classes);
    }

    public void setData(HashMap<String, List<Double>> item, Student student) {
        List<Class> myClasses = new ArrayList<>();
        for (var a : ClassContainer.classMap.entrySet()) {
            for (var b : item.entrySet()) {
                if (a.getKey().equals(b.getKey())) {
                    myClasses.add(a.getValue());
                }
            }
        }

        this.classes = FXCollections.observableArrayList(myClasses);
        this.student = student;
    }
}
