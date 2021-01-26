package com.fundsaccess.ForeignExchange.pojo;

import java.util.ArrayList;
import java.util.List;

public class AvailableCurrencyDTO {

    List<String> availableCurrencies = new ArrayList<>();

    public List<String> getAvailableCurrency() {
        return availableCurrencies;
    }

    public void setAvailableCurrency(List<String> availableCurrencies) {
        this.availableCurrencies = availableCurrencies;
    }

    public void addAvailableCurrency(String availableCurrency){
        this.availableCurrencies.add(availableCurrency);
    }
}
