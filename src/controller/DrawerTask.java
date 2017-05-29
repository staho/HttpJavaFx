package controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import model.ExchangeRate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by staho on 23.05.2017.
 */
public class DrawerTask extends Task {
    private LineChart<Date, Number> lineChart;
    private XYChart.Series<Date, Number> series;
    private LocalDate begin;
    private LocalDate end;


    @Override
    protected Object call() throws Exception{
        ExchangeRate temp = null;
        DataDownloader dataDownloaderTask = new DataDownloader(begin, end);

        new Thread(dataDownloaderTask).start();

            dataDownloaderTask.valueProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
                ExchangeRate temp1 = (ExchangeRate) dataDownloaderTask.getValue();

                series.getData().add(new XYChart.Data<>(Date.from(temp1.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()), temp1.getRates().get("EUR")));
            }));
        series.getData().add(new XYChart.Data<Date, Number>(new Date(2017, 11, 8), new Number() {
            @Override
            public int intValue() {
                return 5;
            }

            @Override
            public long longValue() {
                return 5;
            }

            @Override
            public float floatValue() {
                return 5;
            }

            @Override
            public double doubleValue() {
                return 5;
            }
        }));
        Platform.runLater(() -> lineChart.getData().add(series));


        return null;
    }

    public DrawerTask(LineChart<Date,Number> lineChart, LocalDate begin, LocalDate end){
        this.lineChart = lineChart;
        this.series = new XYChart.Series<>();
        this.begin = begin;
        this.end = end;
        //this.valueProperty = null;

    }
}
