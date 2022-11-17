package com.example.block7crud.Controllers;

import com.example.block7crud.Services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Indicamos que esta clase es una clase controlador de Spring y que la petición que se realice comenzará con /persona
@RestController
@RequestMapping("/persona")
public class DeleteController {

    //Se crea un objeto de tipo PersonaServiceImpl para poder usar los métodos dentro de la clase.
    @Autowired
    public PersonaServiceImpl personaServiceImp;

    //Método que se encarga de llamar al método "deletePersona" de la clase "PersonaServiceImpl" que se encarga de
    //borrar un objeto de la clase Persona de la Base de Datos.
    @DeleteMapping("/{id}")
    public void deletePersonaById(@PathVariable Integer id){
        personaServiceImp.deletePersona(id);
    }
}
