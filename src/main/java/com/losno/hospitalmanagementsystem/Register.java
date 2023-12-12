
package com.losno.hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Register extends javax.swing.JFrame {

    public Register() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        firstnameRegister = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        registerBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        registerTxt = new javax.swing.JLabel();
        lastnameRegister = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        genderRegister = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        emailRegister = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        passwordRegister = new javax.swing.JPasswordField();
        usernameRegister = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(62, 165, 106));

        jLabel7.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Connect with us");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("We hope you find the best care here!");

        firstnameRegister.setBackground(new java.awt.Color(255, 255, 255));
        firstnameRegister.setForeground(new java.awt.Color(51, 51, 51));
        firstnameRegister.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        firstnameRegister.setBorder(null);
        firstnameRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        firstnameRegister.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstnameRegisterFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstnameRegisterFocusLost(evt);
            }
        });
        firstnameRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnameRegisterActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("First Name:");

        registerBtn.setBackground(new java.awt.Color(255, 255, 255));
        registerBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        registerBtn.setForeground(new java.awt.Color(51, 51, 51));
        registerBtn.setText("Register");
        registerBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        registerBtn.setBorderPainted(false);
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Already have an account?");
        jLabel11.setToolTipText("");

        registerTxt.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        registerTxt.setForeground(new java.awt.Color(255, 255, 255));
        registerTxt.setText("Login.");
        registerTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerTxtMouseClicked(evt);
            }
        });
        registerTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                registerTxtKeyPressed(evt);
            }
        });

        lastnameRegister.setBackground(new java.awt.Color(255, 255, 255));
        lastnameRegister.setForeground(new java.awt.Color(51, 51, 51));
        lastnameRegister.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lastnameRegister.setBorder(null);
        lastnameRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lastnameRegister.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lastnameRegisterFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastnameRegisterFocusLost(evt);
            }
        });
        lastnameRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnameRegisterActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Last Name:");

        genderRegister.setBackground(new java.awt.Color(255, 255, 255));
        genderRegister.setForeground(new java.awt.Color(51, 51, 51));
        genderRegister.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Rather not say" }));
        genderRegister.setBorder(null);
        genderRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderRegisterActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Gender:");

        jLabel13.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Username:");

        emailRegister.setBackground(new java.awt.Color(255, 255, 255));
        emailRegister.setForeground(new java.awt.Color(51, 51, 51));
        emailRegister.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emailRegister.setBorder(null);
        emailRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        emailRegister.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailRegisterFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailRegisterFocusLost(evt);
            }
        });
        emailRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailRegisterActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Email:");

        jLabel15.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Password:");

        passwordRegister.setBackground(new java.awt.Color(255, 255, 255));
        passwordRegister.setForeground(new java.awt.Color(51, 51, 51));
        passwordRegister.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordRegister.setBorder(null);
        passwordRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordRegisterActionPerformed(evt);
            }
        });

        usernameRegister.setBackground(new java.awt.Color(255, 255, 255));
        usernameRegister.setForeground(new java.awt.Color(51, 51, 51));
        usernameRegister.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameRegister.setBorder(null);
        usernameRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(114, 114, 114))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(89, 89, 89))
                                    .addComponent(passwordRegister)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(genderRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(usernameRegister))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(firstnameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lastnameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))))
                        .addGap(25, 25, 25))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(registerTxt)))
                        .addGap(118, 118, 118))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstnameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastnameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(genderRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(usernameRegister))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(registerTxt))
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(62, 165, 106));
        jLabel1.setFont(new java.awt.Font("Inter Black", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(62, 165, 106));
        jLabel1.setText("LOSNO");

        jLabel2.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(62, 165, 106));
        jLabel2.setText("A dedicated doctor \nyou can trust.");

        jLabel3.setFont(new java.awt.Font("Inter Light", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(62, 165, 106));
        jLabel3.setText("The health and well-being of our patients and their health ");

        jLabel4.setFont(new java.awt.Font("Inter Light", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(62, 165, 106));
        jLabel4.setText("care team will always be our priority, so we follow ");

        jLabel5.setFont(new java.awt.Font("Inter Light", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(62, 165, 106));
        jLabel5.setText("the best practices for cleanliness.");

        jLabel6.setBackground(new java.awt.Color(187, 187, 187));
        jLabel6.setForeground(new java.awt.Color(107, 202, 148));
        jLabel6.setText("© 2023 Lonso. All Rights Reserved.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(161, 161, 161))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 38, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(39, 39, 39))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75)
                .addComponent(jLabel2)
                .addGap(69, 69, 69)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firstnameRegisterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstnameRegisterFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_firstnameRegisterFocusGained

    private void firstnameRegisterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstnameRegisterFocusLost

    }//GEN-LAST:event_firstnameRegisterFocusLost

    private void firstnameRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnameRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnameRegisterActionPerformed

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
    String firstName = firstnameRegister.getText();
    String lastName = lastnameRegister.getText();
    String gender = (String) genderRegister.getSelectedItem();
    String username = usernameRegister.getText();
    String email = emailRegister.getText();
    String password = new String(passwordRegister.getPassword());
    
    if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
        return; 
    }
    
    if (isUsernameTaken(username) || isEmailTaken(email)) {
        JOptionPane.showMessageDialog(this, "Username or email is already taken. Please choose another.", "Error", JOptionPane.ERROR_MESSAGE);
        return; 
    }

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
        String query = "INSERT INTO users (first_name, last_name, gender, username, email, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User registered successfully!");
                clearTextFields();
                this.dispose();
                Login lg = new Login();
                lg.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "User registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "User registration failed. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_registerBtnActionPerformed
    
    private void clearTextFields() {
    firstnameRegister.setText("");
    lastnameRegister.setText("");
    usernameRegister.setText("");
    emailRegister.setText("");
    passwordRegister.setText("");
}
    private boolean isUsernameTaken(String username) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

private boolean isEmailTaken(String email) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; 
}

    private void registerTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerTxtMouseClicked
        // TODO add your handling code here:
       this.dispose();

        Login lg = new Login();
        lg.setVisible(true);
    }//GEN-LAST:event_registerTxtMouseClicked

    private void registerTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registerTxtKeyPressed
        // TODO add your handling code here:
        this.dispose();

        Login lg = new Login();
        lg.setVisible(true);
    }//GEN-LAST:event_registerTxtKeyPressed

    private void lastnameRegisterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastnameRegisterFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameRegisterFocusGained

    private void lastnameRegisterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastnameRegisterFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameRegisterFocusLost

    private void lastnameRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnameRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameRegisterActionPerformed

    private void emailRegisterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailRegisterFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_emailRegisterFocusGained

    private void emailRegisterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailRegisterFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_emailRegisterFocusLost

    private void emailRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailRegisterActionPerformed

    private void genderRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderRegisterActionPerformed

    private void passwordRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordRegisterActionPerformed

    private void usernameRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameRegisterActionPerformed

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailRegister;
    private javax.swing.JTextField firstnameRegister;
    private javax.swing.JComboBox<String> genderRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lastnameRegister;
    private javax.swing.JPasswordField passwordRegister;
    private javax.swing.JButton registerBtn;
    private javax.swing.JLabel registerTxt;
    private javax.swing.JTextField usernameRegister;
    // End of variables declaration//GEN-END:variables
}
