package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

/**
 * Created by staho on 23.05.2017.
 */
public class DatePickerController {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private Button startButton;

    @FXML
    private void handleCheckClicked(){
        LocalDate begin = datePickerStart.getValue();
        LocalDate end = datePickerEnd.getValue();
        if(begin != null && end != null){
            if(begin.compareTo(end) <= 0){
            System.out.println("Start " + begin);
            System.out.println("End " + end);
            } else {
                LocalDate tmp = begin;
                begin = end;
                end = tmp;
            }
            mainApp.handleCheck(begin, end);
        }
    }
    @FXML
    private void initialize(){

    }
}
