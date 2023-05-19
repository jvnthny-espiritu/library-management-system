package model.panel;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

import model.DatabaseConnector;
import model.Login;

import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Return extends JPanel {
    public Return() {
        initialize();
    }

    private void initialize() {
        setSize(1000, 540);
        setBackground(Login.col_2);
        setLayout(null);

        search = createButton("SEARCH", new Font("Tw Cen MT", 0, 14), Login.col_2, Login.col_3);
        search.setBounds(650, 20, 80, 18);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchfld.getText();
                searchRecords(searchQuery);
            }
        });

        searchfld = createTextField(Login.col_3);
        searchfld.setBounds(750, 20, 200, 18);

        tableBook = new DefaultTableModel();
        tableBook.addColumn("ID");
        tableBook.addColumn("Student ID");
        tableBook.addColumn("Book Title");
        tableBook.addColumn("Date Borrowed");
        tableBook.addColumn("Due Date");
        tableBook.addColumn("Penalties");
        tableBook.addColumn("Status");

        retrieveRecords();

        table = new JTable(tableBook);
        table.setRowHeight(20);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 60, 850, 350);

        TableColumn statusColumn = table.getColumnModel().getColumn(6);
        statusColumn.setCellRenderer(new ButtonRenderer());
        statusColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

        addComponents(scrollPane, search, searchfld);

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
            String query = "SELECT * FROM records WHERE (status='not yet return' OR status='overdue') AND status != 'returned'";
            if (!searchQuery.isEmpty()) {
                query += " AND student_id = ?";
            }
            PreparedStatement statement = conn.prepareStatement(query);
            if (!searchQuery.isEmpty()) {
                statement.setInt(1, Integer.parseInt(searchQuery));
            }
            ResultSet resultSet = statement.executeQuery();
            tableBook.setRowCount(0);
    
            while (resultSet.next()) {
                Object[] rowData = new Object[7];
                for (int columnIndex = 1; columnIndex <= 7; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
    
                String dueDateString = resultSet.getString("due_date");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date dueDate = dateFormat.parse(dueDateString);
                Date currentDate = new Date();
                if (currentDate.after(dueDate)) {
                    double penalties = calculatePenalties(dueDate, currentDate);
                    rowData[5] = penalties;
                    int id = resultSet.getInt("id");
                    updatePenalties(id, penalties);
                    rowData[6] = "<html><font color='red'>Overdue</font></html>";
                }
                tableBook.addRow(rowData);
            }
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
    }    

    private void retrieveRecords() {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM records WHERE (status='not yet return' OR status='overdue') AND status != 'returned'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
    
            while (resultSet.next()) {
                Object[] rowData = new Object[7];
                for (int columnIndex = 1; columnIndex <= 7; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }

                String dueDateString = resultSet.getString("due_date");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date dueDate = dateFormat.parse(dueDateString);
                Date currentDate = new Date();
                if (currentDate.after(dueDate)) {
                    double penalties = calculatePenalties(dueDate, currentDate);
                    rowData[5] = penalties;
                    int id = resultSet.getInt("id");
                    updatePenalties(id, penalties);
                    rowData[6] = "<html><font color='red'>Overdue</font></html>";
                }
                tableBook.addRow(rowData);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
    
    private double calculatePenalties(Date dueDate, Date currentDate) {
        long diffInMilliSec = currentDate.getTime() - dueDate.getTime();
        long diffInDays = diffInMilliSec / (24 * 60 * 60 * 1000);
        double penalties = 50.0 + (0.05 * diffInDays);
        return penalties;
    }
    
    private void updatePenalties(int id, double penalties) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "UPDATE records SET penalties = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDouble(1, penalties);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ButtonClickListener());
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            return button;
        }

        public Object getCellEditorValue() {
            return "";
        }
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = table.convertRowIndexToModel(table.getEditingRow());
            if (row != -1) {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                updateRecordStatus(id, "returned", formatDate());
                tableBook.removeRow(row);
            }
        }

        private void updateRecordStatus(int id, String status, String date) {
            try (Connection conn = DatabaseConnector.getConnection()) {
                String query = "UPDATE records SET status = ?, returned_date = ? WHERE ID = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, status);
                statement.setInt(3, id);
                statement.setString(2, date);
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String formatDate() {
        Date current = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(current);
    }

    private JTextField searchfld;
    private JButton search;
    private JTable table;
    private DefaultTableModel tableBook;

}