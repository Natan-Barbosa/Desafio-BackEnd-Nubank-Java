# Desafio Backend Nubank - Java

## 📋 Sobre o Projeto

API REST desenvolvida em Java com Spring Boot para gerenciamento de clientes e contatos, seguindo os requisitos técnicos propostos no desafio.

## 🎯 Requisitos Implementados

- ✅ **Cadastro de Cliente** (POST `/client`)
- ✅ **Cadastro de Contato** (POST `/contacts`) associado a um cliente existente
- ✅ **Listagem de todos os clientes com seus contatos** (GET `/client`)
- ✅ **Listagem de contatos de um cliente específico** (GET `/client/{id}`)
- ✅ **Spring Boot + Spring Data JPA**
- ✅ **Banco de dados MySQL**
- ✅ **Entidades Cliente e Contato com relacionamento OneToMany / ManyToOne**

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **Spring Web**
- **Spring Validation**
- **MySQL**
- **Lombok**
- **SpringDoc OpenAPI** (Swagger)
- **JUnit 5**
- **Mockito**
- **Spring Boot Test (WebMVC)**

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com.barbosa.desafio_backend_nubank_JAVA/
│   │       ├── controller/
│   │       │   ├── ClientsController
│   │       │   └── ContactsController
│   │       ├── entities/
│   │       │   ├── ClientEntity
│   │       │   └── ContactEntity
│   │       ├── exceptions/
│   │       │   ├── ClientAlreadyExistsException
│   │       │   ├── ClientNotFoundException
│   │       │   └── ContactAlreadyExistsException
│   │       ├── exceptionhandler/
│   │       │   ├── ClientExceptionsHandler
│   │       │   ├── ContactExceptionHandler
│   │       │   └── GlobalExceptionHandler
│   │       ├── repository/
│   │       │   ├── ClientRepository
│   │       │   └── ContactsRepository
│   │       ├── service/
│   │       │   ├── Client
│   │       │   └── Contacts
│   │       └── utils/
│   │           ├── ExceptionModel
│   │           └── DesafioBackendNubankJavaApplication
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com.barbosa.desafio_backend_nubank_JAVA/
            ├── controller/
            └── service/
docker/
└── compose.yml
```

## 🚀 Como Executar

### Pré-requisitos

- Java 21
- Docker e Docker Compose
- Maven

### 1. Configurar o Banco de Dados

Execute o MySQL via Docker:

```bash
cd docker
docker-compose up -d
```

### 2. Configurar a Aplicação

As configurações do banco estão no arquivo `src/main/resources/application.properties`.

### 3. Executar a Aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

### 4. Documentação da API

Acesse a documentação Swagger em: `http://localhost:8080/swagger-ui.html`

## 📝 Endpoints da API

### Clientes

- `POST /client` - Cadastrar novo cliente
- `GET /client` - Listar todos os clientes com seus contatos
- `GET /client/{id}` - Listar contatos de um cliente específico

### Contatos

- `POST /contacts` - Cadastrar novo contato (associado a um cliente)

## 🧪 Testes

O projeto inclui testes unitários e de integração usando:

- **JUnit 5** para estrutura dos testes
- **Mockito** para mocks
- **Spring Boot Test** com WebMVC para testes dos controllers

Para executar os testes:

```bash
mvn test
```

## 📊 Relacionamentos

- **Cliente** (OneToMany) → **Contato** (ManyToOne)
- Um cliente pode ter múltiplos contatos
- Cada contato pertence a um único cliente

## 🔧 Funcionalidades Extras

- **Tratamento de Exceções** personalizado com handlers específicos
- **Validação** de dados de entrada
- **Documentação automática** da API com OpenAPI/Swagger
- **Containerização** do banco de dados com Docker
- **Testes abrangentes** para controllers e services

## 📄 Licença

Este projeto foi desenvolvido como parte de um desafio técnico.