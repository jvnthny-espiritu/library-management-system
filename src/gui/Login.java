
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

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

        passicon =new JLabel();
        passicon.setIcon(new ImageIcon(getClass().getResource("/resource/passicon.png")));
        right.add(passicon);
        passicon.setBounds(50, 230, 50, 50);

        passtxt = new JLabel();
        passtxt.setText("Password:");
        passtxt.setFont(new Font("Tw Cen MT", 0, 18));
        passtxt.setForeground(c1);
        right.add(passtxt);
        passtxt.setBounds(110, 230, 100, 20);

        passfld = new JPasswordField();
        passfld.setBackground(c2);
        passfld.setForeground(c1);
        passfld.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, c1));
        right.add(passfld);
        passfld.setBounds(110, 255, 120, 20);

        loginbtn = new JButton("LOGIN");
        loginbtn.setFont(new Font("Tw Cen MT", 0, 14));
        loginbtn.setBackground(c1);
        loginbtn.setForeground(c2);
        loginbtn.setBorderPainted(false);
        loginbtn.setFocusPainted(false);
        right.add(loginbtn);
        loginbtn.setBounds(110, 320, 80, 18);
        loginbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userfld.getText();
                String password = new String(passfld.getPassword());
                
                if (isValidLogin(username, password)) {
                    // Login successful
                    Login.this.dispose();
                    JOptionPane.showMessageDialog(Login.this, "Login successful!");
                    new AdminPanel().setVisible(true);
                } else {
                    // Login failed
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password. Please try again.");
                }
            }
        });

        pack();
    }

    private boolean isValidLogin(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String dbPassword = "root";
        
        try (Connection conn = DriverManager.getConnection(url, user, dbPassword)) {
            String query = "SELECT * FROM librarian WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            return resultSet.next(); // If a matching row is found, the login is valid
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Login failed due to an exception
        }
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
