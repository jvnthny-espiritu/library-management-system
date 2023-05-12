
package gui;

public class Login extends javax.swing.JFrame {
    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        greeting = new javax.swing.JLabel();
        banner = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        passwordTxt = new javax.swing.JLabel();
        userTxt = new javax.swing.JLabel();
        userIcon = new javax.swing.JLabel();
        userTxtFld = new javax.swing.JTextField();
        passIcon = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setModalExclusionType(null);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(124, 137, 139));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        greeting.setBackground(new java.awt.Color(37, 52, 57));
        greeting.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        greeting.setForeground(new java.awt.Color(37, 52, 57));
        greeting.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        greeting.setText("Welcome to Library");
        jPanel1.add(greeting, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        banner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/banner.png"))); // NOI18N
        jPanel1.add(banner, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 500));

        jPanel2.setBackground(new java.awt.Color(37, 52, 57));
        jPanel2.setMaximumSize(new java.awt.Dimension(280, 330));
        jPanel2.setName("Log In"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        passwordTxt.setBackground(new java.awt.Color(124, 137, 139));
        passwordTxt.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        passwordTxt.setForeground(new java.awt.Color(124, 137, 139));
        passwordTxt.setText("Password");
        jPanel2.add(passwordTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        userTxt.setBackground(new java.awt.Color(124, 137, 139));
        userTxt.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        userTxt.setForeground(new java.awt.Color(124, 137, 139));
        userTxt.setText("Username");
        jPanel2.add(userTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/userIcon.png"))); // NOI18N
        jPanel2.add(userIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 130, -1, -1));

        userTxtFld.setBackground(new java.awt.Color(37, 52, 57));
        userTxtFld.setColumns(2);
        userTxtFld.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        userTxtFld.setForeground(new java.awt.Color(124, 137, 139));
        userTxtFld.setAutoscrolls(false);
        userTxtFld.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(124, 137, 139)));
        userTxtFld.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        userTxtFld.setMargin(new java.awt.Insets(0, 1, 1, 0));
        userTxtFld.setMaximumSize(new java.awt.Dimension(1, 17));
        userTxtFld.setName(""); // NOI18N
        userTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTxtFldActionPerformed(evt);
            }
        });
        jPanel2.add(userTxtFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 190, 25));

        passIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/passIcon.png"))); // NOI18N
        jPanel2.add(passIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 220, -1, -1));

        jPasswordField1.setBackground(new java.awt.Color(37, 52, 57));
        jPasswordField1.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(124, 137, 139));
        jPasswordField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(124, 137, 139)));
        jPasswordField1.setMargin(new java.awt.Insets(0, 1, 1, 0));
        jPanel2.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 190, 25));

        loginButton.setBackground(new java.awt.Color(124, 137, 139));
        loginButton.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        loginButton.setForeground(new java.awt.Color(37, 52, 57));
        loginButton.setText("LOG IN");
        loginButton.setActionCommand("loginButton");
        loginButton.setBorder(null);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        jPanel2.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 300, 80, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 310, 500));

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userTxtFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTxtFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userTxtFldActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banner;
    private javax.swing.JLabel greeting;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel passIcon;
    private javax.swing.JLabel passwordTxt;
    private javax.swing.JLabel userIcon;
    private javax.swing.JLabel userTxt;
    private javax.swing.JTextField userTxtFld;
    // End of variables declaration//GEN-END:variables
}
