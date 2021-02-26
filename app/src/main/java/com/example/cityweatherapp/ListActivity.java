package com.example.cityweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        readWeatherData();

        //Recycler View set up
        RecyclerView weatherList = findViewById(R.id.weatherList);
        WeatherAdapter adapter = new WeatherAdapter(this, weatherData);
        weatherList.setAdapter(adapter);
        weatherList.setLayoutManager(new LinearLayoutManager(this));

        //Exit Button
        Button exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }

    public static List<WeatherSample> weatherData = new ArrayList<>();
    //http://stackoverflow.com/a/19976110
    private void readWeatherData() {
        InputStream is = getResources().openRawResource(R.raw.cityweatherdata);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            //step over header
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                // split by commas
                String[] tokens = line.split(",");

                // read data
                WeatherSample sample = new WeatherSample(
                        tokens[0],
                        tokens[1],
                        Integer.parseInt(tokens[2]),
                        Integer.parseInt(tokens[3]),
                        tokens[4]
                );

                // add sample
                weatherData.add(sample);

                // log
                Log.d("WeatherApp", "Created: " + sample);
            }
        }catch(IOException e){
            Log.wtf("WeatherApp", "Error on line: " + line, e);
            e.printStackTrace();
        }
    }


}