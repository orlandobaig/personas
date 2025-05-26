# Personas App

Aplicación CRUD full-stack con Spring Boot y PostgreSQL, desplegada en Render usando Docker.

---

## 🚀 Live Demo

Accede a la app aquí (URL de Render):

```
https://personas-2fhc.onrender.com/
```

---

## 📄 Descripción

Esta aplicación permite gestionar personas (nombre, apellido, email) mediante operaciones CRUD:

* Crear
* Leer
* Actualizar
* Eliminar

---

## 🚧 Tecnologías utilizadas

* Java 17
* Spring Boot 3.4.6
* PostgreSQL (Render cloud DB)
* Maven
* Docker (multi-stage build)
* HTML, CSS y JS vanilla (frontend estático)

---

## 📊 Estructura del Proyecto

```
/src
  /main
    /java
      /com/example/personas
        Persona.java
        PersonaController.java
        PersonaRepository.java
    /resources
      application.properties
      static/
        index.html
        script.js
```

---

## ⚖️ Variables de Entorno (Render)

Agrega estas en la sección **Environment** de Render:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:5432/personasdb_nombre
SPRING_DATASOURCE_USERNAME=personasdb_usuario
SPRING_DATASOURCE_PASSWORD=tu_contraseña
```

**Nota**: No uses `localhost` en Render. Usa la URL completa proporcionada por la base de datos de Render.

---

## 🔄 Despliegue en Render (pasos claves)

1. **Crear repositorio en GitHub** y subir tu código
2. **Incluir el Maven Wrapper**: `mvnw`, `mvnw.cmd`, y la carpeta `.mvn`
3. Crear un archivo `Dockerfile`:

```Dockerfile
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

4. En `application.properties`, usa:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```

5. En Render:

   * Crear nuevo Web Service
   * Elegir Docker
   * Conectar tu repositorio
   * Agregar variables de entorno
   * Hacer deploy

---

## ❌ Errores comunes que evitamos

| Problema                           | Solución                               |
| ---------------------------------- | -------------------------------------- |
| Error: `localhost:5432 refused`    | Usar URL de PostgreSQL en la nube      |
| `mvnw` no encontrado               | Cometer y subir el Maven Wrapper       |
| Java 21 falló                      | Usar Java 17 en Docker y pom.xml       |
| Variables definidas pero no usadas | Referenciarlas correctamente con `${}` |
| Docker no encontraba el JAR        | Usar Dockerfile con multi-stage build  |

---

## 🤝 Créditos

Desarrollado por \[Tu Nombre].

---

## ✉️ Contacto / Ideas futuras

* Agregar login para usuarios
* Asociar datos por usuario
* Exportar datos a CSV o PDF
* Integrar con API de email o WhatsApp
