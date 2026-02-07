module com.svalero.practicasfeb {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires javafx.base;

    opens com.svalero.practicasfeb.controller to javafx.fxml;
    opens com.svalero.practicasfeb.model to javafx.base;

    opens com.svalero.practicasfeb to javafx.fxml;
    exports com.svalero.practicasfeb;
}