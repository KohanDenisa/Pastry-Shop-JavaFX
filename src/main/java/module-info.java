module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.demo2.domain to javafx.fxml;
    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
    exports com.example.demo2.domain;
}