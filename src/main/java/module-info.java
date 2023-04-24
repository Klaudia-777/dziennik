module com.example.dziennik {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.dziennik to javafx.fxml;
    opens com.example.model to javafx.base;
    exports com.example.dziennik;
}