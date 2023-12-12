/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.losno.hospitalmanagementsystem;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    private JLabel lastClickedLabel;
    private JLabel totalDoctorLabel;
    
    
    public Dashboard() {
        initComponents();
        populateDoctorsTable();
        setupHoverEffects();
        updateTotalDoctorLabel();
        updateTotalPatientsLabel();
        updateNewDoctorLabel();
        updateNewPatientLabel();
        populatePatientsComboBox();
        populateDoctorsComboBox();
        populateScheduleTable();
        displayPatients();
        
        
      
        doctorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = doctorTable.getSelectedRow();
                if (selectedRow != -1) {
                    displayDoctorInfo(selectedRow);
                }
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBtnActionPerformed(e);
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBtnActionPerformed(e);
            }
        });
        
         searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
         patientTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = patientTable.getSelectedRow();
                if (selectedRow != -1) {
                    displayPatientInfo(selectedRow);
                }
            }
        });
         updatePatientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePatientActionPerformed(evt);
            }
        });
         // Assuming this is part of your constructor or initialization method
        deletePatientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePatientBtnActionPerformed(evt);
            }
        });
        // Assuming this is part of your constructor or initialization method
//        addNewPatients.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                addNewPatientsActionPerformed(evt);
//            }
//        });


         // Populate the search type combo box
        searchTypeDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctor ID", "First Name", "Last Name", "Username", "Email", "Specialty" }));
        
        // Populate the search type combo box for patients
        searchTypePatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Patient ID", "First Name", "Last Name", "Gender", "Date of Birth", "Address", "Phone Number", "Email"}));
    }
    
    private void populateScheduleTable() {
    DefaultTableModel model = (DefaultTableModel) scheduleTable.getModel();
    model.setRowCount(0); // Clear the existing rows

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
        String query = "SELECT s.schedule_id, d.first_name AS doctor_first_name, d.last_name AS doctor_last_name, "
                + "p.first_name AS patient_first_name, p.last_name AS patient_last_name, s.appointment_date "
                + "FROM schedule s "
                + "JOIN doctors d ON s.doctor_id = d.id "
                + "JOIN patients p ON s.patient_id = p.id";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Object[] row = {
                        resultSet.getInt("schedule_id"),
                        resultSet.getString("doctor_first_name") + " " + resultSet.getString("doctor_last_name"),
                        resultSet.getString("patient_first_name") + " " + resultSet.getString("patient_last_name"),
                        resultSet.getString("appointment_date")
                    };
                    model.addRow(row);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error fetching data from the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    
    
//    private void addPatientToDatabase(String firstName, String lastName, String gender, String dateOfBirth, String address, String phoneNumber, String email) {
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
//        String query = "INSERT INTO patients (first_name, last_name, gender, date_of_birth, address, phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, firstName);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setString(3, gender);
//            preparedStatement.setString(4, dateOfBirth);
//            preparedStatement.setString(7, address);
//            preparedStatement.setString(6, phoneNumber);
//            preparedStatement.setString(5, email);
//
//            preparedStatement.executeUpdate();
//
//            JOptionPane.showMessageDialog(this, "New patient added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Error adding new patient: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//    }
//}

    private void deletePatientFromDatabase(int patientId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "DELETE FROM patients WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, patientId);

                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Patient deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Patient not found or unable to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting patient: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updatePatientActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        // Get other patient information from text fields, combo box, etc.
        int patientId = Integer.parseInt(patientID.getText());
        String firstName = firstnamePatient.getText();
        String lastName = lastnamePatient.getText();
        String gender = (String) genderPatient.getSelectedItem();
        String dateOfBirth = dateOfBirthPatient.getText();
        String address = emailPatient.getText();
        String phoneNumber = phoneNumberPatient.getText();  // Assume phoneNumber is a string
        String email = addressPatient.getText();

        // Update the database with the new patient information
        updatePatientInDatabase(patientId, firstName, lastName, gender, dateOfBirth, address, phoneNumber, email);

        // Display updated information in the patient table
        displayPatients();

        // Optional: Clear the text fields after the update
        clearPatientFields();

    } catch (NumberFormatException e) {
        // Handle the case where the input is not a valid integer
        JOptionPane.showMessageDialog(this, "Invalid phone number format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void updatePatientInDatabase(int patientId, String firstName, String lastName, String gender, String dateOfBirth, String address, String phoneNumber, String email) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "UPDATE patients SET first_name=?, last_name=?, gender=?, date_of_birth=?, address=?, phone_number=?, email=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, gender);
                preparedStatement.setString(4, dateOfBirth);
                preparedStatement.setString(7, address);
                preparedStatement.setString(6, phoneNumber);
                preparedStatement.setString(5, email);
                preparedStatement.setInt(8, patientId);

                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Patient information updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating patient information: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearPatientFields() {
        // Clear the text fields, combo box, etc. after the update
        patientID.setText("");
        firstnamePatient.setText("");
        lastnamePatient.setText("");
        genderPatient.setSelectedIndex(0); // Assuming 0 is the default index for the gender combo box
        dateOfBirthPatient.setText("");
        emailPatient.setText("");
        addressPatient.setText("");
        phoneNumberPatient.setText("");
    }

    private void displayPatientInfo(int row) {
        DefaultTableModel model = (DefaultTableModel) patientTable.getModel();
        int patientId = (int) model.getValueAt(row, 0);
        String firstName = (String) model.getValueAt(row, 1);
        String lastName = (String) model.getValueAt(row, 2);
        String gender = (String) model.getValueAt(row, 5);
        String dateOfBirth = (String) model.getValueAt(row, 4); // Assuming it's stored as a string
        String address = (String) model.getValueAt(row, 6);
        String phoneNumber = (String) model.getValueAt(row, 7);
        String email = (String) model.getValueAt(row, 3);

        // Display patient information in text fields, combo box, etc.
        patientID.setText(String.valueOf(patientId));
        firstnamePatient.setText(firstName);
        lastnamePatient.setText(lastName);
        genderPatient.setSelectedItem(gender);
        dateOfBirthPatient.setText(dateOfBirth);
        emailPatient.setText(email);
        addressPatient.setText(address);
        phoneNumberPatient.setText(phoneNumber);
    }

    private void displayPatients() {
        // Clear existing data in the table
        DefaultTableModel model = (DefaultTableModel) patientTable.getModel();
        model.setRowCount(0); // Clear the existing rows

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT * FROM patients";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Object[] row = {
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("date_of_birth"),
                            resultSet.getString("gender"),
                            resultSet.getString("address"),
                            resultSet.getString("phone_number")
                        };
                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching patient data from the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateNewPatientLabel() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT first_name, last_name, timestamp_added FROM patients ORDER BY id DESC LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                        Timestamp timestampAdded = resultSet.getTimestamp("timestamp_added");

                        // Format the timestamp as a string (adjust the format as needed)
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String timestampString = dateFormat.format(timestampAdded);

                        // Update the JLabel with the full name and timestamp
                        newPatientAdded.setText(fullName + " (Added on: " + timestampString + ")");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching the newest doctor from the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
  
    private void updateNewDoctorLabel() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT first_name, last_name, timestamp_added FROM doctors ORDER BY id DESC LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                        Timestamp timestampAdded = resultSet.getTimestamp("timestamp_added");

                        // Format the timestamp as a string (adjust the format as needed)
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String timestampString = dateFormat.format(timestampAdded);

                        // Update the JLabel with the full name and timestamp
                        newDoctorAdded.setText(fullName + " (Added on: " + timestampString + ")");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching the newest doctor from the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTotalPatientsLabel() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT COUNT(*) AS total FROM patients";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int total = resultSet.getInt("total");
                        totalPatients.setText(String.valueOf(total));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching total patients from the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   private void updateTotalDoctorLabel() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT COUNT(*) AS total FROM doctors";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int total = resultSet.getInt("total");
                        totalDoctor.setText(Integer.toString(total));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching total doctors from the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setupHoverEffects() {
        // Dashboard label
        setupLabel(dashboard, 0);

        // Doctors label
        setupLabel(doctors, 1);

        // Patients label
        setupLabel(patients, 2);

        // Schedule label
        setupLabel(schedule, 3);

        // Appointment label
        setupLabel(appointment, 4);
        
    }
    
    private void setupLabel(JLabel label, int index) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (label != lastClickedLabel) {
                    label.setForeground(Color.BLACK); // Change color on hover
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (label != lastClickedLabel) {
                    label.setForeground(Color.WHITE); // Change color back on exit
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (lastClickedLabel != null) {
                    lastClickedLabel.setForeground(Color.WHITE); // Reset color of the last clicked label
                }
                label.setForeground(Color.BLACK); // Change color on click
                jTabbedPane2.setSelectedIndex(index);
                lastClickedLabel = label;
            }
        });

        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    private void displayDoctorInfo(int row) {
        DefaultTableModel model = (DefaultTableModel) doctorTable.getModel();
        int doctorId = (int) model.getValueAt(row, 0);
        String firstName = (String) model.getValueAt(row, 1);
        String lastName = (String) model.getValueAt(row, 2);
        String gender = (String) model.getValueAt(row, 3);
        String username = (String) model.getValueAt(row, 4);
        String email = (String) model.getValueAt(row, 5);
        String specialty = (String) model.getValueAt(row, 6);

        // Display doctor information in text fields
        doctorID.setText(String.valueOf(doctorId));
        firstnameDoctors.setText(firstName);
        lastnameDoctors.setText(lastName);
        usernameDoctors.setText(username);
        emailDoctors.setText(email);

        // Set the selected items in JComboBox components
        genderDoctors.setSelectedItem(gender);
        specialtyDoctors.setSelectedItem(specialty);

        // Enable/disable buttons as needed
        updateBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
    }

    private void populateDoctorsTable() {
        DefaultTableModel model = (DefaultTableModel) doctorTable.getModel();
        model.setRowCount(0); // Clear the existing rows

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT * FROM doctors";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Object[] row = {
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("gender"),
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getString("specialty")
                        };
                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        dashboard = new javax.swing.JLabel();
        doctors = new javax.swing.JLabel();
        patients = new javax.swing.JLabel();
        schedule = new javax.swing.JLabel();
        appointment = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        PANELLLLL = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        totalDoctor = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        newDoctorAdded = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        newPatientAdded = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        totalPatients = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        doctorTable = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        doctorID = new javax.swing.JTextField();
        firstnameDoctors = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lastnameDoctors = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        usernameDoctors = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        emailDoctors = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        genderDoctors = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        specialtyDoctors = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        newdoctorsBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        searchBar = new javax.swing.JTextField();
        searchTypeDoctor = new javax.swing.JComboBox<>();
        refreshBtnDoctor = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        patientID = new javax.swing.JTextField();
        firstnamePatient = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        lastnamePatient = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        updatePatientBtn = new javax.swing.JButton();
        deletePatientBtn = new javax.swing.JButton();
        searchPatientBtn = new javax.swing.JButton();
        searchPatientBar = new javax.swing.JTextField();
        searchTypePatient = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        dateOfBirthPatient = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        patientTable = new javax.swing.JTable();
        emailPatient = new javax.swing.JTextField();
        addressPatient = new javax.swing.JTextField();
        phoneNumberPatient = new javax.swing.JTextField();
        genderPatient = new javax.swing.JComboBox<>();
        addNewPatients = new javax.swing.JButton();
        refreshBtnPatient = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        scheduleTable = new javax.swing.JTable();
        refreshSchedule = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        appointmentDay = new javax.swing.JTextField();
        chooseDoctor = new javax.swing.JComboBox<>();
        choosePatient = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        addSchedule = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        jTextField4.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(null);

        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("ID:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(62, 165, 106));

        dashboard.setBackground(new java.awt.Color(255, 255, 255));
        dashboard.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        dashboard.setForeground(new java.awt.Color(255, 255, 255));
        dashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dashboard.setText("Dashboard");
        dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardMouseClicked(evt);
            }
        });

        doctors.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        doctors.setForeground(new java.awt.Color(255, 255, 255));
        doctors.setText("Doctors");
        doctors.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        doctors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doctorsMouseClicked(evt);
            }
        });

        patients.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        patients.setForeground(new java.awt.Color(255, 255, 255));
        patients.setText("Patients");
        patients.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        patients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientsMouseClicked(evt);
            }
        });

        schedule.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        schedule.setForeground(new java.awt.Color(255, 255, 255));
        schedule.setText("Schedule");
        schedule.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        schedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scheduleMouseClicked(evt);
            }
        });

        appointment.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        appointment.setForeground(new java.awt.Color(255, 255, 255));
        appointment.setText("Appointment");
        appointment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        appointment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appointmentMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("LOSNO");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon("C:\\Coding\\Java\\Logo\\dashboard.png")); // NOI18N

        logoutBtn.setBackground(new java.awt.Color(255, 255, 255));
        logoutBtn.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(62, 165, 106));
        logoutBtn.setText("LOGOUT");
        logoutBtn.setBorder(null);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(appointment)
                    .addComponent(schedule)
                    .addComponent(patients)
                    .addComponent(dashboard)
                    .addComponent(doctors))
                .addGap(43, 43, 43))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(73, 73, 73)
                .addComponent(dashboard)
                .addGap(18, 18, 18)
                .addComponent(doctors)
                .addGap(18, 18, 18)
                .addComponent(patients)
                .addGap(18, 18, 18)
                .addComponent(schedule)
                .addGap(18, 18, 18)
                .addComponent(appointment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 549));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, -41, -1, 590));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setForeground(new java.awt.Color(51, 51, 51));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PANELLLLL.setBackground(new java.awt.Color(255, 255, 255));
        PANELLLLL.setForeground(new java.awt.Color(51, 51, 51));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 165, 106)));

        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\Coding\\Java\\Logo\\doctor.png")); // NOI18N

        totalDoctor.setFont(new java.awt.Font("Inter", 1, 48)); // NOI18N
        totalDoctor.setForeground(new java.awt.Color(0, 0, 0));
        totalDoctor.setText("0");

        jLabel3.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Total of Doctors");

        jLabel18.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("New Added:");

        newDoctorAdded.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        newDoctorAdded.setForeground(new java.awt.Color(102, 102, 102));
        newDoctorAdded.setText("Full Name");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newDoctorAdded, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalDoctor))
                        .addComponent(jLabel3)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newDoctorAdded)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 165, 106)));

        newPatientAdded.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        newPatientAdded.setForeground(new java.awt.Color(102, 102, 102));
        newPatientAdded.setText("Full Name");

        jLabel20.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("New Added:");

        jLabel14.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Total of Patients");

        totalPatients.setFont(new java.awt.Font("Inter", 1, 48)); // NOI18N
        totalPatients.setForeground(new java.awt.Color(0, 0, 0));
        totalPatients.setText("0");

        jLabel17.setIcon(new javax.swing.ImageIcon("C:\\Coding\\Java\\Logo\\patient.png")); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPatientAdded, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(totalPatients))
                                .addComponent(jLabel14)))
                        .addGap(0, 182, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalPatients, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPatientAdded)
                .addContainerGap())
        );

        javax.swing.GroupLayout PANELLLLLLayout = new javax.swing.GroupLayout(PANELLLLL);
        PANELLLLL.setLayout(PANELLLLLLayout);
        PANELLLLLLayout.setHorizontalGroup(
            PANELLLLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANELLLLLLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        PANELLLLLLayout.setVerticalGroup(
            PANELLLLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANELLLLLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PANELLLLLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(356, Short.MAX_VALUE))
        );

        jPanel4.add(PANELLLLL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, -1));

        jTabbedPane2.addTab("Dash", jPanel4);

        doctorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Doctor ID", "First Name", "Last Name", "Gender", "Username", "Email", "Specialty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(doctorTable);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("ID:");
        jPanel9.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 37, -1));

        doctorID.setBackground(new java.awt.Color(51, 51, 51));
        doctorID.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        doctorID.setForeground(new java.awt.Color(255, 255, 255));
        doctorID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        doctorID.setBorder(null);
        doctorID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorIDActionPerformed(evt);
            }
        });
        jPanel9.add(doctorID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 150, 30));

        firstnameDoctors.setBackground(new java.awt.Color(51, 51, 51));
        firstnameDoctors.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        firstnameDoctors.setForeground(new java.awt.Color(255, 255, 255));
        firstnameDoctors.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        firstnameDoctors.setBorder(null);
        firstnameDoctors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnameDoctorsActionPerformed(evt);
            }
        });
        jPanel9.add(firstnameDoctors, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 150, 30));

        jLabel7.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("First Name:");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 60, -1));

        lastnameDoctors.setBackground(new java.awt.Color(51, 51, 51));
        lastnameDoctors.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        lastnameDoctors.setForeground(new java.awt.Color(255, 255, 255));
        lastnameDoctors.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lastnameDoctors.setBorder(null);
        lastnameDoctors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnameDoctorsActionPerformed(evt);
            }
        });
        jPanel9.add(lastnameDoctors, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 150, 30));

        jLabel9.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Gender:");
        jPanel9.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 60, -1));

        usernameDoctors.setBackground(new java.awt.Color(51, 51, 51));
        usernameDoctors.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        usernameDoctors.setForeground(new java.awt.Color(255, 255, 255));
        usernameDoctors.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameDoctors.setBorder(null);
        usernameDoctors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameDoctorsActionPerformed(evt);
            }
        });
        jPanel9.add(usernameDoctors, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 150, 30));

        jLabel10.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Specialty:");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 60, -1));

        emailDoctors.setBackground(new java.awt.Color(51, 51, 51));
        emailDoctors.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        emailDoctors.setForeground(new java.awt.Color(255, 255, 255));
        emailDoctors.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emailDoctors.setBorder(null);
        emailDoctors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailDoctorsActionPerformed(evt);
            }
        });
        jPanel9.add(emailDoctors, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 150, 30));

        jLabel11.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Email:");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 60, -1));

        genderDoctors.setBackground(new java.awt.Color(51, 51, 51));
        genderDoctors.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Rather not say" }));
        genderDoctors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderDoctorsActionPerformed(evt);
            }
        });
        jPanel9.add(genderDoctors, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 150, 30));

        jLabel12.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Last Name:");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 60, -1));

        specialtyDoctors.setBackground(new java.awt.Color(51, 51, 51));
        specialtyDoctors.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cardiology", "Orthopedics", "Pediatrics", "Dermatology", "Ophthalmology", "Neurology", "Gynecology" }));
        specialtyDoctors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specialtyDoctorsActionPerformed(evt);
            }
        });
        jPanel9.add(specialtyDoctors, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 150, 30));

        jLabel13.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Username:");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 80, -1));

        updateBtn.setBackground(new java.awt.Color(51, 51, 51));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel9.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 150, 30));

        deleteBtn.setBackground(new java.awt.Color(51, 51, 51));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel9.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 150, 30));

        newdoctorsBtn.setBackground(new java.awt.Color(51, 51, 51));
        newdoctorsBtn.setText("New Doctor");
        newdoctorsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newdoctorsBtnActionPerformed(evt);
            }
        });
        jPanel9.add(newdoctorsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 150, 30));

        searchBtn.setBackground(new java.awt.Color(51, 51, 51));
        searchBtn.setText("Search");
        searchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchBtnMouseClicked(evt);
            }
        });
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel9.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 100, 30));

        searchBar.setBackground(new java.awt.Color(51, 51, 51));
        searchBar.setForeground(new java.awt.Color(255, 255, 255));
        searchBar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchBar.setBorder(null);
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });
        jPanel9.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 220, 30));

        searchTypeDoctor.setBackground(new java.awt.Color(51, 51, 51));
        searchTypeDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctor ID", "First Name", "Last Name", "Username", "Email", "Specialty" }));
        searchTypeDoctor.setToolTipText("");
        searchTypeDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTypeDoctorActionPerformed(evt);
            }
        });
        jPanel9.add(searchTypeDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 150, 30));

        refreshBtnDoctor.setBackground(new java.awt.Color(51, 51, 51));
        refreshBtnDoctor.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        refreshBtnDoctor.setText("Refresh");
        refreshBtnDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnDoctorActionPerformed(evt);
            }
        });
        jPanel9.add(refreshBtnDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 150, 30));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Doc", jPanel5);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("ID:");
        jPanel13.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 37, -1));

        patientID.setBackground(new java.awt.Color(51, 51, 51));
        patientID.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        patientID.setForeground(new java.awt.Color(255, 255, 255));
        patientID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        patientID.setBorder(null);
        patientID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientIDActionPerformed(evt);
            }
        });
        jPanel13.add(patientID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 150, 30));

        firstnamePatient.setBackground(new java.awt.Color(51, 51, 51));
        firstnamePatient.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        firstnamePatient.setForeground(new java.awt.Color(255, 255, 255));
        firstnamePatient.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        firstnamePatient.setBorder(null);
        firstnamePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnamePatientActionPerformed(evt);
            }
        });
        jPanel13.add(firstnamePatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 150, 30));

        jLabel22.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("First Name:");
        jPanel13.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, -1));

        lastnamePatient.setBackground(new java.awt.Color(51, 51, 51));
        lastnamePatient.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        lastnamePatient.setForeground(new java.awt.Color(255, 255, 255));
        lastnamePatient.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lastnamePatient.setBorder(null);
        lastnamePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnamePatientActionPerformed(evt);
            }
        });
        jPanel13.add(lastnamePatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 150, 30));

        jLabel23.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Gender:");
        jPanel13.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 60, -1));

        jLabel24.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Address:");
        jPanel13.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 60, -1));

        jLabel25.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Email:");
        jPanel13.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 60, -1));

        jLabel26.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Last Name:");
        jPanel13.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 150, -1));

        updatePatientBtn.setBackground(new java.awt.Color(51, 51, 51));
        updatePatientBtn.setText("Update");
        updatePatientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePatientBtnActionPerformed(evt);
            }
        });
        jPanel13.add(updatePatientBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 150, 30));

        deletePatientBtn.setBackground(new java.awt.Color(51, 51, 51));
        deletePatientBtn.setText("Delete");
        deletePatientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePatientBtnActionPerformed(evt);
            }
        });
        jPanel13.add(deletePatientBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 150, 30));

        searchPatientBtn.setBackground(new java.awt.Color(51, 51, 51));
        searchPatientBtn.setText("Search");
        searchPatientBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchPatientBtnMouseClicked(evt);
            }
        });
        searchPatientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPatientBtnActionPerformed(evt);
            }
        });
        jPanel13.add(searchPatientBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 100, 30));

        searchPatientBar.setBackground(new java.awt.Color(51, 51, 51));
        searchPatientBar.setForeground(new java.awt.Color(255, 255, 255));
        searchPatientBar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchPatientBar.setBorder(null);
        searchPatientBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPatientBarActionPerformed(evt);
            }
        });
        jPanel13.add(searchPatientBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 220, 30));

        searchTypePatient.setBackground(new java.awt.Color(51, 51, 51));
        searchTypePatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Patient ID", "First Name", "Last Name", "Email", "Gender", "Date of Birth", "Address", "Phone Number" }));
        searchTypePatient.setToolTipText("");
        searchTypePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTypePatientActionPerformed(evt);
            }
        });
        jPanel13.add(searchTypePatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 150, 30));

        jLabel28.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Phone Number:");
        jPanel13.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 90, -1));

        dateOfBirthPatient.setBackground(new java.awt.Color(51, 51, 51));
        dateOfBirthPatient.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        dateOfBirthPatient.setForeground(new java.awt.Color(102, 102, 102));
        dateOfBirthPatient.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dateOfBirthPatient.setText("YYYY-MM-DD");
        dateOfBirthPatient.setBorder(null);
        dateOfBirthPatient.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateOfBirthPatientFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateOfBirthPatientFocusLost(evt);
            }
        });
        dateOfBirthPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateOfBirthPatientActionPerformed(evt);
            }
        });
        jPanel13.add(dateOfBirthPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 150, 30));

        jLabel29.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Date of Birth:");
        jPanel13.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 90, -1));

        patientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient ID", "First Name", "Last Name", "Email", "Date of Birth", "Gender", "Address", "Phone Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(patientTable);

        jPanel13.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 810, 190));

        emailPatient.setBackground(new java.awt.Color(51, 51, 51));
        emailPatient.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        emailPatient.setForeground(new java.awt.Color(255, 255, 255));
        emailPatient.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emailPatient.setBorder(null);
        emailPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailPatientActionPerformed(evt);
            }
        });
        jPanel13.add(emailPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 150, 30));

        addressPatient.setBackground(new java.awt.Color(51, 51, 51));
        addressPatient.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        addressPatient.setForeground(new java.awt.Color(255, 255, 255));
        addressPatient.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        addressPatient.setBorder(null);
        addressPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressPatientActionPerformed(evt);
            }
        });
        jPanel13.add(addressPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 150, 30));

        phoneNumberPatient.setBackground(new java.awt.Color(51, 51, 51));
        phoneNumberPatient.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        phoneNumberPatient.setForeground(new java.awt.Color(255, 255, 255));
        phoneNumberPatient.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phoneNumberPatient.setBorder(null);
        phoneNumberPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberPatientActionPerformed(evt);
            }
        });
        jPanel13.add(phoneNumberPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 150, 30));

        genderPatient.setBackground(new java.awt.Color(51, 51, 51));
        genderPatient.setForeground(new java.awt.Color(255, 255, 255));
        genderPatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Rather not say" }));
        jPanel13.add(genderPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 150, 30));

        addNewPatients.setBackground(new java.awt.Color(51, 51, 51));
        addNewPatients.setText("New Patients");
        addNewPatients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewPatientsActionPerformed(evt);
            }
        });
        jPanel13.add(addNewPatients, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 150, 30));

        refreshBtnPatient.setBackground(new java.awt.Color(51, 51, 51));
        refreshBtnPatient.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        refreshBtnPatient.setText("Refresh");
        refreshBtnPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnPatientActionPerformed(evt);
            }
        });
        jPanel13.add(refreshBtnPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 150, 30));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Pat", jPanel6);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Schedule ID", "Doctor", "Patient", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(scheduleTable);

        refreshSchedule.setText("Refresh");
        refreshSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshScheduleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(refreshSchedule))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(refreshSchedule)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Sch", jPanel7);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        appointmentDay.setBackground(new java.awt.Color(51, 51, 51));
        appointmentDay.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        appointmentDay.setForeground(new java.awt.Color(102, 102, 102));
        appointmentDay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        appointmentDay.setText("YYYY-MM-DD");
        appointmentDay.setBorder(null);
        appointmentDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                appointmentDayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                appointmentDayFocusLost(evt);
            }
        });
        appointmentDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentDayActionPerformed(evt);
            }
        });

        chooseDoctor.setBackground(new java.awt.Color(51, 51, 51));
        chooseDoctor.setForeground(new java.awt.Color(255, 255, 255));
        chooseDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctors" }));

        choosePatient.setBackground(new java.awt.Color(51, 51, 51));
        choosePatient.setForeground(new java.awt.Color(255, 255, 255));
        choosePatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Patient" }));

        jLabel1.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Doctor:");

        jLabel2.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Patient:");

        jLabel4.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Schedule:");

        addSchedule.setBackground(new java.awt.Color(51, 51, 51));
        addSchedule.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        addSchedule.setForeground(new java.awt.Color(255, 255, 255));
        addSchedule.setText("Add ");
        addSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addScheduleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chooseDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(choosePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appointmentDay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choosePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appointmentDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addComponent(addSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("App", jPanel8);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -10, 820, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_dashboardMouseClicked

    private void doctorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorsMouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_doctorsMouseClicked

    private void patientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientsMouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_patientsMouseClicked

    private void scheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scheduleMouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_scheduleMouseClicked

    private void appointmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentMouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(4);
    }//GEN-LAST:event_appointmentMouseClicked

    private void doctorIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doctorIDActionPerformed

    private void firstnameDoctorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnameDoctorsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnameDoctorsActionPerformed

    private void lastnameDoctorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnameDoctorsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameDoctorsActionPerformed

    private void usernameDoctorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameDoctorsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameDoctorsActionPerformed

    private void emailDoctorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailDoctorsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailDoctorsActionPerformed

    private void genderDoctorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderDoctorsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderDoctorsActionPerformed

    private void specialtyDoctorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialtyDoctorsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_specialtyDoctorsActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = doctorTable.getSelectedRow();
        if (selectedRow != -1) {
            // Get data from text fields and JComboBox components
            int doctorId = Integer.parseInt(doctorID.getText());
            String firstName = firstnameDoctors.getText();
            String lastName = lastnameDoctors.getText();
            String gender = (String) genderDoctors.getSelectedItem();
            String username = usernameDoctors.getText();
            String email = emailDoctors.getText();
            String specialty = (String) specialtyDoctors.getSelectedItem();

            // Update the doctor in the database
            updateDoctor(doctorId, firstName, lastName, gender, username, email, specialty);

            // Update the row in the table
            updateDoctorInTable(selectedRow, doctorId, firstName, lastName, gender, username, email, specialty);

            // Clear text fields and disable buttons
            clearTextFields();
        } else {
        JOptionPane.showMessageDialog(this, "Please select a doctor to update.", "No Doctor Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = doctorTable.getSelectedRow();
        if (selectedRow != -1) {
            int doctorId = (int) doctorTable.getValueAt(selectedRow, 0);

            // Delete the doctor from the database
            deleteDoctor(doctorId);

            // Remove the row from the table
            DefaultTableModel model = (DefaultTableModel) doctorTable.getModel();
            model.removeRow(selectedRow);

            // Clear text fields and disable buttons
            clearTextFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a doctor to delete.", "No Doctor Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void newdoctorsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newdoctorsBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
        AddDoctors ad = new AddDoctors();
        ad.setVisible(true);
    }//GEN-LAST:event_newdoctorsBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        dispose(); // Close the current form

        // Redirect to login form
        Login loginForm = new Login(); // Replace LoginForm with the actual class name of your login form
        loginForm.setVisible(true);
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        String selectedSearchType = (String) searchTypeDoctor.getSelectedItem();
        String searchTerm = searchBar.getText().trim();

    // Perform the search based on the selected search type
        switch (selectedSearchType) {
            case "Doctor ID":
                searchDoctors("id", searchTerm);
                break;
            case "First Name":
                searchDoctors("first_name", searchTerm);
                break;
            case "Last Name":
                searchDoctors("last_name", searchTerm);
                break;
            case "Username":
                searchDoctors("username", searchTerm);
                break;
            case "Email":
                searchDoctors("email", searchTerm);
                break;
            case "Specialty":
                searchDoctors("specialty", searchTerm);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid search type", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
       
    }//GEN-LAST:event_searchBtnActionPerformed

    private void searchDoctors(String column, String searchTerm) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT * FROM doctors WHERE " + column + " LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + searchTerm + "%");
                ResultSet resultSet = preparedStatement.executeQuery();

                // Update the doctor table with the search results
                DefaultTableModel model = (DefaultTableModel) doctorTable.getModel();
                model.setRowCount(0); // Clear existing data

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String gender = resultSet.getString("gender");
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String specialty = resultSet.getString("specialty");

                    model.addRow(new Object[]{id, firstName, lastName, gender, username, email, specialty});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching for doctors: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBtnMouseClicked

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBarActionPerformed

    private void searchTypeDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTypeDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTypeDoctorActionPerformed

    private void patientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientIDActionPerformed

    private void firstnamePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnamePatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnamePatientActionPerformed

    private void lastnamePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnamePatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnamePatientActionPerformed

    private void updatePatientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePatientBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updatePatientBtnActionPerformed

    private void deletePatientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePatientBtnActionPerformed
            // TODO add your handling code here:
            // Get the selected patient's ID from the text field
        int patientId = Integer.parseInt(patientID.getText());

        // Confirm with the user before deleting
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this patient?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            // If the user confirms, proceed with the deletion
            deletePatientFromDatabase(patientId);

            // Display updated information in the patient table
            displayPatients();

            // Optional: Clear the text fields after the delete
            clearPatientFields();
        }
    }//GEN-LAST:event_deletePatientBtnActionPerformed

    private void searchPatientBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchPatientBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPatientBtnMouseClicked

    private void searchPatientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPatientBtnActionPerformed
        // TODO add your handling code here:
        String searchType = (String) searchTypePatient.getSelectedItem();
        String searchTerm = searchPatientBar.getText().trim();

        // Construct the SQL query based on the selected search type
        String query = "";
        switch (searchType) {
            case "Patient ID":
                query = "SELECT * FROM patients WHERE id=?";
                break;
            case "First Name":
                query = "SELECT * FROM patients WHERE first_name LIKE ?";
                break;
            case "Last Name":
                query = "SELECT * FROM patients WHERE last_name LIKE ?";
                break;
            case "Email":
                query = "SELECT * FROM patients WHERE email LIKE ?";
                break;
            case "Gender":
                query = "SELECT * FROM patients WHERE gender=?";
                break;
            case "Date of Birth":
                query = "SELECT * FROM patients WHERE date_of_birth=?";
                break;
            case "Address":
                query = "SELECT * FROM patients WHERE address LIKE ?";
                break;
            case "Phone Number":
                query = "SELECT * FROM patients WHERE phone_number LIKE ?";
                break;
            default:
                break;
        }

        // Execute the query and update the patient table
        searchPatients(query, searchTerm);
    }//GEN-LAST:event_searchPatientBtnActionPerformed

    private void searchPatients(String query, String searchTerm) {
        DefaultTableModel model = (DefaultTableModel) patientTable.getModel();
        model.setRowCount(0); // Clear the existing rows

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Set parameters based on the selected search type
                switch (query) {
                    case "SELECT * FROM patients WHERE id=?":
                    case "SELECT * FROM patients WHERE gender=?":
                        preparedStatement.setString(1, searchTerm);
                        break;
                    case "SELECT * FROM patients WHERE first_name LIKE ?":
                    case "SELECT * FROM patients WHERE last_name LIKE ?":
                    case "SELECT * FROM patients WHERE email LIKE ?":
                    case "SELECT * FROM patients WHERE address LIKE ?":
                    case "SELECT * FROM patients WHERE phone_number LIKE ?":
                        preparedStatement.setString(1, "%" + searchTerm + "%");
                        break;
                    case "SELECT * FROM patients WHERE date_of_birth=?":
                        // Assuming date_of_birth is stored as a string
                        preparedStatement.setString(1, searchTerm);
                        break;
                    default:
                        break;
                }

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Object[] row = {
                                resultSet.getInt("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("gender"),
                                resultSet.getString("date_of_birth"),
                                resultSet.getString("address"),
                                resultSet.getString("phone_number"),
                                resultSet.getString("email")
                        };
                        model.addRow(row);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching patients: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchPatientBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPatientBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPatientBarActionPerformed

    private void searchTypePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTypePatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTypePatientActionPerformed

    private void dateOfBirthPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateOfBirthPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateOfBirthPatientActionPerformed

    private void dateOfBirthPatientFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateOfBirthPatientFocusGained
        // TODO add your handling code here:
        if(dateOfBirthPatient.getText().equals("YYYY-MM-DD")){
            dateOfBirthPatient.setText("");
            dateOfBirthPatient.setForeground(new Color(255,255,255) );
        }
        
    }//GEN-LAST:event_dateOfBirthPatientFocusGained

    private void dateOfBirthPatientFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateOfBirthPatientFocusLost
        // TODO add your handling code here:
         if(dateOfBirthPatient.getText().equals("")){
            dateOfBirthPatient.setText("YYYY-MM-DD");
            dateOfBirthPatient.setForeground(new Color(153,153,153) );
        }
    }//GEN-LAST:event_dateOfBirthPatientFocusLost

    private void emailPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailPatientActionPerformed

    private void addressPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressPatientActionPerformed

    private void phoneNumberPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumberPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumberPatientActionPerformed

    private void addNewPatientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewPatientsActionPerformed
        String firstName = firstnamePatient.getText();
        String lastName = lastnamePatient.getText();
        String gender = (String) genderPatient.getSelectedItem();
        String dateOfBirth = dateOfBirthPatient.getText();
        String address = addressPatient.getText();
        // Assuming phone number is stored as a string
        String phoneNumber = phoneNumberPatient.getText();
        String email = emailPatient.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || dateOfBirth.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if email is already taken (similar to the username check)
        if (isEmailTaken(address)) {
            JOptionPane.showMessageDialog(this, "Email is already taken. Please choose another.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "INSERT INTO patients (first_name, last_name, gender, date_of_birth, address, phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, gender);
                preparedStatement.setString(4, dateOfBirth);
                preparedStatement.setString(5, address);
                preparedStatement.setString(6, phoneNumber);
                preparedStatement.setString(7, email);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Patient added successfully!");
                    // Display updated information in the patient table
                    displayPatients();
                    
                    // Clear the text field
                    clearPatientFields();

                } else {
                    JOptionPane.showMessageDialog(this, "Patient addition failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Patient addition failed. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addNewPatientsActionPerformed

    private boolean isEmailTaken(String email) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT COUNT(*) FROM patients WHERE email = ?";
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
        return false; // Return false by default or handle the exception according to your needs
    }

    
    private void appointmentDayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_appointmentDayFocusGained
        // TODO add your handling code here:
        if(appointmentDay.getText().equals("YYYY-MM-DD")){
            appointmentDay.setText("");
            appointmentDay.setForeground(new Color(255,255,255) );
        }
    }//GEN-LAST:event_appointmentDayFocusGained

    private void appointmentDayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_appointmentDayFocusLost
        // TODO add your handling code here:
        if(appointmentDay.getText().equals("")){
            appointmentDay.setText("YYYY-MM-DD");
            appointmentDay.setForeground(new Color(153,153,153) );
        }
    }//GEN-LAST:event_appointmentDayFocusLost

    private void appointmentDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentDayActionPerformed

    private void addScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addScheduleActionPerformed
        // TODO add your handling code here:
        // Get selected doctor, patient, and appointment date
        String selectedDoctor = (String) chooseDoctor.getSelectedItem();
        String selectedPatient = (String) choosePatient.getSelectedItem();
        String appointmentDate = appointmentDay.getText();

        // Split the selected doctor and patient names
        String[] doctorNames = selectedDoctor.split(" ");
        String[] patientNames = selectedPatient.split(" ");

        // Get doctor and patient IDs based on the names
        int doctorId = getDoctorId(doctorNames[0], doctorNames[1]);
        int patientId = getPatientId(patientNames[0], patientNames[1]);

        // Insert the new schedule into the database
        addScheduleToDatabase(doctorId, patientId, appointmentDate);

        // Display updated information in the schedule table
        // Call the method to refresh the schedule table
        // displaySchedule();

        // Optional: Clear the text fields after adding a new schedule
        // clearScheduleFields();
    }//GEN-LAST:event_addScheduleActionPerformed

    private void refreshScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshScheduleActionPerformed
        // TODO add your handling code here:
        // Clear the existing rows in the schedule table
        DefaultTableModel model = (DefaultTableModel) scheduleTable.getModel();
        model.setRowCount(0);

        // Repopulate the schedule table with updated data
        populateScheduleTable();
    }//GEN-LAST:event_refreshScheduleActionPerformed

    private void refreshBtnPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnPatientActionPerformed
        // TODO add your handling code here:
        patientID.setText("");
        firstnamePatient.setText("");
        lastnamePatient.setText("");
        genderPatient.setSelectedIndex(0);
        dateOfBirthPatient.setText("");
        emailPatient.setText("");
        addressPatient.setText("");
        phoneNumberPatient.setText("");
    }//GEN-LAST:event_refreshBtnPatientActionPerformed

    private void refreshBtnDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnDoctorActionPerformed
        // TODO add your handling code here:
        doctorID.setText("");
        firstnameDoctors.setText("");
        lastnameDoctors.setText("");
        usernameDoctors.setText("");
        emailDoctors.setText("");
        genderDoctors.setSelectedIndex(0);
        specialtyDoctors.setSelectedIndex(0);
    }//GEN-LAST:event_refreshBtnDoctorActionPerformed

    private void populateDoctorsComboBox() {
    // Assuming chooseDoctor is your JComboBox for doctors
        chooseDoctor.removeAllItems();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT first_name, last_name FROM doctors";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String doctorName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                        chooseDoctor.addItem(doctorName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching doctor names: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populatePatientsComboBox() {
        // Assuming choosePatient is your JComboBox for patients
        choosePatient.removeAllItems();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT first_name, last_name FROM patients";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String patientName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                        choosePatient.addItem(patientName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching patient names: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private int getDoctorId(String firstName, String lastName) {
        // Retrieve the doctor ID based on the first name and last name
        int doctorId = -1;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT id FROM doctors WHERE first_name = ? AND last_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        doctorId = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching doctor ID: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return doctorId;
    }

    private int getPatientId(String firstName, String lastName) {
        // Retrieve the patient ID based on the first name and last name
        int patientId = -1;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "SELECT id FROM patients WHERE first_name = ? AND last_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        patientId = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching patient ID: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return patientId;
    }

    private void addScheduleToDatabase(int doctorId, int patientId, String appointmentDate) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
            String query = "INSERT INTO schedule (doctor_id, patient_id, appointment_date) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, doctorId);
                preparedStatement.setInt(2, patientId);
                preparedStatement.setString(3, appointmentDate);

                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "New schedule added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding new schedule: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearTextFields() {
        doctorID.setText("");
        firstnameDoctors.setText("");
        lastnameDoctors.setText("");
       
        usernameDoctors.setText("");
        emailDoctors.setText("");
     

        // Disable buttons
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
    private void updateDoctorInTable(int row, int doctorId, String firstName, String lastName,
            String gender, String username, String email, String specialty) {
        DefaultTableModel model = (DefaultTableModel) doctorTable.getModel();
        model.setValueAt(doctorId, row, 0);
        model.setValueAt(firstName, row, 1);
        model.setValueAt(lastName, row, 2);
        model.setValueAt(gender, row, 3);
        model.setValueAt(username, row, 4);
        model.setValueAt(email, row, 5);
        model.setValueAt(specialty, row, 6);
    }
    private void deleteDoctor(int doctorId) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
        String query = "DELETE FROM doctors WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, doctorId);
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error deleting doctor from the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void updateDoctor(int doctorId, String firstName, String lastName, String gender, String username, String email, String specialty) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_hospitalmanagementsystem", "root", "@J9shuajosh13")) {
        String query = "UPDATE doctors SET first_name = ?, last_name = ?, gender = ?, username = ?, email = ?, specialty = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, specialty);
            preparedStatement.setInt(7, doctorId);
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating doctor in the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PANELLLLL;
    private javax.swing.JButton addNewPatients;
    private javax.swing.JButton addSchedule;
    private javax.swing.JTextField addressPatient;
    private javax.swing.JLabel appointment;
    private javax.swing.JTextField appointmentDay;
    private javax.swing.JComboBox<String> chooseDoctor;
    private javax.swing.JComboBox<String> choosePatient;
    private javax.swing.JLabel dashboard;
    private javax.swing.JTextField dateOfBirthPatient;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton deletePatientBtn;
    private javax.swing.JTextField doctorID;
    private javax.swing.JTable doctorTable;
    private javax.swing.JLabel doctors;
    private javax.swing.JTextField emailDoctors;
    private javax.swing.JTextField emailPatient;
    private javax.swing.JTextField firstnameDoctors;
    private javax.swing.JTextField firstnamePatient;
    private javax.swing.JComboBox<String> genderDoctors;
    private javax.swing.JComboBox<String> genderPatient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField lastnameDoctors;
    private javax.swing.JTextField lastnamePatient;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JLabel newDoctorAdded;
    private javax.swing.JLabel newPatientAdded;
    private javax.swing.JButton newdoctorsBtn;
    private javax.swing.JTextField patientID;
    private javax.swing.JTable patientTable;
    private javax.swing.JLabel patients;
    private javax.swing.JTextField phoneNumberPatient;
    private javax.swing.JButton refreshBtnDoctor;
    private javax.swing.JButton refreshBtnPatient;
    private javax.swing.JButton refreshSchedule;
    private javax.swing.JLabel schedule;
    private javax.swing.JTable scheduleTable;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchPatientBar;
    private javax.swing.JButton searchPatientBtn;
    private javax.swing.JComboBox<String> searchTypeDoctor;
    private javax.swing.JComboBox<String> searchTypePatient;
    private javax.swing.JComboBox<String> specialtyDoctors;
    private javax.swing.JLabel totalDoctor;
    private javax.swing.JLabel totalPatients;
    private javax.swing.JButton updateBtn;
    private javax.swing.JButton updatePatientBtn;
    private javax.swing.JTextField usernameDoctors;
    // End of variables declaration//GEN-END:variables
}
