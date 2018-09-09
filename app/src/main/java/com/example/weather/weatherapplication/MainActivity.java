package com.example.weather.weatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView cityTxt, tempTxt, atmosTxt, humidText, minTxt, maxTxt, windTxt, timeTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get button and what happens when button is clicked

        btn = findViewById(R.id.btnWeather);
        cityTxt = findViewById(R.id.cityText);
        tempTxt = findViewById(R.id.tempText);
        atmosTxt = findViewById(R.id.atmosText);
        humidText = findViewById(R.id.humidText);
        minTxt = findViewById(R.id.minText);
        maxTxt = findViewById(R.id.maxText);
        windTxt = findViewById(R.id.windText);
        timeTxt = findViewById(R.id.timeText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                find_weather();

            }
        });
    }

    public void find_weather() {

        String url = "http://api.openweathermap.org/data/2.5/weather?q=houston&appid=f4c80f09e58347b301aca70a77c00438&units=metric";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                    String city = response.getString("name");
                    JSONObject main_object = response.getJSONObject("main");
                    Double currentTemp = main_object.getDouble("temp");
                    Double pressure = main_object.getDouble("pressure");
                    Double humidity = main_object.getDouble("humidity");
                    Double min = main_object.getDouble("temp_min");
                    Double max = main_object.getDouble("temp_max");

                    JSONObject wind = response.getJSONObject("wind");
                    Double windSpeed = wind.getDouble("speed");


                    cityTxt.setText("City: " + city);
                    tempTxt.setText(Double.toString(currentTemp));
                    atmosTxt.setText("Atmospheric Pressure: " + Double.toString(pressure));
                    humidText.setText("Humidity: " + Double.toString(humidity));
                    minTxt.setText("Min Temperature: " + Double.toString(min));
                    maxTxt.setText("Max Temperature: " + Double.toString(max));
                    windTxt.setText("Wind Speed: " + Double.toString(windSpeed));


                    Date currentTime = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");

                    String strDate = dateFormat.format(currentTime);
                    timeTxt.setText(strDate);






                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "JSON exception caught.", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }
}
