package model;

import java.awt.*;
import javax.swing.*;

import model.panel.Book;
import model.panel.Issue;
import model.panel.Record;
import model.panel.Return;
import model.panel.Student;

import java.awt.event.*;

public class Home extends JFrame {
    public Home(String name) {
        initialize(name);
        setLocationRelativeTo(null);
    }

    private void initialize(String name) {
        setTitle("Library Management System - Home");
        setIconImage(new Login().icon.getImage());
        setPreferredSize(new Dimension(1000, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        createHeader(name);
        createBody();
        createButtons();

        pack();
    }

    private void createHeader(String name) {
        header = new JPanel();
        header.setLayout(null);
        header.setBackground(Login.col_3);
        getContentPane().add(header);
        header.setBounds(0, 0, 1000, 160);

        logo = new JLabel();
        logo.setIcon(new ImageIcon(getClass().getResource("/resource/logo.png")));
        brand = new JLabel();
        brand.setText("Bookshelf");
        brand.setFont(new Font("Bookman Old Style", 1, 30));
        brand.setForeground(Login.col_2);
        header.add(logo);
        header.add(brand);
        logo.setBounds(5, 0, 90, 100);
        brand.setBounds(120, 5, 300, 90);

        avatar = new JLabel();
        avatar.setIcon(new ImageIcon(getClass().getResource("/resource/avatar.png")));
        user = new JLabel();
        user.setText(name);
        user.setFont(new Font("Tw Cen MT", 0, 18));
        user.setHorizontalAlignment(SwingConstants.RIGHT);
        user.setForeground(Login.col_2);
        header.add(avatar);
        header.add(user);
        avatar.setBounds(925, 25, 50, 50);
        user.setBounds(710, 40, 200, 20);
    }

    private void createBody() {
        body = new JPanel();
        body.setBackground(Login.col_2);
        body.setLayout(new CardLayout());
        body.setBounds(0, 160, 1000, 540);
        getContentPane().add(body);

        greet = new JLabel("Welcome!");
        greet.setForeground(Login.col_3);
        greet.setFont(new Font("Tw Cen MT", 1, 40));
        greet.setHorizontalAlignment(SwingConstants.CENTER);
        body.add(greet);
    }

    private void createButtons() {
        books = createButton("BOOK", "/resource/book.png");
        issues = createButton("ISSUE", "/resource/issueicn.png");
        returns = createButton("RETURN", "/resource/returnicn.png");
        records = createButton("RECORDS", "/resource/recordicn.png");
        student = createButton("STUDENT", "/resource/student.png");

        header.add(books);
        header.add(issues);
        header.add(returns);
        header.add(records);
        header.add(student);

        setButtonAction(books, Book.class);
        setButtonAction(issues, Issue.class);
        setButtonAction(returns, Return.class);
        setButtonAction(records, Record.class);
        setButtonAction(student, Student.class);

        int buttonX = 0;
        int buttonY = 100;
        int buttonWidth = 200;
        int buttonHeight = 60;
        int buttonGap = 200;

        books.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        issues.setBounds(buttonX + buttonGap, buttonY, buttonWidth, buttonHeight);
        returns.setBounds(buttonX + (buttonGap * 2), buttonY, buttonWidth, buttonHeight);
        records.setBounds(buttonX + (buttonGap * 3), buttonY, buttonWidth, buttonHeight);
        student.setBounds(buttonX + (buttonGap * 4), buttonY, buttonWidth, buttonHeight);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton();
        button.setBackground(Login.col_3);
        button.setForeground(Login.col_2);
        button.setFont(new Font("Tw Cen MT", 1, 20));
        button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        button.setText(text);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    private void setButtonAction(JButton button, Class<?> panelClass) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button.setBackground(Login.col_2);
                button.setForeground(Login.col_3);
                try {
                    JPanel panel = (JPanel) panelClass.getDeclaredConstructor().newInstance();
                    body.removeAll();
                    body.add(panel);
                    body.revalidate();
                    body.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void resetButtonColors() {
        books.setBackground(Login.col_3);
        books.setForeground(Login.col_2);

        issues.setBackground(Login.col_3);
        issues.setForeground(Login.col_2);

        returns.setBackground(Login.col_3);
        returns.setForeground(Login.col_2);

        records.setBackground(Login.col_3);
        records.setForeground(Login.col_2);

        student.setBackground(Login.col_3);
        student.setForeground(Login.col_2);
    }

    
    private JPanel header;
    private JLabel logo;
    private JLabel brand;
    private JLabel avatar;
    private JLabel user;
    private JLabel greet;
    private JPanel body;
    private JButton books;
    private JButton issues;
    private JButton returns;
    private JButton records;
    private JButton student;
}
