
package gui;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {
    public AdminPanel() {
        initialize();
    }
    
    private void initialize() {
        setTitle("Library Management System - Admin Panel");
        setPreferredSize(new Dimension(1000, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        header = new JPanel();
        header.setLayout(null);
        header.setBackground(c1);
        getContentPane().add(header);
        header.setBounds(0, 0, 1000, 100);

        logo = new JLabel();
        logo.setIcon(new ImageIcon(getClass().getResource("/resource/logo.png")));
        brand = new JLabel();
        brand.setText("Bookshelf");
        brand.setFont(new Font("Bookman Old Style", 1, 30));
        brand.setForeground(c2);
        header.add(logo);
        header.add(brand);
        logo.setBounds(5, 0, 90, 100);
        brand.setBounds(120, 5, 300, 90);

        avatar = new JLabel();
        avatar.setIcon(new ImageIcon(getClass().getResource("/resource/avatar.png")));
        user = new JLabel();
        user.setText("Librarian");
        user.setFont(new Font("Tw Cen MT", 0, 18));
        user.setHorizontalAlignment(SwingConstants.RIGHT);
        user.setForeground(c2);
        header.add(avatar);
        header.add(user);
        avatar.setBounds(925, 25, 50, 50);
        user.setBounds(710, 40, 200, 20);

        pack();
    }

    private JPanel header;
    private JLabel logo;
    private JLabel brand;
    private JLabel avatar;
    private JLabel user;
    private JLabel body;
    private JButton books;
    private JButton issues;
    private JButton returns;
    private JButton records;
    private JButton menu;
    private JPanel panel;

    final Color c1 = new Color(255, 255, 255);
    final Color c2 = new Color(37, 52, 57);
}