# Bank API - Spring Boot + Oracle

API REST de um sistema bancário simples, desenvolvida para praticar Java, Spring Boot, Orientação a Objetos e PL/SQL (procedures, functions, triggers) com Oracle Database.

## 🎯 Objetivo
Projeto de estudo para praticar conceitos técnicos exigidos em vagas de desenvolvimento, unindo back-end Java com banco de dados Oracle.

## 🛠️ Tecnologias
- Java 17+
- Spring Boot
- Oracle Database (via Docker)
- PL/SQL (Procedures, Functions, Triggers)
- Maven

## 🚀 Como rodar

### Pré-requisitos
- Docker
- Java 17+
- Maven

### Passos
1. Clone o repositório
2. Suba o banco Oracle:
```bash
   docker run -d --name oracle-banco -p 1521:1521 -e ORACLE_PWD=SuaSenha123 container-registry.oracle.com/database/free:latest
```
3. Execute os scripts em `src/main/resources/sql/` para criar tabelas e procedures
4. Rode a aplicação:
```bash
   ./mvnw spring-boot:run
```
5. A API estará disponível em `http://localhost:8080`

## 📋 Funcionalidades
- [ ] Cadastro de clientes
- [ ] Criação de contas
- [ ] Consulta de saldo
- [ ] Transferência entre contas (via procedure PL/SQL)
- [ ] Log automático de transações (via trigger)

## 📁 Estrutura do projeto

##  Autor
