package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Configuração de acesso ao MySQL por variáveis de ambiente. */
public final class Conexao {
    private static final String URL = valorOuPadrao(
        System.getenv("DB_URL"),
        "jdbc:mysql://localhost:3306/bdaula01?useTimezone=true&serverTimezone=UTC"
    );
    private static final String USUARIO = valorOuPadrao(System.getenv("DB_USER"), "root");
    private static final String SENHA = valorOuPadrao(System.getenv("DB_PASSWORD"), "");

    private Conexao() { }

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    private static String valorOuPadrao(String valor, String padrao) {
        return valor == null || valor.isBlank() ? padrao : valor;
    }
}
