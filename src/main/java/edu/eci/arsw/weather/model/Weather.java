package edu.eci.arsw.weather.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase que contine la información de los datos del clima de una ciudad
 */
public class Weather {

    private String pais;
    private String ciudad;
    private String clima;
    private String descripcion;
    private double temperatura;
    private double thermalSensation;
    private double latitud;
    private double longitud;

    public Weather() {
    }


    public Weather(String country, String city, String weather, String description, double temperatura, double thermalSensation, double latitud, double longitud) {
        this.pais = country;
        this.ciudad = city;
        this.clima = weather;
        this.descripcion = description;
        this.temperatura = temperatura;
        this.thermalSensation = thermalSensation;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getCountry() {
        return pais;
    }

    public void setCountry(String country) {
        this.pais = country;
    }

    public String getCity() {
        return ciudad;
    }

    public void setCity(String city) {
        this.ciudad = city;
    }

    public String getWeather() {
        return clima;
    }

    public void setWeather(String weather) {
        this.clima = weather;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String description) {
        this.descripcion = description;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getThermalSensation() {
        return thermalSensation;
    }

    public void setThermalSensation(double thermalSensation) {
        this.thermalSensation = thermalSensation;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather1 = (Weather) o;
        return Double.compare(weather1.temperatura, temperatura) == 0 &&
                Double.compare(weather1.thermalSensation, thermalSensation) == 0 &&
                Double.compare(weather1.latitud, latitud) == 0 &&
                Double.compare(weather1.longitud, longitud) == 0 &&
                Objects.equals(pais, weather1.pais) &&
                Objects.equals(ciudad, weather1.ciudad) &&
                Objects.equals(clima, weather1.clima) &&
                Objects.equals(descripcion, weather1.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pais, ciudad, clima, descripcion, temperatura, thermalSensation, latitud, longitud);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "countryCode='" + pais + '\'' +
                ", city='" + ciudad + '\'' +
                ", weather='" + clima + '\'' +
                ", description='" + descripcion + '\'' +
                ", temperatura=" + temperatura +
                ", thermalSensation=" + thermalSensation +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}