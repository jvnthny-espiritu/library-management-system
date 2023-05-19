package model.panel;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

import model.DatabaseConnector;
import model.Login;

import java.awt.event.*;

public class Student extends JPanel {
    public Student() {
        initialize();
    }

    private void initialize() {
        setSize(1000, 540);
        setBackground(Login.col_2);
        setLayout(null);

        search = createButton("", new ImageIcon(getClass().getResource("/resource/search.png")), Login.col_2, Login.col_3);
        search.setBounds(690, 124, 24, 24);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int searchID = Integer.parseInt(txtfld1.getText());
                data.Student studentData = search(searchID);

                if (studentData != null) {
                    txtfld2.setText(studentData.getName());
                    txtfld3.setText(studentData.getYearLevel());
                    txtfld4.setText(studentData.getProgram());
                } else {
                    JOptionPane.showMessageDialog(Student.this, "Student not found.");
                }
            }
        });

        label1 = createLabel("STUDENT ID:", SwingConstants.RIGHT, labelFont, Login.col_3);
        label1.setBounds(125, 124, 200, 24);

        label2 = createLabel("STUDENT'S NAME:", SwingConstants.RIGHT, labelFont, Login.col_3);
        label2.setBounds(125, 153, 200, 24);

        label3 = createLabel("YEAR:", SwingConstants.RIGHT, labelFont, Login.col_3);
        label3.setBounds(125, 182, 200, 24);

        label4 = createLabel("PROGRAM:", SwingConstants.RIGHT, labelFont, Login.col_3);
        label4.setBounds(125, 211, 200, 24);

        txtfld1 = createTextField(Login.col_3);
        txtfld1.setBounds(375, 124, 300, 24);

        txtfld2 = createTextField(Login.col_3);
        txtfld2.setBounds(375, 153, 300, 24);

        txtfld3 = createTextField(Login.col_3);
        txtfld3.setBounds(375, 182, 300, 24);

        txtfld4 = createTextField(Login.col_3);
        txtfld4.setBounds(375, 211, 300, 24);

        add = createButton("SAVE", new Font("Tw Cen MT", 0, 14), Login.col_3, Login.col_2);
        add.setBounds(470, 250, 80, 25);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtfld2.getText();
                String year = txtfld3.getText();
                String program = txtfld4.getText();
                try (Connection conn = DatabaseConnector.getConnection()) {
                    if (recordExists(conn, name)) {
                        update(conn, name, year, program);
                        JOptionPane.showMessageDialog(Student.this, "Student record updated successfully.");
                    } else {
                        add(conn, name, year, program);
                        JOptionPane.showMessageDialog(Student.this, "Student added successfully.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancel = createButton("CANCEL", new Font("Tw Cen MT", 0, 14), Login.col_3, Login.col_2);
        cancel.setBounds(560, 250, 80, 25);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtfld1.setText("");
                txtfld2.setText("");
                txtfld3.setText("");
                txtfld4.setText("");
            }
        });

        delete = createButton("", new ImageIcon(getClass().getResource("/resource/delete.png")), Login.col_3, Login.col_2);
        delete.setBounds(650, 250, 25, 25);
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtfld1.getText());
                if (delete(id)) {
                    JOptionPane.showMessageDialog(Student.this, "Student record deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(Student.this, "Failed to delete student record.");
                }
            }
        });

        addComponents();
    }

    private void addComponents() {
        add(search);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(txtfld1);
        add(txtfld2);
        add(txtfld3);
        add(txtfld4);
        add(add);
        add(cancel);
        add(delete);
    }

    private JButton createButton(String text, Font font, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        return button;
    }

    private JButton createButton(String text, Icon icon, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setIcon(icon);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    private JLabel createLabel(String text, int alignment, Font font, Color foreground) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(alignment);
        label.setFont(font);
        label.setForeground(foreground);
        return label;
    }

    private JTextField createTextField(Color background) {
        JTextField textField = new JTextField();
        textField.setBackground(background);
        return textField;
    }

    private static boolean recordExists(Connection conn, String name) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("SELECT * FROM student WHERE name=?")) {
            pst.setString(1, name);
            try (ResultSet rst = pst.executeQuery()) {
                return rst.next();
            }
        }
    }

    private void add(Connection conn, String name, String year, String program) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("INSERT INTO student (name, year_level, program) VALUES (?, ?, ?)")) {
            pst.setString(1, name);
            pst.setString(2, year);
            pst.setString(3, program);
            pst.executeUpdate();
        }
    }

    private void update(Connection conn, String name, String year, String program) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("UPDATE student SET year_level=?, program=? WHERE name=?")) {
            pst.setString(1, name);
            pst.setString(2, year);
            pst.setString(3, program);
            pst.executeUpdate();
        }
    }

    private boolean delete(int id) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "DELETE FROM student WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private data.Student search(int id) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM student WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String year = resultSet.getString("year_level");
                String program = resultSet.getString("program");

                return new data.Student(id, name, year, program);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField txtfld1;
    private JTextField txtfld2;
    private JTextField txtfld3;
    private JTextField txtfld4;
    private JButton search;
    private JButton add;
    private JButton cancel;
    private JButton delete;

    private final Font labelFont = new Font("Tw Cen MT", 0, 16);

}