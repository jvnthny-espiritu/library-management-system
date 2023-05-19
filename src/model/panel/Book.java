package model.panel;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

import model.DatabaseConnector;
import model.Login;

import java.awt.event.*;

public class Book extends JPanel {
    public Book() {
        initialize();
    }

    private void initialize() {
        setSize(1000, 540);
        setBackground(Login.col_2);
        setLayout(null);

        label1 = createLabel("ISBN:", 125, 80);
        label2 = createLabel("BOOK TITLE:", 125, 109);
        label3 = createLabel("AUTHOR:", 125, 138);
        label4 = createLabel("PUBLICATION YEAR:", 125, 167);
        label5 = createLabel("GENRE:", 125, 196);

        search = createButton("SEARCH", new Font("Tw Cen MT", 0, 14), Login.col_3, Login.col_2);
        search.setBounds(650, 20, 80, 18);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTitle = searchfld.getText();
                model.data.Book bookData = search(searchTitle);

                if (bookData != null) {
                    setBookDataFields(bookData);
                    searchfld.setText("");
                } else {
                    searchfld.setText("");
                    JOptionPane.showMessageDialog(Book.this, "Book not found.");
                }
            }
        });

        searchfld = createTextField(Login.col_3);
        searchfld.setBounds(750, 20, 200, 18);

        txtfld1 = createTextField(Login.col_3);
        txtfld1.setBounds(375, 80, 300, 24);

        txtfld2 = createTextField(Login.col_3);
        txtfld2.setBounds(375, 109, 300, 24);

        txtfld3 = createTextField(Login.col_3);
        txtfld3.setBounds(375, 138, 300, 24);

        txtfld4 = createTextField(Login.col_3);
        txtfld4.setBounds(375, 167, 300, 24);

        txtfld5 = createTextField(Login.col_3);
        txtfld5.setBounds(375, 196, 300, 24);

        add = createButton("ADD", new Font("Tw Cen MT", 0, 14), Login.col_3, Login.col_2);
        add.setBounds(310, 320, 80, 25);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbn = txtfld1.getText();
                String title = txtfld2.getText();
                String author = txtfld3.getText();
                String date = txtfld4.getText();
                String genre = txtfld5.getText();

                if (add(isbn, title, author, date, genre)) {
                    JOptionPane.showMessageDialog(Book.this, "Book added successfully.");
                } else {
                    JOptionPane.showMessageDialog(Book.this, "Failed to add book.");
                }
            }
        });

        update = createButton("UPDATE", new Font("Tw Cen MT", 0, 14), Login.col_3, Login.col_2);
        update.setBounds(410, 320, 80, 25);
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbn = txtfld1.getText();
                String title = txtfld2.getText();
                String author = txtfld3.getText();
                String date = txtfld4.getText();
                String genre = txtfld5.getText();

                if (update(isbn, title, author, date, genre)) {
                    JOptionPane.showMessageDialog(Book.this, "Book updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(Book.this, "Failed to update book.");
                }
            }
        });

        delete = createButton("DELETE", new Font("Tw Cen MT", 0, 14), Login.col_3, Login.col_2);
        delete.setBounds(510, 320, 80, 25);
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbn = txtfld1.getText();

                if (delete(isbn)) {
                    JOptionPane.showMessageDialog(Book.this, "Book deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(Book.this, "Failed to delete book.");
                }
            }
        });

        cancel = createButton("CANCEL", new Font("Tw Cen MT", 0, 14), Login.col_3, Login.col_2);
        cancel.setBounds(610, 320, 80, 25);
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
        add(search);
        add(searchfld);
        add(txtfld1);
        add(txtfld2);
        add(txtfld3);
        add(txtfld4);
        add(txtfld5);
        add(add);
        add(update);
        add(delete);
        add(cancel);
    }

    private void setBookDataFields(model.data.Book bookData) {
        txtfld1.setText(bookData.getISBN());
        txtfld2.setText(bookData.getTitle());
        txtfld3.setText(bookData.getAuthor());
        txtfld4.setText(bookData.getYear());
        txtfld5.setText(bookData.getGenre());
    }

    private void clearFields() {
        txtfld1.setText("");
        txtfld2.setText("");
        txtfld3.setText("");
        txtfld4.setText("");
        txtfld5.setText("");
    }

    private boolean add(String isbn, String title, String author, String year, String genre) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO book (book_title, ISBN, author, publication_year, genre) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, isbn);
            statement.setString(3, author);
            statement.setString(4, year);
            statement.setString(5, genre);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean update(String isbn, String title, String author, String year, String genre) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "UPDATE book SET book_title = ?, author = ?, publication_year = ?, genre = ? WHERE ISBN = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, year);
            statement.setString(4, genre);
            statement.setString(5, isbn);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean delete(String isbn) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "DELETE FROM book WHERE ISBN = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, isbn);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private model.data.Book search(String title) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM book WHERE book_title = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String isbn = resultSet.getString("ISBN");
                String bookTitle = resultSet.getString("book_title");
                String author = resultSet.getString("author");
                String year = resultSet.getString("publication_year");
                String genre = resultSet.getString("genre");

                return new model.data.Book(bookTitle, isbn, author, year, genre);
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
    private JLabel label5;
    private JTextField txtfld1;
    private JTextField txtfld2;
    private JTextField txtfld3;
    private JTextField txtfld4;
    private JTextField txtfld5;
    private JTextField searchfld;
    private JButton add;
    private JButton update;
    private JButton delete;
    private JButton cancel;
    private JButton search;

    private final Font f1 = new Font("Tw Cen MT", 0, 16);
}