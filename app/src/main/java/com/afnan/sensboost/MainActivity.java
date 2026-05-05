package com.afnan.sensboost;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("sensboost", MODE_PRIVATE);

        Switch toggle = findViewById(R.id.toggleService);
        SeekBar slider = findViewById(R.id.sensitivitySlider);
        TextView label = findViewById(R.id.sensitivityLabel);

        float saved = prefs.getFloat("multiplier", 1.0f);
        int progress = (int)((saved - 1.0f) * 10);
        slider.setProgress(progress);
        label.setText("Multiplier: " + saved + "x");

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int p, boolean u) {
                float mult = 1.0f + (p / 10.0f);
                label.setText("Multiplier: " + mult + "x");
                prefs.edit().putFloat("multiplier", mult).apply();
            }
            public void onStartTrackingTouch(SeekBar s) {}
            public void onStopTrackingTouch(SeekBar s) {}
        });
    }
}
