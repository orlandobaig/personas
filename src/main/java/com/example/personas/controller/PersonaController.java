// Paquete donde se encuentra la clase
package com.example.personas.controller;

// Importamos la clase Persona (modelo de datos)
import com.example.personas.model.Persona;

// Importamos el repositorio que maneja operaciones con la base de datos
import com.example.personas.repository.PersonaRepository;

// Importamos anotaciones necesarias de Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Indicamos que esta clase es un controlador REST
@RestController

// Definimos la ruta base para todas las peticiones a este controlador
@RequestMapping("/api/personas")
public class PersonaController {

    // Inyección automática del repositorio para acceder a los datos de personas
    @Autowired
    private PersonaRepository personaRepository;

    // Método para obtener todas las personas (GET /api/personas)
    @GetMapping
    public List<Persona> getAllPersonas() {
        // Devuelve la lista completa de personas desde la base de datos
        return personaRepository.findAll();
    }

    // Método para crear una nueva persona (POST /api/personas)
    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        // Guarda la nueva persona en la base de datos y la devuelve
        return personaRepository.save(persona);
    }

    // Método para obtener una persona por su ID (GET /api/personas/{id})
    @GetMapping("/{id}")
    public Persona getPersonaById(@PathVariable Long id) {
        // Busca la persona por ID; si no existe, devuelve null
        return personaRepository.findById(id).orElse(null);
    }

    // Método para actualizar una persona existente (PUT /api/personas/{id})
    @PutMapping("/{id}")
    public Persona updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        // Busca la persona por su ID
        Persona persona = personaRepository.findById(id).orElse(null);

        // Si la persona existe, actualiza sus datos
        if (persona != null) {
            persona.setNombre(personaDetails.getNombre());
            persona.setApellido(personaDetails.getApellido());
            persona.setEmail(personaDetails.getEmail());

            // Guarda y devuelve la persona actualizada
            return personaRepository.save(persona);
        } else {
            // Si no se encontró la persona, devuelve null
            return null;
        }
    }

    // Método para eliminar una persona por ID (DELETE /api/personas/{id})
    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id) {
        // Elimina la persona con el ID especificado
        personaRepository.deleteById(id);
    }
}
