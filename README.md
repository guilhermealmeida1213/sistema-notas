Sistema de Notas e Faltas com Cibersegurança

Sistema web acadêmico desenvolvido para gerenciamento de usuários, alunos, professores, turmas, disciplinas, matrículas, notas e faltas, com controle de acesso, auditoria, recuperação de senha e assistente acadêmico com Inteligência Artificial.

O projeto foi desenvolvido no curso de Análise e Desenvolvimento de Sistemas (ADS), na disciplina de Tecnologias Emergentes, com foco em desenvolvimento web, segurança de aplicações, banco de dados e uso de IA.

Funcionalidades

Aluno

Consulta suas próprias informações acadêmicas

Consulta notas e médias

Consulta faltas e frequência

Visualiza sua situação acadêmica

Consulta suas matrículas

Utiliza o Assistente Acadêmico para perguntas sobre seu desempenho

Professor

Consulta suas próprias turmas e disciplinas

Consulta alunos relacionados às suas turmas

Lança notas

Corrige notas permitidas pelo sistema

Registra faltas

Corrige faltas

Trabalha somente com as disciplinas e turmas pelas quais é responsável

Administrador

Gerencia usuários

Ativa e desativa usuários

Gerencia alunos

Gerencia professores

Gerencia turmas

Gerencia disciplinas

Gerencia matrículas

Vincula professores às disciplinas

Consulta notas e faltas

Lança e gerencia a avaliação AVI conforme as regras do sistema

Consulta registros de auditoria

Segurança

A segurança é uma parte central do projeto.

Entre os mecanismos implementados estão:

Autenticação baseada em JWT

Senhas protegidas com BCrypt

Controle de acesso baseado em perfis

Perfis de ALUNO, PROFESSOR e ADMINISTRADOR

Validação de autorização nas operações acadêmicas

Professor limitado às suas próprias turmas e disciplinas

Aluno limitado aos próprios dados acadêmicos

Controle de usuário ativo/inativo

Validação contra operações não autorizadas

Proteção contra exclusão de registros vinculados

Auditoria de ações relevantes

Tratamento centralizado de exceções

Configurações sensíveis por variáveis de ambiente

Quando um usuário é desativado, o acesso é bloqueado e o histórico acadêmico não é apagado.

Assistente Acadêmico com IA

O sistema possui um Assistente Acadêmico que permite ao aluno realizar perguntas sobre seu próprio desempenho.

Exemplos:

Como estão minhas notas?

Quantas faltas eu tenho?

Qual matéria está com a menor nota?

Qual disciplina precisa de mais atenção?

Qual minha média em determinada disciplina?

Como está minha frequência?

O projeto inicialmente utilizava Ollama, mas a implementação atual utiliza a NVIDIA API.

Tecnologia utilizada

Serviço: NvidiaAiService

API: NVIDIA

Modelo: openai/gpt-oss-20b

Endpoint: https://integrate.api.nvidia.com/v1/chat/completions

Autenticação: Bearer Token por variável de ambiente

A integração utiliza configurações como:

temperature: 0.2

top_p: 0.7

max_tokens: 1024

stream: false

reasoning_effort: low

O Assistente Acadêmico não deve inventar informações acadêmicas.

Os cálculos importantes de média, situação e frequência são realizados pelo Java. A IA recebe os dados acadêmicos fornecidos pelo sistema e auxilia na interpretação e apresentação das informações.

Segurança da IA

O assistente possui regras para impedir, entre outras situações:

exposição de prompts ou instruções internas;

exposição de credenciais ou tokens;

alteração de notas ou faltas;

acesso a dados de outros alunos;

tentativa de elevação de privilégio;

invenção de notas, faltas, médias ou critérios acadêmicos.

Recuperação de senha

O sistema possui fluxo de recuperação de senha por e-mail.

Tecnologias utilizadas:

Resend

Token de recuperação armazenado no banco

Link de recuperação com validade limitada

Uso único do token

As configurações de e-mail são realizadas por variáveis de ambiente.

Para desenvolvimento/testes, pode ser utilizado um remetente compatível com a configuração da conta Resend.

Avaliações e desempenho acadêmico

O sistema trabalha com avaliações como:

AVC

AVG

AVI

Existem validações para evitar operações inválidas, incluindo duplicidade de avaliações e alterações não autorizadas.

O sistema calcula e apresenta informações como:

média por disciplina;

faltas;

frequência;

situação acadêmica;

desempenho nas avaliações;

disciplina que precisa de maior atenção.

A lógica acadêmica permanece no backend Java, evitando que a IA seja responsável por cálculos críticos.

Matrículas

O relacionamento acadêmico principal é:

Aluno
|
v
Matrícula
|
v
Turma

A matrícula possui informações como:

aluno;

turma;

data da matrícula;

status;

semestre do aluno.

Status utilizados pelo sistema incluem:

ATIVA

TRANCADA

CANCELADA

CONCLUIDA

As operações acadêmicas atuais consideram as regras de matrícula implementadas pelo sistema.

Turmas e disciplinas

O sistema relaciona:

Turma
|
v
TurmaDisciplina
|
+---- Disciplina
|
+---- Professor

Esse relacionamento permite determinar qual professor é responsável por determinada disciplina em uma turma.

Auditoria

O sistema possui um módulo de auditoria para registrar ações relevantes.

A auditoria contribui para:

rastreabilidade;

identificação de alterações;

acompanhamento de operações administrativas;

aumento da segurança e confiabilidade do sistema.

O acesso aos registros de auditoria é restrito conforme as permissões do sistema.

Filtros e pesquisas

O frontend possui filtros para facilitar a consulta dos dados.

Alunos

matrícula;

nome;

e-mail.

Professores

registro;

nome;

e-mail.

Matrículas

matrícula/aluno;

turma;

status.

Turmas

nome;

ano;

semestre.

Disciplinas

nome.

Vínculos

turma;

disciplina;

professor;

registro.

Tecnologias utilizadas

Backend

Java 21

Spring Boot 4.1.1

Spring Web MVC

Spring Data JPA

Hibernate

Spring Security

JWT

BCrypt

Maven

Banco de dados

MySQL

MySQL Workbench

Frontend

HTML

CSS

JavaScript

Inteligência Artificial

NVIDIA API

openai/gpt-oss-20b

E-mail

Resend

Documentação e desenvolvimento

Swagger / OpenAPI

IntelliJ IDEA

Git

GitHub

Deploy

Render

Arquitetura

O backend segue uma organização em camadas:

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

A estrutura principal do backend é organizada em componentes como:

config/
controller/
dto/
exception/
initializer/
model/
repository/
service/

O frontend utiliza HTML, CSS e JavaScript, consumindo a API REST protegida pela autenticação.

Configuração

As informações sensíveis não devem ser armazenadas diretamente no código-fonte.

A aplicação utiliza variáveis de ambiente.

Principais variáveis:

DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
DEMO_DATA_ENABLED
APP_BASE_URL
RESEND_API_KEY
MAIL_FROM
NVIDIA_API_KEY

Exemplo de configuração:

spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/mydb}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.open-in-view=false

jwt.secret=${JWT_SECRET}

demo.data.enabled=${DEMO_DATA_ENABLED:false}

app.base-url=${APP_BASE_URL:http://localhost:8080}

resend.api.key=${RESEND_API_KEY}
app.mail.from=${MAIL_FROM:onboarding@resend.dev}

nvidia.api.key=${NVIDIA_API_KEY}

Nunca publique senhas, tokens JWT ou chaves de API no repositório.

Executando o projeto

Pré-requisitos

Java 21

MySQL

Maven ou Maven Wrapper

Conta/configuração da NVIDIA API para utilizar o assistente

Conta/configuração do Resend para utilizar a recuperação de senha

Banco de dados

O projeto utiliza MySQL.

Configuração padrão para desenvolvimento:

jdbc:mysql://localhost:3306/mydb

As informações de conexão podem ser alteradas pelas variáveis:

DB_URL
DB_USERNAME
DB_PASSWORD

Chave JWT

Configure uma chave segura na variável:

JWT_SECRET

A chave não deve ser publicada no código-fonte.

NVIDIA API

Configure a chave da NVIDIA na variável:

NVIDIA_API_KEY

A chave deve permanecer fora do código-fonte.

Resend

Configure:

RESEND_API_KEY
MAIL_FROM

Iniciando a aplicação

No Windows:

mvnw.cmd spring-boot:run

No Linux ou macOS:

./mvnw spring-boot:run

Após a inicialização, a aplicação fica disponível, por padrão, em:

http://localhost:8080

Swagger / OpenAPI

A API possui documentação interativa utilizando Swagger/OpenAPI.

Com a aplicação em execução:

http://localhost:8080/swagger-ui/index.html

Estrutura do projeto

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
│       │   ├── js/
│       │   └── *.html
│       └── application.properties
│
└── test/

Controle de acesso

Perfil

Principais permissões

ALUNO

Consultar os próprios dados acadêmicos e utilizar o assistente

PROFESSOR

Consultar suas turmas e gerenciar notas e faltas das disciplinas sob sua responsabilidade

ADMINISTRADOR

Gerenciar usuários, dados acadêmicos, vínculos, avaliações administrativas e auditoria

Além do perfil, algumas operações verificam a propriedade ou o vínculo do recurso.

Por exemplo, possuir o perfil PROFESSOR não significa que o usuário pode alterar qualquer nota do sistema. O professor precisa estar relacionado à disciplina e à turma correspondente.

Principais regras de negócio

Entre as regras implementadas estão:

Aluno acessa somente seus próprios dados acadêmicos.

Professor trabalha somente com suas turmas e disciplinas.

Administrador possui acesso às operações administrativas.

Avaliações duplicadas são validadas.

Notas devem respeitar os valores permitidos pelo sistema.

Usuários devem possuir perfis compatíveis com seus registros acadêmicos.

Registros vinculados possuem proteção contra exclusões indevidas.

Alterações relevantes podem gerar registros de auditoria.

Usuários inativos não podem utilizar o sistema.

O histórico acadêmico permanece mesmo quando o acesso de um usuário é desativado.

Testes de segurança realizados

Foram realizados testes envolvendo:

autenticação;

autorização por perfil;

acesso de professor às próprias turmas;

tentativa de professor acessar turma de outro professor;

acesso administrativo à AVI;

bloqueio de operações não permitidas ao professor;

tentativa de lançamento de avaliação duplicada;

acesso do aluno somente aos próprios dados;

tentativas de prompt injection no assistente;

tentativa de acesso a dados de outro usuário;

tentativa de elevação de privilégio;

tentativa de obtenção de credenciais.

Exemplos de respostas de segurança validadas:

Professor tentando acessar recurso não permitido
→ HTTP 403

AVI duplicada
→ HTTP 409

Objetivo do projeto

O objetivo é desenvolver uma aplicação acadêmica que vá além do simples cadastro de notas e faltas.

O projeto combina:

gestão acadêmica + segurança + controle de acesso + auditoria + análise de desempenho + Inteligência Artificial.

A aplicação também serve como projeto prático para aplicar conhecimentos de:

programação Java;

desenvolvimento web;

APIs REST;

banco de dados;

arquitetura em camadas;

autenticação e autorização;

segurança de aplicações;

integração com serviços externos;

Inteligência Artificial.

Escopo

O sistema foi desenvolvido com foco nas necessidades acadêmicas definidas para o projeto.

Funcionalidades adicionais, como grade horária e relatórios de IA para professores, foram consideradas sugestões e não fazem parte do escopo atual da aplicação.

A prioridade nesta etapa final é:

FUNCIONAR
↓
ESTAR SEGURO
↓
ESTAR ORGANIZADO
↓
ESTAR DOCUMENTADO
↓
ESTAR PRONTO PARA APRESENTAR

Autores

Guilherme Almeida da Silva Santos
Daniel Lima da Silva
Lidiane Marcelino da Silva

Estudantes de Análise e Desenvolvimento de Sistemas.