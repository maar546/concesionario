module com.svalero.practicasfeb {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires javafx.base;
    requires com.fasterxml.jackson.databind;

    opens com.svalero.practicasfeb.controller to javafx.fxml;
    opens com.svalero.practicasfeb to javafx.fxml;
    opens com.svalero.practicasfeb.model to com.fasterxml.jackson.databind;
    exports com.svalero.practicasfeb;
}