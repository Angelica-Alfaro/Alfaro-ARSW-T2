package edu.eci.arsw.weather.controller;

import edu.eci.arsw.weather.services.WeatherServiceException;
import edu.eci.arsw.weather.services.WeatherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(value = "/weather")
public class OpenWeatherController {

    @Autowired
    @Qualifier("weatherServices")
    private WeatherServices weatherServices;

    /**
     * Obtiene los datos climáticos de una ciudad
     * @param nombre de la ciudad
     * @return Un formato JSON con la información de de datos climáticos
     **/
    @GetMapping("/{nombre}")
    public ResponseEntity<?> getWeatherByCity(@PathVariable String nombre) {
        try {
            return new ResponseEntity<>(weatherServices.getWeatherByCity(nombre), HttpStatus.ACCEPTED);
        } catch (WeatherServiceException ex) {
            Logger.getLogger(OpenWeatherController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}