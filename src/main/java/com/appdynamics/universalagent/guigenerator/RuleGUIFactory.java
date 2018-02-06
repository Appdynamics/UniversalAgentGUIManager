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
package com.appdynamics.universalagent.guigenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import com.appdynamics.universalagent.events.SaveEventListener;
import com.appdynamics.universalagent.exceptions.AttributeException;
import com.appdynamics.universalagent.gui.HintTextFieldUI;
import com.appdynamics.universalagent.rules.Rule;
import com.appdynamics.universalagent.universalagent.Rulebook;

/**
 * Class RuleGUIFactory is responsible to generate a rule panel based on the
 * attribute key values given from the GUI There are two option one which will
 * generate a new empty panel and another that will generate a panel with the
 * given attributes
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class RuleGUIFactory {
	public JPanel rulePanel(String monitor, final JTabbedPane tabbedPane, ArrayList<String> attributeNames,
			HashMap<String, String> attributeNamesMap) {
		final RulePanel rulePanel = new RulePanel(attributeNames, attributeNamesMap);
		rulePanel.setLayout((LayoutManager) new BoxLayout(rulePanel, BoxLayout.Y_AXIS));
		/**
		 * General Settings of A Rulebook , static variables
		 */
		final JPanel ruleSettings = new JPanel(new GridLayout(6, 2));
		JLabel rule = new JLabel(monitor + " Rule");
		JButton removeRule = new JButton("Remove Rule");
		removeRule.setBackground(Color.RED);
		removeRule.setOpaque(true);
		JLabel ruleName = new JLabel("Name: ");
		final JTextField ruleNameInput = new JTextField(20);
		JLabel ruleComments = new JLabel("Comments: ");
		final JTextField ruleCommentsInput = new JTextField(20);
		JLabel ruleMonitor = new JLabel("Monitor: ");
		String[] monitors = { "java", "machine", "analytics", "universal", "dotnet", "network" };
		final JComboBox ruleMonitorInput = new JComboBox(monitors);
		ruleMonitorInput.setSelectedItem(monitor);
		ruleMonitorInput.setEditable(false);
		ruleMonitorInput.setEnabled(false);
		String[] conditions = { "true", "false", "platform_family == 'windows'" };
		JLabel ruleCondition = new JLabel("Condition: ");
		final JComboBox ruleConditionInput = new JComboBox(conditions);
		removeRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(rulePanel, "Remove Rule",
						"Delere rule " + ruleNameInput.getText() + " ?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					tabbedPane.remove(rulePanel);
					tabbedPane.getParent().validate();
					tabbedPane.getParent().repaint();
				}
			}
		});
		ruleSettings.add(rule);
		ruleSettings.add(removeRule);
		ruleSettings.add(ruleName);
		ruleSettings.add(ruleNameInput);
		ruleSettings.add(ruleComments);
		ruleSettings.add(ruleCommentsInput);
		ruleSettings.add(ruleMonitor);
		ruleSettings.add(ruleMonitorInput);
		ruleSettings.add(ruleCondition);
		ruleSettings.add(ruleConditionInput);
		rulePanel.add(ruleSettings);
		final JPanel rulebookConfigPanel = new JPanel();
		rulebookConfigPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rulebookConfigPanel.setLayout((LayoutManager) new BoxLayout(rulebookConfigPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollableConfig = new JScrollPane(rulebookConfigPanel);
		rulePanel.add(scrollableConfig);

		ConfigurationAttributes attributes = new ConfigurationAttributes();
		attributes.initializeJavaAttributes();
		final JComboBox<String> attributeList = new JComboBox(rulePanel.getAttributeNames().toArray());
		final JButton attributeAddButton = new JButton(new AbstractAction("Click to add") {

			public void actionPerformed(ActionEvent e) {
				if (attributeList.getItemCount() > 0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							JPanel panel = new JPanel(new GridLayout(1, 3));
							panel.add(new JLabel(attributeList.getSelectedItem().toString()));
							JTextField attributeInputField = new JTextField(20);
							attributeInputField.setUI(new HintTextFieldUI(
									rulePanel.getAttributeNamesMap().get(attributeList.getSelectedItem().toString()),
									true));
							panel.add(attributeInputField);
							int index = attributeList.getSelectedIndex();
							final JButton button = new JButton("-");
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									JPanel parentPanel = (JPanel) button.getParent();
									parentPanel.getComponentCount();
									for (int i = 0; i < parentPanel.getComponentCount(); i++) {
										if (parentPanel.getComponent(i).getClass() == JLabel.class) {
											JLabel label = (JLabel) parentPanel.getComponent(i);
											attributeList.addItem(label.getText());
										}
									}
									rulebookConfigPanel.remove(parentPanel);
									rulePanel.getParent().validate();
									rulePanel.getParent().repaint();
								}
							});
							panel.add(button);
							rulebookConfigPanel.add(panel);
							attributeList.removeItemAt(index);
							rulePanel.getParent().validate();
							rulePanel.getParent().repaint();
						}
					});
				}
			}
		});
		ruleSettings.add(attributeAddButton);
		ruleSettings.add(attributeList);
		JButton save = new JButton("save");
		rulePanel.addListener(new SaveEventListener() {
			public void save() {
				HashMap<String, String> inputAttributes = new HashMap<String, String>();
				String ruleNameFinal = ruleNameInput.getText();
				String ruleCommentsFinal = ruleCommentsInput.getText();
				String ruleMonitorFinal = (String) ruleMonitorInput.getSelectedItem();
				String ruleConditionFinal = (String) ruleConditionInput.getSelectedItem();
				inputAttributes.put("name", ruleNameFinal);
				inputAttributes.put("comments", ruleCommentsFinal);
				inputAttributes.put("monitor", ruleMonitorFinal);
				inputAttributes.put("condition", ruleConditionFinal);
				for (int i = 0; i < rulebookConfigPanel.getComponentCount(); i++) {
					JPanel configPanel = (JPanel) rulebookConfigPanel.getComponent(i);
					inputAttributes.put(((JLabel) configPanel.getComponent(0)).getText(),
							((JTextField) configPanel.getComponent(1)).getText());
				}
				rulePanel.setRuleMap(inputAttributes);
				rulePanel.setValidator(true);
				if (ruleNameFinal == "" || ruleNameFinal == null || ruleNameFinal.isEmpty()) {
					JOptionPane.showMessageDialog(rulePanel.getParent(), "Rule Name Cannot Be Null", "Attribute Error",
							JOptionPane.ERROR_MESSAGE);
					rulePanel.setValidator(false);
				} else if (inputAttributes.get("state") == null) {
					rulePanel.setValidator(false);
					JOptionPane.showMessageDialog(rulePanel.getParent(), "Rule Attribute State is required",
							"Attribute Error", JOptionPane.ERROR_MESSAGE);
				} else if (inputAttributes.get("state") == "" || inputAttributes.get("state").isEmpty()) {
					rulePanel.setValidator(false);
					JOptionPane.showMessageDialog(rulePanel.getParent(), "Rule Attribute State cannot be empty",
							"Attribute Error", JOptionPane.ERROR_MESSAGE);
				} else if (inputAttributes.get("version") == null) {
					JOptionPane.showMessageDialog(rulePanel.getParent(), "Agent Attribute Version is required",
							"Attribute Error", JOptionPane.ERROR_MESSAGE);
				} else {
					rulePanel.setValidator(true);
				}
			}
		});
		return rulePanel;
	}

	public JPanel initializePanelWithAttributes(String monitor, final JTabbedPane tabbedPane,
			ArrayList<String> attributeNames, HashMap<String, String> attributeNamesMap, Rule inputRule) {
		final RulePanel rulePanel = new RulePanel(attributeNames, attributeNamesMap);
		rulePanel.setLayout((LayoutManager) new BoxLayout(rulePanel, BoxLayout.Y_AXIS));
		/**
		 * General Settings of A Rulebook , static variables
		 */
		final JPanel ruleSettings = new JPanel(new GridLayout(6, 2));
		JLabel rule = new JLabel(monitor + " Rule");
		JButton removeRule = new JButton("Remove Rule");
		removeRule.setBackground(Color.RED);
		removeRule.setOpaque(true);
		JLabel ruleName = new JLabel("Name: ");
		final JTextField ruleNameInput = new JTextField(20);
		ruleNameInput.setText(inputRule.getName());
		JLabel ruleComments = new JLabel("Comments: ");
		final JTextField ruleCommentsInput = new JTextField(20);
		ruleCommentsInput.setText(inputRule.getComments());
		JLabel ruleMonitor = new JLabel("Monitor: ");
		String[] monitors = { "java", "machine", "analytics", "universal", "dotnet", "network" };
		final JComboBox ruleMonitorInput = new JComboBox(monitors);
		ruleMonitorInput.setSelectedItem(inputRule.getMonitor());
		ruleMonitorInput.setSelectedItem(monitor);
		ruleMonitorInput.setEditable(false);
		ruleMonitorInput.setEnabled(false);
		String[] conditions = { "true", "false", "platform_family == 'windows'" };
		JLabel ruleCondition = new JLabel("Condition: ");
		final JComboBox ruleConditionInput = new JComboBox(conditions);
		ruleConditionInput.setSelectedItem(inputRule.getCondition());
		removeRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(rulePanel, "Remove Rule",
						"Delere rule " + ruleNameInput.getText() + " ?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					tabbedPane.remove(rulePanel);
					tabbedPane.getParent().validate();
					tabbedPane.getParent().repaint();
				}
			}
		});
		ruleSettings.add(rule);
		ruleSettings.add(removeRule);
		ruleSettings.add(ruleName);
		ruleSettings.add(ruleNameInput);
		ruleSettings.add(ruleComments);
		ruleSettings.add(ruleCommentsInput);
		ruleSettings.add(ruleMonitor);
		ruleSettings.add(ruleMonitorInput);
		ruleSettings.add(ruleCondition);
		ruleSettings.add(ruleConditionInput);
		rulePanel.add(ruleSettings);
		final JPanel rulebookConfigPanel = new JPanel();
		rulebookConfigPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rulebookConfigPanel.setLayout((LayoutManager) new BoxLayout(rulebookConfigPanel, BoxLayout.Y_AXIS));

		JScrollPane scrollableConfig = new JScrollPane(rulebookConfigPanel);
		rulePanel.add(scrollableConfig);
		ConfigurationAttributes attributes = new ConfigurationAttributes();
		attributes.initializeJavaAttributes();
		final JComboBox<String> attributeList = new JComboBox(rulePanel.getAttributeNames().toArray());

		HashMap<String, String> existingRuleAttributes = inputRule.getInstanciatedAttributes();
		/*
		 * Iterate over a given map (key-value) and generate the required swing
		 * components for a complete RuleBook panel
		 */
		for (String key : existingRuleAttributes.keySet()) {
			JPanel panel = new JPanel(new GridLayout(1, 3));
			panel.add(new JLabel(key));
			JTextField attributeInputField = new JTextField(20);
			attributeInputField.setText(existingRuleAttributes.get(key));
			panel.add(attributeInputField);
			final JButton button = new JButton("-");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JPanel parentPanel = (JPanel) button.getParent();
					parentPanel.getComponentCount();
					for (int i = 0; i < parentPanel.getComponentCount(); i++) {
						if (parentPanel.getComponent(i).getClass() == JLabel.class) {
							JLabel label = (JLabel) parentPanel.getComponent(i);
							attributeList.addItem(label.getText());
						}
					}
					rulebookConfigPanel.remove(parentPanel);
				}
			});
			panel.add(button);
			rulebookConfigPanel.add(panel);
			attributeList.removeItem(key);
		}

		final JButton attributeAddButton = new JButton(new AbstractAction("Click to add") {
			public void actionPerformed(ActionEvent e) {
				if (attributeList.getItemCount() > 0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							JPanel panel = new JPanel(new GridLayout(1, 3));
							panel.add(new JLabel(attributeList.getSelectedItem().toString()));
							JTextField attributeInputField = new JTextField(20);
							attributeInputField.setUI(new HintTextFieldUI(
									rulePanel.getAttributeNamesMap().get(attributeList.getSelectedItem().toString()),
									true));
							panel.add(attributeInputField);
							int index = attributeList.getSelectedIndex();

							final JButton button = new JButton("-");
							button.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									JPanel parentPanel = (JPanel) button.getParent();
									parentPanel.getComponentCount();
									for (int i = 0; i < parentPanel.getComponentCount(); i++) {

										if (parentPanel.getComponent(i).getClass() == JLabel.class) {
											JLabel label = (JLabel) parentPanel.getComponent(i);
											attributeList.addItem(label.getText());
										}
									}

									rulebookConfigPanel.remove(parentPanel);
									rulePanel.getParent().validate();
									rulePanel.getParent().repaint();

								}

							});

							panel.add(button);
							rulebookConfigPanel.add(panel);
							attributeList.removeItemAt(index);
							rulePanel.getParent().validate();
							rulePanel.getParent().repaint();
						}
					});
				}
			}
		});

		ruleSettings.add(attributeAddButton);
		ruleSettings.add(attributeList);

		JButton save = new JButton("save");

		rulePanel.addListener(new SaveEventListener() {

			public void save() {
				HashMap<String, String> inputAttributes = new HashMap<String, String>();
				String ruleNameFinal = ruleNameInput.getText();
				String ruleCommentsFinal = ruleCommentsInput.getText();
				String ruleMonitorFinal = (String) ruleMonitorInput.getSelectedItem();
				String ruleConditionFinal = (String) ruleConditionInput.getSelectedItem();
				inputAttributes.put("name", ruleNameFinal);
				inputAttributes.put("comments", ruleCommentsFinal);
				inputAttributes.put("monitor", ruleMonitorFinal);
				inputAttributes.put("condition", ruleConditionFinal);
				for (int i = 0; i < rulebookConfigPanel.getComponentCount(); i++) {
					JPanel configPanel = (JPanel) rulebookConfigPanel.getComponent(i);
					inputAttributes.put(((JLabel) configPanel.getComponent(0)).getText(),
							((JTextField) configPanel.getComponent(1)).getText());
				}
				rulePanel.setRuleMap(inputAttributes);
				rulePanel.setValidator(true);
				if (ruleNameFinal == "" || ruleNameFinal == null || ruleNameFinal.isEmpty()) {
					JOptionPane.showMessageDialog(rulePanel.getParent(), "Rule Name Cannot Be Null", "Attribute Error",
							JOptionPane.ERROR_MESSAGE);
					rulePanel.setValidator(false);
				} else if (inputAttributes.get("state") == null) {
					rulePanel.setValidator(false);
					JOptionPane.showMessageDialog(rulePanel.getParent(), "Rule Attribute State is required",
							"Attribute Error", JOptionPane.ERROR_MESSAGE);
				} else if (inputAttributes.get("state") == "" || inputAttributes.get("state").isEmpty()) {
					rulePanel.setValidator(false);
					JOptionPane.showMessageDialog(rulePanel.getParent(), "Rule Attribute State cannot be empty",
							"Attribute Error", JOptionPane.ERROR_MESSAGE);
				} else if (inputAttributes.get("version") == null) {
					JOptionPane.showMessageDialog(rulePanel.getParent(), "Agent Attribute Version is required",
							"Attribute Error", JOptionPane.ERROR_MESSAGE);
				} else {
					rulePanel.setValidator(true);
				}

			}

		});

		return rulePanel;

	}

}
