package com.example.blackjackgameapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectBetAmount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bet_amount);

        // Display Logo Bar Code
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Blackjack");


        PlayerRoundInformation round1 = (PlayerRoundInformation) getIntent().getSerializableExtra("roundInfo");
        round1.setBetAmount(0);

        final TextView betAmountTextView = findViewById(R.id.betAmount);
        final Button btnBet5 = findViewById(R.id.btnBet5);
        final Button btnBet10 = findViewById(R.id.btnBet10);
        final Button btnBet20 = findViewById(R.id.btnBet20);
        final Button btnBet50 = findViewById(R.id.btnBet50);
        final Button btnBetAll = findViewById(R.id.btnBetAll);
        final Button btnSubmitBet = findViewById(R.id.btnSubmitBet);
        final Button btnBack = findViewById(R.id.btnBack);

        final TextView remainingAmountTextView = findViewById(R.id.remainingAmount);
        int remainingAmount = round1.getRemainingAmount();
        remainingAmountTextView.setText("€" + remainingAmount);

        if (round1.getRemainingAmount() <= 0) {
            Intent bankrupt = new Intent(SelectBetAmount.this, GameOver.class);
            startActivity(bankrupt);
        }

        btnBet5.setOnClickListener(v -> {
            int betAmount = round1.getBetAmount();
            if (betAmount + 5 <= round1.getRemainingAmount()) {
                betAmount += 5;
                round1.setBetAmount(betAmount);
                betAmountTextView.setText("€" + String.valueOf(betAmount));
            }
        });

        btnBet10.setOnClickListener(v -> {
            int betAmount = round1.getBetAmount();
            if (betAmount + 10 <= round1.getRemainingAmount()) {
                betAmount += 10;
                round1.setBetAmount(betAmount);
                betAmountTextView.setText("€" + String.valueOf(betAmount));
            }
        });

        btnBet20.setOnClickListener(v -> {
            int betAmount = round1.getBetAmount();
            if (betAmount + 20 <= round1.getRemainingAmount()) {
                betAmount += 20;
                round1.setBetAmount(betAmount);
                betAmountTextView.setText("€" + String.valueOf(betAmount));
            }
        });

        btnBet50.setOnClickListener(v -> {
            int betAmount = round1.getBetAmount();
            if (betAmount + 50 <= round1.getRemainingAmount()) {
                betAmount += 50;
                round1.setBetAmount(betAmount);
                betAmountTextView.setText("€" + String.valueOf(betAmount));
            }
        });

        btnBetAll.setOnClickListener(v -> {
            round1.setBetAmount(round1.getRemainingAmount());
            betAmountTextView.setText("€" + String.valueOf(round1.getBetAmount()));
        });

        btnSubmitBet.setOnClickListener(v -> {
            if (round1.getBetAmount()!=0) {
                Intent selectBetAmount = new Intent(SelectBetAmount.this, Gameplay.class);
                selectBetAmount.putExtra("roundInfo", round1);
                startActivity(selectBetAmount);
            }
            else{
                String errorMessage = "ERROR! Morati unijeti kolicinu!";
                betAmountTextView.setText(errorMessage);
            }
        });

        btnBack.setOnClickListener(v -> {
            Intent howToPlay = new Intent(SelectBetAmount.this, MainActivity.class);
            startActivity(howToPlay);
        });
    }
}
