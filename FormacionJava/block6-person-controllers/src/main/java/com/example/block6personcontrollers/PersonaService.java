package com.example.block6personcontrollers;

import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    //Aquí se crea un objeto tipo Persona
    public Persona persona;
    //Método que se encarga de recoger los datos para crear un objeto tipo persona y devolverlo.
    public Persona createPersona(String name, Integer age, String city){
        return persona = new Persona(name, age, city);
    }
    //Método que devuelve un objeto tipo Persona.
    public Persona getPersona(){
        return persona;
    }
}
