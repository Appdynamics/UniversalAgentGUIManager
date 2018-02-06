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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import com.appdynamics.universalaagent.rule.factories.AnalyticsRuleFactory;
import com.appdynamics.universalaagent.rule.factories.DotNetRuleFactory;
import com.appdynamics.universalaagent.rule.factories.JavaRuleFactory;
import com.appdynamics.universalaagent.rule.factories.MachineRuleFactory;
import com.appdynamics.universalaagent.rule.factories.NetworkRuleFactory;
import com.appdynamics.universalaagent.rule.factories.RulebookFactory;
import com.appdynamics.universalaagent.rule.factories.UniversalAgentRuleFactory;
import com.appdynamics.universalagent.configuration.AnalyticsAgentConfigurationAttributes;
import com.appdynamics.universalagent.configuration.DotNetAgentConfigurationAttributes;
import com.appdynamics.universalagent.configuration.JavaConfigurationAttributes;
import com.appdynamics.universalagent.configuration.MachineAgentConfigurationAttributes;
import com.appdynamics.universalagent.configuration.NetworkAgentConfigurationAttributes;
import com.appdynamics.universalagent.configuration.RulebookConfigurationAttributes;
import com.appdynamics.universalagent.configuration.UniversalAgentConfigurationAttributes;
import com.appdynamics.universalagent.guigenerator.RuleGUIFactory;
import com.appdynamics.universalagent.guigenerator.RulePanel;
import com.appdynamics.universalagent.guigenerator.RulebookGUIFactory;
import com.appdynamics.universalagent.guigenerator.RulebookPanel;
import com.appdynamics.universalagent.rules.JavaRule;
import com.appdynamics.universalagent.rules.Rule;
import com.appdynamics.universalagent.universalagent.RestController;
import com.appdynamics.universalagent.universalagent.Rulebook;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class UpdateRulebookNew {

	private RestController controller;
	private Rulebook rulebook;
	private JPanel rulePanel = new JPanel();

	public UpdateRulebookNew(RestController controller, Rulebook rulebook) {
		this.controller = controller;
		this.rulebook = rulebook;
		initialize(rulebook);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Rulebook rulebook) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 1400, 700);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		JPanel mainRulebookPanel = new JPanel(new BorderLayout());
		frame.getContentPane().add(mainRulebookPanel);
		JPanel Configuration = new JPanel();
		Configuration.setMaximumSize(new Dimension(400, 0));
		Configuration.setSize(400, 700);
		RulebookConfigurationAttributes rulebookConfigurationAttributes = new RulebookConfigurationAttributes();
		JPanel rulebookPanel = new RulebookGUIFactory().rulebookPanelFromRulebook(Configuration,
				rulebookConfigurationAttributes.getAttributeNames(), rulebookConfigurationAttributes.getAttributesMap(),
				rulebook);
		Configuration.add(rulebookPanel);
		JScrollPane scrollableRulebook = new JScrollPane(Configuration);
		mainRulebookPanel.add(scrollableRulebook, BorderLayout.WEST);

		JLabel headerLabel = new JLabel("Rulebook Configuration");
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		mainRulebookPanel.add(headerLabel, BorderLayout.NORTH);
		JPanel AgentsPanel = new JPanel();
		mainRulebookPanel.add(AgentsPanel, BorderLayout.CENTER);
		AgentsPanel.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(rulePanel);
		AgentsPanel.add(scrollPane, BorderLayout.CENTER);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		rulePanel.add(tabbedPane);
		JButton saveButton = new JButton("save");
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
		mainRulebookPanel.add(buttonsPanel, BorderLayout.PAGE_END);
		JPanel agentButtons = new JPanel(new FlowLayout());
		JButton addAgent = new JButton("Add New Agent:");
		String[] agentNames = { "java", "machine", "analytics", "universal", "dotnet", "network" };
		JComboBox agents = new JComboBox(agentNames);

		/**
		 * RULE INPUT
		 */

		ArrayList<Rule> rules = (ArrayList<Rule>) rulebook.getRules();

		for (Rule rule : rules) {
			if (rule.getMonitor().equals("java")) {
				JavaConfigurationAttributes javaConfig = new JavaConfigurationAttributes();
				ArrayList<String> attributeNames = javaConfig.getAttributeNames();
				HashMap<String, String> attributeNamesMap = javaConfig.getAttributesMap();
				tabbedPane.add(new RuleGUIFactory().initializePanelWithAttributes(rule.getMonitor(), tabbedPane,
						attributeNames, attributeNamesMap, rule), rule.getMonitor());
				frame.validate();
				frame.repaint();
			} else if (rule.getMonitor().equals("machine")) {
				MachineAgentConfigurationAttributes machineConfig = new MachineAgentConfigurationAttributes();
				ArrayList<String> attributeNames = machineConfig.getAttributeNames();
				HashMap<String, String> attributeNamesMap = machineConfig.getAttributesMap();
				tabbedPane.add(new RuleGUIFactory().initializePanelWithAttributes(rule.getMonitor(), tabbedPane,
						attributeNames, attributeNamesMap, rule), rule.getMonitor());
				frame.validate();
				frame.repaint();
			} else if (rule.getMonitor().equals("analytics")) {
				AnalyticsAgentConfigurationAttributes analyticsConfig = new AnalyticsAgentConfigurationAttributes();
				ArrayList<String> attributeNames = analyticsConfig.getAttributeNames();
				HashMap<String, String> attributeNamesMap = analyticsConfig.getAttributesMap();
				tabbedPane.add(new RuleGUIFactory().initializePanelWithAttributes(rule.getMonitor(), tabbedPane,
						attributeNames, attributeNamesMap, rule), rule.getMonitor());
				frame.validate();
				frame.repaint();
			} else if (rule.getMonitor().equals("universal")) {
				UniversalAgentConfigurationAttributes universalConfig = new UniversalAgentConfigurationAttributes();
				ArrayList<String> attributeNames = universalConfig.getAttributeNames();
				HashMap<String, String> attributeNamesMap = universalConfig.getAttributesMap();
				tabbedPane.add(new RuleGUIFactory().initializePanelWithAttributes(rule.getMonitor(), tabbedPane,
						attributeNames, attributeNamesMap, rule), rule.getMonitor());
				frame.validate();
				frame.repaint();
			} else if (rule.getMonitor().equals("dotnet")) {
				DotNetAgentConfigurationAttributes dotNetConfig = new DotNetAgentConfigurationAttributes();
				ArrayList<String> attributeNames = dotNetConfig.getAttributeNames();
				HashMap<String, String> attributeNamesMap = dotNetConfig.getAttributesMap();
				tabbedPane.add(new RuleGUIFactory().initializePanelWithAttributes(rule.getMonitor(), tabbedPane,
						attributeNames, attributeNamesMap, rule), rule.getMonitor());
				frame.validate();
				frame.repaint();
			} else if (rule.getMonitor().equals("network")) {
				NetworkAgentConfigurationAttributes networkConfig = new NetworkAgentConfigurationAttributes();
				ArrayList<String> attributeNames = networkConfig.getAttributeNames();
				HashMap<String, String> attributeNamesMap = networkConfig.getAttributesMap();
				tabbedPane.add(new RuleGUIFactory().initializePanelWithAttributes(rule.getMonitor(), tabbedPane,
						attributeNames, attributeNamesMap, rule), rule.getMonitor());
				frame.validate();
				frame.repaint();
			}
		}

		addAgent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String monitor = (String) agents.getSelectedItem();

				if (monitor.equals("java")) {
					JavaConfigurationAttributes javaConfig = new JavaConfigurationAttributes();
					ArrayList<String> attributeNames = javaConfig.getAttributeNames();
					HashMap<String, String> attributeNamesMap = javaConfig.getAttributesMap();
					tabbedPane.add(
							new RuleGUIFactory().rulePanel(monitor, tabbedPane, attributeNames, attributeNamesMap),
							monitor);
					frame.validate();
					frame.repaint();

				} else if (monitor.equals("machine")) {
					MachineAgentConfigurationAttributes machineConfig = new MachineAgentConfigurationAttributes();
					ArrayList<String> attributeNames = machineConfig.getAttributeNames();
					HashMap<String, String> attributeNamesMap = machineConfig.getAttributesMap();
					tabbedPane.add(
							new RuleGUIFactory().rulePanel(monitor, tabbedPane, attributeNames, attributeNamesMap),
							monitor);
					frame.validate();
					frame.repaint();
				} else if (monitor.equals("analytics")) {
					AnalyticsAgentConfigurationAttributes analyticsConfig = new AnalyticsAgentConfigurationAttributes();
					ArrayList<String> attributeNames = analyticsConfig.getAttributeNames();
					HashMap<String, String> attributeNamesMap = analyticsConfig.getAttributesMap();
					tabbedPane.add(
							new RuleGUIFactory().rulePanel(monitor, tabbedPane, attributeNames, attributeNamesMap),
							monitor);
					frame.validate();
					frame.repaint();

				} else if (monitor.equals("universal")) {
					UniversalAgentConfigurationAttributes universalConfig = new UniversalAgentConfigurationAttributes();
					ArrayList<String> attributeNames = universalConfig.getAttributeNames();
					HashMap<String, String> attributeNamesMap = universalConfig.getAttributesMap();

					tabbedPane.add(
							new RuleGUIFactory().rulePanel(monitor, tabbedPane, attributeNames, attributeNamesMap),
							monitor);
					frame.validate();
					frame.repaint();
				} else if (monitor.equals("dotnet")) {
					DotNetAgentConfigurationAttributes dotNetConfig = new DotNetAgentConfigurationAttributes();
					ArrayList<String> attributeNames = dotNetConfig.getAttributeNames();
					HashMap<String, String> attributeNamesMap = dotNetConfig.getAttributesMap();
					tabbedPane.add(
							new RuleGUIFactory().rulePanel(monitor, tabbedPane, attributeNames, attributeNamesMap),
							monitor);
					frame.validate();
					frame.repaint();
				} else if (monitor.equals("network")) {
					NetworkAgentConfigurationAttributes networkConfig = new NetworkAgentConfigurationAttributes();
					ArrayList<String> attributeNames = networkConfig.getAttributeNames();
					HashMap<String, String> attributeNamesMap = networkConfig.getAttributesMap();
					tabbedPane.add(
							new RuleGUIFactory().rulePanel(monitor, tabbedPane, attributeNames, attributeNamesMap),
							monitor);
					frame.validate();
					frame.repaint();
				}

			}

		});

		agentButtons.add(agents);
		agentButtons.add(addAgent);
		agentButtons.setBorder(BorderFactory.createLineBorder(Color.black));
		buttonsPanel.add(agentButtons);

		JPanel overallButtons = new JPanel(new FlowLayout());
		overallButtons.setBorder(BorderFactory.createLineBorder(Color.black));
		buttonsPanel.add(overallButtons);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Rule> rules = new ArrayList<Rule>();
				boolean procceed = true;
				for (int i = 0; i < tabbedPane.getComponentCount(); i++) {
					if (tabbedPane.getComponent(i).getClass() == RulePanel.class) {
						((RulePanel) tabbedPane.getComponent(i)).fireEvent();
						HashMap<String, String> attributes = ((RulePanel) tabbedPane.getComponent(i)).getRuleMap();
						if (((RulePanel) tabbedPane.getComponent(i)).isValidator()) {

						} else {
							procceed = false;
						}
						if (attributes.get("monitor") != null && attributes.get("monitor").equals("java")) {
							JavaRuleFactory javaRuleFactory = new JavaRuleFactory();
							rules.add(javaRuleFactory.createJavaRule(attributes));
						} else if (attributes.get("monitor") != null && attributes.get("monitor").equals("machine")) {
							MachineRuleFactory machineRuleFactory = new MachineRuleFactory();
							rules.add(machineRuleFactory.createMachineRule(attributes));
						} else if (attributes.get("monitor") != null && attributes.get("monitor").equals("analytics")) {
							AnalyticsRuleFactory analyticsRuleFactory = new AnalyticsRuleFactory();
							rules.add(analyticsRuleFactory.createAnalyticsRule(attributes));
						} else if (attributes.get("monitor") != null && attributes.get("monitor").equals("universal")) {
							UniversalAgentRuleFactory universalRuleFactory = new UniversalAgentRuleFactory();
							rules.add(universalRuleFactory.createUniversalAgentRule(attributes));
						} else if (attributes.get("monitor") != null && attributes.get("monitor").equals("dotnet")) {
							DotNetRuleFactory dotNetRuleFactory = new DotNetRuleFactory();
							rules.add(dotNetRuleFactory.createDotNetRule(attributes));
						} else if (attributes.get("monitor") != null && attributes.get("monitor").equals("network")) {
							NetworkRuleFactory networkRuleFactory = new NetworkRuleFactory();
							rules.add(networkRuleFactory.createNetworkAgentRule(attributes));
						}
					}
				}
				for (int i = 0; i < Configuration.getComponentCount(); i++) {
					if (Configuration.getComponent(i).getClass() == RulebookPanel.class) {
						((RulebookPanel) Configuration.getComponent(i)).fireEvent();
						HashMap<String, String> rulebookAttributes = ((RulebookPanel) Configuration.getComponent(i))
								.getRulebookMap();
						RulebookFactory rulebookFactory = new RulebookFactory();
						Rulebook rulebook = rulebookFactory.createRulebook(rulebookAttributes);
						rulebook.setRules(rules);

						/*
						 * Check if rule validation has passed
						 */
						if (procceed) {
							int resultCode = controller.createRulebook(rulebook);
							if (resultCode >= 200 && resultCode < 220) {
								JOptionPane.showMessageDialog(frame,
										rulebook.getName() + " has been successfully updated!", "Successful Creation",
										JOptionPane.INFORMATION_MESSAGE);
								frame.dispose();
							} else {
								String result = "The resulted Code was: " + resultCode;
								JOptionPane.showMessageDialog(null, "Rulebook Cration failed!" + resultCode, result,
										JOptionPane.ERROR_MESSAGE);
							}

						} else {
							procceed = true;
						}
					}

				}

			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to cancel", "Rulebook Update",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					frame.dispose();
				}
			}
		});

		overallButtons.add(saveButton);
		overallButtons.add(btnCancel);

		frame.setVisible(true);

	}

}
