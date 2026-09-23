# Aula 07 - Java Swing + MySQL (VS Code)

Projeto didático em Java Swing com acesso ao MySQL via JDBC, sem NetBeans, Maven ou Ant.

## Estrutura

```text
.
├── lib/
│   └── mysql-connector-j-8.1.0.jar
├── sql/
│   └── bdaula01.sql
├── src/
│   ├── app/
│   │   └── Main.java
│   ├── beans/
│   │   └── Pessoa.java
│   ├── conexao/
│   │   └── Conexao.java
│   ├── dao/
│   │   └── PessoaDAO.java
│   └── ui/
│       └── PessoaFrame.java
└── README.md
```

## Pré-requisitos

- Java/JDK instalado.
- MySQL em execução.
- VS Code com a extensão **Extension Pack for Java**.

## Preparação do banco

1. Execute o arquivo `sql/bdaula01.sql` no MySQL.
2. Configure as credenciais do banco por variáveis de ambiente.

No PowerShell:

```powershell
$env:DB_USER="root"
$env:DB_PASSWORD="SUA_SENHA"
```

Opcionalmente, a URL JDBC também pode ser alterada:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/bdaula01?useTimezone=true&serverTimezone=UTC"
```

Se `DB_USER` ou `DB_URL` não forem informadas, o projeto usa `root` e o banco local `bdaula01`. A senha não possui valor padrão.

## Executar pelo VS Code

Abra a raiz do repositório no VS Code e execute `src/app/Main.java` pelo botão **Run**.

## Compilar e executar pelo PowerShell

Na raiz do projeto:

```powershell
New-Item -ItemType Directory -Force out | Out-Null

javac -encoding UTF-8 -cp "lib/mysql-connector-j-8.1.0.jar" -d out (Get-ChildItem -Recurse src -Filter *.java).FullName

java -cp "out;lib/mysql-connector-j-8.1.0.jar" app.Main
```

## Funcionalidades

A aplicação implementa um CRUD simples de pessoas:

- Cadastro;
- Consulta/listagem;
- Atualização;
- Exclusão.

O exemplo é dividido em modelo (`Pessoa`), conexão JDBC (`Conexao`), acesso a dados (`PessoaDAO`), interface Swing (`PessoaFrame`) e ponto de entrada (`Main`).
