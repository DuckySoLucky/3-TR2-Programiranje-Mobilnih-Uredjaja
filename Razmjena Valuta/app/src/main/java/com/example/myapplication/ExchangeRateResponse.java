package com.example.myapplication;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ExchangeRateResponse {
    @SerializedName("conversion_rates")
    private Map<String, Double> rates;

    public double getRate(String toCurrency) {
        Double rate = rates.get(toCurrency);

        return rate != null ? rate : 0.0;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}