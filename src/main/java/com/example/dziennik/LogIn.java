package com.example.dziennik;

import com.example.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogIn {

    public LogIn() {

    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin(event);

    }

    private void checkLogin(ActionEvent event) throws IOException {
        Main m = new Main();
        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
            wrongLogIn.setText("Success!");
            m.changeScene("groupListAdmin.fxml");
        } else if (Utils.getStudentByNameAndSurname(username.getText().toString(), password.getText().toString()) != null) {
            Student student = Utils.getStudentByNameAndSurname(username.getText(), password.getText());
            wrongLogIn.setText("Success!");
            showStudentViewAfterLogin(student,event);
        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Please enter your data.");
        } else {
            wrongLogIn.setText("Wrong username or password!");
        }
    }


    @FXML
    private void showStudentViewAfterLogin(Student student, ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentViewAfterLogin.fxml"));

        StudentViewAfterLogin controller = new StudentViewAfterLogin();
        controller.setData(student);
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}