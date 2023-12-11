package com.example.pisanaprovjera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TextView textView;

    private EditText editText;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewHello);

        editText = findViewById(R.id.editTextInsert);

        imageView = findViewById(R.id.imageViewSlika);
    }

    public void changeTextView(View view) {
        String text = editText.getText().toString();

        textView.setText(text);
    }


    public void changePrvuSliku(View view) {
        try {
            imageView.setImageResource(R.drawable.perry_the_platipus);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void changeDruguSliku(View view) {
        try {
            imageView.setImageResource(R.drawable.github);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}