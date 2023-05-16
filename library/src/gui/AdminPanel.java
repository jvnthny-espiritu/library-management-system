
package gui;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {
    public AdminPanel() {
        initialize();
    }
    
    private void initialize() {
        setTitle("Library Management System - Admin Panel");
        setPreferredSize(new Dimension(700, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        header = new JPanel();
        header.setLayout(null);
        header.setBackground(c1);
        getContentPane().add(header);
        header.setBounds(0, 0, 400, 500);

        logo = new JLabel();
        logo.setIcon(new ImageIcon(getClass.getResource("/resource/logo.png")));
        logo.setText("");
        
    }

    private JPanel header;
    private JLabel logo;
    private JLabel user;
    private JLabel body;
    private JButton books;
    private JButton issues;
    private JButton returns;
    private JButton records;
    private JButton menu;
    private JPanel panel;

    final Color c1 = new Color(124, 137, 139);
    final Color c2 = new Color(37, 52, 57);
}