module com.svalero.practicasfeb {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires javafx.base;
    requires com.fasterxml.jackson.databind;

    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.svalero.practicasfeb.controller to javafx.fxml;
    opens com.svalero.practicasfeb to javafx.fxml;
    opens com.svalero.practicasfeb.model to com.fasterxml.jackson.databind, javafx.base;
    exports com.svalero.practicasfeb;
}