package com.example.block7crud.Controllers;

import com.example.block7crud.Pojos.Persona;
import com.example.block7crud.Services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//Indicamos que esta clase es una clase controlador de Spring y que la petición que se realice comenzará con /persona
@RestController
@RequestMapping("/persona")
public class PutController {

    //Se crea un objeto de tipo PersonaServiceImpl para poder usar los métodos dentro de la clase.
    @Autowired
    public PersonaServiceImpl personaServiceImpl;

    //Método encargado de cambiar los datos de una persona con el id que se pone en el path y rellenarlo con los datos que le pasamos en el body.
    @PutMapping("/{id}")
    public String editPersona(@PathVariable Integer id,
                              @RequestBody Persona persona){
        //Si alguno de los datos, tanto el "id" del path, como el resto de datos del body está vacío se devuelve un mensaje de aviso.
        if (id == null || persona.getName().isEmpty() || persona.getAge() == null || persona.getPopulation().isEmpty()){
            return "Debes rellenar todos los campos para continuar";
        }
        //Si están todos los datos requeridos correctamente, se crea un objeto tipo Persona con los datos indicados en el path y en el body y
        //luego se llama al método de la clase PersonaServiceImpl que se encarga de guardar a la nueva persona con los datos nuevos.
        else {
            try {
                Persona personaActualizada = new Persona(id, persona.getName(), persona.getAge(), persona.getPopulation());
                return personaServiceImpl.updatePersona(personaActualizada).toString();
            }
            catch (Exception exception){
                return exception.toString();
            }
        }
    }
}
