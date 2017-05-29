package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import model.DateAxis;

import java.util.Date;

/**
 * Created by staho on 23.05.2017.
 */
public class ChartController {

    @FXML
    private LineChart<Date, Number> lineChart;

    private final DateAxis xAxis = new DateAxis();
    private final NumberAxis yAxis = new NumberAxis();

    @FXML
    public void initialize(){
        xAxis.setLabel("Date");
    }

    public LineChart<Date, Number> getLineChart(){
        return lineChart;
    }


}
