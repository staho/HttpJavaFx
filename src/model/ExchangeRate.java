package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

/**
 * Created by staho on 26.05.2017.
 */
public class ExchangeRate {
    private String base;
    private LocalDate date;
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
