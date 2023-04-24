package com.example.dziennik;

import com.example.model.Class;
import com.example.model.ClassContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowGroupsAdmin implements Initializable {

    @FXML
    private TableView<Class> tbData;

    @FXML
    public TableColumn<Class, String> groupName;


    private ObservableList<Class> classes = FXCollections.observableArrayList(
            ClassContainer.classMap.values()
    );


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        groupName.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        tbData.setItems(classes);

        tbData.setRowFactory(tv -> {
            TableRow<Class> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {

                    try {
                        showStudentsView(row.getItem(), event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML
    private void showStudentsView(Class item, MouseEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentsListAdmin.fxml"));

        ShowStudentsAdmin controller = new ShowStudentsAdmin();
        controller.setData(item);
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
