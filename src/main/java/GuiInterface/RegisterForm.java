/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiInterface;

import java.awt.Color;

/**
 *
 * @author burritos
 */
public class RegisterForm extends javax.swing.JFrame {
    HomeForm formPrecedente = null;
    /**
     * Creates new form RegisterForm
     * @param formInput form precedente, utilizzato per tornare indietro
     */
    public RegisterForm(HomeForm formInput) {
        formPrecedente = formInput;
        initComponents();
        this.setVisible(true);
        labelError.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRegistrazione = new javax.swing.JPanel();
        goBackButton = new javax.swing.JButton();
        labelLogo = new javax.swing.JLabel();
        labelLogin = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();
        inputUsername = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        inputEmail = new javax.swing.JTextField();
        labelPassword = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        buttonRegistrazione = new javax.swing.JButton();
        labelError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(700, 350));
        setResizable(false);

        panelRegistrazione.setBackground(new java.awt.Color(47, 54, 64));

        goBackButton.setBackground(new java.awt.Color(47, 54, 64));
        goBackButton.setFont(new java.awt.Font("URW Gothic L", 1, 36)); // NOI18N
        goBackButton.setForeground(new java.awt.Color(255, 255, 255));
        goBackButton.setText("<-");
        goBackButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        goBackButton.setBorderPainted(false);
        goBackButton.setContentAreaFilled(false);
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackButtonActionPerformed(evt);
            }
        });

        labelLogo.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        labelLogo.setForeground(new java.awt.Color(255, 255, 255));
        labelLogo.setText("HouseBreak");

        labelLogin.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        labelLogin.setForeground(new java.awt.Color(240, 52, 52));
        labelLogin.setText("Registrazione");

        labelUsername.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        labelUsername.setForeground(new java.awt.Color(255, 255, 255));
        labelUsername.setText("Username");

        inputUsername.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        inputUsername.setForeground(new java.awt.Color(204, 204, 204));
        inputUsername.setText("Username");
        inputUsername.setMargin(new java.awt.Insets(3, 6, 3, 6));
        inputUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputUsernameMouseClicked(evt);
            }
        });

        labelEmail.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        labelEmail.setForeground(new java.awt.Color(255, 255, 255));
        labelEmail.setText("Email");

        inputEmail.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        inputEmail.setForeground(new java.awt.Color(204, 204, 204));
        inputEmail.setText("Email");
        inputEmail.setMargin(new java.awt.Insets(3, 6, 3, 6));
        inputEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputEmailMouseClicked(evt);
            }
        });

        labelPassword.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        labelPassword.setForeground(new java.awt.Color(255, 255, 255));
        labelPassword.setText("Password");

        inputPassword.setForeground(new java.awt.Color(204, 204, 204));
        inputPassword.setText("Password");
        inputPassword.setMargin(new java.awt.Insets(3, 6, 3, 6));
        inputPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputPasswordMouseClicked(evt);
            }
        });

        buttonRegistrazione.setBackground(new java.awt.Color(240, 52, 52));
        buttonRegistrazione.setFont(new java.awt.Font("Ubuntu Mono", 0, 24)); // NOI18N
        buttonRegistrazione.setForeground(new java.awt.Color(255, 255, 255));
        buttonRegistrazione.setText("Registrati");
        buttonRegistrazione.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonRegistrazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrazioneActionPerformed(evt);
            }
        });

        labelError.setForeground(new java.awt.Color(255, 51, 51));
        labelError.setText("Devi inserire tutti i dati.");

        javax.swing.GroupLayout panelRegistrazioneLayout = new javax.swing.GroupLayout(panelRegistrazione);
        panelRegistrazione.setLayout(panelRegistrazioneLayout);
        panelRegistrazioneLayout.setHorizontalGroup(
            panelRegistrazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrazioneLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRegistrazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistrazioneLayout.createSequentialGroup()
                        .addGroup(panelRegistrazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUsername)
                            .addComponent(goBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(labelLogo)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrazioneLayout.createSequentialGroup()
                        .addGroup(panelRegistrazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(inputEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputUsername, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLogin))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrazioneLayout.createSequentialGroup()
                        .addGroup(panelRegistrazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(inputPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistrazioneLayout.createSequentialGroup()
                                .addGroup(panelRegistrazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEmail, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelRegistrazioneLayout.createSequentialGroup()
                                .addComponent(labelError)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonRegistrazione, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))))
        );
        panelRegistrazioneLayout.setVerticalGroup(
            panelRegistrazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrazioneLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRegistrazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLogo)
                    .addComponent(goBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLogin)
                .addGap(18, 18, 18)
                .addComponent(labelUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRegistrazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonRegistrazione)
                    .addComponent(labelError))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRegistrazione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRegistrazione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRegistrazioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrazioneActionPerformed
        if(inputEmail.getText().equals("") || inputUsername.getText().equals("") || inputPassword.getPassword().length < 8){
            labelError.setVisible(true);
        }else if(!inputEmail.getText().equals("") && !inputUsername.getText().equals("") || inputPassword.getPassword().length >= 8){
            labelError.setVisible(false);
        }
    }//GEN-LAST:event_buttonRegistrazioneActionPerformed

    private void inputUsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputUsernameMouseClicked
        inputUsername.setText("");
        inputUsername.setForeground(Color.black);
    }//GEN-LAST:event_inputUsernameMouseClicked

    private void inputEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputEmailMouseClicked
        inputEmail.setText("");
        inputEmail.setForeground(Color.black);
    }//GEN-LAST:event_inputEmailMouseClicked

    private void inputPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputPasswordMouseClicked
        inputPassword.setText("");
        inputPassword.setForeground(Color.black);
    }//GEN-LAST:event_inputPasswordMouseClicked

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackButtonActionPerformed
        //Chiuse il frame
        this.dispose();
        formPrecedente.setVisible(true);
    }//GEN-LAST:event_goBackButtonActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonRegistrazione;
    private javax.swing.JButton goBackButton;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelError;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JPanel panelRegistrazione;
    // End of variables declaration//GEN-END:variables
}
