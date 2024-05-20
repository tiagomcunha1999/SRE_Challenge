# Use a imagem base do Maven para compilar o projeto
FROM maven:3.8.4-openjdk-11 AS build

# Defina o diretório de trabalho
WORKDIR /usr/src/app

# Copie os arquivos pom.xml e src para o diretório de trabalho
COPY pom.xml .
COPY src ./src

# Compile o projeto
RUN mvn clean package

# Use uma imagem mais leve do JDK para rodar o aplicativo
FROM openjdk:11-jre-slim

# Defina o diretório de trabalho
WORKDIR /usr/src/app

# Copie o arquivo JAR do estágio de compilação para o diretório de trabalho
COPY --from=build /usr/src/app/target/sre_challenge-1.0-SNAPSHOT.jar app.jar

# Defina o comando de inicialização
CMD ["java", "-jar", "app.jar"]
