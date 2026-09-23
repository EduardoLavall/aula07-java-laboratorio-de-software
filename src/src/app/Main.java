package app;

import javax.swing.SwingUtilities;
import ui.PessoaFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PessoaFrame().setVisible(true));
    }
}
