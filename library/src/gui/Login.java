
package gui;

import javax.swing.*;
import java.awt.*;
public class Login extends JFrame {
    public Login() {
        initialize();
    }
    
    private void initialize() {

        setTitle("Library Management System - Login");
        setPreferredSize(new Dimension(700, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        left = new JPanel();
        left.setLayout(null);
        left.setBackground(c1);
        getContentPane().add(left);
        left.setBounds(0, 0, 400, 500);

        greet = new JLabel();
        greet.setText("Welcome to Library");
        greet.setFont(new Font("Tw Cen MT", 1, 36));
        greet.setHorizontalAlignment(SwingConstants.CENTER);
        greet.setForeground(c2);
        left.add(greet);
        greet.setBounds(0, 100, 400, 40);

        banner = new JLabel();
        banner.setIcon(new ImageIcon(getClass().getResource("/resource/banner.png")));
        left.add(banner);
        banner.setBounds(75, 225, 250, 135);

        right = new JPanel();
        right.setLayout(null);
        right.setBackground(c2);
        getContentPane().add(right);
        right.setBounds(400, 0, 300, 500);

        usericon =new JLabel();
        usericon.setIcon(new ImageIcon(getClass().getResource("/resource/usericon.png")));
        right.add(usericon);
        usericon.setBounds(50, 150, 50, 50);

        usertxt = new JLabel();
        usertxt.setText("Username:");
        usertxt.setFont(new Font("Tw Cen MT", 0, 18));
        usertxt.setForeground(c1);
        right.add(usertxt);
        usertxt.setBounds(110, 150, 100, 20);

        userfld = new JTextField();
        userfld.setBackground(c2);
        userfld.setForeground(c1);
        userfld.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, c1));
        right.add(userfld);
        userfld.setBounds(110, 175, 120, 20);

        

        pack();
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
