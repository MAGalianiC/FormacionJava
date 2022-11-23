package com.example.block7crud.Services;

import com.example.block7crud.Interfaces.PersonaRepository;
import com.example.block7crud.Interfaces.PersonaService;
import com.example.block7crud.Pojos.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Persona> findPersonaById(Integer id){
        return personaRepository.findById(id);
    }

    @Override
    public List<Persona> findPersonaByName(String name) {
        return personaRepository.findByName(name);
    }
}
