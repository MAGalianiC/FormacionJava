package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controlador1 {

    //Se crea un objeto de la clase PersonaService
    @Autowired
    public PersonaService personaService;
    //Se crea un objeto de la clase CityService
    @Autowired
    public CityService cityService;
    //Se crea un objeto de la clase BeanService
    @Autowired
    public BeanService beanService;

    //Método que se encarga de mostrar la persona que se crea con los datos que el usuario pasa por los Headers
    @GetMapping("/controlador1/addPersona")
    public Persona addPersona(@RequestHeader(value = "name") String name,
                              @RequestHeader(value = "age") Integer age,
                              @RequestHeader(value = "city") String city){
        return personaService.createPersona(name, age, city);
    }
    //Método que se encarga de crear y llamar al método que añade objetos tipo City a una List de City y luego mostrarlo.
    @PostMapping("/controlador1/addCiudad")
    public City addCity(@RequestHeader(value = "name") String name,
                        @RequestHeader(value = "numHabitantes") Integer numHabitantes){
        City city = new City(name, numHabitantes);
        cityService.createCity(city);
        return city;
    }
    //Método que se encarga de recoger el bean que va en el path de la peticion http.
    @GetMapping("/controlador/bean/{bean}")
    public Persona getBean(@PathVariable String bean) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        return beanService.getBean(bean);
    }

}
