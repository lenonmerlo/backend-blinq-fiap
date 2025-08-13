# Fintech (FIAP) — Java/Maven

## 🇧🇷 PT-BR

Projeto acadêmico em **Java puro** (sem Spring) para modelar as principais entidades de um sistema *Fintech*.
Os métodos exigidos pela atividade **apenas imprimem mensagens** com `System.out.println`, sem regras de negócio.

### ✅ Requisitos
- **Java JDK** 21 (ou 17+)
- **Maven** 3.8+
- IntelliJ IDEA (recomendado)

### 🧱 Estrutura do Projeto
```
fintech/
├─ pom.xml
└─ src/
   └─ main/
      └─ java/
         └─ com/fiap/fintech/
            ├─ App.java
            └─ domain/
               ├─ Account.java
               ├─ Client.java
               ├─ CreditCard.java
               ├─ Investment.java
               ├─ Transaction.java
               └─ TransactionType.java
```

### ▶️ Como executar
**Via IntelliJ**
1. Abra o projeto (Maven) no IntelliJ.
2. Navegue até `src/main/java/com/fiap/fintech/App.java`.
3. Clique em **Run** ▶️ para executar o `main`.

**Via Maven (terminal)**
```bash
mvn clean compile
mvn exec:java
```
> O `pom.xml` já está configurado para rodar `com.fiap.fintech.App` com o `exec-maven-plugin`.

### 🧩 Classes implementadas
- **Client**
  - **Atributos:** `id`, `fullName`, `cpf`, `email`, `registrationDate`
  - **Métodos:** `updateProfile()`, `activate()`, `deactivate()`
- **Account**
  - **Atributos:** `id`, `bank`, `branch`, `number`, `balance`
  - **Métodos:** `deposit(amount)`, `withdraw(amount)`, `transfer(destination, amount)`
- **CreditCard**
  - **Atributos:** `id`, `label`, `number`, `expiryMonth`, `expiryYear`, `limitAmount`
  - **Métodos:** `pay(amount, description)`, `generateInvoice()`, `increaseLimit(amount)`
- **Investment**
  - **Atributos:** `id`, `productName`, `amount`, `annualRate`
  - **Métodos:** `applyYield()`, `addContribution(value)`, `redeem(value)`
- **Transaction**
  - **Atributos:** `id`, `type (enum)`, `amount`, `description`, `createdAt`
  - **Métodos:** `register()`, `reverse()`
- **TransactionType (enum):** `INCOME`, `EXPENSE`

> Observação: os métodos **não** implementam lógica financeira; exibem mensagens descritivas por exigência da atividade.

### 📦 Como exportar para entrega (ZIP – FIAP)
1. No IntelliJ, clique com o botão direito no **nome do projeto** → **Open in → Explorer**.
2. **Compacte a pasta do projeto inteira** (incluindo `pom.xml`) em **.zip**.
3. Envie o `.zip` na plataforma conforme instruções do enunciado.

---

## 🇺🇸 EN

Academic project in **plain Java** (no Spring) modeling core *Fintech* entities.
Per assignment rules, all methods **only print messages** using `System.out.println`—no business logic.

### ✅ Requirements
- **Java JDK** 21 (or 17+)
- **Maven** 3.8+
- IntelliJ IDEA (recommended)

### 🧱 Project Structure
```
fintech/
├─ pom.xml
└─ src/
   └─ main/
      └─ java/
         └─ com/fiap/fintech/
            ├─ App.java
            └─ domain/
               ├─ Account.java
               ├─ Client.java
               ├─ CreditCard.java
               ├─ Investment.java
               ├─ Transaction.java
               └─ TransactionType.java
```

### ▶️ How to run
**Using IntelliJ**
1. Open the Maven project in IntelliJ.
2. Go to `src/main/java/com/fiap/fintech/App.java`.
3. Click **Run** ▶️ to execute the `main` method.

**Using Maven (terminal)**
```bash
mvn clean compile
mvn exec:java
```
> `pom.xml` is already configured with `exec-maven-plugin` to run `com.fiap.fintech.App`.

### 🧩 Implemented Classes
- **Client**
  - **Fields:** `id`, `fullName`, `cpf`, `email`, `registrationDate`
  - **Methods:** `updateProfile()`, `activate()`, `deactivate()`
- **Account**
  - **Fields:** `id`, `bank`, `branch`, `number`, `balance`
  - **Methods:** `deposit(amount)`, `withdraw(amount)`, `transfer(destination, amount)`
- **CreditCard**
  - **Fields:** `id`, `label`, `number`, `expiryMonth`, `expiryYear`, `limitAmount`
  - **Methods:** `pay(amount, description)`, `generateInvoice()`, `increaseLimit(amount)`
- **Investment**
  - **Fields:** `id`, `productName`, `amount`, `annualRate`
  - **Methods:** `applyYield()`, `addContribution(value)`, `redeem(value)`
- **Transaction**
  - **Fields:** `id`, `type (enum)`, `amount`, `description`, `createdAt`
  - **Methods:** `register()`, `reverse()`
- **TransactionType (enum):** `INCOME`, `EXPENSE`

> Note: methods **do not** implement business rules; they print descriptive messages as required by the assignment.

### 📦 Packaging for submission (ZIP)
1. In IntelliJ, right-click the **project name** → **Open in → Explorer**.
2. **Zip the entire project folder** (including `pom.xml`) into a **.zip** file.
3. Submit the `.zip` on the platform per assignment instructions.
