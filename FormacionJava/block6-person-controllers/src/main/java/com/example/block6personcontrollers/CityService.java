package com.example.block6personcontrollers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    //Se crea un objeto tipo City.
    public City city;
    //Se crea un ArrayList de City para almacenar las que se vayan creando.
    public List<City> cityList = new ArrayList<>();

    //Método que añade un objeto City a la lista cityList.
    public void createCity(City city){
        cityList.add(city);
    }

    //Método que muestra la lista completa de objetos City.
    public List<City> getCityList(){
        return cityList;
    }
}
