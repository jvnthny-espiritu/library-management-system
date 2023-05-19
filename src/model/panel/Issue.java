package model.panel;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

import model.DatabaseConnector;
import model.Login;

import java.awt.event.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Issue extends JPanel {
    public Issue() {
        initialize();
    }

    private void initialize() {
        setSize(1000, 540);
        setBackground(Login.col_2);
        setLayout(null);

        label1 = createLabel("STUDENT ID:", 125, 100);
        label2 = createLabel("STUDENT'S NAME:", 125, 129);
        label3 = createLabel("BOOK TITLE:", 125, 158);
        label4 = createLabel("DATE BORROWED:", 125, 187);
        label5 = createLabel("DUE DATE:", 125, 216);

        txtfld1 = createTextField(Login.col_3);
        txtfld1.setBounds(375, 100, 300, 24);
        txtfld1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int studentId = Integer.parseInt(txtfld1.getText());
                    String studentName = getName(studentId);
                    txtfld2.setText(studentName);
                }
            }
        });

        txtfld2 = createTextField(Login.col_3);
        txtfld2.setBounds(375, 129, 300, 24);

        txtfld3 = createTextField(Login.col_3);
        txtfld3.setBounds(375, 158, 300, 24);

        txtfld4 = createTextField(Login.col_3);
        txtfld4.setBounds(375, 187, 300, 24);

        txtfld5 = createTextField(Login.col_3);
        txtfld5.setBounds(375, 216, 300, 24);

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formatDate();
            }
        });
        timer.start();

        add = createButton("SAVE", new Font("Tw Cen MT", 0, 14), Login.col_3, Login.col_2);
        add.setBounds(410, 320, 80, 25);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentID = txtfld1.getText();
                String studentName = txtfld2.getText();
                String bookTitle = txtfld3.getText();
                String issueDate = txtfld4.getText();
                String dueDate = txtfld5.getText();

                if (save(studentID, studentName, bookTitle, issueDate, dueDate)) {
                    JOptionPane.showMessageDialog(Issue.this, "Issue saved successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(Issue.this, "Failed to issue book. Please try again.");
                }
            }
        });

        cancel = createButton("CANCEL", new Font("Tw Cen MT", 0, 14), Login.col_3, Login.col_2);
        cancel.setBounds(510, 320, 80, 25);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        addComponents();
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(f1);
        label.setForeground(Login.col_3);
        add(label);
        label.setBounds(x, y, 200, 24);
        return label;
    }

    private JButton createButton(String text, Font font, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        return button;
    }

    private JTextField createTextField(Color background) {
        JTextField textField = new JTextField();
        textField.setBackground(background);
        return textField;
    }

    private void addComponents() {
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(txtfld1);
        add(txtfld2);
        add(txtfld3);
        add(txtfld4);
        add(txtfld5);
        add(add);
        add(cancel);
    }

    private boolean save(String studentID, String studentName, String bookTitle, String issueDate, String dueDate) {    
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO records (student_id, book_title, date_borrowed, due_date, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, studentID);
            statement.setString(2, bookTitle);
            statement.setString(3, issueDate);
            statement.setString(4, dueDate);
            statement.setString(5, "not yet return");
            int rowsInserted = statement.executeUpdate();
    
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private String getName(int id) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM student WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                return name;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void clearFields() {
        txtfld1.setText("");
        txtfld2.setText("");
        txtfld3.setText("");
        txtfld4.setText("");
        txtfld5.setText("");
    }

    private void formatDate() {
        Date current = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = dateFormat.format(current);
        txtfld4.setText(currentDate);

        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date due = cal.getTime();
        String dueDate = dateFormat.format(due);
        txtfld5.setText(dueDate);
    }

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField txtfld1;
    private JTextField txtfld2;
    private JTextField txtfld3;
    private JTextField txtfld4;
    private JTextField txtfld5;
    private JButton add;
    private JButton cancel;

    final Font f1 = new Font("Tw Cen MT", 0, 16);
}
