package com.example.cityweatherapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {
    Context ctx;
    private List<WeatherSample> weatherData = new ArrayList<>();

    public WeatherAdapter(Context ctx, List<WeatherSample> weatherData) {
        this.ctx = ctx;
        this.weatherData = weatherData;

        //this.dtlClickListener = dtlClickListener;
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
        holder.country.setText(weatherData.get(position).getCountry());
        holder.weather.setText(weatherData.get(position).getWeather());
        holder.temp.setText(weatherData.get(position).getTemp() + " Celsius");
        holder.rating.setText("" + weatherData.get(position).getRating());

        int id = ctx.getResources().getIdentifier(
                weatherData.get(position).getCountry().toLowerCase(),
                "drawable",
                ctx.getPackageName()
        );
        holder.flag.setImageResource(id);
        holder.aclayout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailsActivity.class);
                intent.putExtra("City", weatherData.get(position).getCity());
                intent.putExtra("Country", weatherData.get(position).getCountry());
                intent.putExtra("Humidity", weatherData.get(position).getHumidity());
                intent.putExtra("Note", weatherData.get(position).getNote());
                intent.putExtra("Temp", weatherData.get(position).getTemp());
                intent.putExtra("Rating", weatherData.get(position).getRating());
                intent.putExtra("Weather", weatherData.get(position).getWeather());
                ctx.startActivity(intent);

            }

        });
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView country, weather, temp, rating;
        ImageView flag;

        ConstraintLayout aclayout;


        //dtlListener dtlClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            country = itemView.findViewById(R.id.city);
            temp = itemView.findViewById(R.id.temp);
            weather = itemView.findViewById(R.id.weather);
            rating = itemView.findViewById(R.id.rating);
            flag = itemView.findViewById(R.id.flag);
            aclayout = itemView.findViewById(R.id.listactivityconstraint);

            //this.dtlClickListener = detailsClickListener;
            //itemView.setOnClickListener(this);
        }

        //@Override
        //public void onClick(View v) {
            //dtlClickListener.detailsClick(getAdapterPosition());
        //}
    }
    //public interface dtlListener {
        //void detailsClick(int position);
    }

