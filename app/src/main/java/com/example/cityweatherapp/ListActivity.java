package com.example.cityweatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.style.TabStopSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListActivity extends AppCompatActivity {

    WeatherAdapter adapter;
    RecyclerView recyclerView;
    int[] flags = {R.drawable.dk, R.drawable.fi, R.drawable.us,R.drawable.au, R.drawable.na, R.drawable.sg, R.drawable.ru, R.drawable.ae, R.drawable.fo, R.drawable.us, R.drawable.fj, R.drawable.jp};
    List<WeatherSample> weatherData = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        if(weatherData.isEmpty()) {
            readWeatherData();
        }

        //Recycler View set up
        recyclerView = findViewById(R.id.weatherList);
        adapter = new WeatherAdapter(this, weatherData, flags);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Exit Button
        Button exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(f -> {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) { //intent callback
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 666) {
            if(resultCode == RESULT_OK)
            {
                int position = intent.getIntExtra("Position", 0);

                String newNote = intent.getStringExtra("Notes");

                weatherData.get(position).setRating(intent.getDoubleExtra("Rating", 0.0));
                weatherData.get(position).setNote(newNote);

                adapter = new WeatherAdapter(this, weatherData, flags);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        }
    }


    //http://stackoverflow.com/a/19976110
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void readWeatherData() {
        InputStream is = getResources().openRawResource(R.raw.cityweatherdata);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );

        weatherData = new ArrayList<>();
        try {
            //step over header
            String line = reader.readLine();
            line = reader.readLine();

            while (line != null) {
                String[] tokens = line.split(",");
                WeatherSample sample = Mapper(tokens);
                weatherData.add(sample);


                line = reader.readLine();
            }
        }catch(IOException e){
            Log.wtf("WeatherApp", "Error on line: ", e);
            e.printStackTrace();
        }
    }

    private WeatherSample Mapper(String[] tokens) {
        WeatherSample sample = new WeatherSample();

        sample.setCity(tokens[0]);
        sample.setCity(tokens[1]);
        sample.setTemp(Double.parseDouble(tokens[2]));
        sample.setHumidity(Double.parseDouble(tokens[3]));
        sample.setWeather(tokens[4]);

        return sample;
    }
}