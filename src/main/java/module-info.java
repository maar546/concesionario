module com.svalero.practicasfeb {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires javafx.base;

    opens com.svalero.practicasfeb to javafx.fxml;
    exports com.svalero.practicasfeb;
}