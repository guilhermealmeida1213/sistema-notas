# Sistema de Notas e Faltas

Sistema acadêmico desenvolvido para gerenciamento de **notas, faltas, matrículas, turmas e disciplinas**, com controle de acesso baseado em perfis, autenticação via JWT, auditoria de alterações e um assistente inteligente para consulta do desempenho acadêmico.

O projeto foi desenvolvido como parte do curso de **Análise e Desenvolvimento de Sistemas**, buscando aplicar conceitos de desenvolvimento backend, banco de dados, segurança de aplicações e tecnologias emergentes.

---

## Funcionalidades

O sistema possui três perfis de acesso:

### Aluno

- Consulta suas matrículas
- Consulta suas notas
- Consulta suas faltas
- Visualiza médias por disciplina
- Visualiza resumo acadêmico
- Consulta frequência
- Verifica sua situação acadêmica
- Utiliza o assistente inteligente para consultar seu desempenho

### Professor

- Consulta alunos e matrículas
- Consulta suas turmas e disciplinas
- Lança notas
- Corrige notas
- Registra faltas
- Corrige faltas
- Acessa apenas informações relacionadas às turmas pelas quais é responsável

### Administrador

- Gerencia usuários
- Gerencia alunos e professores
- Gerencia turmas e disciplinas
- Gerencia matrículas
- Define professores responsáveis pelas disciplinas
- Consulta notas e faltas
- Lança e corrige a avaliação integrada (AVI)
- Consulta registros de auditoria

---

## Assistente Inteligente

O sistema possui um assistente que permite ao aluno realizar perguntas relacionadas ao próprio desempenho acadêmico.

Exemplos:

```text
Como estão minhas notas?

Quantas faltas eu tenho?

Qual matéria preciso melhorar?

Qual minha média em Técnicas de Programação?

Qual minha frequência em Tecnologias Emergentes?

Estou aprovado em Governança?

Me explica como estou indo neste semestre e onde preciso prestar mais atenção.
```

O assistente utiliza os dados acadêmicos do próprio aluno para gerar respostas contextualizadas.

---

## Segurança

A aplicação possui mecanismos de segurança para proteger os dados acadêmicos e controlar o acesso aos recursos.

Entre eles:

- Autenticação utilizando JWT
- Senhas armazenadas utilizando hash
- Controle de acesso baseado em perfis
- Separação entre permissões de ALUNO, PROFESSOR e ADMINISTRADOR
- Validação de autorização nas operações acadêmicas
- Professor só pode alterar dados relacionados às suas próprias turmas
- Aluno acessa somente seus próprios dados acadêmicos
- Auditoria de alterações
- Tratamento centralizado de exceções
- Credenciais e segredos configurados por variáveis de ambiente

---

## Avaliações

O sistema trabalha com diferentes tipos de avaliação:

- **AVC**
- **AVG**
- **AVI**

A AVI é uma avaliação integrada e possui regras específicas de acesso administrativo.

O sistema também impede situações inválidas, como notas fora do intervalo permitido e determinadas avaliações duplicadas.

---

## Desempenho Acadêmico

A aplicação consegue consolidar informações acadêmicas para apresentar:

- Média por disciplina
- Quantidade de faltas
- Frequência
- Situação acadêmica
- Disciplinas que precisam de maior atenção

Essas informações também são utilizadas pelo assistente inteligente.

---

## Auditoria

Alterações importantes realizadas no sistema podem ser registradas para permitir rastreabilidade.

A auditoria auxilia na identificação de mudanças realizadas em informações acadêmicas, aumentando a segurança e a confiabilidade do sistema.

---

## Tecnologias utilizadas

### Backend

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Security
- JWT
- Maven

### Banco de Dados

- MySQL

### Frontend

- HTML
- CSS
- JavaScript

### Documentação e desenvolvimento

- Swagger / OpenAPI
- IntelliJ IDEA
- Git
- GitHub

---

## Arquitetura

O backend utiliza uma arquitetura organizada em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Banco de Dados
```

O projeto também utiliza:

```text
Model
DTO
Config
Security
Exception
Initializer
```

Essa separação busca manter as responsabilidades organizadas e facilitar a manutenção da aplicação.

---

## Configuração

As informações sensíveis não ficam armazenadas diretamente no código-fonte.

A aplicação utiliza variáveis de ambiente:

```properties
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
DEMO_DATA_ENABLED
```

Exemplo de configuração:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/mydb}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

jwt.secret=${JWT_SECRET}

demo.data.enabled=${DEMO_DATA_ENABLED:false}
```

> Nunca publique senhas reais ou chaves JWT no repositório.

---

## ▶️ Executando o projeto

### Pré-requisitos

É necessário possuir:

- Java 21
- MySQL
- Maven ou Maven Wrapper

Clone o repositório e entre na pasta do projeto.

Configure as variáveis de ambiente necessárias para conexão com o banco de dados e geração/validação dos tokens JWT.

No Windows, a aplicação pode ser iniciada utilizando:

```bash
mvnw.cmd spring-boot:run
```

Em Linux/macOS:

```bash
./mvnw spring-boot:run
```

Após a inicialização, a aplicação estará disponível por padrão em:

```text
http://localhost:8080
```

---

## Estrutura do projeto

```text
src/
├── main/
│   ├── java/
│   │   └── com/guilherme/sistemanotas/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── exception/
│   │       ├── initializer/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   │
│   └── resources/
│       └── static/
│           ├── css/
│           └── js/
│
└── test/
```

---

## Objetivo do projeto

O objetivo é desenvolver uma aplicação acadêmica que vá além do simples cadastro de notas e faltas.

O sistema combina:

**gestão acadêmica + segurança + controle de acesso + análise de desempenho + assistente inteligente.**

O projeto também serve como aplicação prática dos conhecimentos adquiridos durante a graduação em Análise e Desenvolvimento de Sistemas.

---

## Autores

**Guilherme Almeida, Lidiane Marcelino e Daniel Lima**

Estudantes de Análise e Desenvolvimento de Sistemas.