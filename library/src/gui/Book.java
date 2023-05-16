package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Book extends JPanel {
    public Book() {
        intialize();
    }

    private void intialize() {
        setSize(1000, 540);
        setBackground(c1);
        setLayout(null);

        label1 = new JLabel();
        label1.setText("ISBN:");
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        label1.setFont(f1);
        label1.setForeground(c2);
        add(label1);
        label1.setBounds(125, 50, 200, 24);

        label2 = new JLabel();
        label2.setText("BOOK TITLE:");
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        label2.setFont(f1);
        label2.setForeground(c2);
        add(label2);
        label2.setBounds(125, 79, 200, 24);

        label3 = new JLabel();
        label3.setText("AUTHOR:");
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        label3.setFont(f1);
        label3.setForeground(c2);
        add(label3);
        label3.setBounds(125, 108, 200, 24);

        label4 = new JLabel();
        label4.setText("PUBLICATION YEAR:");
        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        label4.setFont(f1);
        label4.setForeground(c2);
        add(label4);
        label4.setBounds(125, 137, 200, 24);

        label5 = new JLabel();
        label5.setText("GENRE:");
        label5.setHorizontalAlignment(SwingConstants.RIGHT);
        label5.setFont(f1);
        label5.setForeground(c2);
        add(label5);
        label5.setBounds(125, 166, 200, 24);

        search = new JButton("SEARCH:");
        search.setFont(new Font("Tw Cen MT", 0, 14));
        search.setBackground(c1);
        search.setForeground(c2);
        search.setBorder(new LineBorder(c2, 1));
        search.setFocusPainted(false);
        add(search);
        search.setBounds(110, 320, 80, 18);

        searchfld = new JTextField();
        searchfld.setBackground(c2);
        add(searchfld);
        search.setBounds(710, 10, 200, 24);

        txtfld1 = new JTextField();
        txtfld1.setBackground(c2);
        add(txtfld1);
        txtfld1.setBounds(375, 50, 300, 24);

        txtfld2 = new JTextField();
        txtfld2.setBackground(c2);
        add(txtfld2);
        txtfld2.setBounds(375, 79, 300, 24);

        txtfld3 = new JTextField();
        txtfld3.setBackground(c2);
        add(txtfld3);
        txtfld3.setBounds(375, 108, 300, 24);

        txtfld4 = new JTextField();
        txtfld4.setBackground(c2);
        add(txtfld4);
        txtfld4.setBounds(375, 137, 300, 24);

        txtfld5 = new JTextField();
        txtfld5.setBackground(c2);
        add(txtfld5);
        txtfld5.setBounds(375, 166, 300, 24);

        add = new JButton("ADD");
        add.setFont(new Font("Tw Cen MT", 0, 14));
        add.setBackground(c1);
        add.setForeground(c2);
        add.setBorderPainted(false);
        add.setFocusPainted(false);
        add.add(add);
        add.setBounds(110, 320, 80, 18);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String label1 = txtfld1.getText();
                String label2 = txtfld2.getText();
                String label3 = txtfld3.getText();
                String label4 = txtfld4.getText();
                String label5 = txtfld5.getText();
                JOptionPane.showMessageDialog(Book.this,
                        "Book Saved: " + label2 + " by " + label3,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtfld1.setText("");
                txtfld2.setText("");
                txtfld3.setText("");
                txtfld4.setText("");
                txtfld5.setText("");

            }
        });

        delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(Book.this,
                        "Are you sure you want to delete this book?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(Book.this,
                            "Book Deleted",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(Book.this,
                        "Are you sure you want to update this book?",
                        "Confirm Update", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String label1 = txtfld1.getText();
                    String label2 = txtfld2.getText();
                    String label3 = txtfld3.getText();
                    String label4 = txtfld4.getText();
                    String label5 = txtfld5.getText();
                    // Perform update operation with the book details
                    JOptionPane.showMessageDialog(Book.this,
                            "Book Updated: " + label2 + " by " + label3,
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
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

    final Color c1 = new Color(37, 52, 57);
    final Color c2 = new Color(255, 255, 255);
    final Font f1 = new Font("Tw Cen MT", 0, 16);
}