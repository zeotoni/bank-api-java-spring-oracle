# Bank API - Spring Boot + Oracle

API REST de um sistema bancário simples, desenvolvida para praticar Java, Spring Boot, Orientação a Objetos e PL/SQL (procedures) com Oracle Database.

## Objetivo

Projeto de estudo para praticar conceitos técnicos exigidos em vagas de desenvolvimento, unindo back-end Java com banco de dados Oracle — com foco especial em integração Java + PL/SQL para regras de negócio críticas (transferências bancárias).

## Tecnologias

- Java 17+
- Spring Boot
- Spring Data JPA
- Oracle Database 23ai Free (via Docker)
- Flyway (versionamento de banco de dados)
- PL/SQL
- Maven
- Docker Compose

## Como rodar

### Pré-requisitos

- Docker
- Java 17+
- Maven

### Passos

1. Clone o repositório

2. Crie o arquivo `.env` na raiz do projeto, baseado no `.env.example`:
   ```bash
   cp .env.example .env
   ```
   Edite o `.env` e defina uma senha (ex: `ORACLE_PWD=SuaSenha123`)

3. Suba o banco Oracle com Docker Compose:
   ```bash
   docker compose up -d
   ```
   Aguarde ~2 minutos para o banco inicializar completamente. Acompanhe com:
   ```bash
   docker logs -f oracle-banco
   ```
   até aparecer a mensagem `DATABASE IS READY TO USE!`

4. Exporte a variável de ambiente da aplicação (mesma senha usada no `.env`):
   ```bash
   export DB_PASSWORD=SuaSenha123
   ```

5. Rode a aplicação (as tabelas e a procedure são criadas automaticamente via Flyway):
   ```bash
   ./mvnw spring-boot:run
   ```

6. A API estará disponível em `http://localhost:8080`

##  Funcionalidades

- [x] Cadastro e consulta de clientes
- [x] Criação e consulta de contas (vinculadas a um cliente)
- [x] Transferência entre contas (via procedure PL/SQL, com validação de saldo e lock de linha)
- [x] Consulta de histórico de transações
- [x] Log de auditoria automático de mudanças de saldo (via trigger)
- [x] Tratamento global de erros (validação, entidade não encontrada, erros de negócio da procedure)

##  Endpoints

### Clientes

| Método | Rota | Descrição |
|---|---|---|
| POST | /clientes | Cria um cliente |
| GET | /clientes | Lista todos os clientes |
| GET | /clientes/{id} | Busca cliente por id |

### Contas

| Método | Rota | Descrição |
|---|---|---|
| POST | /contas | Cria uma conta (vinculada a um cliente) |
| GET | /contas | Lista todas as contas |
| GET | /contas/{id} | Busca conta por id |

### Transações

| Método | Rota | Descrição |
|---|---|---|
| POST | /transacoes | Realiza uma transferência entre duas contas |
| GET | /transacoes | Lista todas as transações |
| GET | /transacoes/{id} | Busca transação por id |

### Auditoria

| Método | Rota | Descrição |
|---|---|---|
| GET | /auditoria | Lista o histórico de alterações de saldo (gerado automaticamente por trigger) |

## Destaques técnicos

- Procedure PL/SQL com validação de regras de negócio (contas iguais, valor inválido, saldo insuficiente) e `FOR UPDATE` para prevenir race conditions em transferências concorrentes
- Trigger de auditoria (`AFTER UPDATE ... FOR EACH ROW`) que registra automaticamente todo histórico de mudanças de saldo, independente de qual operação a originou
- Transação gerenciada pelo Spring (`@Transactional`), delegando commit/rollback à aplicação em vez de controlar isso dentro da procedure
- Versionamento de banco de dados com Flyway
- Ambiente 100% reproduzível via Docker Compose (usuário do banco criado automaticamente no startup, via script de inicialização)
- Tratamento global de exceções (`@RestControllerAdvice`), incluindo extração de mensagens de erro customizadas vindas da procedure PL/SQL (`RAISE_APPLICATION_ERROR`)


## Próximos passos

- View para consulta de saldo total por cliente
- Operações de saque e depósito
