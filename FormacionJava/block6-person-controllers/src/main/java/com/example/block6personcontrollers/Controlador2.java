package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controlador2 {

    //Aquí se crea un objeto tipo CityService.
    @Autowired
    public CityService cityService;
    //Método que se encarga de recoger los datos para crear un objeto tipo Persona, duplicar el atributo "age" y devolver el objeto.
    @GetMapping("/controlador2/getPersona")
    public Persona getPersonaDoubleAge(String name, Integer age, String city){
        return new Persona(name, age*2, city);
    }
    //Método que se encarga de devolver la lista completa de objetos tipo City.
    @GetMapping("/controlador1/getCiudad")
    public List<City> getCityList(){
        return cityService.getCityList();
    }
}
