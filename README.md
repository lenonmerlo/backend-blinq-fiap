# Fintech (FIAP) — Java/Maven + Oracle

## 🇧🇷 PT-BR

Projeto acadêmico em **Java puro** (sem Spring) com **Maven**, integrando ao **Oracle Database (instância FIAP)**.  
Atividade da disciplina: **Exibir e Cadastrar informações de uma listagem do Fintech, agora com banco de dados**.

---

### ✅ Requisitos
- **Java JDK** 17+ (recomendado: 21)
- **Maven** 3.8+
- **IntelliJ IDEA** (recomendado)
- **Oracle SQL Developer** (para executar o DDL e validar inserts/selects)

---

### 🧱 Estrutura do Projeto
```
fintech/
├─ pom.xml
├─ src/
│  └─ main/
│     ├─ java/com/fiap/fintech/
│     │  ├─ App.java
│     │  ├─ factory/ConnectionFactory.java
│     │  ├─ dao/
│     │  │   ├─ ExpenseDAO.java
│     │  │   ├─ IncomeDAO.java
│     │  │   └─ InvestmentDAO.java
│     │  ├─ domain/
│     │  │   ├─ Expense.java
│     │  │   ├─ Income.java
│     │  │   ├─ Investment.java
│     │  │   ├─ Account.java
│     │  │   ├─ Client.java
│     │  │   ├─ CreditCard.java
│     │  │   ├─ Transaction.java
│     │  │   └─ TransactionType.java
│     └─ resources/db.properties
└─ test/
   └─ java/com/fiap/fintech/
      ├─ TestExpenseDAO.java
      ├─ TestIncomeDAO.java
      └─ TestInvestmentDAO.java
```

---

### ⚙️ Configuração do Banco
O arquivo `src/main/resources/db.properties` contém:
```
db.url=jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL
db.user=SEU_USUARIO
db.password=SUA_SENHA
```

- Execute o DDL exportado do **Oracle Data Modeler** no **SQL Developer**.
- Certifique-se de que as tabelas (`GASTO`, `RECEBIMENTO`, `INVESTIMENTO`, etc.) estejam criadas.

---

### ▶️ Como executar
**Via IntelliJ**
1. Abra o projeto (Maven).
2. Execute uma das classes de teste:
  - `TestExpenseDAO`
  - `TestIncomeDAO`
  - `TestInvestmentDAO`

**Via Maven (terminal)**
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=com.fiap.fintech.TestExpenseDAO
```
(substitua pelo teste desejado)

---

### 🧩 Funcionalidades implementadas
- **DAO** para acesso ao banco Oracle.
  - `insert()` → cadastra no banco.
  - `getAll()` → consulta e retorna lista de objetos.
- **Tratamento de exceções** com `try-catch`.
- **Testes** (`TestExpenseDAO`, `TestIncomeDAO`, `TestInvestmentDAO`) que:
  - Inserem 5 registros em cada entidade.
  - Consultam todos os registros e exibem no console.

---

### 📦 Como exportar para entrega (ZIP – FIAP)
1. No IntelliJ: `File > Export > Project to ZIP...`  
   (ou comprima manualmente a pasta do projeto incluindo `pom.xml` e `src/`).
2. Nomeie o arquivo como **GRUPO_XX.zip** (ou RM se individual).
3. Envie o `.zip` na plataforma FIAP ON.

---

## 🇺🇸 EN

Academic project in **plain Java** with **Maven**, integrated with **Oracle Database (FIAP instance)**.  
Assignment: **List, Insert and Retrieve Fintech entities using a database connection**.

### ✅ Requirements
- **Java JDK** 17+ (recommended: 21)
- **Maven** 3.8+
- **IntelliJ IDEA** (recommended)
- **Oracle SQL Developer** (to run DDL and validate inserts/selects)

### ▶️ How to run
**Using IntelliJ**
1. Open the Maven project.
2. Run one of the test classes:
  - `TestExpenseDAO`
  - `TestIncomeDAO`
  - `TestInvestmentDAO`

**Using Maven (terminal)**
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=com.fiap.fintech.TestExpenseDAO
```

### 🧩 Implemented Features
- **DAO classes** with `insert()` and `getAll()`
- **Exception handling** with `try-catch`
- **Tests** inserting 5 records per entity and retrieving them back

---
