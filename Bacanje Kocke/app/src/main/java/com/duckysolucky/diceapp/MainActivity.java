package com.duckysolucky.diceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView diceImage;
    private TextView diceNumber;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceImage = findViewById(R.id.dice_image);
        diceNumber = findViewById(R.id.dice_number);
        Button rollButton = findViewById(R.id.roll_button);

        rollButton.setOnClickListener(v -> rollDice());
    }

    private void rollDice() {
        int number = random.nextInt(6) + 1;
        int resId = getResources().getIdentifier("dice" + number, "drawable", getPackageName());
        diceImage.setImageResource(resId);
        diceNumber.setText(String.valueOf(number));
    }
}