# Etapa 1: Build leve
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
# Baixa as dependências primeiro para aproveitar o cache
RUN mvn dependency:go-offline -B
COPY src ./src
# Compila ignorando testes e limitando uso de memória
RUN MAVEN_OPTS="-Xmx512m" mvn clean package -DskipTests -Dmaven.test.skip=true

# Etapa 2: Imagem final de execução
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Define a porta como variável
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]