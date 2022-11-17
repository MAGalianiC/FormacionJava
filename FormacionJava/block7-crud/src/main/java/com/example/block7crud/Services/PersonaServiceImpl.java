package com.example.block7crud.Services;

import com.example.block7crud.Interfaces.PersonaRepository;
import com.example.block7crud.Interfaces.PersonaService;
import com.example.block7crud.Pojos.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    public PersonaRepository personaRepository;

    @Override
    public Persona addPersona(Persona persona) {
        personaRepository.save(persona);
        return persona;
    }

    @Override
    public Persona updatePersona(Persona persona) throws Exception {
        personaRepository.save(persona);
        return persona;
    }

    @Override
    public void deletePersona(Integer id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona findPersonaById(Integer id) throws FileNotFoundException{
        return personaRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Persona no encontrada"));
    }

    @Override
    public List<Persona> findPersonaByName(String name) {
        return personaRepository.findByName(name);
    }
}
