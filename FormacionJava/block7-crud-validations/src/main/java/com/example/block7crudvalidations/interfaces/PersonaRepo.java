package com.example.block7crudvalidations.interfaces;

import com.example.block7crudvalidations.pojos.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepo extends CrudRepository<Persona, Integer> {
    Optional<Persona> findById(String idPersona);
    List<Persona> findByUsuario(String usuario);
}
