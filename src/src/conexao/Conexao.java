package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Configuracao de acesso ao MySQL. Ajuste usuario e senha para o seu ambiente. */
public final class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/bdaula01?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";

    private Conexao() { }

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
