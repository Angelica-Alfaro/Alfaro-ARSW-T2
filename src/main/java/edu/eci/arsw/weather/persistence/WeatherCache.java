package edu.eci.arsw.weather.persistence;

import edu.eci.arsw.weather.model.Weather;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Clase que realiza la persitencia del caché en el servidor
 */
@Service("weatherCache")
public class WeatherCache implements WeatherPersistence {

    public final List<Weather> weatherArrayList = new CopyOnWriteArrayList<>();

    /**
     * Método que obtiene los datos del clima de una ciudad desde el caché
     * @param city Ciudad
     * @return Obtiene los datos del clima de una ciudad desde el caché
     */
    public Weather getWeatherByCity(String city) {
        Weather weather = null;
        for (Weather w : weatherArrayList) {
            if (city.equals(w.getCity())) {
                weather = w;
                break;
            }
        }
        return weather;
    }

    /**
     * Método que coloca en caché los datos del clima de una ciudad
     * @param weather
     */
    public void saveOnCache(Weather weather) {
        weatherArrayList.add(weather);
    }

    /**
     * Método que elimina del caché los datos del clima de una ciudad
     * @param weather
     */
    public void deleteFromCache(Weather weather) {
        weatherArrayList.remove(weather);
    }

    /**
     * Método que verificar la exitencia de una ciudad en el caché
     * @param city Ciudad enviada
     * @return booleano que represente o no la existencia de la ciudad
     */
    public boolean containsOnCache(String city) {
        boolean booleanCache = true;
        Weather weather1 = getWeatherByCity(city);
        if (weather1 == null) {
            booleanCache = false;
        } else {
            deleteFromCache(weather1);
            booleanCache = false;
        }
        return booleanCache;
    }
}