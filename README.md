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

- Consulta alunos matriculados em suas próprias turmas
- Consulta suas turmas e disciplinas
- Lança notas nas disciplinas pelas quais é responsável
- Corrige notas
- Registra faltas
- Corrige faltas
- Acessa informações acadêmicas relacionadas às suas próprias turmas

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

O assistente utiliza os dados acadêmicos do próprio aluno para fornecer respostas contextualizadas sobre notas, faltas, frequência e desempenho.

A integração com inteligência artificial é realizada utilizando um modelo executado localmente por meio do **Ollama**.

---

## Segurança

A aplicação possui mecanismos de segurança para proteger os dados acadêmicos e controlar o acesso aos recursos.

Entre eles:

- Autenticação utilizando JWT
- Senhas armazenadas utilizando hash com BCrypt
- Controle de acesso baseado em perfis
- Separação entre permissões de ALUNO, PROFESSOR e ADMINISTRADOR
- Validação de autorização nas operações acadêmicas
- Professor só pode alterar notas e faltas relacionadas às disciplinas pelas quais é responsável
- Aluno acessa somente seus próprios dados acadêmicos
- Proteção contra exclusão indevida de registros vinculados
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

O sistema também aplica validações para impedir situações inválidas, como:

- Notas fora do intervalo permitido
- Determinadas avaliações duplicadas
- Alterações não autorizadas
- Professor alterando dados de disciplinas pelas quais não é responsável

---

## Desempenho Acadêmico

A aplicação consolida informações acadêmicas para apresentar:

- Média por disciplina
- Quantidade de faltas
- Frequência
- Situação acadêmica
- Disciplinas que precisam de maior atenção

Essas informações também podem ser utilizadas pelo assistente inteligente para responder às perguntas do aluno.

---

## Auditoria

Alterações importantes realizadas no sistema podem ser registradas para permitir rastreabilidade.

A auditoria auxilia na identificação de mudanças realizadas em informações acadêmicas, aumentando a segurança e a confiabilidade do sistema.

O acesso aos registros de auditoria é restrito ao perfil de administrador.

---

## Tecnologias utilizadas

### Backend

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- Spring Security
- JWT
- BCrypt
- Maven

### Banco de Dados

- MySQL

### Frontend

- HTML
- CSS
- JavaScript

### Inteligência Artificial

- Ollama
- Modelo de linguagem executado localmente

### Documentação e desenvolvimento

- Swagger / OpenAPI
- IntelliJ IDEA
- Git
- GitHub

---

## Arquitetura

O backend utiliza uma arquitetura organizada em camadas:

```text
Interface Web
     |
     v
Controller
     |
     v
Service
     |
     v
Repository
     |
     v
MySQL
```

O projeto também utiliza componentes organizados em:

```text
config/
controller/
dto/
exception/
initializer/
model/
repository/
service/
```

Essa separação busca manter as responsabilidades organizadas e facilitar a manutenção e evolução da aplicação.

---

## Configuração

As informações sensíveis não ficam armazenadas diretamente no código-fonte.

A aplicação utiliza variáveis de ambiente para configurações que não devem ser publicadas no repositório.

Principais variáveis:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
DEMO_DATA_ENABLED
```

A configuração do Spring Boot utiliza essas variáveis da seguinte forma:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/mydb}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

jwt.secret=${JWT_SECRET}

demo.data.enabled=${DEMO_DATA_ENABLED:false}
```

Nunca publique senhas reais, tokens ou chaves JWT no repositório.

---

## Executando o projeto

### Pré-requisitos

Para executar o sistema, é necessário possuir:

- Java 21
- MySQL
- Maven ou Maven Wrapper
- Ollama
- Modelo de IA utilizado pelo assistente instalado localmente

---

### Banco de Dados

O projeto utiliza MySQL.

Por padrão, a aplicação está preparada para utilizar:

```text
jdbc:mysql://localhost:3306/mydb
```

A URL, usuário e senha podem ser configurados através das variáveis de ambiente:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

---

### JWT

Também é necessário configurar uma chave para geração e validação dos tokens JWT:

```text
JWT_SECRET
```

A chave deve ser mantida fora do código-fonte e não deve ser publicada no GitHub.

---

### Assistente Inteligente

Para utilizar o assistente inteligente, o Ollama deve estar instalado e executando localmente.

O modelo utilizado pela aplicação também precisa estar disponível no Ollama.

Caso o projeto esteja configurado para utilizar o modelo `qwen3:1.7b`, ele pode ser instalado com:

```bash
ollama pull qwen3:1.7b
```

O Ollama deve estar em execução antes de utilizar as funcionalidades do assistente.

---

### Iniciando a aplicação

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Em Linux ou macOS:

```bash
./mvnw spring-boot:run
```

Após a inicialização, a aplicação estará disponível por padrão em:

```text
http://localhost:8080
```

---

## Swagger / OpenAPI

A API possui documentação interativa utilizando Swagger/OpenAPI.

Com a aplicação em execução, a interface pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

A documentação permite visualizar e explorar os endpoints disponíveis na API.

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
│       ├── static/
│       │   ├── css/
│       │   └── js/
│       └── application.properties
│
└── test/
```

---

## Controle de Acesso

O sistema utiliza três perfis principais:

| Perfil | Principais permissões |
| --- | --- |
| ALUNO | Consultar seus próprios dados acadêmicos e utilizar o assistente |
| PROFESSOR | Consultar suas turmas e gerenciar notas e faltas das disciplinas sob sua responsabilidade |
| ADMINISTRADOR | Gerenciar informações acadêmicas, usuários, vínculos e auditorias |

Além da verificação do perfil, algumas operações possuem validações de propriedade dos dados.

Por exemplo, possuir o perfil `PROFESSOR` não significa que o usuário pode alterar qualquer nota do sistema. O professor deve estar vinculado à disciplina correspondente.

---

## Principais Regras de Negócio

Entre as regras implementadas no sistema estão:

- Aluno acessa somente seus próprios dados acadêmicos
- Professor gerencia notas e faltas somente das disciplinas sob sua responsabilidade
- Administrador possui acesso às operações administrativas
- AVI possui regras específicas de gerenciamento
- Notas devem respeitar os valores permitidos pelo sistema
- Avaliações duplicadas são validadas
- Usuários não podem possuir registros acadêmicos incompatíveis com seus perfis
- Registros vinculados possuem proteção contra exclusões indevidas
- Alterações relevantes podem gerar registros de auditoria

---

## Objetivo do projeto

O objetivo é desenvolver uma aplicação acadêmica que vá além do simples cadastro de notas e faltas.

O sistema combina:

**gestão acadêmica + segurança + controle de acesso + análise de desempenho + assistente inteligente.**

O projeto também serve como aplicação prática dos conhecimentos adquiridos durante a graduação em Análise e Desenvolvimento de Sistemas, incluindo desenvolvimento backend, modelagem de banco de dados, APIs REST, segurança de aplicações e tecnologias emergentes.

---

## Autores

**Guilherme Almeida, Lidiane Marcelino e Daniel Lima**

Estudantes de Análise e Desenvolvimento de Sistemas.
