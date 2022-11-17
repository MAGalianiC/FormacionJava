package com.example.block7crud.Controllers;

import com.example.block7crud.Pojos.Persona;
import com.example.block7crud.Services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Indicamos que esta clase es una clase controlador de Spring y que la petición que se realice comenzará con /persona
@RestController
@RequestMapping("/persona")
public class PostController {

    //Se crea un objeto de tipo PersonaServiceImpl para poder usar los métodos dentro de la clase.
    @Autowired
    public PersonaServiceImpl personaServiceImpl;

    //Método que se encarga de llamar al método que añade personas a la base de datos que se encuentra en la clase PersonaServiceImpl.
    @PostMapping
    public Persona addPersona(@RequestBody Persona persona){
        return personaServiceImpl.addPersona(persona);
    }
}
