package com.example.cityweatherapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {
    Context ctx;
    List<WeatherSample> weatherData;
    int[] imagesArray;

    public WeatherAdapter(Context ctx, List<WeatherSample> weatherData, int[] images) {
        this.ctx = ctx;
        this.weatherData = weatherData;
        imagesArray = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.row_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.city.setText(weatherData.get(position).getCity());
        holder.weather.setText(weatherData.get(position).getWeather());
        holder.temp.setText(String.format("%s C", weatherData.get(position).getTemp()));
        holder.rating.setText(String.format("%s", weatherData.get(position).getRating()));
        holder.flag.setImageResource(imagesArray[position]);

        holder.list_activity_layout.setOnClickListener(f -> {
                Intent intent = new Intent(ctx, DetailsActivity.class);
                intent.putExtra("City", weatherData.get(position).getCity());
                intent.putExtra("Country", weatherData.get(position).getCountry());
                intent.putExtra("Humidity", weatherData.get(position).getHumidity());
                intent.putExtra("Notes", weatherData.get(position).getNote());
                intent.putExtra("Temp", weatherData.get(position).getTemp());
                intent.putExtra("Rating", weatherData.get(position).getRating());
                intent.putExtra("Weather", weatherData.get(position).getWeather());
                intent.putExtra("Position", position);

                ((ListActivity) ctx).startActivityForResult(intent, 666);
        });
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView city, weather, temp, rating;
        ImageView flag;
        ConstraintLayout list_activity_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city);
            weather = itemView.findViewById(R.id.weather);
            temp = itemView.findViewById(R.id.temp);
            rating = itemView.findViewById(R.id.rating);
            flag = itemView.findViewById(R.id.flag);
            list_activity_layout = itemView.findViewById(R.id.listactivityconstraint);
        }
    }
}



