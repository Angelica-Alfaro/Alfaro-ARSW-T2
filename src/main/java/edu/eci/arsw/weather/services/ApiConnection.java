package edu.eci.arsw.weather.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.weather.model.Weather;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service("api")
public class ApiConnection {

    /**
     * Realiza conexión al API de OpenWeather
     *
     * @param nombre de la ciudad
     * @return Los datos climáticos de una ciudad
     * @throws OpenWeatherServiceException Error de conexión con el API
     */
    public Weather getWeatherByCity(String nombre) throws WeatherServiceException, UnsupportedEncodingException {
        HttpResponse<JsonNode> response;
        String encodedQuery = URLEncoder.encode(nombre, StandardCharsets.UTF_8.toString());
        nombre = encodedQuery.replace("+", "%20");
        try {
            response = Unirest
                    .get("https://api.openweathermap.org/data/2.5/weather?q=" + nombre + "&appid=e6d589177c6d5fbf9467ccb98fab7dfb")
                    .asJson();
        } catch (UnirestException e) {
            throw new WeatherServiceException("Error de conexion con Open Weather");
        }
        JSONObject jsonObject = response.getBody().getObject();
        if (jsonObject.getInt("cod") == 404) {
            throw new WeatherServiceException("Ciudad no encontrada");
        }
        return getWeather(jsonObject);
    }

    /**
     * Toma el JSON obtenido del API de Open Weather 
     *
     * @param jsonOb JSON del API de Open Weather
     * @return Objeto filtrado
     */
    private Weather getWeather(JSONObject jsonOb) {
        String countryCode = jsonOb.getJSONObject("sys").getString("country");
        String city = jsonOb.getString("name");
        String weather = jsonOb.getJSONArray("weather").getJSONObject(0).getString("main");
        String description = jsonOb.getJSONArray("weather").getJSONObject(0).getString("description");
        double temperature = Math.round((jsonOb.getJSONObject("main").getDouble("temp") - 273.15) * 10d) / 10d;
        double thermalSensation = Math.round((jsonOb.getJSONObject("main").getDouble("feels_like") - 273.15) * 10d) / 10d;
        double latitud = Math.round(jsonOb.getJSONObject("coord").getDouble("lat") * 10d) / 10d;
        double longitud = Math.round(jsonOb.getJSONObject("coord").getDouble("lon") * 10d) / 10d;

        return new Weather(countryCode, city, weather, description, temperature, thermalSensation, latitud, longitud);
    }

}