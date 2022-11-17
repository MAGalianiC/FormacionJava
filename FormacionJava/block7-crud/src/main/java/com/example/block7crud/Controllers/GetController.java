package com.example.block7crud.Controllers;

import com.example.block7crud.Pojos.Persona;
import com.example.block7crud.Services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

//Indicamos que esta clase es una clase controlador de Spring y que la petición que se realice comenzará con /persona
@RestController
@RequestMapping("/persona")
public class GetController {

    //Se crea un objeto de tipo PersonaServiceImpl para poder usar los métodos dentro de la clase.
    @Autowired
    public PersonaServiceImpl personaServiceImpl;

    //Método que se encarga de buscar un objeto Persona en la base de datos buscándolo a partir del "id" y devolver un
    //String con el contenido de esa Persona encontrada bien estructurado.
    @GetMapping("/{id}")
    public String getPersonaById(@PathVariable Integer id) throws FileNotFoundException {
        //Creamos un Optional para poder comprobar correctamente que algún campo de Persona pueda estar a null y tratarlo correctamente.
        Optional<Persona> persona = Optional.ofNullable(personaServiceImpl.findPersonaById(id));
        //Si la Persona estuviera vacía, devolveríamos el mensaje de que no se ha encontrado a la Persona
        if (persona.isEmpty() == true){
            return "Persona no encontrada";
        }
        //Si la persona está rellena se devuelve el objeto con un toString para que lo formatee correctamente.
        else {
            return persona.get().toString();
        }
    }

    //Método que se encarga de buscar uno o varios objetos Persona en la base de datos buscándolo a partir del "name" y devolver un array de
    //String con el contenido de esas Personas con el mismo nombre encontradas, de manera bien estructurada.
    @GetMapping("/nombre/{name}")
    public String getPersonaByName(@PathVariable String name){
        //Se crea una List de Persona para guardar en ella todas las personas cuyo name coincida con la búsqueda y llamamos al método
        //que filtrará la base de datos en busca de las personas cuyo name coincida.
        List<Persona> personaList = personaServiceImpl.findPersonaByName(name);
        //Si la lista de personas está vacía querrá decir que no se encontró ninguna persona con ese nombre, así que se devuelve
        //que no se encontró ninguna persona con ese nombre
        if (personaList.size() == 0){
            return "No se encontró ninguna persona con ese name";
        }
        //Si la lista tiene al menos una persona, se devuelve con un toString para que se formatee correctamente.
        else {
            return personaList.toString();
        }
    }
}
