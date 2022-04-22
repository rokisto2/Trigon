module com.example.kwork2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kwork2 to javafx.fxml;
    exports com.example.kwork2;
}