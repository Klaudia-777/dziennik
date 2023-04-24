package com.example.dziennik;

import com.example.model.ClassContainer;
import com.example.model.Student;
import com.example.model.Class;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        primaryStage.setTitle("Dziennik");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        prepareData();
        launch(args);
    }

    static void prepareData() {

        ClassContainer classContainer = new ClassContainer();

        List<Student> studentsGroupA = new ArrayList<>();
        studentsGroupA.add(new Student("1", "Weronika", "Bednarz", 2002, 2));
        studentsGroupA.add(new Student("2", "Emilia", "Myzia", 2002, 2));
        studentsGroupA.add(new Student("3", "Katarzyna", "Koza", 2002, 1));

        Class classA = new Class("Sygnały i systemy", studentsGroupA, 10);
        classA.addPointsToStudent("1", List.of(3.0, 4.0, 5.0));
        classA.addPointsToStudent("2", List.of(3.0, 4.0, 5.0));
        classA.addPointsToStudent("3", List.of(3.0, 4.0, 5.0));
        classContainer.addClass(classA);

        List<Student> studentsGroupB = new ArrayList<>();
        studentsGroupB.add(new Student("4", "Marta", "Tomaszewska", 2003, 0));
        studentsGroupB.add(new Student("5", "Milena", "Tomaszewska", 2003, 1));
        studentsGroupB.add(new Student("6", "Magdalena", "Partyka", 2003, 2));

        Class classB = new Class("Metody numeryczne", studentsGroupB, 10);
        classB.addPointsToStudent("4", List.of(3.0, 4.0, 5.0));
        classB.addPointsToStudent("5", List.of(3.0, 4.0, 5.0));
        classB.addPointsToStudent("6", List.of(3.0, 4.0, 5.0));
        classContainer.addClass(classB);

        List<Student> studentsGroupC = new ArrayList<>();
        studentsGroupC.add(new Student("7", "Gabriela", "Merchut", 2001, 1));
        studentsGroupC.add(new Student("8", "Karolina", "Iskrzycka", 2001, 0));
        studentsGroupC.add(new Student("9", "Justyna", "Kurzeja", 2001, 1));


        Class classC = new Class("Technika cyfrowa", studentsGroupC, 10);
        classC.addPointsToStudent("7", List.of(3.0, 4.0, 5.0));
        classC.addPointsToStudent("8", List.of(3.0, 4.0, 5.0));
        classC.addPointsToStudent("9", List.of(3.0, 4.0, 5.0));
        classContainer.addClass(classC);
    }

}
