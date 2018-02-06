/*******************************************************************************
 * Copyright 2018 AppDynamics LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.appdynamics.universalagent.gui;

/**
 * 
 * @author nikolaos.papageorgiou
 *
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import com.appdynamics.universalagent.universalagent.ConnectionController;
import javax.swing.SwingConstants;
import java.awt.Font;

public class LoginAuthentication {

	private JFrame frame;
	private JTextField textControllerUrl;
	private JPasswordField textPassword;
	private JTextField textControllerPort;
	private JTextField textAccountName;
	private JTextField textUsername;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAuthentication window = new LoginAuthentication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAuthentication window = new LoginAuthentication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginAuthentication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JList list = new JList();
		list.setBounds(238, 31, 1, 1);
		frame.getContentPane().add(list);

		JList list_1 = new JList();
		list_1.setBounds(64, 90, 1, 1);
		frame.getContentPane().add(list_1);

		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(150, 166, 200, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Controller Domain");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(150, 65, 200, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textControllerUrl = new JTextField();
		textControllerUrl.setBounds(350, 62, 157, 20);
		frame.getContentPane().add(textControllerUrl);
		textControllerUrl.setColumns(10);

		textPassword = new JPasswordField();
		textPassword.setBounds(350, 163, 157, 20);
		frame.getContentPane().add(textPassword);

		JLabel lblNewLabel_2 = new JLabel("Account Name");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(150, 116, 200, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(150, 141, 200, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblPort = new JLabel("Port");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setBounds(150, 91, 200, 14);
		frame.getContentPane().add(lblPort);
		final JRadioButton radioIsSSL = new JRadioButton("SSL Enabled");
		radioIsSSL.setBounds(600, 87, 109, 23);
		frame.getContentPane().add(radioIsSSL);

		JList list_2 = new JList();
		list_2.setBounds(188, 144, 1, 1);
		frame.getContentPane().add(list_2);

		JLabel lblNewLabel_4 = new JLabel("Controller Authentication");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(78, 11, 631, 29);
		frame.getContentPane().add(lblNewLabel_4);

		JButton authenticateButton = new JButton("Authenticate");
		authenticateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String password = new String(textPassword.getPassword());
				String username = textUsername.getText();
				String controllerUrl = textControllerUrl.getText();
				String controllerPort = textControllerPort.getText();
				String account = textAccountName.getText();
				Boolean isSSL = radioIsSSL.isSelected();
				ConnectionController connectionDetails = new ConnectionController();

				connectionDetails.setAccount(account);
				connectionDetails.setPassword(password);
				connectionDetails.setPort(controllerPort);
				connectionDetails.setUrl(controllerUrl);
				connectionDetails.setSSL(isSSL);
				connectionDetails.setUsername(username);

				if (connectionDetails.authenticate()) {
					MainApplicationWindow window = new MainApplicationWindow(connectionDetails);
					window.main(connectionDetails);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		authenticateButton.setBounds(182, 219, 110, 23);
		frame.getContentPane().add(authenticateButton);

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textControllerUrl.setText(null);
				textControllerPort.setText(null);
				textAccountName.setText(null);
				textUsername.setText(null);
				textPassword.setText(null);

			}
		});
		resetButton.setBounds(350, 219, 110, 23);
		frame.getContentPane().add(resetButton);

		textControllerPort = new JTextField();
		textControllerPort.setColumns(10);
		textControllerPort.setBounds(350, 88, 157, 20);
		frame.getContentPane().add(textControllerPort);

		textAccountName = new JTextField();
		textAccountName.setColumns(10);
		textAccountName.setBounds(350, 113, 157, 20);
		frame.getContentPane().add(textAccountName);

		textUsername = new JTextField();
		textUsername.setColumns(10);
		textUsername.setBounds(350, 138, 157, 20);
		frame.getContentPane().add(textUsername);

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit",
						"Contoller Authentication", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}

			}
		});
		exitButton.setBounds(510, 219, 110, 23);
		frame.getContentPane().add(exitButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 194, 764, 14);
		frame.getContentPane().add(separator);

	}
}
