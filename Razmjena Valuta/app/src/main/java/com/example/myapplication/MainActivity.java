package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private Spinner fromCurrencySpinner;
    private Spinner toCurrencySpinner;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText amountEditText;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        Button exchangeButton = findViewById(R.id.exchangeButton);
        resultTextView = findViewById(R.id.resultTextView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v6.exchangerate-api.com/v6/4dd05d489d0f69bc46eba70e/latest/USD/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExchangeRateService service = retrofit.create(ExchangeRateService.class);
        Call<ExchangeRateResponse> call = service.getExchangeRate("USD");

        call.enqueue(new Callback<ExchangeRateResponse>() {
            @Override
            public void onResponse(@NonNull Call<ExchangeRateResponse> call, @NonNull Response<ExchangeRateResponse> response) {
                if (response.isSuccessful()) {
                    ExchangeRateResponse exchangeRateResponse = response.body();
                    assert exchangeRateResponse != null;
                    Map<String, Double> rates = exchangeRateResponse.getRates();
                    ArrayList<String> currencies = new ArrayList<>(rates.keySet());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, currencies);
                    fromCurrencySpinner.setAdapter(adapter);
                    toCurrencySpinner.setAdapter(adapter);
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<ExchangeRateResponse> call, @NonNull Throwable t) {
                resultTextView.setText("Error: " + t.getMessage());
            }
        });

        exchangeButton.setOnClickListener(v -> {
            String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
            String toCurrency = toCurrencySpinner.getSelectedItem().toString();
            double amount = Double.parseDouble(amountEditText.getText().toString());

            Call<ExchangeRateResponse> call1 = service.getExchangeRate(fromCurrency);

            call1.enqueue(new Callback<ExchangeRateResponse>() {
                @Override
                public void onResponse(@NonNull Call<ExchangeRateResponse> call1, @NonNull Response<ExchangeRateResponse> response) {
                    if (response.isSuccessful()) {
                        ExchangeRateResponse exchangeRateResponse = response.body();
                        assert exchangeRateResponse != null;
                        double exchangeRate = exchangeRateResponse.getRate(toCurrency);
                        double result = amount * exchangeRate;
                        resultTextView.setText(String.valueOf(result));
                    }
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFailure(@NonNull Call<ExchangeRateResponse> call1, @NonNull Throwable t) {
                    resultTextView.setText("Error: " + t.getMessage());
                }
            });
        });
    }
}