import javax.swing.SwingUtilities;

import model.Login;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}