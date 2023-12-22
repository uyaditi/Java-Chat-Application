package UI;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    private JPanel contentPane;
    private JPanel panelNorth;
    private JPanel panelNorthSouth;
	private JLabel lblChatapp;
	private JTextArea txtAreaLogs;
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField idField = new JTextField();
    JPasswordField pass = new JPasswordField();
    JLabel userLabel = new JLabel("Nickname:");
    JLabel passLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();
    JLabel title = new JLabel("Welcome to SECURE-CHAT");
    JPanel pan = new JPanel();
    JButton su = new JButton("Sign Up");
    JCheckBox showPassword = new JCheckBox("Show Password");

    Connection connection;
    Statement statement;

    LoginPage() {
        try {
            String url = "jdbc:mysql://localhost:3306/user";
            String user = "root";
            String password = "Aditi@123";

            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // swing here
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.DARK_GRAY);
		
		panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));
		
		lblChatapp = new JLabel("SECURE-CHAT");
		lblChatapp.setHorizontalAlignment(SwingConstants.CENTER);
		lblChatapp.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panelNorth.add(lblChatapp, BorderLayout.NORTH);
		
		panelNorthSouth = new JPanel();
		panelNorth.add(panelNorthSouth, BorderLayout.SOUTH);
		panelNorthSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.LIGHT_GRAY);
	    contentPane.add(panelCenter, BorderLayout.CENTER);
	    panelCenter.setLayout(null);

        userLabel.setBounds(40, 60, 100, 25);
        panelCenter.add(userLabel);

        passLabel.setBounds(40, 110, 100, 25);
        panelCenter.add(passLabel);
        
        idField.setBounds(150, 60, 200, 25);
        panelCenter.add(idField);

        pass.setBounds(150, 110, 200, 25);
        panelCenter.add(pass);

        loginButton.setBounds(150, 200, 90, 30);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        panelCenter.add(loginButton);

        resetButton.setBounds(150, 250, 200, 30);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        panelCenter.add(resetButton);

        su.setBounds(260, 200, 90, 30);
        su.setFocusable(false);
        su.addActionListener(this);
        panelCenter.add(su);

        showPassword.setBounds(150, 150, 120, 25);
        showPassword.setFocusable(false);
        showPassword.addActionListener(this);
        panelCenter.add(showPassword);
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            idField.setText("");
            pass.setText("");
        }

        if (e.getSource() == loginButton) {
            String nickname = idField.getText();
            String password = String.valueOf(pass.getPassword());

            try {
                String query = "SELECT * FROM users WHERE nickname='" + nickname + "' AND password='" + password + "'";
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(frame, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    idField.setText("");
                    pass.setText("");
                    // You can add code here to navigate to the next page or show a success message
                    try {
    					ClientUI frame = new ClientUI();
    					System.setOut(new PrintStream(new TextAreaOutputStream(frame.txtAreaLogs)));
    					frame.setVisible(true);
    				} catch (Exception e1) {
    					e1.printStackTrace();
    				}
                    dispose(); // Close the LoginPage frame
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
                    idField.setText("");
                    pass.setText("");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == su) {
            SignUp signUp = new SignUp(connection, statement);
            frame.dispose();
        }

        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                pass.setEchoChar((char) 0); // Show the password in text form
            } else {
                pass.setEchoChar('*'); // Mask the password
            }
        }
    }

    //SwingUtilities.invokeLater(() -> new LoginPage());
    
    public static void main(String[] args) {
        
        try {
			LoginPage frame = new LoginPage();
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(frame);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}


