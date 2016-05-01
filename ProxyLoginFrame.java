package com.net.connect.proxy;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class ProxyLoginFrame extends JFrame implements ActionListener {
	JButton loginButton;
	JButton logoutButton;
	JPanel panel;
	JLabel label1, label2;
	final JTextField userName, password;

	ProxyLoginFrame() {
		label1 = new JLabel();
		label1.setText("Username:");
		userName = new JTextField(15);

		label2 = new JLabel();
		label2.setText("Password:");
		password = new JPasswordField(15);

		loginButton = new JButton("SUBMIT");
		logoutButton = new JButton("Logout");

		panel = new JPanel(new GridLayout(3, 1));
		panel.add(label1);
		panel.add(userName);
		panel.add(label2);
		panel.add(password);
		panel.add(loginButton);
		add(panel, BorderLayout.CENTER);
		loginButton.addActionListener(this);
		logoutButton.setSize(10, 5);
		logoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logoutUser();

			}
		});
		setTitle("USER LOGIN FORM");
	}

	public void actionPerformed(ActionEvent ae) {
		String value1 = userName.getText();
		String value2 = password.getText();
		if (authenticateUser(value1, value2)) {
			LoggedInFrame page = new LoggedInFrame();
			page.setVisible(true);
			JLabel label = new JLabel("Access Allowed:" + value1);
			page.getContentPane().add(label);
			page.getContentPane().add(logoutButton);
			this.dispose();
		} else {
			System.out.println("enter the valid username and password");
			JOptionPane.showMessageDialog(this, "Incorrect login or password",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private boolean logoutUser() {

		String serverName = "127.0.0.1";
		int port = Integer.parseInt("8089");
		try {
			System.out.println("Connecting to " + serverName + " on port "
					+ port);
			Socket client = new Socket(serverName, port);
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			out.writeUTF("Logout"+"::"+  "::" +  "::"
					+ client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			String response = in.readUTF();
			client.close();
			if (response.equalsIgnoreCase("true")) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
		return false;
	}

	private boolean authenticateUser(String value1, String value2) {

		String serverName = "127.0.0.1";
		int port = Integer.parseInt("8089");
		try {
			System.out.println("Connecting to " + serverName + " on port "
					+ port);
			Socket client = new Socket(serverName, port);
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			out.writeUTF("Login"+"::"+value1 + "::" + value2 + "::"
					+ client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			String response = in.readUTF();
			client.close();
			if (response.equalsIgnoreCase("true")) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
