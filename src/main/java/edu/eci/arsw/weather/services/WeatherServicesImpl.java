package edu.eci.arsw.weather.services;


import edu.eci.arsw.weather.model.Weather;
import edu.eci.arsw.weather.persistence.WeatherCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("weatherServices")
public class WeatherServicesImpl implements WeatherServices {

    @Autowired
    @Qualifier("openWeatherCache")
    WeatherCache cacheInterface;

    /**
     * Obtiene los datos del clima de una ciudad. Cuando se consulta una ciudad, esta permanece en caché durante 5 minutos. Al pasar 5 minutos se elimina del caché. Si la ciudad no está en caché se consulta directamente del API de Open Weather
     *
     * @param nombre La ciudad
     * @return los datos del clima de la ciudad
     * @throws OpenWeatherServiceException Si ocurre un error con la conexión con el API externo o si ocurre algo con el caché
     */
    @Override
    public Weather getWeatherByCity(String nombre) throws WeatherServiceException {
        Weather weather;
        cacheInterface.containsOnCache(nombre);
		weather = cacheInterface.getWeatherByCity(nombre);
        return weather;
    }
}
