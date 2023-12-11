package com.example.loginformjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;

    // Lista imena i lozinki
    private final String[] usernames = {"admin", "user0", "user1", "user2"};
    private final String[] passwords = {"admin", "password0", "password1", "password2" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Postavi varijable za EditText elemente (username i password)
        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);

        // Postavi listener za gumb "Login"
        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(v -> clickButtonLogin());

        // Postavi listener za "Enter" tipku na tipkovnici
        password.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                clickButtonLogin();
                return true;
            }

            return false;
        });
    }

    public void clickButtonLogin() {
        // Dohvati unesene podatke iz EditText elemenata (username i password)
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();

        // Provjeri jesu li uneseni podaci ispravni
        if (isValidCredentials(usernameInput, passwordInput)) {
            // Ako jesu otvori novi Activity
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        } else {
            // Ako nisu prikazi poruku o gresci
            Toast.makeText(this, "Krivo Uneseno", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidCredentials(String usernameInput, String passwordInput) {
        for (int i = 0; i < usernames.length; i++) {
            if (usernameInput.equals(usernames[i]) && passwordInput.equals(passwords[i])) {
                return true;
            }
        }

        return false;
    }
}