package com.example.block7crud.Interfaces;

import com.example.block7crud.Pojos.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//Interfaz que hereda de CrudRepository donde se encuentra el cuerpo básico de los CRUD básicos de Spring.
public interface PersonaRepository extends CrudRepository<Persona, Integer> {

    //Se añade un método más para guardar en una lista un conjunto de objetos tipo Persona, buscados por "name".
    List<Persona> findByName(String name);
}
