package com.example.kwork2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerAboutProgramm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();//При нажатии кнопки мы закрываем окно и показываем окно с калькулятором
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


}
