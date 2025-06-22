package com.attestation.starter.service;


import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Temperature;
import com.github.prominence.openweathermap.api.request.weather.CurrentWeatherRequester;
import com.github.prominence.openweathermap.api.request.weather.single.SingleResultCurrentWeatherRequestTerminator;
import org.springframework.stereotype.Component;

public class WeatherService {
    /**
     * Название города, для которого мы будем получать информацию по погоде.
     */
    private final String defaultCity;
    /**
     * OpenWeatherMap клиент из библиотеки `openweathermap-api`.
     */
    private final OpenWeatherMapClient client;

    public WeatherService(String defaultCity, OpenWeatherMapClient client) {
        this.defaultCity = defaultCity;
        this.client = client;
    }

    /**
     * Получить текстовое описание погоды для выбранного города.
     * @return строка с описанием текущей погоды
     */
    public String getTemperature() {
        CurrentWeatherRequester requester = client.currentWeather();

        SingleResultCurrentWeatherRequestTerminator terminator = requester.single()
                .byCityName(defaultCity)
                .unitSystem(UnitSystem.METRIC) // позволяет получать замеры в Цельсиях
                .retrieve();

        Temperature temperature = terminator.asJava().getTemperature();

        return temperature.toString();
    }
}
