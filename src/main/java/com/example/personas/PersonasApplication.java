// Función: Define el paquete donde se encuentra la clase principal
// Contexto: Organiza la estructura del proyecto y evita conflictos de nombres
// Implementación: Paquete raíz del proyecto
// Resumen: Esta clase pertenece al paquete base del proyecto 'personas'
package com.example.personas;

// Función: Importa la clase que permite ejecutar una aplicación Spring Boot
// Contexto: Necesaria para iniciar el contexto de Spring y lanzar la aplicación
// Implementación: Se importa desde el paquete org.springframework.boot
// Resumen: Permite ejecutar el método run() para iniciar la app
import org.springframework.boot.SpringApplication;

// Función: Importa la anotación que marca esta clase como una aplicación Spring Boot
// Contexto: Habilita características como escaneo de componentes, configuración automática, etc.
// Implementación: Se aplica a nivel de clase
// Resumen: Marca la clase como punto de entrada para Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Función: Anotación que combina @Configuration, @EnableAutoConfiguration y @ComponentScan
// Contexto: Esencial para cualquier clase que arranque una app Spring Boot
// Implementación: Se coloca encima de la clase principal
// Resumen: Indica que esta es la clase principal de la aplicación
@SpringBootApplication
public class PersonasApplication {

	// Función: Método principal (main) que arranca la aplicación
	// Contexto: Es el punto de entrada en una aplicación Java
	// Implementación: Usa SpringApplication.run para iniciar Spring Boot
	// Resumen: Lanza la aplicación con configuración automática
	public static void main(String[] args) {
		SpringApplication.run(PersonasApplication.class, args);
	}
}
