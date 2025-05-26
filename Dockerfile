# Usa una imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo .jar ya construido desde el sistema host al contenedor
COPY target/personas-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que Spring Boot corre por defecto
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
