package UI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SignUp extends JFrame implements ActionListener {
	
	private JPanel contentPane;
    private JPanel panelNorth;
    private JPanel panelNorthSouth;
	private JLabel lblChatapp;
    JFrame frame = new JFrame();
    JLabel heading;
    JLabel firstNameLabel = new JLabel("Firstname");
    JLabel lastNameLabel = new JLabel("Lastname");
    JLabel user = new JLabel("Nickname");
    JLabel pass = new JLabel("Password");
    JLabel confirm = new JLabel("Confirm Password");
    JTextField firstNameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField usertext = new JTextField();
    JPasswordField passfield = new JPasswordField();
    JPasswordField confirmfield = new JPasswordField();
    JButton signUpButton = new JButton("Sign Up");
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JPanel panel = new JPanel();
    JLabel title = new JLabel("Register Here");
    JCheckBox showPassword = new JCheckBox("Show Password");

    Connection connection;
    Statement statement;

    SignUp(Connection conn, Statement stmt) {
        connection = conn;
        statement = stmt;
        
        try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setVisible(true);
        //frame.setContentPane(new JLabel(new ImageIcon("C:\\viraj_adj\\Workshop\\Microproject\\Images\\1.png")));
        frame.setBounds(100, 100, 570, 400);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); 
        
        heading = new JLabel("SECURE-CHAT");
        heading.setBounds(40, 10, 200, 40);
        heading.setForeground(Color.black);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 40));
        contentPane.add(heading);
        
        contentPane.setVisible(true);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.LIGHT_GRAY);
	    contentPane.add(panelCenter, BorderLayout.CENTER);
	    panelCenter.setLayout(null);

        firstNameLabel.setBounds(40, 60, 100, 25);
        firstNameLabel.setForeground(Color.black);

        lastNameLabel.setBounds(40, 110, 100, 25);
        lastNameLabel.setForeground(Color.black);

        user.setBounds(40, 150, 100, 25);
        user.setForeground(Color.black);

        pass.setBounds(40, 200, 100, 25);
        pass.setForeground(Color.black);

        confirm.setBounds(40, 250, 100, 25);
        confirm.setForeground(Color.black);

        firstNameField.setBounds(150, 60, 200, 25);
        lastNameField.setBounds(150, 110, 200, 25);
        usertext.setBounds(150, 150, 200, 25);
        passfield.setBounds(150, 200, 200, 25);
        confirmfield.setBounds(150, 250, 200, 25);

        signUpButton.setBounds(260, 310, 90, 30);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);

        loginButton.setBounds(150, 310, 90, 30);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(150, 350, 200, 30);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        //title.setBounds(150, 200, 800, 100);
        //title.setFont(new Font("TIMES_NEW_ROMAN", Font.BOLD, 50));
        //Color logoYellow = new Color(255, 160, 14);
        //title.setForeground(logoYellow);

        showPassword.setBounds(180, 280, 190, 25);
        showPassword.setFocusable(false);
        showPassword.addActionListener(this);

        frame.add(firstNameLabel);
        frame.add(lastNameLabel);
        frame.add(user);
        frame.add(pass);
        frame.add(confirm);
        frame.add(firstNameField);
        frame.add(lastNameField);
        frame.add(usertext);
        frame.add(passfield);
        frame.add(confirmfield);
        frame.add(signUpButton);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(title);
        frame.add(panel);
        frame.add(showPassword);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetButton) {
            firstNameField.setText("");
            lastNameField.setText("");
            usertext.setText("");
            passfield.setText("");
            confirmfield.setText("");
        }

        if (e.getSource() == signUpButton) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String userID = usertext.getText();
            String password = String.valueOf(passfield.getPassword());
            String confirmPassword = String.valueOf(confirmfield.getPassword());

            if (firstName.isEmpty() || lastName.isEmpty() || userID.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                passfield.setText("");
                confirmfield.setText("");
            } else {
                try {
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nickname='" + userID + "'");
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(frame, "Nickname already taken! Choose another.", "Error", JOptionPane.ERROR_MESSAGE);
                        usertext.setText("");
                    } else {
                        statement.executeUpdate("INSERT INTO users (nickname, firstname, lastname, password) VALUES ('" + userID + "', '" + firstName + "', '" + lastName + "', '" + password + "')");
                        JOptionPane.showMessageDialog(frame, "Registered Successfully! Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        LoginPage lp = new LoginPage();
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passfield.setEchoChar((char) 0);
                confirmfield.setEchoChar((char) 0);
            } else {
                passfield.setEchoChar('*');
                confirmfield.setEchoChar('*');
            }
        }
        if (e.getSource() == loginButton) {
            LoginPage lp = new LoginPage();
            frame.dispose();
        }
    }
}
