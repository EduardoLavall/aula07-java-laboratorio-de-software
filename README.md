# Aula 07 - Java Swing + MySQL

Projeto baseado nos slides da Aula 07 de Laboratório de Desenvolvimento de Software.

A implementação foi mantida simples e próxima ao exemplo apresentado em aula:

- conexão com MySQL pela classe `Conexao`;
- classe `Pessoa` no pacote `beans`;
- classe `PessoaDAO` responsável pelo `INSERT`;
- interface Swing simples para cadastrar pessoas;
- exercícios SQL da aula incluídos em `sql/bdaula01.sql`.

## Estrutura

```text
.
├── drivers/
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

## Banco de dados

Execute `sql/bdaula01.sql` no MySQL.

O arquivo contém os exemplos e exercícios da aula:

- criação do banco `BDAula01`;
- criação da tabela `pessoa`;
- inserções e consulta de pessoas;
- criação do banco `escola`;
- tabelas `alunos`, `professores` e `matriculas`;
- cinco alunos;
- três professores;
- cinco matrículas;
- consulta de alunos e seus cursos;
- consulta de professores e suas disciplinas.

## Conexão

A classe `src/conexao/Conexao.java` segue o exemplo da aula:

```text
usuário: root
senha: 1234
banco: bdaula01
```

No laboratório, a senha pode ser diferente. Se necessário, altere apenas a senha na classe `Conexao`.

## Interface

A interface segue a Atividade 1 dos slides:

- Nome em campo de texto;
- Sexo com `JRadioButton` para Masculino e Feminino;
- Idioma em `JComboBox`;
- botão `Salvar`;
- cadastro usando `PessoaDAO.inserir()`.

## Executar

Abra o projeto e execute `src/app/Main.java`.

Pelo PowerShell:

```powershell
New-Item -ItemType Directory -Force out | Out-Null

javac -encoding UTF-8 -cp "drivers/mysql-connector-j-8.1.0.jar" -d out (Get-ChildItem -Recurse src -Filter *.java).FullName

java -cp "out;drivers/mysql-connector-j-8.1.0.jar" app.Main
```

## Próxima atividade

A **Atividade 2** dos slides — conexão com o banco `escola` e cadastro de Alunos e Professores — fica para a próxima etapa e ainda não foi implementada.
