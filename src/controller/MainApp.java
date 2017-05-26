package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by staho on 23.05.2017.
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private DatePickerController dateController;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Eur - Pln");

        initRootLayout();
        loadDatePickerLayout();
        loadLineChartLayout();

    }

    private void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/rootLayout.fxml"));

            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadDatePickerLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/datePickerLayout.fxml"));

            AnchorPane datePicker = loader.load();
            rootLayout.setLeft(datePicker);

            dateController = loader.getController();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadLineChartLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/lineChartLayout.fxml"));

            LineChart<Number, Number> lineChart = loader.load();
            rootLayout.setCenter(lineChart);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void handleCheck(LocalDate begin, LocalDate end){

    }

    public static void main(String[] args){
        launch(args);
    }
}

