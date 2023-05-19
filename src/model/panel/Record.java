package model.panel;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

import model.DatabaseConnector;
import model.Login;

import java.awt.event.*;

public class Record extends JPanel {
    public Record() {
        initialize();
    }

    private void initialize() {
        setSize(1000, 540);
        setBackground(Login.col_2);
        setLayout(null);

        searchButton = createButton("SEARCH", buttonFont, Login.col_2, Login.col_3);
        searchButton.setBounds(650, 20, 80, 18);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                searchRecords(searchQuery);
            }
        });

        searchField = createTextField(Login.col_3);
        searchField.setBounds(750, 20, 200, 18);

        tableBook = new DefaultTableModel();
        tableBook.addColumn("ID");
        tableBook.addColumn("Student ID");
        tableBook.addColumn("Book Title");
        tableBook.addColumn("Date Borrowed");
        tableBook.addColumn("Due Date");
        tableBook.addColumn("Return Date");
        tableBook.addColumn("Penalties");
        tableBook.addColumn("Status");

        retrieveRecords();

        table = new JTable(tableBook);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(45, 60, 900, 400);

        addComponents(searchButton, searchField, scrollPane);

        SwingUtilities.invokeLater(() -> {
            TableColumnModel columnModel = table.getColumnModel();
            for (int column = 0; column < table.getColumnCount(); column++) {
                int width = 15; // Minimum width
                for (int row = 0; row < table.getRowCount(); row++) {
                    TableCellRenderer renderer = table.getCellRenderer(row, column);
                    Component comp = table.prepareRenderer(renderer, row, column);
                    width = Math.max(comp.getPreferredSize().width + 10, width);
                }
                columnModel.getColumn(column).setPreferredWidth(width);
            }
        });
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

    private void addComponents(Component... components) {
        for (Component component : components) {
            add(component);
        }
    }

    private void searchRecords(String searchQuery) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM records";
            if (!searchQuery.isEmpty()) {
                query += " WHERE student_id = ?";
            }
            PreparedStatement statement = conn.prepareStatement(query);
            if (!searchQuery.isEmpty()) {
                statement.setInt(1, Integer.parseInt(searchQuery));
            }
            ResultSet resultSet = statement.executeQuery();
            tableBook.setRowCount(0);

            while (resultSet.next()) {
                Object[] rowData = new Object[8];
                for (int columnIndex = 1; columnIndex <= 8; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
                tableBook.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void retrieveRecords() {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM records";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Object[] rowData = new Object[8];
                for (int columnIndex = 1; columnIndex <= 8; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
                tableBook.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private JTextField searchField;
    private JButton searchButton;
    private JTable table;
    private DefaultTableModel tableBook;

    private final Font buttonFont = new Font("Tw Cen MT", 0, 14);
}