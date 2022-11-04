package com.example.block6simplecontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PersonaController {

    @Autowired
    PersonaService personaService;

    //Se crea un método con GetMapping para que muestre a la persona cuyo "name" coincida con el pasado en el método HTTP
    @GetMapping("/user/{name}")
    public String getPersonByName(@PathVariable String name){
        return personaService.getPersonByName(name);
    }

    //Se crea un método con PostMapping para que añada una Persona cuando se introduzca el metodo HTTP correcto y le suma 1 a la edad
    @PostMapping("/useradd")
    public Persona addPerson(@RequestBody Persona persona){
        persona.setAge(persona.getAge()+1);
        personaService.addPerson(persona);
        return persona;
    }

}
