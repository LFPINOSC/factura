# Imagen con Java 21
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

# Dar permisos de ejecución a mvnw
RUN chmod +x mvnw

# Compilar el proyecto
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/unidos-0.0.1-SNAPSHOT.jar"]
