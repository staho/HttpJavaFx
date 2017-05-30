package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by staho on 23.05.2017.
 */
public class DrawerTask extends Task {
    private XYChart.Series series;
    private LocalDate begin;
    private LocalDate end;


    @Override
    protected Object call() throws Exception{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (LocalDate date = begin; !date.isEqual(end.plusDays(1)); date = date.plusDays(1)) {
            String urlString = "http://api.fixer.io/" + date + "?base=PLN";
            System.out.println("url string: " + urlString);
            URL url = new URL(urlString);

            URLConnection urlCon = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            ObjectMapper mapper = new ObjectMapper();

            ExchangeRate ex = mapper.readValue(in, ExchangeRate.class);
            in.close();
            Platform.runLater(() -> series.getData().add(new XYChart.Data(df.format(ex.getDate()), ex.getRates().get("EUR"))));

        }
        //Platform.runLater(() -> lineChart.getData().add(series));


        return null;
    }

    public DrawerTask(XYChart.Series series, LocalDate begin, LocalDate end){
        this.series = series;
        this.begin = begin;
        this.end = end;
        //this.valueProperty = null;

    }
}
