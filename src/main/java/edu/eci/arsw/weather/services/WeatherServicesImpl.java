package edu.eci.arsw.weather.services;


import edu.eci.arsw.weather.model.Weather;
import edu.eci.arsw.weather.persistence.WeatherCache;
import edu.eci.arsw.weather.persistence.WeatherPersistenceException;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("weatherServices")
public class WeatherServicesImpl implements WeatherServices {

    @Autowired
    @Qualifier("weatherCache")
    WeatherCache cacheInterface;
    
    @Autowired
    @Qualifier("api")
    ApiConnection apiconnection = null;

    /**
     * Obtiene los datos del clima de una ciudad. Cuando se consulta una ciudad, esta permanece en caché durante 5 minutos. Al pasar 5 minutos se elimina del caché. Si la ciudad no está en caché se consulta directamente del API de Open Weather
     *
     * @param nombre La ciudad
     * @return los datos del clima de la ciudad
     * @throws UnsupportedEncodingException 
     * @throws WeatherServiceException Si ocurre un error con la conexión con el API externo 
     */
    @Override
    public Weather getWeatherByCity(String nombre) throws WeatherServiceException{
        Weather weather;
        try {
            if (cacheInterface.containsOnCache(nombre)){
                weather = cacheInterface.getWeatherByCity(nombre);
            } else {
                weather = apiconnection.getWeatherByCity(nombre);
                cacheInterface.saveOnCache(weather);
            }
        } catch (WeatherServiceException | UnsupportedEncodingException e) {
            throw new WeatherServiceException(e.getMessage());
        }
        return weather;
    }
}
