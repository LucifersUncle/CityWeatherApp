package com.example.cityweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        //Intent click
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        //Widgets
        ImageView flag = findViewById(R.id.flag_details);
        TextView city = findViewById(R.id.cityEdit_text);
        TextView rating = findViewById(R.id.rating_text1);
        EditText notes = findViewById(R.id.notes_text_field);
        SeekBar seekBar = findViewById(R.id.seekBar);

        //Get ressources
        int id = getResources().getIdentifier(
                ListActivity.weatherData.get(position).getCountry().toLowerCase(),
                "drawable",
                getPackageName()
        );
        flag.setImageResource(id);

        city.setText(ListActivity.weatherData.get(position).getCity());
        rating.setText(ListActivity.weatherData.get(position).getRating()+ "");
        notes.setText(ListActivity.weatherData.get(position).getNote());
        seekBar.setProgress((int)ListActivity.weatherData.get(position).getRating()*10);

        //Buttons
        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        Button okBtn = findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        //SeekBar
        double value;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rating.setText(seekConvert(progress) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public double seekConvert(int progress) {
        return (double)progress/10;
    }


}
