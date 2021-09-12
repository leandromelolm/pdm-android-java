package br.edu.ifpe.tads.pdm.pratica08rede;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ForecastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_forecast);
        Intent intent = getIntent();
        ArrayList<CharSequence> data = intent.getCharSequenceArrayListExtra("data");
        for (CharSequence entry : data) {
            TextView text = new TextView(this);
            text.setText(entry);
            text.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            layout.addView(text);
        }
    }
}