package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Created by staho on 23.05.2017.
 */
public class ChartController {

    @FXML
    private LineChart lineChart;

    private XYChart.Series series;

    private final CategoryAxis xAxis = new CategoryAxis();
    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize(){
        xAxis.setLabel("Date");
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0.23);
        yAxis.setUpperBound(0.24);
        series = new XYChart.Series();
        series.setName("EUR - PLN");

        lineChart.getData().add(series);

    }

    public LineChart getLineChart(){
        return lineChart;
    }
    public XYChart.Series getSeries(){
        return series;
    }

}
