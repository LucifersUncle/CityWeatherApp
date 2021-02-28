package com.example.cityweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    TextView city, rating;
    ImageView flag;
    EditText notes;
    SeekBar seekBar;
    String  cityData, notesData;
    double tempData, ratingData;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        //Widgets
        flag = findViewById(R.id.flag_details);
        city = findViewById(R.id.cityEdit_text);
        rating = findViewById(R.id.rating_text1);
        notes = findViewById(R.id.notes_text_field);
        seekBar = findViewById(R.id.seekBar);

        getData();
        setData();

        //Buttons
        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(v -> {
           setResult(RESULT_CANCELED);
           finish();
        });

        Button okBtn = findViewById(R.id.okBtn);
        okBtn.setOnClickListener(v -> {
                Intent resultIntent = getIntent();
                notesData = notes.getText().toString();

                resultIntent.putExtra("Notes", notesData);
                resultIntent.putExtra("Rating", Double.valueOf(seekBar.getProgress()));
                setResult(RESULT_OK, resultIntent);
                finish();
        });

        //SeekBar
        double value;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rating.setText(String.format("User Rating: %s", Double.parseDouble(String.valueOf(progress))));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void getData() {
        if(getIntent().hasExtra("City") && getIntent().hasExtra("Temp") && getIntent().hasExtra("Weather") && getIntent().hasExtra("Humidity") && getIntent().hasExtra("imagesArray"))
        {
            cityData = getIntent().getStringExtra("City");
            tempData = getIntent().getDoubleExtra("Temp",1);
            image = getIntent().getIntExtra("imagesArray",1);
            ratingData = getIntent().getDoubleExtra("Rating",1);
            notesData = getIntent().getStringExtra("Notes");
        }
        else
        {
            Toast.makeText(this,"Intent Extra is empty, can't load in data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        city.setText(cityData);
        flag.setImageResource(image);
        rating.setText(String.format("User Rating: %s", ratingData));
        notes.setText(notesData);
        seekBar.setProgress((int)ratingData);
    }

}
