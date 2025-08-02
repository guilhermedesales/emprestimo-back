# Usa imagem oficial do OpenJDK para compilar e rodar a aplicação
FROM openjdk:17-jdk-alpine AS build

# Define diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos de build do Maven (ou Gradle)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw

# Copia o código fonte para dentro do container
COPY src src

# Build da aplicação (gera o .jar)
RUN ./mvnw clean package -DskipTests

# Segundo estágio: imagem menor para rodar o jar gerado
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copia o jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Porta que a aplicação vai usar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
