package com.example.dziennik;

import com.example.model.ClassContainer;
import com.example.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StudentViewAfterLogin implements Initializable {

    private Student student;

    @FXML
    private Text allMyClassesString;
    @FXML
    private Button button;
    @FXML
    private Button confirmButton;
    @FXML
    private Text myClassesString;
    @FXML
    private ComboBox<String> comboBoxData1;
    @FXML
    private Text joinClassesString;
    @FXML
    private ComboBox<String> comboBoxData2;
    @FXML
    private Button joinGroupButton;

    @FXML
    private Text leaveClassesString;
    @FXML
    private ComboBox<String> comboBoxData3;
    @FXML
    private Button leaveGroupButton;

    @FXML
    private Button logout;


    Tooltip tooltip1 = new Tooltip("");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> myClasses = FXCollections.observableArrayList(
                student.getAllPoints().keySet()
        );

        ObservableList<String> joinClasses = FXCollections.observableArrayList(
                ClassContainer.classMap.keySet().stream().filter(n -> !student.getAllPoints().keySet().contains(n)).collect(Collectors.toSet())
        );

        allMyClassesString.setText("Wyświetl wszystkie moje przedmioty");
        button.setText("Pokaż");

        EventHandler<ActionEvent> buttonevent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    showStudentGroupsView(student.getAllPoints(), e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };

        button.setOnAction(buttonevent);

        myClassesString.setText("Wyświetl dane dotyczące przedmiotu:");

        myClasses.add("Dowolny");

        EventHandler<ActionEvent> comboBoxEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String avg = String.valueOf(student.getAverage(comboBoxData1.getValue()));
                tooltip1.setText(avg);
                comboBoxData1.setTooltip(tooltip1);
            }
        };
        comboBoxData1.setItems(myClasses);
        comboBoxData1.setOnAction(comboBoxEvent);

        EventHandler<ActionEvent> confirmButtonEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    showConcreteGroup(comboBoxData1.getValue(), student, e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        confirmButton.setText("Pokaż");
        confirmButton.setOnAction(confirmButtonEvent);

        joinClassesString.setText("Zapisz się na przedmiot:");
        comboBoxData2.setItems(joinClasses);

        EventHandler<ActionEvent> joinGroupButtonEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                joinGroup(comboBoxData2.getValue(), student);
            }
        };
        joinGroupButton.setText("Zapisz się");
        joinGroupButton.setOnAction(joinGroupButtonEvent);


        leaveClassesString.setText("Wypisz się z przedmiotu:");
        comboBoxData3.setItems(myClasses);

        EventHandler<ActionEvent> leaveGroupButtonEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                leaveGroup(comboBoxData3.getValue(), student);
            }
        };
        leaveGroupButton.setText("Wypisz się");
        leaveGroupButton.setOnAction(leaveGroupButtonEvent);


        EventHandler<ActionEvent> logoutbuttonevent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    userLogOut(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };

        logout.setText("Wyloguj się");
        logout.setOnAction(logoutbuttonevent);

    }

    private void joinGroup(String value, Student student) {
        student.joinGroup(value);
    }

    private void leaveGroup(String value, Student student) {
        student.leaveGroup(value);
    }

    public void setData(Student student) {
        this.student = student;
    }

    @FXML
    private void showConcreteGroup(String value, Student student, ActionEvent e) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("showConcreteGroupStudent.fxml"));

        ShowConcreteGroupStudent controller = new ShowConcreteGroupStudent();
        controller.setData(value, student);
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showStudentGroupsView(HashMap<String, List<Double>> item, ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("showGroupsStudent.fxml"));

        ShowGroupsStudent controller = new ShowGroupsStudent();
        controller.setData(item, student);
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void userLogOut(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
