package edu.eci.arsw.weather.services;

import edu.eci.arsw.weather.model.Weather;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

@Service
public interface WeatherServices {
    Weather getWeatherByCity(String nombre) throws WeatherServiceException, UnsupportedEncodingException;

}