package controller;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Date;

/**
 * Created by staho on 23.05.2017.
 */
public class DrawerTask extends Task {
    private LineChart<Date, Number> lineChart;
    private ObservableList<XYChart.Series<Date, Number>> series;

    @Override
    protected Object call() throws Exception{
        DataDownloader dataDownloaderTask = new DataDownloader();
        dataDownloaderTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                series = (ObservableList<XYChart.Series<Date, Number>>)dataDownloaderTask.getValue();
            }
        });
        return null;
    }

    public DrawerTask(LineChart<Date,Number> lineChart){
        this.lineChart = lineChart;
    }
}
