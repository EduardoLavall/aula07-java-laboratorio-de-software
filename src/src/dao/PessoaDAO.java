package dao;

import beans.Pessoa;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** DAO da tabela pessoa: inserir, consultar, editar e excluir. */
public class PessoaDAO {
    public void inserir(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, sexo, idioma) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preencher(stmt, pessoa);
            stmt.executeUpdate();
            try (ResultSet chaves = stmt.getGeneratedKeys()) {
                if (chaves.next()) pessoa.setId(chaves.getInt(1));
            }
        }
    }

    public List<Pessoa> listar() throws SQLException {
        String sql = "SELECT id, nome, sexo, idioma FROM pessoa ORDER BY id";
        List<Pessoa> pessoas = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) pessoas.add(mapear(rs));
        }
        return pessoas;
    }

    public void atualizar(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE pessoa SET nome = ?, sexo = ?, idioma = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            preencher(stmt, pessoa);
            stmt.setInt(4, pessoa.getId());
            if (stmt.executeUpdate() == 0) throw new SQLException("Pessoa nao encontrada: id " + pessoa.getId());
        }
    }

    public void excluir(int id) throws SQLException {
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM pessoa WHERE id = ?")) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() == 0) throw new SQLException("Pessoa nao encontrada: id " + id);
        }
    }

    private void preencher(PreparedStatement stmt, Pessoa pessoa) throws SQLException {
        stmt.setString(1, pessoa.getNome());
        stmt.setString(2, pessoa.getSexo());
        stmt.setString(3, pessoa.getIdioma());
    }

    private Pessoa mapear(ResultSet rs) throws SQLException {
        Pessoa pessoa = new Pessoa(rs.getString("nome"), rs.getString("sexo"), rs.getString("idioma"));
        pessoa.setId(rs.getInt("id"));
        return pessoa;
    }
}
