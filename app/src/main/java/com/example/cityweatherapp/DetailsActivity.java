package com.example.cityweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

       ///Intent
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        //Widgets
        ImageView flag = findViewById(R.id.flag_details);
        TextView city = findViewById(R.id.city_details);
        TextView temp = findViewById(R.id.temp_details);
        TextView humidity = findViewById(R.id.humidity_details);
        TextView weather = findViewById(R.id.weather_details);
        TextView rating = findViewById(R.id.rating_details);
        TextView notes = findViewById(R.id.notes_field);

        //Get the correct flag from drawable directory
        int id = getResources().getIdentifier(
                ListActivity.weatherData.get(position).getCountry().toLowerCase(),
                "drawable",
                getPackageName()
        );
        flag.setImageResource(id);

        city.setText(ListActivity.weatherData.get(position).getCity());
        temp.setText(ListActivity.weatherData.get(position).getTemp() + " Celsius");
        humidity.setText(ListActivity.weatherData.get(position).getHumidity() + " %");
        weather.setText(ListActivity.weatherData.get(position).getWeather());
        rating.setText(ListActivity.weatherData.get(position).getRating() + "");
        notes.setText(ListActivity.weatherData.get(position).getNote());


        //Back button function bind - rolls backs to the list activity
        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        //Edit button, with intent to start new acitivity

        /*Button editBtn = findViewById(R.id.editBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);;
            }
        })*/

    }
}
