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
 * CreateOrUpdateGroup is the class that controller the pop up window when update group is slelected from the main group
 * @author nikolaos.papageorgiou
 *
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import com.appdynamics.universalagent.universalagent.RestController;
import com.appdynamics.universalagent.universalagent.Group;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateOrUpdateGroup {

	private JFrame frame;
	private JTextField textGroupName;
	private JTextField textGroupComments;
	private JLabel txtGroupName;
	private JLabel txtGroupComments;
	private JLabel Instructions;
	private JButton btnNewButton;
	private RestController controller;
	private JLabel lblNameCannotContain;

	public void run(final RestController controller) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateOrUpdateGroup window = new CreateOrUpdateGroup(controller);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public CreateOrUpdateGroup(RestController controller) {
		this.controller = controller;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 700, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Create Or Update Group");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel.setBounds(109, 11, 263, 45);
		frame.getContentPane().add(lblNewLabel);

		textGroupName = new JTextField();
		textGroupName.setBounds(176, 79, 235, 20);
		frame.getContentPane().add(textGroupName);
		textGroupName.setColumns(10);

		textGroupComments = new JTextField();
		textGroupComments.setColumns(10);
		textGroupComments.setBounds(176, 131, 235, 20);
		frame.getContentPane().add(textGroupComments);

		txtGroupName = new JLabel("Group Name:");
		txtGroupName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGroupName.setBounds(10, 76, 148, 20);
		frame.getContentPane().add(txtGroupName);

		txtGroupComments = new JLabel("Group Comments:");
		txtGroupComments.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGroupComments.setBounds(10, 131, 148, 20);
		frame.getContentPane().add(txtGroupComments);

		Instructions = new JLabel("If group exists it will be updated with the new values");
		Instructions.setFont(new Font("Tahoma", Font.ITALIC, 12));
		Instructions.setBounds(10, 207, 448, 25);
		frame.getContentPane().add(Instructions);

		btnNewButton = new JButton("Create Or Update Group");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String groupName = textGroupName.getText();
				String groupComments = textGroupComments.getText();

				Group group = new Group();

				group.setName(groupName);
				group.setComments(groupComments);

				int resultCode = controller.createOrUpdateGroup(group);

				if (resultCode == 200) {
					JOptionPane.showMessageDialog(frame, "Group " + group.getName() + " has been created!",
							"Group Created or Updated Successfully", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				} else {
					String result = "The resulted Code was: " + resultCode;
					JOptionPane.showMessageDialog(null, "The group creation or update has failed", result,
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton.setBounds(176, 173, 235, 23);
		frame.getContentPane().add(btnNewButton);

		lblNameCannotContain = new JLabel("Name cannot contain spaces or special characters");
		lblNameCannotContain.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNameCannotContain.setBounds(176, 99, 298, 25);
		frame.getContentPane().add(lblNameCannotContain);
	}

}
