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

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import com.appdynamics.universalagent.events.SaveEventListener;
import com.appdynamics.universalagent.gui.HintTextFieldUI;
import com.appdynamics.universalagent.universalagent.Rulebook;

/**
 * Class RulebookGUIFactory is responsible to generate a rulebook panel based on
 * the attribute key values given from the GUI There are two option one which
 * will generate a new empty panel and another that will generate a panel with
 * the given attributes
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class RulebookGUIFactory {
	public JPanel rulebookPanel(final JPanel parentPanel, ArrayList<String> attributeNames,
			HashMap<String, String> attributeNamesMap) {
		// final RulebookPanel rulebookPanel =new RulebookPanel(new GridLayout(4,1),
		// attributeNames, attributeNamesMap);

		final RulebookPanel rulebookPanel = new RulebookPanel(attributeNames, attributeNamesMap);
		rulebookPanel.setLayout((LayoutManager) new BoxLayout(rulebookPanel, BoxLayout.Y_AXIS));
		/**
		 * General Settings of A Rulebook , static variables
		 */
		final JPanel ruleSettings = new JPanel(new GridLayout(6, 2));
		final JPanel rulebookSettings = new JPanel();
		JLabel rulebookName = new JLabel("Name: ");
		final JTextField rulebookNameInput = new JTextField(10);
		JLabel rulebookComments = new JLabel("Comments: ");
		final JTextField rulebookCommentsInput = new JTextField(10);
		JLabel rulebookVersion = new JLabel("Version: ");
		final JTextField rulebookVersionInput = new JTextField(10);
		ruleSettings.add(rulebookName);
		ruleSettings.add(rulebookNameInput);
		ruleSettings.add(rulebookComments);
		ruleSettings.add(rulebookCommentsInput);
		ruleSettings.add(rulebookVersion);
		ruleSettings.add(rulebookVersionInput);
		rulebookPanel.add(ruleSettings);
		final JPanel rulebookConfigPanel = new JPanel();
		rulebookConfigPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rulebookConfigPanel.setLayout((LayoutManager) new BoxLayout(rulebookConfigPanel, BoxLayout.Y_AXIS));
		rulebookPanel.add(rulebookConfigPanel);

		final JComboBox<String> attributeList = new JComboBox(attributeNames.toArray());
		final ArrayList<JPanel> configuredAttributes = new ArrayList<JPanel>();
		final JButton attributeAddButton = new JButton(new AbstractAction("Click to add") {
			public void actionPerformed(ActionEvent e) {
				if (attributeList.getItemCount() > 0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							// JPanel panel = new JPanel(new GridLayout(1,3));
							JPanel panel = new JPanel();
							panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.X_AXIS));
							panel.add(new JLabel(attributeList.getSelectedItem().toString()));
							JTextField attributeInputField = new JTextField(10);

							attributeInputField.setUI(new HintTextFieldUI(
									attributeNamesMap.get(attributeList.getSelectedItem().toString()), true));
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
									rulebookPanel.getParent().validate();
									rulebookPanel.getParent().repaint();
								}
							});
							panel.add(button);
							rulebookConfigPanel.add(panel);
							attributeList.removeItemAt(index);
							rulebookPanel.getParent().validate();
							rulebookPanel.getParent().repaint();
						}
					});
				}
			}
		});
		ruleSettings.add(attributeAddButton);
		ruleSettings.add(attributeList);

		rulebookPanel.addListener(new SaveEventListener() {
			public void save() {
				String ruleNameFinal = rulebookNameInput.getText();
				String ruleCommentsFinal = rulebookCommentsInput.getText();
				String rulebookVersion = rulebookVersionInput.getText();
				HashMap<String, String> inputAttributes = new HashMap<String, String>();
				inputAttributes.put("name", ruleNameFinal);
				inputAttributes.put("comments", ruleCommentsFinal);
				inputAttributes.put("rulebook_version", rulebookVersion);
				if (ruleNameFinal == "" || ruleNameFinal == null || ruleNameFinal.isEmpty()) {
					JOptionPane.showMessageDialog(rulebookPanel, "Rulebook Name Cannot Be Null", "Attribute Error",
							JOptionPane.ERROR_MESSAGE);
				}
				for (int i = 0; i < rulebookConfigPanel.getComponentCount(); i++) {
					JPanel configPanel = (JPanel) rulebookConfigPanel.getComponent(i);
					inputAttributes.put(((JLabel) configPanel.getComponent(0)).getText(),
							((JTextField) configPanel.getComponent(1)).getText());
				}
				rulebookPanel.setRulebookMap(inputAttributes);
			}
		});
		return rulebookPanel;
	}

	public JPanel rulebookPanelFromRulebook(final JPanel parentPanel, ArrayList<String> attributeNames,
			HashMap<String, String> attributeNamesMap, Rulebook inputRulebook) {
		// final RulebookPanel rulebookPanel =new RulebookPanel(new GridLayout(4,1),
		// attributeNames, attributeNamesMap);

		final RulebookPanel rulebookPanel = new RulebookPanel(attributeNames, attributeNamesMap);
		rulebookPanel.setLayout((LayoutManager) new BoxLayout(rulebookPanel, BoxLayout.Y_AXIS));
		/**
		 * /** General Settings of A Rulebook , static variables
		 */
		final JPanel ruleSettings = new JPanel(new GridLayout(6, 2));
		JLabel rulebookName = new JLabel("Name: ");
		final JTextField rulebookNameInput = new JTextField(5);
		rulebookNameInput.setText(inputRulebook.getName());
		JLabel rulebookComments = new JLabel("Comments: ");
		final JTextField rulebookCommentsInput = new JTextField(5);
		rulebookCommentsInput.setText(inputRulebook.getComments());
		JLabel rulebookVersion = new JLabel("Version: ");
		final JTextField rulebookVersionInput = new JTextField(5);
		rulebookVersionInput.setText(inputRulebook.getVersion());
		ruleSettings.add(rulebookName);
		ruleSettings.add(rulebookNameInput);
		ruleSettings.add(rulebookComments);
		ruleSettings.add(rulebookCommentsInput);
		ruleSettings.add(rulebookVersion);
		ruleSettings.add(rulebookVersionInput);
		rulebookPanel.add(ruleSettings);
		final JPanel rulebookConfigPanel = new JPanel();
		rulebookConfigPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		rulebookConfigPanel.setLayout((LayoutManager) new BoxLayout(rulebookConfigPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollableConfig = new JScrollPane(rulebookConfigPanel);
		rulebookPanel.add(scrollableConfig);

		final JComboBox<String> attributeList = new JComboBox(attributeNames.toArray());

		HashMap<String, String> existingRulebookConfig = inputRulebook.getInstanciatedAttributes();
		for (String key : existingRulebookConfig.keySet()) {
			// JPanel panel = new JPanel(new GridLayout(1,3));

			JPanel panel = new JPanel();
			panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.X_AXIS));
			panel.add(new JLabel(key));
			JTextField attributeInputField = new JTextField(10);
			attributeInputField.setText(existingRulebookConfig.get(key));
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
					rulebookPanel.getParent().validate();
					rulebookPanel.getParent().repaint();

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
							// JPanel panel = new JPanel(new GridLayout(1,3));
							JPanel panel = new JPanel();
							panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.X_AXIS));
							panel.add(new JLabel(attributeList.getSelectedItem().toString()));
							JTextField attributeInputField = new JTextField(10);
							attributeInputField.setUI(new HintTextFieldUI(
									attributeNamesMap.get(attributeList.getSelectedItem().toString()), true));
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
									rulebookPanel.getParent().validate();
									rulebookPanel.getParent().repaint();
								}
							});
							panel.add(button);
							rulebookConfigPanel.add(panel);
							attributeList.removeItemAt(index);
							rulebookPanel.getParent().validate();
							rulebookPanel.getParent().repaint();
						}
					});
				}
			}
		});
		ruleSettings.add(attributeAddButton);
		ruleSettings.add(attributeList);
		rulebookPanel.addListener(new SaveEventListener() {
			public void save() {
				String ruleNameFinal = rulebookNameInput.getText();
				String ruleCommentsFinal = rulebookCommentsInput.getText();
				String rulebookVersion = rulebookVersionInput.getText();
				HashMap<String, String> inputAttributes = new HashMap<String, String>();
				inputAttributes.put("name", ruleNameFinal);
				inputAttributes.put("comments", ruleCommentsFinal);
				inputAttributes.put("rulebook_version", rulebookVersion);
				if (ruleNameFinal == "" || ruleNameFinal == null || ruleNameFinal.isEmpty()) {
					JOptionPane.showMessageDialog(rulebookPanel.getParent(), "Rulebook Name Cannot Be Null",
							"Attribute Error", JOptionPane.ERROR_MESSAGE);
				}
				for (int i = 0; i < rulebookConfigPanel.getComponentCount(); i++) {
					JPanel configPanel = (JPanel) rulebookConfigPanel.getComponent(i);
					inputAttributes.put(((JLabel) configPanel.getComponent(0)).getText(),
							((JTextField) configPanel.getComponent(1)).getText());
				}
				rulebookPanel.setRulebookMap(inputAttributes);
			}
		});
		return rulebookPanel;
	}
}
