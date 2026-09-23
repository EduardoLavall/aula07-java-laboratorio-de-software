package ui;

import beans.Pessoa;
import dao.PessoaDAO;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PessoaFrame extends JFrame {

    private final JTextField txtNome = new JTextField(15);

    private final JRadioButton rbMasculino = new JRadioButton("Masculino");
    private final JRadioButton rbFeminino = new JRadioButton("Feminino");

    private final JComboBox<String> cbIdioma = new JComboBox<>(
        new String[] { "Português", "Inglês", "Espanhol", "Alemão", "Holandês" }
    );

    private final JButton btnSalvar = new JButton("Salvar");

    public PessoaFrame() {
        super("Cadastro de Pessoa");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 220);
        setLocationRelativeTo(null);

        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(rbMasculino);
        grupoSexo.add(rbFeminino);

        JPanel painelSexo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSexo.add(rbMasculino);
        painelSexo.add(rbFeminino);

        JPanel formulario = new JPanel(new GridLayout(4, 2, 5, 5));

        formulario.add(new JLabel("Nome:"));
        formulario.add(txtNome);

        formulario.add(new JLabel("Sexo:"));
        formulario.add(painelSexo);

        formulario.add(new JLabel("Idioma:"));
        formulario.add(cbIdioma);

        formulario.add(new JLabel(""));
        formulario.add(btnSalvar);

        add(formulario);

        btnSalvar.addActionListener(e -> salvar());
    }

    private void salvar() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome.");
            return;
        }

        if (!rbMasculino.isSelected() && !rbFeminino.isSelected()) {
            JOptionPane.showMessageDialog(this, "Selecione o sexo.");
            return;
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(txtNome.getText().trim());
        pessoa.setSexo(rbMasculino.isSelected() ? "M" : "F");
        pessoa.setIdioma((String) cbIdioma.getSelectedItem());

        PessoaDAO pDAO = new PessoaDAO();
        pDAO.inserir(pessoa);

        JOptionPane.showMessageDialog(this, "Pessoa cadastrada!");

        txtNome.setText("");
        grupoSexo.clearSelection();
        cbIdioma.setSelectedIndex(0);
    }

    private final ButtonGroup grupoSexo = criarGrupoSexo();

    private ButtonGroup criarGrupoSexo() {
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbMasculino);
        grupo.add(rbFeminino);
        return grupo;
    }
}
