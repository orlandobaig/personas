package com.example.personas.controller;

import com.example.personas.model.Persona;
import com.example.personas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    // GET all personas
    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    // POST a new persona
    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaRepository.save(persona);
    }

    // GET a persona by ID
    @GetMapping("/{id}")
    public Persona getPersonaById(@PathVariable Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    // PUT (update) persona
    @PutMapping("/{id}")
    public Persona updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        Persona persona = personaRepository.findById(id).orElse(null);
        if (persona != null) {
            persona.setNombre(personaDetails.getNombre());
            persona.setApellido(personaDetails.getApellido());
            persona.setEmail(personaDetails.getEmail());
            return personaRepository.save(persona);
        } else {
            return null;
        }
    }

    // DELETE persona
    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id) {
        personaRepository.deleteById(id);
    }
}
