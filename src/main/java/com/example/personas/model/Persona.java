// This package defines the location of the class in your project structure
package com.example.personas.model;

// Import statements for Jakarta Persistence API annotations
import jakarta.persistence.*;

// This annotation marks the class as a JPA entity that maps to a database table
@Entity

// Specifies the name of the table in the database that this entity maps to
@Table(name = "personas")
public class Persona {

    // Marks this field as the primary key (ID) of the entity
    @Id

    // Specifies that the ID will be generated automatically by the database
    // GenerationType.IDENTITY means the database will auto-increment the ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // These are simple fields (columns) for the entity
    private String nombre;
    private String apellido;
    private String email;

    // Default constructor (required by JPA)
    public Persona() {
    }

    // Custom constructor to create a Persona with specific values
    public Persona(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    // Getter method for the ID
    public Long getId() {
        return id;
    }

    // Getter and setter for 'nombre'
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter and setter for 'apellido'
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getter and setter for 'email'
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
