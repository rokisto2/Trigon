package com.example.kwork2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerAuthor {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void back(ActionEvent event) {//При нажатии кнопки мы закрываем окно и показываем окно с калькулятором
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
        Stage mainWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Application.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 975, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainWindow.setTitle("Main");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    @FXML
    void initialize() {

    }

}
