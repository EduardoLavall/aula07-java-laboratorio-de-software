# Aula 07 - Java Swing + MySQL (VS Code)

Projeto sem NetBeans, Maven ou Ant. Inclui o driver `mysql-connector-j-8.1.0.jar` baixado junto aos slides.

## Preparação

1. No MySQL, execute o arquivo `sql/bdaula01.sql`.
2. Em `src/conexao/Conexao.java`, ajuste `USUARIO` e `SENHA` para as credenciais locais.
3. Abra esta pasta no VS Code com a extensão **Extension Pack for Java** instalada.
4. Execute `src/app/Main.java` pelo botão **Run** do VS Code.

## Pelo terminal (PowerShell)

```powershell
javac -encoding UTF-8 -cp "lib/mysql-connector-j-8.1.0.jar" -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp "out;lib/mysql-connector-j-8.1.0.jar" app.Main
```

O projeto preserva o exemplo dos slides (`Conexao`, `Pessoa`, `PessoaDAO` e `Main`) e completa o CRUD na interface Swing: cadastro, consulta, atualização e exclusão.
