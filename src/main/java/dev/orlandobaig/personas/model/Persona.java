// Función: Declara la ubicación del paquete de esta clase modelo
// Contexto: Organiza archivos y evita conflictos de nombres
// Implementación: Paquete llamado 'model' dentro de 'dev.orlandobaig.personas'
// Resumen: Esta clase pertenece a la capa 'model' de la aplicación
package dev.orlandobaig.personas.model;

// Función: Importa anotaciones necesarias para mapear esta clase a una tabla de base de datos
// Contexto: Usa Jakarta Persistence (JPA) para ORM (Mapeo Objeto-Relacional)
// Implementación: Importa Entity, Table, Id, GeneratedValue, GenerationType
// Resumen: Permite que esta clase funcione como una entidad JPA
import jakarta.persistence.*;

// Función: Marca esta clase como una entidad JPA
// Contexto: Requerido para que Hibernate/Spring reconozca esta clase como una tabla
// Implementación: Se aplica justo arriba de la clase
// Resumen: Convierte 'Persona' en una entidad gestionada
@Entity

// Función: Define el nombre de la tabla en la base de datos
// Contexto: Reemplaza el nombre por defecto (el nombre de la clase)
// Implementación: La tabla se llamará 'personas'
// Resumen: Asocia esta entidad con la tabla 'personas'
@Table(name = "personas")
public class Persona {

    // Función: Define la clave primaria de la entidad
    // Contexto: Cada entidad debe tener un identificador único
    // Implementación: Campo 'id' marcado con @Id
    // Resumen: 'id' identifica de forma única cada registro
    @Id

    // Función: Configura la generación automática de la clave primaria
    // Contexto: Usa auto-incremento proporcionado por la base de datos
    // Implementación: Estrategia establecida como GenerationType.IDENTITY
    // Resumen: La base de datos asigna el ID automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Función: Declara los campos 'nombre', 'apellido', 'email'
    // Contexto: Se mapearán como columnas en la base de datos
    // Implementación: Variables privadas simples
    // Resumen: Son los atributos de cada objeto Persona
    private String nombre;
    private String apellido;
    private String email;

    // Función: Constructor sin argumentos
    // Contexto: Necesario para que JPA pueda crear instancias automáticamente
    // Implementación: Constructor vacío
    // Resumen: Permite que Spring y JPA instancien la clase
    public Persona() {
    }

    // Función: Constructor con argumentos
    // Contexto: Útil para crear objetos Persona con valores específicos
    // Implementación: Asigna valores a todos los campos excepto el ID
    // Resumen: Permite crear objetos con datos iniciales
    public Persona(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    // Función: Getter para el ID
    // Contexto: Permite acceder al ID de la entidad
    // Implementación: Retorna el valor de 'id'
    // Resumen: Método público para leer el ID
    public Long getId() {
        return id;
    }

    // Función: Getter y setter para 'nombre'
    // Contexto: Métodos estándar JavaBean para encapsulación
    // Implementación: Métodos get y set simples
    // Resumen: Métodos de acceso para el campo 'nombre'
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Función: Getter y setter para 'apellido'
    // Contexto: Patrón JavaBean
    // Implementación: Métodos get y set directos
    // Resumen: Controla el acceso al campo 'apellido'
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Función: Getter y setter para 'email'
    // Contexto: Permite obtener/modificar el correo
    // Implementación: Métodos básicos de get/set
    // Resumen: Maneja el acceso al campo 'email'
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
