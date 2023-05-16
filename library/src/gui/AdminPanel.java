
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.*;

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
        header.setBounds(0, 0, 1000, 160);

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

        body = new JPanel();
        body.setBackground(c2);
        body.setBounds(0, 160, 1000, 540);
        getContentPane().add(body);

        books = new JButton();
        books.setBackground(c1);
        books.setForeground(c2);
        books.setFont(new Font("Tw Cen MT", 1, 20));
        books.setIcon(new ImageIcon(getClass().getResource("/resource/book.png")));
        books.setText("Book");
        books.setBorderPainted(false);
        books.setFocusPainted(false);
        header.add(books);
        books.setBounds(0,100,200,60);
        books.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                books.setBackground(c2);
                books.setForeground(c1);
                Book book = new Book();
                body.removeAll();
                body.add(book);
                body.revalidate();
                body.repaint();
            }
        });

        pack();
    }

    private JPanel header;
    private JLabel logo;
    private JLabel brand;
    private JLabel avatar;
    private JLabel user;
    private JPanel body;
    private JButton books;
    private JButton issues;
    private JButton returns;
    private JButton records;
    private JButton menu;
    private JPanel panel;

    final Color c1 = new Color(255, 255, 255);
    final Color c2 = new Color(37, 52, 57);
}