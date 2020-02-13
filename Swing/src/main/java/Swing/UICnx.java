package Swing;

/**
 * UICnx
 */

import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UICnx extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btn;
    private JLabel label;
    private JPanel contentPane;

    public UICnx() {

        // Create and set the windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 2014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Creation of the label Login
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        // Add field for the user input
        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        // Add field for password input
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        // Creation of the label username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        // Creation of the label password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        // Add a button
        btn = new JButton("Login");
        btn.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btn.setBounds(545, 392, 162, 73);

        btn.addActionListener(new ActionListener() {
            // Trigger from clicking the button

            public void actionPerformed(ActionEvent e) {

                String user = textField.getText();
                char[] password = passwordField.getPassword();

                try {

                    // Connection to the database
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projet",
                            "root", "root");

                    // SQL Query to retrieve the informations needed (which is the Username and
                    // Password from the user imput) from the database

                    PreparedStatement st = (PreparedStatement) connection
                            .prepareStatement("SELECT Username, PWD from clients where Username =?");
                    st.setString(1, user);
                    ResultSet rs = st.executeQuery();

                    if (rs.next()) {

                        // Get the input from the field Username and PWD
                        String user1 = rs.getString("Username");
                        String pwd = rs.getString("PWD");

                        // Test to compare the input entered by the user and the informations that are
                        // in the database to check if the inputs are the same as the ones in the
                        // database

                        if (user.equals(user1) && HashingTest(new String(password), pwd)) {
                            // We convert the char [] from the password input into a String for easy
                            // comparison

                            dispose();
                            // Display of a window to confirm that the user is connected
                            UICnx ah = new UICnx();
                            ah.setTitle("Bienvenue");
                            ah.setVisible(true);
                            JOptionPane.showMessageDialog(btn, "Connecté ! ");
                            MainTable field = new MainTable();
                            field.setVisible(true);
                            setVisible(false);
                            dispose();

                        } else {
                            // Display an error if the inputs are incorrect
                            JOptionPane.showMessageDialog(btn, "Mauvais identifiant ou mot de passe ! Réessayez ! ");
                        }
                    } else {
                        // Display an error if the inputs are incorrect
                        JOptionPane.showMessageDialog(btn, "Mauvais identifiant ou mot de passe ! Réessayez ! ");

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });
        // Adding the button in the window
        contentPane.add(btn);
        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);

    }

    // This method allows the comparison between a plain password (from the password
    // entered in the field) in a String format
    // to a hashed password which is in the database
    private boolean HashingTest(String pwd, String hashedPwd) {

        if (BCrypt.checkpw(pwd, hashedPwd)) {
            return true;
        } else {
            return false;
        }
    }

}