FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

# ⚠️ Dar permisos de ejecución al wrapper de Maven
RUN chmod +x mvnw

# ✅ Compilar sin tests
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
