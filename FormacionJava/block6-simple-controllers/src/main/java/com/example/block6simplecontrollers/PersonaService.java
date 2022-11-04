package com.example.block6simplecontrollers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {
    //Crea una List de Persona para almacenar la lista de personas que se vayan creando.
    public List<Persona> listaPersonas = new ArrayList<>();

    //Añade una Persona a la List de Personas
    public void addPerson(Persona person){
        listaPersonas.add(person);
    }

    //Busca una Persona de la List de Persona donde el "name" de Persona coincida, si lo hace, la guarda en una variable
    //"saludo" de tipo String que usaremos para añadirle un saludo delante al nombre. Si no coincide el "name" con ningún
    //"name" de la List de Persona, devolveremos un String vacío.
    public String getPersonByName(String name){
        String saludo = "";
        for (Persona person : listaPersonas){
            if (person.getName().equals(name)){
                saludo = "Hola " + name;
                return saludo;
            }
        }
        return saludo;
    }
}
