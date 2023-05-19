package model;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame {
    public Login() {
        initialize();
        setLocationRelativeTo(null);
    }
    
    private void initialize() {
        setTitle("Library Management System - Login");
        setIconImage(icon.getImage());
        setPreferredSize(new Dimension(700, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        createLeftPanel();
        createRightPanel();

        pack();
    }
    
    private void createLeftPanel() {
        left = new JPanel();
        left.setLayout(null);
        left.setBackground(col_1);
        getContentPane().add(left);
        left.setBounds(0, 0, 400, 500);

        greet = new JLabel();
        greet.setText("Welcome to Library");
        greet.setFont(new Font("Tw Cen MT", Font.BOLD, 36));
        greet.setHorizontalAlignment(SwingConstants.CENTER);
        greet.setForeground(col_2);
        left.add(greet);
        greet.setBounds(0, 100, 400, 40);

        banner = new JLabel();
        banner.setIcon(new ImageIcon(getClass().getResource("/resource/banner.png")));
        left.add(banner);
        banner.setBounds(75, 225, 250, 135);
    }
    
    private void createRightPanel() {
        right = new JPanel();
        right.setLayout(null);
        right.setBackground(col_2);
        getContentPane().add(right);
        right.setBounds(400, 0, 300, 500);

        usericon = new JLabel();
        usericon.setIcon(new ImageIcon(getClass().getResource("/resource/usericon.png")));
        right.add(usericon);
        usericon.setBounds(50, 150, 50, 50);

        usertxt = new JLabel();
        usertxt.setText("Username:");
        usertxt.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
        usertxt.setForeground(col_1);
        right.add(usertxt);
        usertxt.setBounds(110, 150, 100, 20);

        userfld = new JTextField();
        userfld.setBackground(col_2);
        userfld.setForeground(col_1);
        userfld.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col_1));
        right.add(userfld);
        userfld.setBounds(110, 175, 120, 20);

        passicon = new JLabel();
        passicon.setIcon(new ImageIcon(getClass().getResource("/resource/passicon.png")));
        right.add(passicon);
        passicon.setBounds(50, 230, 50, 50);

        passtxt = new JLabel();
        passtxt.setText("Password:");
        passtxt.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
        passtxt.setForeground(col_1);
        right.add(passtxt);
        passtxt.setBounds(110, 230, 100, 20);

        passfld = new JPasswordField();
        passfld.setBackground(col_2);
        passfld.setForeground(col_1);
        passfld.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, col_1));
        right.add(passfld);
        passfld.setBounds(110, 255, 120, 20);

        loginbtn = new JButton("LOGIN");
        loginbtn.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
        loginbtn.setBackground(col_1);
        loginbtn.setForeground(col_2);
        loginbtn.setBorderPainted(false);
        loginbtn.setFocusPainted(false);
        right.add(loginbtn);
        loginbtn.setBounds(110, 320, 80, 18);
        
        loginbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userfld.getText();
                String password = new String(passfld.getPassword());
                
                String adminName = getAdminName(username, password);
                if (adminName != null) {
                    Login.this.dispose();
                    JOptionPane.showMessageDialog(Login.this, "Login successful!");
                    new Home(adminName).setVisible(true); // Pass the admin name to AdminPanel constructor
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password. Please try again.");
                }
            }
        });
    }

    private String getAdminName(String username, String password) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM librarian WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                return name;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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

    final public static Color col_1 = new Color(124, 137, 139);
    final public static Color col_2 = new Color(37, 52, 57);
    final public static Color col_3 = new Color(255, 255, 255);
    final public ImageIcon icon = new ImageIcon(getClass().getResource("/resource/icon_book.png"));
}
