package ui;

import beans.Pessoa;
import dao.PessoaDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Interface Swing para exercitar INSERT, SELECT, UPDATE e DELETE. */
public class PessoaFrame extends JFrame {
    private final JTextField txtNome = new JTextField(22);
    private final JComboBox<String> cbSexo = new JComboBox<>(new String[] { "F", "M", "Outro" });
    private final JTextField txtIdioma = new JTextField(22);
    private final DefaultTableModel modelo = new DefaultTableModel(new String[] { "ID", "Nome", "Sexo", "Idioma" }, 0) {
        @Override public boolean isCellEditable(int linha, int coluna) { return false; }
    };
    private final JTable tabela = new JTable(modelo);
    private final PessoaDAO dao = new PessoaDAO();
    private Integer idSelecionado;

    public PessoaFrame() {
        super("Cadastro de Pessoas - Aula 07");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 420);
        setLocationRelativeTo(null);

        JPanel formulario = new JPanel(new GridLayout(3, 2, 8, 8));
        formulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formulario.add(new JLabel("Nome:")); formulario.add(txtNome);
        formulario.add(new JLabel("Sexo:")); formulario.add(cbSexo);
        formulario.add(new JLabel("Idioma:")); formulario.add(txtIdioma);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton cadastrar = new JButton("Cadastrar");
        JButton atualizar = new JButton("Atualizar");
        JButton excluir = new JButton("Excluir");
        JButton limpar = new JButton("Limpar");
        botoes.add(cadastrar); botoes.add(atualizar); botoes.add(excluir); botoes.add(limpar);
        JPanel topo = new JPanel(new BorderLayout());
        topo.add(formulario, BorderLayout.CENTER); topo.add(botoes, BorderLayout.SOUTH);
        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        cadastrar.addActionListener(e -> cadastrar());
        atualizar.addActionListener(e -> atualizar());
        excluir.addActionListener(e -> excluir());
        limpar.addActionListener(e -> limpar());
        tabela.getSelectionModel().addListSelectionListener(e -> preencherFormulario());
        carregarTabela();
    }

    private Pessoa pessoaDoFormulario() {
        String nome = txtNome.getText().trim();
        String idioma = txtIdioma.getText().trim();
        if (nome.isEmpty() || idioma.isEmpty()) throw new IllegalArgumentException("Informe nome e idioma.");
        Pessoa pessoa = new Pessoa(nome, (String) cbSexo.getSelectedItem(), idioma);
        if (idSelecionado != null) pessoa.setId(idSelecionado);
        return pessoa;
    }
    private void cadastrar() { try { dao.inserir(pessoaDoFormulario()); limpar(); carregarTabela(); } catch (Exception e) { erro(e); } }
    private void atualizar() { if (idSelecionado == null) { aviso("Selecione uma pessoa na tabela."); return; } try { dao.atualizar(pessoaDoFormulario()); limpar(); carregarTabela(); } catch (Exception e) { erro(e); } }
    private void excluir() { if (idSelecionado == null) { aviso("Selecione uma pessoa na tabela."); return; } try { dao.excluir(idSelecionado); limpar(); carregarTabela(); } catch (SQLException e) { erro(e); } }
    private void carregarTabela() { try { modelo.setRowCount(0); for (Pessoa p : dao.listar()) modelo.addRow(new Object[] { p.getId(), p.getNome(), p.getSexo(), p.getIdioma() }); } catch (SQLException e) { erro(e); } }
    private void preencherFormulario() { int linha = tabela.getSelectedRow(); if (linha < 0) return; idSelecionado = (Integer) modelo.getValueAt(linha, 0); txtNome.setText((String) modelo.getValueAt(linha, 1)); cbSexo.setSelectedItem(modelo.getValueAt(linha, 2)); txtIdioma.setText((String) modelo.getValueAt(linha, 3)); }
    private void limpar() { idSelecionado = null; tabela.clearSelection(); txtNome.setText(""); txtIdioma.setText(""); cbSexo.setSelectedIndex(0); }
    private void aviso(String mensagem) { JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE); }
    private void erro(Exception e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); }
}
