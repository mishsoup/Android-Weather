package com.example.weather.weatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get button and what happens when button is clicked

        btn = findViewById(R.id.btnWeather);
        txt = findViewById(R.id.myText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt.setText("Button Clicked!");

            }
        });
    }

    public void find_weather() {

    }
}
