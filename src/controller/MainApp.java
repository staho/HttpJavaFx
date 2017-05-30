package controller;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by staho on 23.05.2017.
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private DatePickerController dateController;
    private ChartController chartController;

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
            dateController.setMainApp(this);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadLineChartLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/lineChartLayout.fxml"));

            LineChart<String, Double> lineChart = loader.load();
            rootLayout.setCenter(lineChart);
            chartController = loader.getController();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void handleCheck(LocalDate begin, LocalDate end){
        //LineChart<String, Double> lineChart = chartController.getLineChart();

        XYChart.Series series = chartController.getSeries();

        final DrawerTask drawerTask = new DrawerTask(series, begin, end);

        new Thread(drawerTask).start();
        drawerTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("Drawing task succed");
            }
        });
    }

    public static void main(String[] args){
        launch(args);
    }
}

