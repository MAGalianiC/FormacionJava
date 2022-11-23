package com.example.block7crud.Interfaces;

import com.example.block7crud.Pojos.Persona;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

//Interfaz donde se encuentra la base de los métodos que usaremos en la aplicación.
public interface PersonaService {

    Persona addPersona(Persona persona);
    Persona updatePersona(Persona persona) throws Exception;
    void deletePersona(Integer id);
    Optional<Persona> findPersonaById(Integer id) throws FileNotFoundException;
    List<Persona> findPersonaByName(String name);

}
