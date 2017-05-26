package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

import java.util.Date;

/**
 * Created by staho on 23.05.2017.
 */
public class DataDownloader extends Task{

    private ObservableList<XYChart.Series<Date, Number>> series = FXCollections.observableArrayList();

    @Override
    public Object call() throws Exception{

        return series;
    }
}
