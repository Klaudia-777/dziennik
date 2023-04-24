package com.example.dziennik;

import com.example.model.Class;
import com.example.model.Student;
import com.example.model.StudentCondition;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static javafx.scene.control.cell.TextFieldTableCell.*;

public class ShowStudentsAdmin implements Initializable {

    @FXML
    private TableView<Student> tbData;

    @FXML
    public TableColumn<Student, String> name;
    @FXML
    public TableColumn<Student, String> surname;
    @FXML
    public TableColumn<Student, String> studentCondition;
    @FXML
    public TableColumn<Student, String> year;
    @FXML
    public TableColumn<Student, String> points;
    @FXML
    public TableColumn<Student, String> np;
    @FXML
    private Button logout;

    private String item;


    private ObservableList<Student> studentList = FXCollections.observableArrayList(
    );


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        studentCondition.setCellValueFactory((var) -> new ReadOnlyObjectWrapper(String.valueOf(var.getValue().getGroupStatuses().get(item))));
        year.setCellValueFactory((var) -> new ReadOnlyObjectWrapper(String.valueOf(var.getValue().getYear())));
        points.setCellValueFactory((var) -> new ReadOnlyObjectWrapper(String.valueOf(var.getValue().getPointsForClass(item))));
        np.setCellValueFactory((var) -> new ReadOnlyObjectWrapper(String.valueOf(var.getValue().getNp())));
        tbData.setItems(studentList);
        editableCols();

        tbData.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {

                    try {
                        showStudent();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

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

    private void showStudent() throws IOException {
        Main m = new Main();
        m.changeScene("studentsListAdmin.fxml");
    }

    public void setData(Class item) {
        studentList = FXCollections.observableArrayList(
                item.getStudentsList()
        );
        this.item = item.getGroupName();
    }

    private void editableCols() {
        name.setCellFactory(forTableColumn());
        name.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue()));

        surname.setCellFactory(forTableColumn());
        surname.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setSurname(e.getNewValue()));

        studentCondition.setCellFactory(forTableColumn());
        studentCondition.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setGroupStatus(item, StudentCondition.valueOf(e.getNewValue())));

        year.setCellFactory(forTableColumn());
        year.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setYear(Integer.parseInt(e.getNewValue())));

        points.setCellFactory(forTableColumn());
        points.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setPointsForClass(item, Arrays.stream(e.getNewValue().replace("[", "").replace("]", "").split(",")).map(Double::valueOf).collect(Collectors.toList())));

        np.setCellFactory(forTableColumn());
        np.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setNp(Integer.parseInt(e.getNewValue())));


        /* Allow for the values in each cell to be changable */
        tbData.setEditable(true);
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
