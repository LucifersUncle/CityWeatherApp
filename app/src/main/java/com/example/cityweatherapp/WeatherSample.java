package com.example.cityweatherapp;

public class WeatherSample {
    private String City;
    private String Country;
    private int Temp;
    private int Humidity;
    private String Weather;
    private Double Rating;
    private String Note;


    public WeatherSample(String city, String country, int temp, int hum, String weather) {
        City = city;
        Country = country;
        Temp = temp;
        Humidity = hum;
        Weather = weather;
        Rating = 0.0;
        Note = "Type note here";
    }
    public double getRating() {
        return Rating;
    }
    public void setRating(double rating) {
        this.Rating = rating;
    }

    public String getCity() {
        return City;
    }
    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }
    public void setCountry(String country) {
        Country = country;
    }

    public double getTemp() {
        return Temp;
    }
    public void setTemp(int temp) {
        Temp = temp;
    }

    public double getHumidity() {
        return Humidity;
    }
    public void setHumidity(int humidity) {
        Humidity = humidity;
    }

    public String getWeather() {
        return Weather;
    }
    public void setWeather(String weather) {
        Weather = weather;
    }

    public String getNote() { return Note; }
    public void setNote(String newNote) { this.Note = newNote; }

}
