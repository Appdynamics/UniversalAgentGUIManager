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
import javax.swing.JFrame;
import com.appdynamics.universalagent.exceptions.NoAgentsException;
import com.appdynamics.universalagent.models.AgentTableModel;
import com.appdynamics.universalagent.models.GroupTableModel;
import com.appdynamics.universalagent.models.RulebookTableModel;
import com.appdynamics.universalagent.universalagent.Agent;
import com.appdynamics.universalagent.universalagent.ConnectionController;
import com.appdynamics.universalagent.universalagent.RestController;
import com.appdynamics.universalagent.universalagent.Group;
import com.appdynamics.universalagent.universalagent.Rulebook;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

public class MainApplicationWindow {

	/**
	 * Declare all swing components used from the UI
	 */
	private JFrame frame;
	private RestController controller;
	private JTable agentTable;
	private JScrollPane scrollPane;
	private JTable rulebookTable;
	private JLabel lblRulebooks;
	private JLabel lblUniversalAgents;
	private JLabel lblGroups;
	private JLabel controllerLabel;
	private JTable groupsTable;
	private JScrollPane scrollPane_2;
	private JButton btnCreateGroup;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;
	private JLabel lblSelectGroupAnd;
	private JButton btnNewButton_2;
	private JButton assignAgentToGroup;
	private JButton btnUpdate;
	private JLabel lblSelectGroupTo;
	private JButton refreshButton;
	private JButton btnNewButton;
	private JTextField rulebookFilterField;
	private TableRowSorter<RulebookTableModel> rulebookSorter;
	private JTextField agentFilterField;
	private TableRowSorter<AgentTableModel> agentSorter;
	private JTextField groupFilterField;
	private TableRowSorter<GroupTableModel> groupSorter;

	/**
	 * Launch the application.
	 */
	public void main(final ConnectionController controllerDetails) {
		this.controller = new RestController(controllerDetails);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplicationWindow window = new MainApplicationWindow(controllerDetails);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplicationWindow(ConnectionController controllerDetails) {
		this.controller = new RestController(controllerDetails);
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1400, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 97, 482, 158);
		frame.getContentPane().add(scrollPane);
		//Instantiate the table for all agents if there are any
		//Add swing components to enable table customization
		agentTable = new JTable();
		scrollPane.setViewportView(agentTable);
		agentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		agentTable.setVisible(true);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 361, 482, 169);
		frame.getContentPane().add(scrollPane_1);
		agentFilterField = new JTextField();
		agentFilterField.setBounds(300, 56, 150, 30);
		frame.getContentPane().add(agentFilterField);
		JLabel agentFilterLabel = new JLabel("Filter: ");
		agentFilterLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		agentFilterLabel.setBounds(260, 56, 50, 30);
		frame.getContentPane().add(agentFilterLabel);
		
		//Instantiate the table for all rulebooks if there are any
		//Add swing components to enable table customization
		rulebookTable = new JTable();
		rulebookTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		rulebookTable.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(rulebookTable);
		rulebookFilterField = new JTextField();
		rulebookFilterField.setBounds(300, 320, 150, 30);
		frame.getContentPane().add(rulebookFilterField);
		JLabel rulebookFilterLabel = new JLabel("Filter: ");
		rulebookFilterLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		rulebookFilterLabel.setBounds(260, 320, 50, 30);
		frame.getContentPane().add(rulebookFilterLabel);
		
		//Instantiate and customize labels
		lblRulebooks = new JLabel("Rulebooks");
		lblRulebooks.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblRulebooks.setBounds(23, 320, 482, 30);
		frame.getContentPane().add(lblRulebooks);
		lblUniversalAgents = new JLabel("Universal Agents");
		lblUniversalAgents.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUniversalAgents.setBounds(23, 56, 482, 30);
		frame.getContentPane().add(lblUniversalAgents);
		lblGroups = new JLabel("Groups");
		lblGroups.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGroups.setBounds(722, 56, 482, 30);
		frame.getContentPane().add(lblGroups);
		String tittle = "Controller: " + controller.getControllerUrl();
		controllerLabel = new JLabel(tittle);
		controllerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		controllerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		controllerLabel.setBounds(23, 11, 1081, 34);
		frame.getContentPane().add(controllerLabel);

		//Instantiate the table for all groups if there are any
		//Add swing components to enable table customization
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(722, 97, 306, 433);
		frame.getContentPane().add(scrollPane_2);
		groupsTable = new JTable();
		scrollPane_2.setViewportView(groupsTable);
		groupFilterField = new JTextField();
		groupFilterField.setBounds(880, 56, 150, 30);
		frame.getContentPane().add(groupFilterField);
		JLabel groupFilterLabel = new JLabel("Filter: ");
		groupFilterLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		groupFilterLabel.setBounds(840, 56, 50, 30);
		frame.getContentPane().add(groupFilterLabel);
		btnCreateGroup = new JButton("Create Group");
		btnCreateGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateOrUpdateGroup createGroupWindow = new CreateOrUpdateGroup(controller);
				createGroupWindow.run(controller);
			}
		});
		btnCreateGroup.setBounds(883, 541, 159, 29);
		frame.getContentPane().add(btnCreateGroup);
		btnNewButton_1 = new JButton("Delete Group");
		
		// Action Lister for the Delete Group - groups button Provides the functionality
		//to delete one or multiple groups
		 
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> groupToBeDeleted = getSelectedGroup(groupsTable);
				String groupNames = "";
				for (String s : groupToBeDeleted) {
					groupNames += " " + s;
				}
				if (groupNames.length() > 1) {
					if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete: " + groupNames + " ?",
							"Delete Group", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						int resultCode = 0;
						if (groupToBeDeleted.size() < 1) {
						} else if (groupToBeDeleted.size() == 1) {
							resultCode = controller.deleteGroup(groupToBeDeleted.get(0));
						} else {
							int result = 200;
							for (int i = 0; i < groupToBeDeleted.size(); i++) {
								result = controller.deleteGroup(groupToBeDeleted.get(i));
								if (result > 399) {
									resultCode = result;
								}
							}
						}
						if (resultCode > 199 & resultCode < 250) {
							showInformationMessage("Group " + groupNames + " have been deleted", "Groups Removed");
						} else if (resultCode == 0) {

						} else {
							showErrorMessage("Failed to delete groups " + groupNames,
									"Resulted Code was: " + resultCode);
						}
					}
					refresh();
				}
			}
		});
		btnNewButton_1.setBounds(1125, 190, 120, 30);
		frame.getContentPane().add(btnNewButton_1);
		lblNewLabel = new JLabel("Select Group to Delete");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblNewLabel.setBounds(1058, 165, 266, 14);
		frame.getContentPane().add(lblNewLabel);
		lblSelectGroupAnd = new JLabel("Select Group and Press Details");
		lblSelectGroupAnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectGroupAnd.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblSelectGroupAnd.setBounds(1038, 304, 286, 14);
		frame.getContentPane().add(lblSelectGroupAnd);
		
		//Button to open groupDetails Version
		btnNewButton_2 = new JButton("View Details");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String groupName = "";
				if (groupsTable.getSelectedRow() != -1) {
					// Convert View index to model index
					int row = groupsTable.convertRowIndexToModel(groupsTable.getSelectedRow());
					int col = 0;
					groupName = (String) groupsTable.getModel().getValueAt(row, col);
				} else {
					int rowCount = groupsTable.getRowCount();
					String[] groupNames = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						groupNames[i] = (String) groupsTable.getModel().getValueAt(i, 0);
					}
					Object response = JOptionPane.showInputDialog(frame, "Please select the target group",
							"Select Group", JOptionPane.QUESTION_MESSAGE, null, groupNames, "E");
					groupName = (String) response;
				}
				GroupDetails groupDetails = new GroupDetails(controller, groupName);
				groupDetails.run(controller, groupName);

			}
		});
		btnNewButton_2.setBounds(1125, 323, 120, 30);
		frame.getContentPane().add(btnNewButton_2);

		 // Button that assigns a rulebook to a group
		JButton assignRulebookToGroup = new JButton("Assign To Group");
		assignRulebookToGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rulebookName = "";
				String groupName = "";
				int selectedRulebookRow = -1;
				// Convert View index to model index
				if (rulebookTable.getSelectedRowCount() > 0) {
					selectedRulebookRow = rulebookTable.convertRowIndexToModel(rulebookTable.getSelectedRow());
				}
				int rulebookColumn = 0;
				// if there is not rulebook selected then a pop up window will
				// appear with a drop down list of all possible rulebooks
				if (selectedRulebookRow > -1) {
					rulebookName = (String) rulebookTable.getModel().getValueAt(selectedRulebookRow, rulebookColumn);
				} else {
					int rowCount = rulebookTable.getRowCount();
					String[] rulebookNames = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						rulebookNames[i] = (String) rulebookTable.getModel().getValueAt(i, 0);
					}
					Object response = JOptionPane.showInputDialog(frame, "Please select a rulebook", "Select Rulebook",
							JOptionPane.QUESTION_MESSAGE, null, rulebookNames, "E");
					rulebookName = (String) response;
				}
				// After a rulebook has been selected check if a group has been
				// selected as well, if not use a window pop up to select from a
				// list of groups
				if (rulebookName != null) {
					int selectedGroupRow = -1;
					if (groupsTable.getSelectedRowCount() > 0) {
						// Convert View index to model index
						selectedGroupRow = groupsTable.convertRowIndexToModel(groupsTable.getSelectedRow());
					}
					int groupColumn = 0;
					if (selectedGroupRow > -1) {
						groupName = (String) groupsTable.getModel().getValueAt(selectedGroupRow, groupColumn);
					} else {
						int rowCount = groupsTable.getRowCount();
						String[] groupNames = new String[rowCount];
						for (int i = 0; i < rowCount; i++) {
							groupNames[i] = (String) groupsTable.getModel().getValueAt(i, 0);
						}
						Object response = JOptionPane.showInputDialog(frame, "Please select the target group",
								"Select Rulebook", JOptionPane.QUESTION_MESSAGE, null, groupNames, "E");
						groupName = (String) response;
					}
				}
				if (groupName != null && rulebookName != null) {
					int result = 0;
					JFrame frmLoginSystem = new JFrame("Exit");
					if (rulebookTable.getSelectedRowCount() > 1 || groupsTable.getSelectedRowCount() > 1) {
						JOptionPane.showMessageDialog(frame, "Each Group can be assigned with one Rulebook only ",
								"Rulebook-Group Relationship", JOptionPane.INFORMATION_MESSAGE);
					}
					if (JOptionPane.showConfirmDialog(frmLoginSystem,
							"Confirm if you want to assign " + rulebookName + " to " + groupName + " group", " ",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						result = controller.assignRulebookToGroup(groupName, rulebookName);
					}
					if (result >= 200 && result < 220) {
						JOptionPane.showMessageDialog(frame, rulebookName + " has been assigned to " + groupName,
								"Successful assignment", JOptionPane.INFORMATION_MESSAGE);
					} else {
						String resultCode = "Result Code: " + result;
						JOptionPane.showMessageDialog(null, "Failed to assign " + rulebookName + " to " + groupName,
								resultCode, JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});
		assignRulebookToGroup.setBounds(346, 538, 159, 29);
		frame.getContentPane().add(assignRulebookToGroup);
		
		//Add functionality on the button responsible to assign agents
		//to groups
		assignAgentToGroup = new JButton("Assign To Group");
		assignAgentToGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedGroupRow = -1;
				// Convert View index to model index
				if (groupsTable.getSelectedRowCount() > 0) {
					selectedGroupRow = groupsTable.convertRowIndexToModel(groupsTable.getSelectedRow());
				}
				int groupColumn = 0;
				String selectedGroup = "";
				if (selectedGroupRow > -1) {
					selectedGroup = (String) groupsTable.getModel().getValueAt(selectedGroupRow, groupColumn);
				} else {
					int rowCount = groupsTable.getRowCount();
					String[] groupNames = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						groupNames[i] = (String) groupsTable.getModel().getValueAt(i, 0);
					}
					Object response = JOptionPane.showInputDialog(frame, "Please select the target group",
							"Select Agent", JOptionPane.QUESTION_MESSAGE, null, groupNames, "E");
					selectedGroup = (String) response;
				}
				if (selectedGroup != null & selectedGroup != "" && !selectedGroup.isEmpty()) {
					int selectedAgentsCount = 0;
					selectedAgentsCount = agentTable.getSelectedRowCount();
					String agentToBeAssigned = "";
					ArrayList<String> selectedAgentNames = new ArrayList<String>();
					if (selectedAgentsCount > 1) {
						int[] selectedRows = agentTable.getSelectedRows();
						// Convert View index to model index
						for (int i = 0; i < selectedRows.length; i++) {
							selectedRows[i] = agentTable.convertRowIndexToModel(selectedRows[i]);
						}
						for (Integer temp : selectedRows) {
							selectedAgentNames.add((String) agentTable.getModel().getValueAt(temp, 1));
						}
					} else if (selectedAgentsCount < 1) {
						int rowCount = agentTable.getRowCount();
						String[] agentNames = new String[rowCount];
						for (int i = 0; i < rowCount; i++) {
							agentNames[i] = (String) agentTable.getModel().getValueAt(i, 1);
						}
						Object response = JOptionPane.showInputDialog(frame,
								"Please select the agent you want to move to " + selectedGroup, "Select Agent",
								JOptionPane.QUESTION_MESSAGE, null, agentNames, "E");
						agentToBeAssigned = (String) response;
					} else if (selectedAgentsCount == 1) {
						// Convert View index to model index
						int selectedAgentRow = agentTable.convertRowIndexToModel(agentTable.getSelectedRow());
						agentToBeAssigned = (String) agentTable.getModel().getValueAt(selectedAgentRow, 1);
					}
					if (agentToBeAssigned != null && !agentToBeAssigned.isEmpty()) {
						int result = 0;
						JFrame frmLoginSystem = new JFrame("Exit");
						if (JOptionPane.showConfirmDialog(frmLoginSystem,
								"Do you want to assign " + agentToBeAssigned + " to " + selectedGroup + " group", " ",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
							result = controller.assignAgentToGroup(selectedGroup, agentToBeAssigned);
						}
						if (result >= 200 && result < 220) {
							JOptionPane.showMessageDialog(frame,
									agentToBeAssigned + " has been successfully added to " + selectedGroup,
									"Successful assignment", JOptionPane.INFORMATION_MESSAGE);
						} else if (result == 0) {
						} else {
							String resultCode = "Result Code: " + result;
							JOptionPane.showMessageDialog(null, "Failed to assign " + agentToBeAssigned, resultCode,
									JOptionPane.ERROR_MESSAGE);
						}
					} else if (selectedAgentNames.size() > 1) {
						String agentsToBeAssigned = "";
						for (int i = 0; i < selectedAgentNames.size(); i++) {
							agentsToBeAssigned += " " + selectedAgentNames.get(i);
						}
						int result = 0;
						JFrame frmLoginSystem = new JFrame("Exit");
						if (JOptionPane.showConfirmDialog(frmLoginSystem,
								"Do you want to assign " + agentsToBeAssigned + " to " + selectedGroup + " group", " ",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
							result = controller.assignAgentsFromGroup(selectedAgentNames, selectedGroup);
						}
						if (result >= 200 && result < 220) {
							JOptionPane.showMessageDialog(frame,
									selectedAgentNames + " have been successfully assigned to " + selectedGroup,
									"Successful assignment", JOptionPane.INFORMATION_MESSAGE);
						} else {
							String resultCode = "Result Code: " + result;
							JOptionPane.showMessageDialog(null, "Failed to assign " + agentToBeAssigned, resultCode,
									JOptionPane.ERROR_MESSAGE);
						}

					}
				}
				refresh();
			}
		});
		assignAgentToGroup.setBounds(346, 263, 159, 29);
		frame.getContentPane().add(assignAgentToGroup);
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String groupName = "";
				if (groupsTable.getSelectedRow() != -1) {
					// Convert View index to model index
					int row = groupsTable.convertRowIndexToModel(groupsTable.getSelectedRow());
					int col = 0;
					groupName = (String) groupsTable.getModel().getValueAt(row, col);

				} else {
					int rowCount = groupsTable.getRowCount();
					String[] groupNames = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						groupNames[i] = (String) groupsTable.getModel().getValueAt(i, 0);
					}
					Object response = JOptionPane.showInputDialog(frame, "Please select the target group",
							"Select Group", JOptionPane.QUESTION_MESSAGE, null, groupNames, "E");
					groupName = (String) response;
				}
				UpdateGroup updateGroup = new UpdateGroup(controller, groupName);
				updateGroup.run(controller, groupName);
			}
		});
		btnUpdate.setBounds(1125, 262, 120, 30);
		frame.getContentPane().add(btnUpdate);
		lblSelectGroupTo = new JLabel("Select Group to Update");
		lblSelectGroupTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectGroupTo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblSelectGroupTo.setBounds(1058, 241, 266, 14);
		frame.getContentPane().add(lblSelectGroupTo);
		JButton createRulebookButton = new JButton("Create Rulebook");
		createRulebookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateRulebookNew(controller);
			}
		});
		createRulebookButton.setBounds(23, 538, 139, 29);
		frame.getContentPane().add(createRulebookButton);

		
		 // Button to find the selected Rulebook from the table
		JButton updateRulebookBtn = new JButton("Update Rulebook");
		updateRulebookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRulebookRow = -1;
				// Convert View index to model index
				selectedRulebookRow = rulebookTable.convertRowIndexToModel(rulebookTable.getSelectedRow());
				if (selectedRulebookRow > -1) {
					String rulebookName = (String) rulebookTable.getModel().getValueAt(selectedRulebookRow, 0);
					Rulebook rulebook;
					try {
						rulebook = controller.getRulebookByName(rulebookName);
						new UpdateRulebookNew(controller, rulebook);

					} catch (NoAgentsException e1) {
						e1.printStackTrace();
					}
				} else {
					int rowCount = rulebookTable.getRowCount();
					String[] rulebookName = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						rulebookName[i] = (String) rulebookTable.getModel().getValueAt(i, 0);
					}
					Object response = JOptionPane.showInputDialog(frame,
							"Please select the rulebook you want to update", "Select Rulebook",
							JOptionPane.QUESTION_MESSAGE, null, rulebookName, "E");
					if (response != null) {
						Rulebook rulebook;
						try {
							rulebook = controller.getRulebookByName((String) response);
							new UpdateRulebookNew(controller, rulebook);
						} catch (NoAgentsException e1) {
							e1.printStackTrace();
						}
					}
				}
			}

		});
		updateRulebookBtn.setBounds(23, 578, 139, 29);
		frame.getContentPane().add(updateRulebookBtn);

		// Button used to refresh the frame
		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		refreshButton.setBounds(1125, 18, 117, 39);
		frame.getContentPane().add(refreshButton);
		
		// Button to delete a specific rulebook.
		btnNewButton = new JButton("Delete Rulebook");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRulebookRow = -1;
				// Convert View index to model index
				selectedRulebookRow = rulebookTable.convertRowIndexToModel(rulebookTable.getSelectedRow());
				int rulebookColumn = 0;
				int result = -1;
				String name = "";
				// if there is not rulebook selected then a pop up window will
				// appear with a drop down list of all possible rulebooks
				if (selectedRulebookRow > -1) {
					name = (String) rulebookTable.getModel().getValueAt(selectedRulebookRow, rulebookColumn);
				} else {
					int rowCount = rulebookTable.getRowCount();
					String[] rulebookName = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						rulebookName[i] = (String) rulebookTable.getModel().getValueAt(i, 0);
					}
					Object response = JOptionPane.showInputDialog(frame,
							"Please select the rulebook you want to update", "Select Rulebook",
							JOptionPane.QUESTION_MESSAGE, null, rulebookName, "E");
					name = (String) response;

				}
				
				// JOptionPanes are used to notify the user for its next actions
				if (name != null && !name.isEmpty()) {
					if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete: " + name + " ?",
							"Delete Rulebook", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						result = controller.deleteRulebook(name);
					}
					if (result >= 200 && result < 220) {
						JOptionPane.showMessageDialog(frame, name + " has been successfully removed!", "Successful ",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (result < 0) {
					} else {
						String resultCode = "Result Code: " + result;
						JOptionPane.showMessageDialog(null, "The " + name + " has not been removed!", resultCode,
								JOptionPane.ERROR_MESSAGE);
					}
					refresh();
				}
			}
		});
		btnNewButton.setBounds(186, 538, 139, 29);
		frame.getContentPane().add(btnNewButton);

		//Code to manage the deletion of a selected agent
		JButton deleteAgentButton = new JButton("Delete Agent");
		deleteAgentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedAgentsCount = 0;
				selectedAgentsCount = agentTable.getSelectedRowCount();
				String agentToBeDeleted = "";
				ArrayList<String> selectedAgentNames = new ArrayList<String>();
				ArrayList<Integer> rowIndexes = new ArrayList<Integer>();
				if (selectedAgentsCount > 1) {
					int[] selectedRows = agentTable.getSelectedRows();
					// Convert View index to model index
					for (int i = 0; i < selectedRows.length; i++) {
						selectedRows[i] = agentTable.convertRowIndexToModel(selectedRows[i]);
					}
					for (Integer temp : selectedRows) {
						selectedAgentNames.add((String) agentTable.getModel().getValueAt(temp, 1));
						rowIndexes.add(temp);
					}
				} else if (selectedAgentsCount < 1) {
					int rowCount = agentTable.getRowCount();
					String[] agentNames = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						agentNames[i] = (String) agentTable.getModel().getValueAt(i, 1);
					}
					Object response = JOptionPane.showInputDialog(frame, "Please select the agent you want to delete",
							"Select Agent", JOptionPane.QUESTION_MESSAGE, null, agentNames, "E");
					if (response != null) {
						agentToBeDeleted = (String) response;
						selectedAgentNames.add(agentToBeDeleted);
					}
				} else if (selectedAgentsCount == 1) {
					// Convert View index to model index
					int selectedAgentRow = agentTable.convertRowIndexToModel(agentTable.getSelectedRow());
					agentToBeDeleted = (String) agentTable.getModel().getValueAt(selectedAgentRow, 1);
					selectedAgentNames.add(agentToBeDeleted);
				}
				if (selectedAgentNames.size() > 0) {
					String aboutToDelete = "";
					for (int i = 0; i < selectedAgentNames.size(); i++) {
						aboutToDelete += " " + selectedAgentNames.get(i);
					}
					int result = 0;
					JFrame frmLoginSystem = new JFrame("Exit");
					if (JOptionPane.showConfirmDialog(frmLoginSystem, "Do you want to delete " + aboutToDelete, " ",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						result = controller.deleteAgent(selectedAgentNames);
					}
					if (result >= 199 && result < 220) {
						JOptionPane.showMessageDialog(frame, aboutToDelete + " has been removed", "Successfuly removed",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (result == 0) {
					} else {
						String resultCode = "Result Code: " + result;
						showErrorMessage("Failed to delete " + aboutToDelete, resultCode);
					}
				}
				refresh();
			}
		});
		deleteAgentButton.setBounds(166, 262, 159, 29);
		frame.getContentPane().add(deleteAgentButton);
		JButton exportButton = new JButton("Export");
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExportWindow exportWindow = new ExportWindow();
				exportWindow.run();
				// ExportHistoryToBash exporter =new ExportHistoryToBash();
				// exporter.export();
			}
		});
		exportButton.setBounds(1269, 18, 89, 39);
		frame.getContentPane().add(exportButton);
		refresh();
	}
	
	/**
	* Forces the frame to dynamically refresh all of its entries for its table
	*/
	public void refresh() {
		try {
			ArrayList<Group> groups = controller.getAllGroups();
			GroupTableModel groupModel = new GroupTableModel(groups);
			groupsTable.setModel(groupModel);
			groupSorter = new TableRowSorter<GroupTableModel>(groupModel);
			groupsTable.setRowSorter(groupSorter);
			groupFilterField.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					filterGroup();
				}
				public void removeUpdate(DocumentEvent e) {
					filterGroup();
				}
				public void insertUpdate(DocumentEvent e) {
					filterGroup();
				}
				public void filterGroup() {
					groupSorter.setRowFilter(RowFilter.regexFilter("(?i)" + groupFilterField.getText()));
					groupsTable.setRowSorter(groupSorter);
				}
			});
		} catch (NoAgentsException ae) {
			groupsTable.setModel(new GroupTableModel(new ArrayList<Group>()));
		}
		try {
			ArrayList<Rulebook> rulebooks = controller.getAllRulebooks();
			RulebookTableModel rulebookModel = new RulebookTableModel(rulebooks);
			rulebookTable.setModel(rulebookModel);
			rulebookSorter = new TableRowSorter<RulebookTableModel>(rulebookModel);
			rulebookTable.setRowSorter(rulebookSorter);
			rulebookFilterField.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					filter();
				}
				public void removeUpdate(DocumentEvent e) {
					filter();
				}
				public void insertUpdate(DocumentEvent e) {
					filter();
				}
				public void filter() {
					rulebookSorter.setRowFilter(RowFilter.regexFilter("(?i)" + rulebookFilterField.getText()));
					rulebookTable.setRowSorter(rulebookSorter);
				}
			});
		} catch (NoAgentsException ae) {
			rulebookTable.setModel(new RulebookTableModel(new ArrayList<Rulebook>()));
		}
		try {
			ArrayList<Agent> agents = controller.getAllAgents();
			AgentTableModel agentsModel = new AgentTableModel(agents);
			agentTable.setModel(agentsModel);
			agentSorter = new TableRowSorter<AgentTableModel>(agentsModel);
			agentTable.setRowSorter(agentSorter);
			agentFilterField.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					filter();
				}

				public void removeUpdate(DocumentEvent e) {
					filter();
				}

				public void insertUpdate(DocumentEvent e) {
					filter();
				}

				public void filter() {
					agentSorter.setRowFilter(RowFilter.regexFilter("(?i)" + agentFilterField.getText()));
					agentTable.setRowSorter(agentSorter);
				}
			});

		} catch (NoAgentsException ae) {
			agentTable.setModel(new AgentTableModel(new ArrayList<Agent>()));
			agentTable.removeAll();
		}

	}

	/**
	 * Method to return the selected group-groups
	 * 
	 * @param tableName
	 * @return
	 */
	public ArrayList<String> getSelectedGroup(JTable tableName) {
		ArrayList<String> selectedItems = new ArrayList<String>();
		int selectedRowCount = tableName.getSelectedRowCount();
		int col = 0;
		if (selectedRowCount != -1 && selectedRowCount == 1) {
			// Convert View index to model index
			int row = tableName.convertRowIndexToModel(tableName.getSelectedRow());
			selectedItems.add((String) tableName.getModel().getValueAt(row, col));

		} else if (selectedRowCount > 1) {
			// Convert View index to model index
			int index[] = tableName.getSelectedRows();
			for (int i = 0; i < index.length; i++) {
				index[i] = tableName.convertRowIndexToModel(index[i]);
			}
			for (Integer temp : index) {
				selectedItems.add((String) tableName.getModel().getValueAt(temp, col));
			}
		} else {
			int rowCount = tableName.getRowCount();
			String[] groupNames = new String[rowCount];
			for (int i = 0; i < rowCount; i++) {
				groupNames[i] = (String) tableName.getModel().getValueAt(i, 0);
			}
			Object response = JOptionPane.showInputDialog(frame, "Please select the group you want to delete",
					"Delete Group", JOptionPane.QUESTION_MESSAGE, null, groupNames, "E");
			if (response != null) {
				selectedItems.add((String) response);
			}
		}
		return selectedItems;
	}
	public void showInformationMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	public void showErrorMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
}
