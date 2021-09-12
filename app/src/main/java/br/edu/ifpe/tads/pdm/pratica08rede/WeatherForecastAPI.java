package br.edu.ifpe.tads.pdm.pratica08rede;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherForecastAPI {
    static final String APPID = "a11ac945f2360e8cf7d496e7cb53dc00";
    @GET("forecast/daily?APPID=" + APPID + "&mode=json&units=metric&cnt=7")
    public Call<WeatherForecast> getForecast(@Query("q") String city);
}
