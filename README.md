# Fintech API — Spring Boot + Oracle

API REST do projeto **Fintech (FIAP)**, construída em **Java 17 / Spring Boot 3**, com **JPA/Hibernate** e **Oracle Database** (instância FIAP).

## ⚙️ Stack

- Java 17
- Spring Boot (web, data-jpa, validation)
- Oracle JDBC (ojdbc11)
- BCrypt para hash de senha (`spring-security-crypto`)
- JPA/Hibernate com **sequences Oracle**

---

## 🚀 Como rodar

### 1) Configurar o Oracle

#### **Aviso (Ambiente Acadêmico)**: As credenciais do Oracle usadas pela turma estão *intencionalmente versionadas no application.yml* para facilitar a correção e a execução compartilhada. Em produção, moveríamos para variáveis de ambiente/secret manager.

No seu Oracle (FIAP), garanta as sequences (ajuste o `START WITH` se precisar):

```sql
-- Gasto
CREATE SEQUENCE GASTO_SEQ        START WITH 51  INCREMENT BY 1 NOCACHE;

-- Recebimento (Income)
CREATE SEQUENCE RECEBIMENTO_SEQ  START WITH 121 INCREMENT BY 1 NOCACHE;

-- Investimento
CREATE SEQUENCE INVESTIMENTO_SEQ START WITH 311 INCREMENT BY 1 NOCACHE;

-- Usuário
CREATE SEQUENCE USUARIO_SEQ      START WITH 1   INCREMENT BY 1 NOCACHE;

```

Na tabela **USUARIO**, os campos de data podem ter default no banco (opcional) e os índices únicos:

```sql
ALTER TABLE USUARIO MODIFY (DATA_CRIACAO DEFAULT SYSTIMESTAMP);
ALTER TABLE USUARIO MODIFY (ULTIMO_LOGIN DEFAULT SYSTIMESTAMP);

-- Unicidade
ALTER TABLE USUARIO ADD CONSTRAINT USUARIO_USERNAME_UK UNIQUE (USERNAME);
ALTER TABLE USUARIO ADD CONSTRAINT USUARIO_EMAIL_UK    UNIQUE (EMAIL);

```

### 2) `application.properties` (exemplo)

`src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:oracle:thin:@//<host>:<port>/<service>
spring.datasource.username=RMXXXXX
spring.datasource.password=SUASENHA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

```

### 3) Build e run

```bash
./mvnw clean package
java -jar target/fintech-*.jar
# ou direto pelo IntelliJ (Run FintechApplication)

```

Base URL (default): [**http://localhost:8080**](http://localhost:8080/)

---

## 🧱 Entidades

- **Expense** (tabela `GASTO`)
- **Income** (tabela `RECEBIMENTO`)
- **Investment** (tabela `INVESTIMENTO`)
- **User** (tabela `USUARIO`)

---

## 🔒 Padrão de Erros (handler global)

Quando ocorre erro, a API retorna JSON no formato:

```json
{
  "timestamp": "2025-10-20T22:13:39.067084400Z",
  "status": 400,
  "error": "Bad Request",
  "message": "campo X: não deve estar em branco",
  "path": "/api/..."
}

```

Códigos mais comuns: **200/201/204**, **400/404/405/409/415/422**, **500**.

---

## 📚 Endpoints

### Users

**Modelo (resumo)**

- `id: Long`
- `username: String` (único)
- `email: String` (único)
- `passwordHash: String` (interno)
- `status: "ATIVO" | "INATIVO"`
- `createdAt: LocalDateTime`
- `lastLogin: LocalDateTime`

### Listar (paginação)

`GET /api/users?page=0&size=10` → **200 OK**

### Buscar por id

`GET /api/users/{id}` → **200 OK** | **404 Not Found**

### Criar

`POST /api/users` → **201 Created**

Body:

```json
{
  "username": "teste.user",
  "password": "Senha@123",
  "email": "teste.user@example.com",
  "status": "ATIVO"
}

```

**Observações**

- `password` é salvo com **BCrypt** (hash) — nunca retorna na resposta.
- `status` pode ser omitido; default: **ATIVO** (via `@PrePersist`).
- `createdAt` e `lastLogin` são preenchidos no `@PrePersist`.

### Atualizar (parcial de dados públicos)

`PUT /api/users/{id}` → **200 OK** | **404** | **409** (duplicidade)

Body:

```json
{
  "username": "novo.user",
  "email": "novo.email@example.com",
  "status": "ATIVO"
}

```

### Alterar senha

`PATCH /api/users/{id}/password` → **204 No Content** | **404**

Body:

```json
{
  "newPassword": "NovaSenha@123"
}

```

### Excluir

`DELETE /api/users/{id}` → **204 No Content** | **404**

---

### Expenses (Gastos)

**Modelo (resumo)**

- `id: Long` (sequence `GASTO_SEQ`)
- `date: LocalDate`
- `amount: BigDecimal`
- `description: String`
- `paymentMethod: String`

### Listar (paginação)

`GET /api/expenses?page=0&size=10` → **200**

### Buscar

`GET /api/expenses/{id}` → **200** | **404**

### Criar

`POST /api/expenses` → **201**

Body:

```json
{
  "date": "2025-10-20",
  "amount": 120.0,
  "description": "Mercado",
  "paymentMethod": "PIX"
}

```

### Atualizar

`PUT /api/expenses/{id}` → **200** | **404**

Body:

```json
{
  "date": "2025-10-21",
  "amount": 150.0,
  "description": "Mercado (ajuste)",
  "paymentMethod": "Débito"
}

```

### Excluir

`DELETE /api/expenses/{id}` → **204** | **404**

---

### Incomes (Recebimentos)

**Modelo (resumo)**

- `id: Long` (sequence `RECEBIMENTO_SEQ`)
- `date: LocalDate`
- `amount: BigDecimal`
- `description: String`
- `source: String` *(pode existir, dependendo da sua versão; quando obrigatório, enviar)*

### Listar

`GET /api/incomes?page=0&size=10` → **200**

### Buscar

`GET /api/incomes/{id}` → **200** | **404**

### Criar

`POST /api/incomes` → **201**

Body (exemplo):

```json
{
  "date": "2025-10-20",
  "amount": 5000.0,
  "description": "Salário",
  "source": "Empresa X"
}

```

### Atualizar

`PUT /api/incomes/{id}` → **200** | **404**

Body:

```json
{
  "date": "2025-10-20",
  "amount": 5100.0,
  "description": "Salário - reajuste",
  "source": "Empresa X"
}

```

### Excluir

`DELETE /api/incomes/{id}` → **204** | **404**

---

### Investments (Investimentos)

**Modelo (resumo)**

- `id: Long` (sequence `INVESTIMENTO_SEQ`)
- `applicationDate: LocalDate`
- `amountApplied: BigDecimal` (>= 0.01)
- `annualRate: BigDecimal` (ex.: 0.12 → 12%)
- `months: Integer` (>= 1)
- `productName: String` (ex.: “CDB 100% CDI”)

### Listar

`GET /api/investments?page=0&size=10` → **200**

### Buscar

`GET /api/investments/{id}` → **200** | **404**

### Criar

`POST /api/investments` → **201**

Body:

```json
{
  "applicationDate": "2025-10-20",
  "amountApplied": 1000.0,
  "annualRate": 0.12,
  "months": 12,
  "productName": "CDB 100% CDI"
}

```

### Atualizar

`PUT /api/investments/{id}` → **200** | **404**

Body:

```json
{
  "applicationDate": "2025-10-21",
  "amountApplied": 2000.0,
  "annualRate": 0.115,
  "months": 18,
  "productName": "LCI 95% CDI"
}

```

### Excluir

`DELETE /api/investments/{id}` → **204** | **404**

---

## ✅ Códigos HTTP por operação

- **GET (listar/buscar)**: 200
- **POST (criar)**: 201 + `Location`
- **PUT/PATCH**: 200 (ou 204, conforme implementação)
- **DELETE**: 204
- **Erros de validação**: 400
- **Registro não encontrado**: 404
- **Método não permitido**: 405
- **Conflito (chave única / FK / duplicidade)**: 409
- **Mídia não suportada**: 415
- **Regra de negócio**: 422
- **Erro inesperado**: 500

---

## 🔐 Senhas e Segurança

- Senhas são **hash** com **BCrypt** (`PasswordEncoder` via `CryptoConfig`).
- O endpoint de criação de usuário aceita `password` em texto claro e armazena apenas o **hash**.
- Para login (quando implementado), validar com `passwordEncoder.matches(raw, hash)`.

---

## 🧪 Dicas de teste (curl)

```bash
# Criar usuário
curl -X POST http://localhost:8080/api/users \
 -H "Content-Type: application/json" \
 -d '{"username":"teste.user","password":"Senha@123","email":"teste.user@example.com","status":"ATIVO"}'

# Alterar senha
curl -X PATCH http://localhost:8080/api/users/1/password \
 -H "Content-Type: application/json" \
 -d '{"newPassword":"NovaSenha@123"}'

# Criar investment
curl -X POST http://localhost:8080/api/investments \
 -H "Content-Type: application/json" \
 -d '{"applicationDate":"2025-10-20","amountApplied":1000,"annualRate":0.12,"months":12,"productName":"CDB 100% CDI"}'

```

---

## 📁 Organização (principais pacotes)

```
com.fiap.fintech
├─ controller/     # REST Controllers
├─ domain/         # Entidades JPA (@Entity)
├─ dto/            # DTOs de request/response
├─ repository/     # Spring Data JPA
├─ service/        # Regras de negócio
├─ exception/      # ApiExceptionHandler, ApiError
└─ config/         # CryptoConfig (BCrypt)

```

---

## 📝 Licença

Uso acadêmico (FIAP).