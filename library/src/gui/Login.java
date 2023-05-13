
package gui;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    public Login() {
        initialize();
    }
    
    private void initialize() {

        setTitle("Library Management System - Login");
        setSize(700, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        left = new JPanel();
        left.setSize(400, 500);
        left.setLayout(null);
        left.setBackground(c1);
        add(left, BorderLayout.WEST);

        greet = new JLabel();
        greet.setText("Welcome to Library");
        greet.setBounds(0, 50, 400, 40);
        greet.setFont(new Font("Tw Cen MT", 1, 36));
        greet.setForeground(c2);
        greet.setHorizontalAlignment(SwingConstants.CENTER);
        left.add(greet);

        banner = new JLabel();
        ImageIcon img = new ImageIcon("/resource/banner.png");
        banner.setIcon(img);
        left.add(banner);
    }

    private JPanel left;
    private JLabel greet;
    private JLabel banner;
    private JPanel right;
    private JLabel usertxt;
    private JLabel usericon;
    private JTextField userfld;
    private JLabel passtxt;
    private JLabel passicon;
    private JPasswordField passfld;
    private JButton loginbtn;

    final Color c1 = new Color(124, 137, 139);
    final Color c2 = new Color(37, 52, 57);
}
