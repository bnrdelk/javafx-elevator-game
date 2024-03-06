module com.example.elevatorgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.elevatorgame to javafx.fxml;
    exports com.example.elevatorgame;
}