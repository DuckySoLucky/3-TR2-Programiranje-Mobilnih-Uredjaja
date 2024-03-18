package com.duckysolucky.prevoditeljglagoljice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private Button translateButton;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        translateButton = findViewById(R.id.translateButton);
        outputText = findViewById(R.id.outputText);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latinText = inputText.getText().toString();
                String translatedText = translate(latinText);
                outputText.setText(translatedText);
            }
        });
    }

    private static final Map<Character, String> latinToGlagoliticMap = new HashMap<>();

    static {
        latinToGlagoliticMap.put('a', "Ⰰ");
        latinToGlagoliticMap.put('b', "Ⰱ");
        latinToGlagoliticMap.put('v', "Ⰲ");
        latinToGlagoliticMap.put('g', "Ⰳ");
        latinToGlagoliticMap.put('d', "Ⰴ");
        latinToGlagoliticMap.put('e', "Ⰵ");
        latinToGlagoliticMap.put('ž', "Ⰶ");
        latinToGlagoliticMap.put('z', "Ⰷ");
        latinToGlagoliticMap.put('i', "Ⰸ");
        latinToGlagoliticMap.put('j', "Ⰹ");
        latinToGlagoliticMap.put('k', "Ⰺ");
        latinToGlagoliticMap.put('l', "Ⰻ");
        latinToGlagoliticMap.put('m', "Ⰼ");
        latinToGlagoliticMap.put('n', "Ⰽ");
        latinToGlagoliticMap.put('o', "Ⰾ");
        latinToGlagoliticMap.put('p', "Ⰿ");
        latinToGlagoliticMap.put('r', "Ⱀ");
        latinToGlagoliticMap.put('s', "Ⱁ");
        latinToGlagoliticMap.put('t', "Ⱂ");
        latinToGlagoliticMap.put('u', "Ⱃ");
        latinToGlagoliticMap.put('f', "Ⱄ");
        latinToGlagoliticMap.put('h', "Ⱅ");
        latinToGlagoliticMap.put('c', "Ⱆ");
        latinToGlagoliticMap.put('č', "Ⱇ");
        latinToGlagoliticMap.put('š', "Ⱈ");
        latinToGlagoliticMap.put('ŝ', "Ⱉ");
        latinToGlagoliticMap.put('y', "Ⱊ");
        latinToGlagoliticMap.put('ê', "Ⱋ");
        latinToGlagoliticMap.put('â', "Ⱌ");
        latinToGlagoliticMap.put('č', "Ⱍ");
        latinToGlagoliticMap.put('č', "Ⱎ");
        latinToGlagoliticMap.put('ṱ', "Ⱏ");
        latinToGlagoliticMap.put('ě', "Ⱐ");
        latinToGlagoliticMap.put('ü', "Ⱑ");
        latinToGlagoliticMap.put('ô', "Ⱒ");
        latinToGlagoliticMap.put('ï', "Ⱓ");
        latinToGlagoliticMap.put('ӧ', "Ⱔ");
        latinToGlagoliticMap.put('і', "Ⱕ");
        latinToGlagoliticMap.put('ѵ', "Ⱖ");
        latinToGlagoliticMap.put('а', "Ⱗ");
        latinToGlagoliticMap.put('б', "Ⱘ");
        latinToGlagoliticMap.put('в', "Ⱙ");
        latinToGlagoliticMap.put('г', "Ⱚ");
        latinToGlagoliticMap.put('д', "Ⱛ");
        latinToGlagoliticMap.put('є', "Ⱜ");
        latinToGlagoliticMap.put('ж', "Ⱝ");
        latinToGlagoliticMap.put('з', "Ⱞ");
        latinToGlagoliticMap.put('и', "Ⱟ");
        latinToGlagoliticMap.put('ї', "ⰰ");
        latinToGlagoliticMap.put('й', "ⰱ");
        latinToGlagoliticMap.put('к', "ⰲ");
        latinToGlagoliticMap.put('л', "ⰳ");
        latinToGlagoliticMap.put('м', "ⰴ");
        latinToGlagoliticMap.put('н', "ⰵ");
        latinToGlagoliticMap.put('о', "ⰶ");
        latinToGlagoliticMap.put('п', "ⰷ");
        latinToGlagoliticMap.put('р', "ⰸ");
        latinToGlagoliticMap.put('с', "ⰹ");
        latinToGlagoliticMap.put('т', "ⰺ");
        latinToGlagoliticMap.put('у', "ⰻ");
        latinToGlagoliticMap.put('ф', "ⰼ");
        latinToGlagoliticMap.put('х', "ⰽ");
        latinToGlagoliticMap.put('ц', "ⰾ");
        latinToGlagoliticMap.put('ч', "ⰿ");
        latinToGlagoliticMap.put('ш', "ⱀ");
        latinToGlagoliticMap.put('щ', "ⱁ");
        latinToGlagoliticMap.put('ъ', "ⱂ");
        latinToGlagoliticMap.put('ы', "ⱃ");
        latinToGlagoliticMap.put('ь', "ⱄ");
        latinToGlagoliticMap.put('ѡ', "ⱅ");
        latinToGlagoliticMap.put('э', "ⱆ");
        latinToGlagoliticMap.put('ю', "ⱇ");
        latinToGlagoliticMap.put('я', "ⱈ");
    }

    public static String translate(String latinString) {
        StringBuilder glagoliticString = new StringBuilder();

        for (char c : latinString.toCharArray()) {
            String glagoliticChar = latinToGlagoliticMap.getOrDefault(Character.toLowerCase(c), String.valueOf(c));
            glagoliticString.append(glagoliticChar);
        }

        return glagoliticString.toString();
    }
}