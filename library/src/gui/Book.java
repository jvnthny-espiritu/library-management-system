package gui;

import javax.swing.*;
import java.awt.*;

public class Book extends JPanel{
    public Book() {
        intialize();
    }

    private void intialize() {
        setSize(1000, 540);
        setBackground(c1);

        label1 = new JLabel();
        label1.setText("ISBN");
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        label1.setFont(f1);
        label1.setBounds(0, 0, 500, 20);
        label1.setForeground(c2);
        
        add(label1);
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
    private JButton find;
    
    final Color c1 = new Color(37, 52, 57);
    final Color c2 = new Color(255, 255, 255);
    final Font f1 = new Font("Tw Cen MT", 0, 16);
}
