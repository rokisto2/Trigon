package com.example.kwork2;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerMain {

    @FXML
    private Label area;

    @FXML
    private Label bigRadius;

    @FXML
    private Label error;


    @FXML
    private Label medianaA;

    @FXML
    private Label medianaB;

    @FXML
    private Label medianaC;

    @FXML
    private TextField sizeA;

    @FXML
    private Label sizeAAnswer;

    @FXML
    private TextField sizeB;

    @FXML
    private Label sizeBAnswer;

    @FXML
    private TextField sizeC;

    @FXML
    private Label sizeCAnswer;

    @FXML
    private Label smallRadius;

    @FXML
    private TextField angleA;

    @FXML
    private Label angleAAnswer;

    @FXML
    private TextField angleB;

    @FXML
    private Label angleBAnswer;

    @FXML
    private TextField angleG;

    @FXML
    private Label angleGAnswer;

    @FXML
    void closeWindow(ActionEvent event) {//При нажатии кнопки мы закрываем окно
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void showAuthorWindow(ActionEvent event) {//При нажатии кнопки мы закрываем окно и показываем окно с информацией об авторе
        Stage authorWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("AboutAuthor.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        authorWindow.setTitle("Об авторе");
        authorWindow.setScene(scene);
        authorWindow.show();
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void clearData(ActionEvent event) {//При нажатии кнопки мы очищаем все поля
        sizeAAnswer.setText("");
        sizeBAnswer.setText("");
        sizeCAnswer.setText("");
        angleAAnswer.setText("");
        angleBAnswer.setText("");
        angleGAnswer.setText("");
        medianaA.setText("");
        medianaB.setText("");
        medianaC.setText("");
        area.setText("");
        smallRadius.setText("");
        bigRadius.setText("");
        error.setText("");
        sizeA.setText("");
        sizeB.setText("");
        sizeC.setText("");
        angleA.setText("");
        angleB.setText("");
        angleG.setText("");
    }
    @FXML
    void computeTrigon(ActionEvent event)
    {//При нажатии кнопки мы рассчитываем данные в треугольнике
        double a = getSize(sizeA.getText().trim());
        double b = getSize(sizeB.getText().trim());
        double c = getSize(sizeC.getText().trim());
        double alpha = getAngle(angleA.getText().trim());
        double beta = getAngle(angleB.getText().trim());
        double gamma = getAngle(angleG.getText().trim());
        boolean errorControl = false;//требуется для проверки правильности ввода
        //Проверка на правильность ввода
        if (a==-100 || b==-100 || c == -100 || alpha == -100 || beta == -100 || gamma == -100)
        {
            errorControl =true;
            error.setText("Неправельный ввод данных");
        }

        Trigon trigon = new Trigon(a,b,c,Math.toRadians(alpha),Math.toRadians(beta),Math.toRadians(gamma));
        // Создаем переменную для понимания что нам оптимально использовать
        double v = (a + b + c) - (Math.abs(a) + Math.abs(b) + Math.abs(c));
        if (Math.abs(v)== 6 )
        {
            error.setText("Не хватает данных для рассчёта");
            errorControl = true;
        }
        //создаём переменную для проверки нулевого угла
        double maxAngle = Math.max(alpha, Math.max(beta, gamma));
        //Проверка на то чтобы использовать 3 блок формул
        if (Math.abs(v) == 4 )
        {
            if(alpha+beta+gamma- maxAngle !=0)
                trigon = Convector.computeTrigon3(a, b, c, Math.toRadians(alpha), Math.toRadians(beta), Math.toRadians(gamma));
            else
            {
                error.setText("Не хватает данных для рассчёта");
                errorControl = true;
            }

        }
        //Проверка на то чтобы использовать 1 блок формул
        if (Math.abs(v) == 2 )
        {
            if(maxAngle !=0)
                trigon = Convector.computeTrigon1(a,b,c,Math.toRadians(alpha), Math.toRadians(beta), Math.toRadians(gamma));
            else
            {
                error.setText("Не хватает данных для рассчёта");
                errorControl = true;
            }
        }
        //Проверка на то чтобы использовать 2 блок формул
        if (Math.abs(v) == 0 )
        {
            trigon = Convector.computeTrigon2(a,b,c,Math.toRadians(alpha), Math.toRadians(beta), Math.toRadians(gamma));
        }
        //Проверка на существования треугольника
        if(Math.toDegrees(trigon.alpha) ==180|| Math.toDegrees(trigon.beta) ==180 || Math.toDegrees(trigon.gamma) ==180 ){
            errorControl = true;
            error.setText("Такого треугольник нет");
        }
        if (errorControl == false)
        {
            printData(trigon);
        }
    }
    @FXML
    void showAboutProgrammWindow(ActionEvent event)//При нажатии кнопки мы закрываем окно и показываем окно с информацией о программе
    {
        Stage authorWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("AboutProgramm.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        authorWindow.setTitle("О программе");
        authorWindow.setScene(scene);
        authorWindow.show();
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public static double getSize(String data) {
        double size;
        //если поле пустое то в начальное значение длины запишем -1
        if (data.equals(""))
        {
            size = -1;
        }
        else
        {
            //если в поле неправельны добавлены данные то в начальное значение длины запишем -100
            //иначе длину которую ввели
            try {
                size = Double.parseDouble(data);
                if (size < 1)
                {
                    size = -100;
                }
            } catch(Exception e)  {
                size = -100;
            }
        }
        return size;
    }
    public static double getAngle(String data) {//полность анологично как и в функциии getSize, только здесь вместо -1 запишем 0
        double size;
        if (data.equals(""))
        {
            size = 0;
        }
        else
        {
            try {
                size = Double.parseDouble(data);
            } catch(Exception e)  {
                size = -100;
            }
        }
        return size;
    }
    public void printData(Trigon trigon)//Передаём в программу все рассчитанные данные
    {
        trigon.median_calculation();
        trigon.area_calculation();
        trigon.radius_calculation();
        sizeAAnswer.setText("Сторона A = " + String.format("%.1f",trigon.a));
        sizeBAnswer.setText("Сторона B = " + String.format("%.1f",trigon.b));
        sizeCAnswer.setText("Сторона C = " + String.format("%.1f",trigon.c));
        angleAAnswer.setText("Угол α = " + String.format("%.1f",Math.toDegrees(trigon.alpha)));
        angleBAnswer.setText("Угол β = " + String.format("%.1f",Math.toDegrees(trigon.beta)));
        angleGAnswer.setText("Угол γ = " + String.format("%.1f",Math.toDegrees(trigon.gamma)));
        medianaA.setText("Медиана с онсование A = " + String.format("%.1f",trigon.medianA));
        medianaB.setText("Медиана с онсование B = " + String.format("%.1f",trigon.medianB));
        medianaC.setText("Медиана с онсование C = " + String.format("%.1f",trigon.medianC));
        area.setText("Площадь = " + String.format("%.1f",trigon.area));
        smallRadius.setText("Радиус вписанной = " + String.format("%.1f",trigon.smallRadius));
        bigRadius.setText("Радиус описанной = " + String.format("%.1f",trigon.bigRadius));
        error.setText("");
    }
}
