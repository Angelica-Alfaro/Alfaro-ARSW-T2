package edu.eci.arsw.weather.persistence;

import org.springframework.stereotype.Service;

import edu.eci.arsw.weather.model.Weather;

@Service
public interface WeatherPersistence {
	Weather getWeatherByCity(String city) throws WeatherPersistenceException;

    void saveOnCache(Weather weather) throws WeatherPersistenceException;

    void deleteFromCache(Weather weather) throws WeatherPersistenceException;

    boolean containsOnCache(String city) throws WeatherPersistenceException;

}
