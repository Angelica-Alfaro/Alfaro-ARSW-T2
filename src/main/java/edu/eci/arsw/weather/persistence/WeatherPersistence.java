package edu.eci.arsw.weather.persistence;

import edu.eci.arsw.weather.model.Weather;

public interface WeatherPersistence {
	Weather getWeatherByCity(String city) throws WeatherPersistenceException;

    void saveOnCache(Weather weather) throws WeatherPersistenceException;

    void deleteFromCache(Weather weather) throws WeatherPersistenceException;

    boolean containsOnCache(String city) throws WeatherPersistenceException;

}
