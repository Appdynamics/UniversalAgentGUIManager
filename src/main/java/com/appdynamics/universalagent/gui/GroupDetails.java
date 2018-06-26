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

import java.awt.EventQueue;
import javax.swing.JFrame;
import com.appdynamics.universalagent.exceptions.NoAgentsException;
import com.appdynamics.universalagent.models.AgentTableModel;
import com.appdynamics.universalagent.models.RulebookTableModel;
import com.appdynamics.universalagent.universalagent.Agent;
import com.appdynamics.universalagent.universalagent.Group;
import com.appdynamics.universalagent.universalagent.RestController;
import com.appdynamics.universalagent.universalagent.Rulebook;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.Dimension;

/**
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class GroupDetails {

	/**
	 * Swing component declaration
	 */
	private JFrame frame;
	private RestController controller;
	private JTable assignedAgentsTable;
	private JTable assignedRulebooksTable;
	private JTable availableRulebooksTable;
	private JTable availableAgentTable;
	private String selectedGroup;
	private JComboBox groupSelectionComboBox;
	private JPanel innerAvailableRuleboooksPanel;
	private JTextField availableAgentFilterField;
	private TableRowSorter<AgentTableModel> availableAgentSorter;
	private JTextField assignedAgentFilterField;
	private TableRowSorter<AgentTableModel> assignedAgentSorter;
	private JTextField assignedRulebookFilterField;
	private TableRowSorter<RulebookTableModel> assignedRulebookSorter;
	private JTextField availableRulebookFilterField;
	private TableRowSorter<RulebookTableModel> availableRulebookSorter;

	/**
	 * Launch the application.
	 */
	public void run(final RestController controller, final String selectedGroup) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupDetails window = new GroupDetails(controller, selectedGroup);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GroupDetails(RestController controller, String selectedGroup) {
		this.controller = controller;
		this.selectedGroup = selectedGroup;
		initialize();
	}

	/**
	 * Initialize the main frame of the application.
	 * Instantiate, customize and apply features to swing components
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1400, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Group Details ");
		lblNewLabel.setBounds(0, 0, 1384, 36);
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		JPanel availableAgentsPanel = new JPanel();
		availableAgentsPanel.setBounds(10, 95, 249, 512);
		frame.getContentPane().add(availableAgentsPanel);
		availableAgentsPanel.setLayout(null);

		 // Selected Group's agents panel
		JPanel innerAvailableAgentsPanel = new JPanel();
		innerAvailableAgentsPanel.setBounds(10, 52, 229, 420);
		availableAgentsPanel.add(innerAvailableAgentsPanel);
		availableAgentTable = new JTable();
		JScrollPane availableAgentScrollPane = new JScrollPane();
		availableAgentScrollPane.setPreferredSize(
				new Dimension(innerAvailableAgentsPanel.getWidth(), innerAvailableAgentsPanel.getHeight()));
		availableAgentScrollPane.setBounds(10, 52, 229, 420);
		availableAgentScrollPane.setViewportView(availableAgentTable);
		availableAgentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		availableAgentTable.setVisible(true);
		innerAvailableAgentsPanel.add(availableAgentScrollPane);
		JLabel lblAgentsAvailable = new JLabel("Agents Available");
		lblAgentsAvailable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAgentsAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgentsAvailable.setBounds(10, 0, 229, 30);
		availableAgentsPanel.add(lblAgentsAvailable);
		
    	//Text field to filter available agent table
		availableAgentFilterField= new JTextField();
		availableAgentFilterField.setBounds(45,30 , 150, 20);
		availableAgentsPanel.add(availableAgentFilterField);
		JLabel agentFilterLabel= new JLabel("Filter:");
		agentFilterLabel.setFont(new Font("Times New Roman",Font.ITALIC,15));
		agentFilterLabel.setBounds(10, 30, 43, 20);
		availableAgentsPanel.add(agentFilterLabel);
		agentFilterLabel.setFont(new Font("Times New Roman",Font.ITALIC,15));
		agentFilterLabel.setBounds(5, 30, 50, 20);
		
		//Button functionality to assign available agents
		//to the open group
		JButton btnNewButton = new JButton("Assign To Group");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedAgentsCount = 0;
				selectedAgentsCount = availableAgentTable.getSelectedRowCount();
				String agentToBeAssigned = "";
				ArrayList<String> selectedAgentNames = new ArrayList<String>();
				if (selectedAgentsCount > 1) {
					int[] selectedRows = availableAgentTable.getSelectedRows();
					for(int i=0;i<selectedRows.length;i++) {
						selectedRows[i]=availableAgentTable.convertRowIndexToModel(selectedRows[i]);
					}
					for (Integer temp : selectedRows) {
						selectedAgentNames.add((String) availableAgentTable.getModel().getValueAt(temp, 1));
					}
				} else if (selectedAgentsCount < 1) {
					int rowCount = availableAgentTable.getRowCount();
					String[] agentNames = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						agentNames[i] = (String) availableAgentTable.getModel().getValueAt(i, 1);
					}
					Object response = JOptionPane.showInputDialog(frame,
							"Please select the agent you want to assign to " + selectedGroup, "Select Agent",
							JOptionPane.QUESTION_MESSAGE, null, agentNames, "E");
					agentToBeAssigned = (String) response;
				} else if (selectedAgentsCount == 1) {
					int selectedAgentRow = availableAgentTable.convertRowIndexToModel(availableAgentTable.getSelectedRow());
					agentToBeAssigned = (String) availableAgentTable.getModel().getValueAt(selectedAgentRow, 1);
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
								"" + agentsToBeAssigned + " has been successfully assigned to " + selectedGroup,
								"Successful assignment", JOptionPane.INFORMATION_MESSAGE);
					} else if (result == 0) {

					} else {
						String resultCode = "Result Code: " + result;
						JOptionPane.showMessageDialog(null, "Failed to assign " + agentToBeAssigned, resultCode,
								JOptionPane.ERROR_MESSAGE);
					}
				}
				refresh();
			}
		});
		btnNewButton.setBounds(20, 475, 200, 35);
		availableAgentsPanel.add(btnNewButton);
		JPanel groupPanel = new JPanel();
		groupPanel.setBorder(new LineBorder(Color.GRAY, 3));
		groupPanel.setBounds(284, 95, 814, 515);
		frame.getContentPane().add(groupPanel);
		groupPanel.setLayout(null);
		 
		// Assigned Agents Panel
		JPanel agentsAssignedPanel = new JPanel();
		agentsAssignedPanel.setBounds(10, 51, 388, 420);
		groupPanel.add(agentsAssignedPanel);
		assignedAgentsTable = new JTable();
		JScrollPane agentsScrollPane = new JScrollPane();
		agentsScrollPane
				.setPreferredSize(new Dimension(agentsAssignedPanel.getWidth(), agentsAssignedPanel.getHeight()));
		agentsScrollPane.setBounds(10, 51, 388, 420);
		agentsScrollPane.setViewportView(assignedAgentsTable);
		assignedAgentsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		assignedAgentsTable.setVisible(true);
		agentsAssignedPanel.add(agentsScrollPane);
		//Filter Label+ Field
		JLabel assignedAgentFilterLabel= new JLabel("Filter:");
		assignedAgentFilterLabel.setBounds(10, 30, 40, 16);
		assignedAgentFilterLabel.setFont(new Font("Times New Roman",Font.ITALIC,15));
		groupPanel.add(assignedAgentFilterLabel);
		//Text field to filter assigned agent table
		assignedAgentFilterField= new JTextField();
		groupPanel.add(assignedAgentFilterField);
		assignedAgentFilterField.setBounds(50,30 ,150, 20);
	
		 // Assigned Rulebooks Panel
		JPanel assignedRulebooksPanel = new JPanel();
		assignedRulebooksPanel.setBounds(414, 51, 390, 420);
		groupPanel.add(assignedRulebooksPanel);
		assignedRulebooksTable = new JTable();
		JScrollPane rulebooksScrollPane = new JScrollPane();
		rulebooksScrollPane
				.setPreferredSize(new Dimension(assignedRulebooksPanel.getWidth(), assignedRulebooksPanel.getHeight()));
		rulebooksScrollPane.setBounds(414, 51, 390, 420);
		rulebooksScrollPane.setViewportView(assignedRulebooksTable);
		assignedRulebooksTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		assignedRulebooksTable.setVisible(true);
		assignedRulebooksPanel.add(rulebooksScrollPane);
		
		//Text field to filter assigned Rulebooks table
		JLabel assignedRulebookFilterLabel= new JLabel("Filter: ");
		groupPanel.add(assignedRulebookFilterLabel);
		assignedRulebookFilterLabel.setFont(new Font("Times New Roman",Font.ITALIC,15));
		assignedRulebookFilterLabel.setBounds(414, 30, 43, 20);
		assignedRulebookFilterField= new JTextField();
		groupPanel.add(assignedRulebookFilterField);
		assignedRulebookFilterField.setBounds(454, 30, 150, 20);
		JLabel lblAgentsOfThis = new JLabel("Agents of This Group");
		lblAgentsOfThis.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgentsOfThis.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAgentsOfThis.setBounds(10, 0, 388, 30);
		groupPanel.add(lblAgentsOfThis);
		JLabel lblRulebooksOfThis = new JLabel("Rulebooks of This Group");
		lblRulebooksOfThis.setHorizontalAlignment(SwingConstants.CENTER);
		lblRulebooksOfThis.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblRulebooksOfThis.setBounds(416, 0, 388, 30);
		groupPanel.add(lblRulebooksOfThis);

		 // Button to Remove Agent or agents for group
		JButton btnNewButton_1 = new JButton("Remove From Group");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedAgentsCount = 0;
				selectedAgentsCount = assignedAgentsTable.getSelectedRowCount();
				String agentToBeDeleted = "";
				ArrayList<String> selectedAgentNames = new ArrayList<String>();
				if (selectedAgentsCount > 1) {
					int[] selectedRows = assignedAgentsTable.getSelectedRows();
					for(int i=0;i<selectedRows.length;i++) {
						selectedRows[i]= assignedAgentsTable.convertRowIndexToModel(selectedRows[i]);
					}
					for (Integer temp : selectedRows) {
						selectedAgentNames.add((String) assignedAgentsTable.getModel().getValueAt(temp, 1));
					}
				} else if (selectedAgentsCount < 1) {
					int rowCount = assignedAgentsTable.getRowCount();
					String[] agentNames = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						agentNames[i] = (String) assignedAgentsTable.getModel().getValueAt(i, 1);
					}
					Object response = JOptionPane.showInputDialog(frame,
							"Please select the agent you want to remove from " + selectedGroup, "Select Rulebook",
							JOptionPane.QUESTION_MESSAGE, null, agentNames, "E");
					agentToBeDeleted = (String) response;

				} else if (selectedAgentsCount == 1) {
					int selectedAgentRow = assignedAgentsTable.convertRowIndexToModel(assignedAgentsTable.getSelectedRow());
					agentToBeDeleted = (String) assignedAgentsTable.getModel().getValueAt(selectedAgentRow, 1);
				}
				if (agentToBeDeleted != null && !agentToBeDeleted.isEmpty()) {
					int result = 0;
					JFrame frmLoginSystem = new JFrame("Exit");
					if (JOptionPane.showConfirmDialog(frmLoginSystem,
							"Do you want to remove " + agentToBeDeleted + " from " + selectedGroup + " group", " ",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						result = controller.deleteAgentFromGroup(agentToBeDeleted, selectedGroup);
					}
					if (result >= 200 && result < 220) {
						JOptionPane.showMessageDialog(frame,
								agentToBeDeleted + " has been successfully removed from " + selectedGroup,
								"Successfully removed", JOptionPane.INFORMATION_MESSAGE);
					} else if (result == 0) {
					} else {
						String resultCode = "Result Code: " + result;
						JOptionPane.showMessageDialog(null, "Failed to delete " + agentToBeDeleted, resultCode,
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
							"Do you want to remove " + agentsToBeAssigned + " from " + selectedGroup + " group", " ",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						result = controller.deleteAgentsFromGroup(selectedAgentNames, selectedGroup);
					}
					if (result >= 200 && result < 220) {
						JOptionPane.showMessageDialog(frame,
								"" + agentsToBeAssigned + " has been successfully removed from " + selectedGroup,
								"Successful assignment", JOptionPane.INFORMATION_MESSAGE);
					} else if (result == 0) {
					} else {
						String resultCode = "Result Code: " + result;
						JOptionPane.showMessageDialog(null, "Failed to assign " + agentToBeDeleted, resultCode,
								JOptionPane.ERROR_MESSAGE);
					}

				}
				refresh();
			}
		});
		btnNewButton_1.setBounds(110, 475, 200, 35);
		groupPanel.add(btnNewButton_1);

		//Button to remove rulebook from group
		JButton btnRemoveFromGroup = new JButton("Remove From Group");
		btnRemoveFromGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// assignedRulebooksTable
				int selectedRulebookRow = -1;
				selectedRulebookRow =assignedRulebooksTable.convertRowIndexToModel(assignedRulebooksTable.getSelectedRow());
				int rulebookColumn = 0;
				int result = 0;
				String name = "";
				// if there is not rulebook selected then a pop up window will appear with a
				// drop down list of all possible rulebooks
				if (selectedRulebookRow > -1) {
					name = (String) assignedRulebooksTable.getModel().getValueAt(selectedRulebookRow, rulebookColumn);
				} else {
					int rowCount = assignedRulebooksTable.getRowCount();
					String[] rulebookName = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						rulebookName[i] = (String) assignedRulebooksTable.getModel().getValueAt(i, 0);
					}
					Object response = JOptionPane.showInputDialog(frame,
							"Please select the rulebook you want to remove", "Select Rulebook",
							JOptionPane.QUESTION_MESSAGE, null, rulebookName, "E");
					name = (String) response;
				}
				if (name != null && !name.isEmpty()) {
					 // JOptionPanes are used to notify the user for its next actions
					if (JOptionPane.showConfirmDialog(frame,
							"Are you sure you want to remove: " + name + " from " + selectedGroup + " ?",
							"Delete Rulebook", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						result = controller.deleteRulebookFromGroup(selectedGroup);
					}
					if (result >= 200 && result < 220) {
						JOptionPane.showMessageDialog(frame, name + " has been successfully removed!", "Successful ",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (result == 0) {
					}
					else {
						String resultCode = "Result Code: " + result;
						JOptionPane.showMessageDialog(null, "The " + name + " has not been removed!", resultCode,
								JOptionPane.ERROR_MESSAGE);
					}
				}
				refresh();
			}
		});
		btnRemoveFromGroup.setBounds(515, 475, 200, 35);
		groupPanel.add(btnRemoveFromGroup);

		JPanel availableRulebooks = new JPanel();
		availableRulebooks.setBounds(1108, 95, 249, 512);
		frame.getContentPane().add(availableRulebooks);
		availableRulebooks.setLayout(null);

		 //Available Rulebook Panels
		innerAvailableRuleboooksPanel = new JPanel();
		innerAvailableRuleboooksPanel.setBounds(10, 51, 229, 420);
		availableRulebooks.add(innerAvailableRuleboooksPanel);
		availableRulebooksTable = new JTable();
		JScrollPane availableRulebookScrollPane = new JScrollPane(availableRulebooksTable);
		availableRulebookScrollPane.setPreferredSize(
				new Dimension(innerAvailableRuleboooksPanel.getWidth(), innerAvailableRuleboooksPanel.getHeight()));
		availableRulebookScrollPane.setViewportView(availableRulebooksTable);
		availableRulebooksTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		availableRulebooksTable.setVisible(true);
		availableRulebookScrollPane.setVisible(true);
		innerAvailableRuleboooksPanel.add(availableRulebookScrollPane);
		JLabel lblRulebooksAvailable = new JLabel("Rulebooks Available");
		lblRulebooksAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		lblRulebooksAvailable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblRulebooksAvailable.setBounds(10, 0, 229, 30);
		availableRulebooks.add(lblRulebooksAvailable);
		JLabel availableRulebookFilterLabel= new JLabel("Filter: ");
		availableRulebooks.add(availableRulebookFilterLabel);
		availableRulebookFilterLabel.setFont(new Font("Times New Roman",Font.ITALIC,15));
		availableRulebookFilterLabel.setBounds(10, 25, 43, 30);
		//Text field to filter assigned agent table
		availableRulebookFilterField= new JTextField();
		availableRulebooks.add(availableRulebookFilterField);
		availableRulebookFilterField.setBounds(50, 30, 150, 20);
		
		
		
		//Button to Assign Agent/Agents to group
		JButton btnAssignToGroup = new JButton("Assign To Group");
		btnAssignToGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rulebookName = "";
				int selectedRulebookRow = -1;
				selectedRulebookRow = availableRulebooksTable.convertRowIndexToModel(availableRulebooksTable.getSelectedRow());
				int rulebookColumn = 0;
				// if there is not rulebook selected then a pop up window will appear with a
				// drop down list of all possible rulebooks
				if (selectedRulebookRow > -1) {
					rulebookName = (String) availableRulebooksTable.getModel().getValueAt(selectedRulebookRow,
							rulebookColumn);
				} else {
					int rowCount = availableRulebooksTable.getRowCount();
					String[] rulebookNames = new String[rowCount];
					for (int i = 0; i < rowCount; i++) {
						rulebookNames[i] = (String) availableRulebooksTable.getModel().getValueAt(i, 0);
					}
					Object response = JOptionPane.showInputDialog(frame,
							"Please select the rulebook you want to assign to " + selectedGroup, "Select Rulebook",
							JOptionPane.QUESTION_MESSAGE, null, rulebookNames, "E");
					rulebookName = (String) response;
				}
				String groupName = selectedGroup;
				if (groupName != null && rulebookName != null) {
					int result = 0;
					JFrame frmLoginSystem = new JFrame("Exit");
					if (JOptionPane.showConfirmDialog(frmLoginSystem,
							"Confirm if you want to assign " + rulebookName + " to " + groupName + " group", " ",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						result = controller.assignRulebookToGroup(groupName, rulebookName);
					}
					if (result >= 200 && result < 220) {
						JOptionPane.showMessageDialog(frame, "" + rulebookName + " has been assigned to " + groupName,
								"Successful assignment", JOptionPane.INFORMATION_MESSAGE);
					} else if (result == 0) {

					} else {
						String resultCode = "Result Code: " + result;
						JOptionPane.showMessageDialog(null, "Failed to assign " + rulebookName + " to " + groupName,
								resultCode, JOptionPane.ERROR_MESSAGE);
					}

				}
				refresh();
			}

		});
		btnAssignToGroup.setBounds(30, 475, 200, 35);
		availableRulebooks.add(btnAssignToGroup);

		// Group Combo Box dynamically change The details on the page
		groupSelectionComboBox = new JComboBox();
		groupSelectionComboBox.setBounds(284, 47, 249, 41);
		ArrayList<Group> comboGroups = new ArrayList<Group>();
		try {
			comboGroups = controller.getAllGroups();
		} catch (NoAgentsException e1) {
		}
		String[] comboGroupName = new String[comboGroups.size()];
		for (int i = 0; i < comboGroups.size(); i++) {
			comboGroupName[i] = comboGroups.get(i).getName();
			groupSelectionComboBox.addItem(comboGroups.get(i).getName());
		}
		groupSelectionComboBox.setSelectedItem(selectedGroup);
		groupSelectionComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedGroup = (String) groupSelectionComboBox.getSelectedItem();
				refresh();
			}

		});
		frame.getContentPane().add(groupSelectionComboBox);
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(frame, "This tab will be closed", "Move Back to Main Window",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					frame.dispose();
				}
			}
		});
		btnNewButton_2.setBounds(10, 47, 123, 36);
		frame.getContentPane().add(btnNewButton_2);
		refresh();
	}

	/**
	 * Method Refresh, dynamically updates the status of the UI
	 * Is called whenever a user performs an action against the Application.
	 */
	public void refresh() {
		ArrayList<Rulebook> rulebooks = new ArrayList<Rulebook>();
		try {
			rulebooks = controller.getAllRulebooks();
		} catch (NoAgentsException e) {

		}
		ArrayList<Rulebook> assignedRulebooks = new ArrayList<Rulebook>();
		try {
			assignedRulebooks = controller.getAllRulebooksAssociatedWithAGroup(selectedGroup);
		} catch (NoAgentsException e) {

		}
		for (int i = 0; i < rulebooks.size(); i++) {
			Rulebook rulebook = rulebooks.get(i);
			for (Rulebook tempRulebook : assignedRulebooks) {
				if (rulebook.getName().equals(tempRulebook.getName())) {
					rulebooks.remove(i);
				}
			}

		}
		RulebookTableModel assignedRulebooksModel = new RulebookTableModel(assignedRulebooks);
		RulebookTableModel availabledRulebooksModel = new RulebookTableModel(rulebooks);
		assignedRulebooksTable.setModel(assignedRulebooksModel);
		availableRulebooksTable.setModel(availabledRulebooksModel);
		ArrayList<Agent> agents = new ArrayList<Agent>();
		try {
			agents = controller.getAllAgents();
		} catch (NoAgentsException e) {
		}
		ArrayList<Agent> assignedAgents = new ArrayList<Agent>();
		assignedAgents = controller.getAllAgentsAssociatedWithAGroup(selectedGroup);
		for (int i = 0; i < agents.size(); i++) {
			Agent agent = agents.get(i);
			for (Agent tempAgent : assignedAgents) {
				if (agent.getAgentId().equals(tempAgent.getAgentId())) {
					agents.remove(i);
					i--;
				}
			}

		}
		AgentTableModel assignedAgentTableModel = new AgentTableModel(assignedAgents);
		AgentTableModel availableAgentTableModel = new AgentTableModel(agents);

		assignedAgentsTable.setModel(assignedAgentTableModel);
		availableAgentTable.setModel(availableAgentTableModel);
		
    	 // Filter Handle for all tables available on this view
		//Code to filter available agents table
		availableAgentTable.setModel(availableAgentTableModel);
		availableAgentSorter = new TableRowSorter<AgentTableModel>(availableAgentTableModel);
		availableAgentTable.setRowSorter(availableAgentSorter);
		availableAgentFilterField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  filterAvailableAgents();
				  }
				  public void removeUpdate(DocumentEvent e) {
					  filterAvailableAgents();
				  }
				  public void insertUpdate(DocumentEvent e) {
					  filterAvailableAgents();
				  }

				  public void filterAvailableAgents() {
					  availableAgentSorter.setRowFilter(RowFilter.regexFilter("(?i)"+availableAgentFilterField.getText()));
					  availableAgentTable.setRowSorter(availableAgentSorter);
				  }
				});
		
				//Code to filter assigned agents table
				assignedAgentsTable.setModel(assignedAgentTableModel);
				assignedAgentSorter = new TableRowSorter<AgentTableModel>(assignedAgentTableModel);
				assignedAgentsTable.setRowSorter(assignedAgentSorter);
				assignedAgentFilterField.getDocument().addDocumentListener(new DocumentListener() {
					  public void changedUpdate(DocumentEvent e) {
						  filterAssignedAgents();
						  }
						  public void removeUpdate(DocumentEvent e) {
							  filterAssignedAgents();
						  }
						  public void insertUpdate(DocumentEvent e) {
							  filterAssignedAgents();
						  }

						  public void filterAssignedAgents() {
							  assignedAgentSorter.setRowFilter(RowFilter.regexFilter("(?i)"+assignedAgentFilterField.getText()));
							  assignedAgentsTable.setRowSorter(assignedAgentSorter);
						  }
						});

				//Code to filter assigned Rulebooks table
				assignedRulebooksTable.setModel(assignedRulebooksModel);
				assignedRulebookSorter = new TableRowSorter<RulebookTableModel>(assignedRulebooksModel);
				assignedRulebooksTable.setRowSorter(assignedRulebookSorter);
				assignedRulebookFilterField.getDocument().addDocumentListener(new DocumentListener() {
					  public void changedUpdate(DocumentEvent e) {
						  filterAssignedRulebooks();
						  }
						  public void removeUpdate(DocumentEvent e) {
							  filterAssignedRulebooks();
						  }
						  public void insertUpdate(DocumentEvent e) {
							  filterAssignedRulebooks();
						  }

						  public void filterAssignedRulebooks() {
							  assignedRulebookSorter.setRowFilter(RowFilter.regexFilter("(?i)"+assignedRulebookFilterField.getText()));
							  assignedRulebooksTable.setRowSorter(assignedRulebookSorter);
						  }
						});
				
	
				//Code to filter assigned Rulebooks table
				availableRulebooksTable.setModel(availabledRulebooksModel);
				availableRulebookSorter = new TableRowSorter<RulebookTableModel>(availabledRulebooksModel);
				availableRulebooksTable.setRowSorter(availableRulebookSorter);
				availableRulebookFilterField.getDocument().addDocumentListener(new DocumentListener() {
					  public void changedUpdate(DocumentEvent e) {
						  filterAvailableRulebooks();
						  }
						  public void removeUpdate(DocumentEvent e) {
							  filterAvailableRulebooks();
						  }
						  public void insertUpdate(DocumentEvent e) {
							  filterAvailableRulebooks();
						  }

						  public void filterAvailableRulebooks() {
							  availableRulebookSorter.setRowFilter(RowFilter.regexFilter("(?i)"+availableRulebookFilterField.getText()));
							  availableRulebooksTable.setRowSorter(availableRulebookSorter);
						  }
						});
	}
	}


