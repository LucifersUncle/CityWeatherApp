package com.example.cityweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    String cityData, weatherData, notesData;
    double tempData, humidityData, ratingData;
    int image, position;
    ImageView flag;
    TextView city, temp, humidity, weather, rating, notes;
    Button editBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        //Widgets
        flag = findViewById(R.id.flag_details);
        city = findViewById(R.id.city_details);
        temp = findViewById(R.id.temp_details);
        humidity = findViewById(R.id.humidity_details);
        weather = findViewById(R.id.weather_details);
        rating = findViewById(R.id.rating_details);
        notes = findViewById(R.id.notes_field);
        editBtn = findViewById(R.id.editBtn);
        backBtn = findViewById(R.id.backBtn);

        if(savedInstanceState != null) {
            cityData = savedInstanceState.getString("City");
            tempData = savedInstanceState.getDouble("Temp");
            weatherData = savedInstanceState.getString("Weather");
            humidityData = savedInstanceState.getDouble("Humidity");
            ratingData = savedInstanceState.getDouble("Rating", 0);
            notesData = savedInstanceState.getString("Notes");
            image = savedInstanceState.getInt("imagesArray");
        } else {
            getData();
        }
        setData();


        //Back button function bind - rolls backs to the list activity
        backBtn.setOnClickListener(f -> {
                Intent resultIntent = getIntent();
                resultIntent.putExtra("Position", position);
                resultIntent.putExtra("Notes", notesData);
                resultIntent.putExtra("Rating", ratingData);
                setResult(RESULT_OK, resultIntent);
                finish();
        });


        //Edit button, with intent to start new acitivity
        editBtn.setOnClickListener(f -> {
                Intent intent = new Intent(DetailsActivity.this, EditActivity.class);
                intent.putExtra("City", cityData);
                intent.putExtra("Temp", tempData);
                intent.putExtra("Weather", weatherData);
                intent.putExtra("Humidity", humidityData);
                intent.putExtra("imagesArray", image);
                intent.putExtra("Rating", ratingData);
                intent.putExtra("Notes", notesData);
                DetailsActivity.this.startActivityForResult(intent, 666);
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("City", cityData);
        savedInstanceState.putString("Notes", notesData);
        savedInstanceState.putDouble("Temp", tempData);
        savedInstanceState.putDouble("Rating", ratingData);
        savedInstanceState.putString("Weather", weatherData);
        savedInstanceState.putDouble("Humidity", humidityData);
        savedInstanceState.putInt("imagesArray", image);
        savedInstanceState.putInt("Position", position);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void getData() {
        if(getIntent().hasExtra("City") && getIntent().hasExtra("Temp") && getIntent().hasExtra("Weather") && getIntent().hasExtra("Humidity") && getIntent().hasExtra("imagesArray"))
        {
            cityData = getIntent().getExtras().getString("City");
            tempData = getIntent().getExtras().getDouble("Temp",0);
            weatherData = getIntent().getExtras().getString("Weather");
            humidityData = getIntent().getExtras().getDouble("Humidity", 1);
            image = getIntent().getExtras().getInt("imagesArray",1);
            ratingData = getIntent().getExtras().getDouble("Rating",1);
            position = getIntent().getExtras().getInt("Position",1);
            notesData = getIntent().getExtras().getString("Notes");
        }
        else
        {
            Toast.makeText(this,"Intent Extra is empty, can't load in data", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666) {
            if (resultCode == RESULT_OK)
            {
                //Toast.makeText(this,"Inside OnActivityRes", Toast.LENGTH_SHORT).show();
                ratingData = data.getDoubleExtra("Rating",1);
                notesData = data.getStringExtra("Notes");
                notes.setText(notesData);
                rating.setText(String.format("User Rating:  %s", ratingData));
            }
        }
    }

    private void setData() {
        city.setText(cityData);
        temp.setText(String.format("Temp:  %sC", tempData));
        weather.setText(String.format("Weather: %s", weatherData));
        humidity.setText(String.format("Humidity: %s%%", humidityData));
        flag.setImageResource(image);
        notes.setText(notesData);
        rating.setText(String.format("User Rating:  %s", ratingData));
    }

}
