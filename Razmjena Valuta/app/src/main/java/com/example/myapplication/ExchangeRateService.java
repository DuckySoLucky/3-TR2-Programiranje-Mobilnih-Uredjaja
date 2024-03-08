package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ExchangeRateService {
    @GET("/v6/4dd05d489d0f69bc46eba70e/latest/{fromCurrency}")
    Call<ExchangeRateResponse> getExchangeRate(@Path("fromCurrency") String fromCurrency);
}