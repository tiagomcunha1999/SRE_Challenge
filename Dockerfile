# Imagem base do JDK
FROM openjdk:11-jdk-slim

# Criar diretórios necessários
RUN mkdir -p /app/src /app/bin

# Copiar o código fonte para o contêiner
COPY src /app/src

# Compilar todos os arquivos Java
RUN find /app/src -name "*.java" > sources.txt && javac -d /app/bin @sources.txt

# Ponto de entrada para do aplicativo
CMD ["java", "-cp", "/app/bin", "Main"]