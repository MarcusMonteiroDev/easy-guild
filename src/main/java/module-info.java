module com.example {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    exports com.example.models;

    opens com.example.controllers to javafx.fxml;
    opens com.example to javafx.fxml;
    opens com.example.models to com.fasterxml.jackson.databind;
    exports com.example;
}
