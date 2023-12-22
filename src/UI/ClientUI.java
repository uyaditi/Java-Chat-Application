package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import Encryption.symmetric.*;
import Encryption.asymmetric.*;
import Encryption.hash.*;


public class ClientUI extends JFrame implements ActionListener {	

	// Socket Related
	private static Socket clientSocket;
	private static int PORT;
	private PrintWriter out;

	// JFrame related
	private JComboBox dropdown;
	private JPanel contentPane;
	JTextArea txtAreaLogs;
	private JButton btnStart;
	private JPanel panelNorth;
	private JLabel lblChatClient;
	private JPanel panelNorthSouth;
	private JLabel lblPort;
	private JLabel lblName;
	private JLabel lblAlgo;
	private JPanel panelSouth;
	private JButton btnSend;
	private JTextField txtMessage;
	private JTextField txtNickname;
	private JTextField txtPort;
	private String clientName;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUI frame = new ClientUI();
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
					System.setOut(new PrintStream(new TextAreaOutputStream(frame.txtAreaLogs)));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));

		lblChatClient = new JLabel("CHAT CLIENT");
		lblChatClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblChatClient.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panelNorth.add(lblChatClient, BorderLayout.NORTH);

		panelNorthSouth = new JPanel();
		panelNorth.add(panelNorthSouth, BorderLayout.SOUTH);
		panelNorthSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		lblAlgo = new JLabel("Algorithm");
		panelNorthSouth.add(lblAlgo);
		
		String[] choices = {"None", "Asymmetric - RSA", "Hash - MD5", "Hash - SHA", "AES", "DES" };
		dropdown = new JComboBox<String>(choices);

		panelNorthSouth.add(dropdown);
		
		lblName = new JLabel("Nickname");
		panelNorthSouth.add(lblName);

		txtNickname = new JTextField();
		txtNickname.setColumns(10);
		panelNorthSouth.add(txtNickname);

		lblPort = new JLabel("Port");
		panelNorthSouth.add(lblPort);

		txtPort = new JTextField();
		panelNorthSouth.add(txtPort);
		txtPort.setColumns(10);

		btnStart = new JButton("START");
		panelNorthSouth.add(btnStart);
		btnStart.addActionListener(this);
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		txtAreaLogs = new JTextArea();
		txtAreaLogs.setBackground(new Color(128, 0, 0));
		txtAreaLogs.setForeground(Color.WHITE);
		txtAreaLogs.setLineWrap(true);
		scrollPane.setViewportView(txtAreaLogs);

		panelSouth = new JPanel();
		FlowLayout fl_panelSouth = (FlowLayout) panelSouth.getLayout();
		fl_panelSouth.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelSouth, BorderLayout.SOUTH);

		txtMessage = new JTextField();
		panelSouth.add(txtMessage);
		txtMessage.setColumns(50);

		btnSend = new JButton("SEND");
		btnSend.addActionListener(this);
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelSouth.add(btnSend);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			if(btnStart.getText().equals("START")) {
				btnStart.setText("STOP");
				start();
			}else {
				btnStart.setText("START");
				stop();
			}
		}else if(e.getSource() == btnSend) {
			String message = txtMessage.getText().trim();
			if(!message.isEmpty()) {
				
				String selectedAlgo = (String) dropdown.getSelectedItem();
				
				if (selectedAlgo == "None") {
					out.println(message);
					txtMessage.setText("");
				}
				else if (selectedAlgo == "Asymmetric - RSA") {
					try {
			            // Generate key pair
			            KeyPair keyPair = RSAImplementation.generateKeyPair();
			            PublicKey publicKey = keyPair.getPublic();
			            PrivateKey privateKey = keyPair.getPrivate();
			 
			            // Encrypt the user input message using the public key
			            String rsaMssg = RSAImplementation.encrypt(message, publicKey);
			            out.println("Encrypted Message with RSA: " + message);
			 
			            // Decrypt the encrypted message using the private key
			            String rsaMssg2 = RSAImplementation.decrypt(rsaMssg, privateKey);
			            out.println("Decrypted Message: " + rsaMssg2);
			            txtMessage.setText("");
			        } catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
				else if (selectedAlgo == "Hash - MD5") {
					String hashedValue = MD5Implementation.hashMD5(message);
	                out.println("MD5 Hash:" + hashedValue);
					txtMessage.setText("");
				}
				else if (selectedAlgo == "Hash - SHA") {
					String hashedValue = SHAImplementation.hashSHA256(message);
	                out.println("SHA-256 Hash:" + hashedValue);
					txtMessage.setText("");
				}
				else if (selectedAlgo == "DES") {
					AESEncryption des = new AESEncryption();
					try {
						String desMssg = des.encrypt(message, "1234567890123456");
						out.println("Encrypted with DES: " +desMssg);
						String desMssg2 = des.decrypt(desMssg, "1234567890123456");
						out.println("Decrypted text: " +desMssg2);
						txtMessage.setText("");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (selectedAlgo == "AES") {
					AESEncryption aes = new AESEncryption();
					try {
						String aesMssg = aes.encrypt(message, "1234567890123456");
						out.println("Encrypted with AES: " +aesMssg);
						String aesMssg2 = aes.decrypt(aesMssg, "1234567890123456");
						out.println("Decrypted text: " +aesMssg2);
						txtMessage.setText("");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		}
		//Refresh UI
		refreshUIComponents();
	}

	public void refreshUIComponents() {

	}

	public void start() {
		try {
			PORT = Integer.parseInt(txtPort.getText().trim());
			clientName = txtNickname.getText().trim();
			clientSocket = new Socket("localhost", PORT);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			new Thread(new Listener()).start();
			//send name
			out.println(clientName);
		} catch (Exception err) {
			addToLogs("[ERROR] "+err.getLocalizedMessage());
		}
	}

	public void stop(){
		if(!clientSocket.isClosed()) {
			try {
				clientSocket.close();
			} catch (IOException e1) {}
		}
	}

	public static void addToLogs(String message) {
		System.out.printf("%s %s\n", ServerUI.formatter.format(new Date()), message);
	}

	private static class Listener implements Runnable {
		private BufferedReader in;
		@Override
		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String read;
				for(;;) {
					read = in.readLine();
					if (read != null && !(read.isEmpty())) addToLogs(read);
				}
			} catch (IOException e) {
				return;
			}
		}

	}
}
