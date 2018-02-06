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

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.appdynamics.universal.session.ExportHistoryToBash;
import com.appdynamics.universal.session.Session;
import com.appdynamics.universalagent.models.CheckboxListCellRenderer;
import com.appdynamics.universalagent.models.HistoryTableModel;
import com.appdynamics.universalagent.universalagent.RestController;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicFileChooserUI;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExportWindow {

	private JFrame frame;
	private JTable table;
	private JButton selectPathButton;
	private JButton exportButton;

	/**
	 * Launch the application.
	 */
	public void run() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExportWindow window = new ExportWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public ExportWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 250, 700, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel historyLabel = new JLabel("History");
		historyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		historyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(historyLabel, BorderLayout.NORTH);

		JPanel actionPanel = new JPanel();
		frame.getContentPane().add(actionPanel, BorderLayout.CENTER);

		// actionPanel.add(table);

		final Map<String, String> map = Session.getInstance().getSessionMap();

		HistoryTableModel model = new HistoryTableModel();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			model.addRow(new Object[] { entry.getKey(), new Boolean(true) });

		}

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		actionPanel.add(scrollPane);
		final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fc.setDialogTitle("Select a folder");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		selectPathButton = new JButton("Save to File");
		selectPathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				HashMap<String, String> actions = new HashMap<String, String>();

				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 1).toString());

					if (isChecked) {
						actions.put((String) table.getValueAt(i, 0), map.get(table.getValueAt(i, 0)));
					}
				}

				ExportHistoryToBash exporter = new ExportHistoryToBash();

				int returnValue = fc.showOpenDialog(frame);
				File selectedDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (fc.getSelectedFile().isDirectory()) {
						selectedDirectory = fc.getSelectedFile();
					}
					StringBuilder pathBuilder = new StringBuilder();

					pathBuilder.append(selectedDirectory);
					pathBuilder.append(File.separator);
					pathBuilder.append("history.sh");

					String path = pathBuilder.toString();

					if (JOptionPane.showConfirmDialog(frame, "If " + path + " exists, history.sh will be overriden",
							"Override Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						exporter.export(actions, selectedDirectory);
					}

				}

				// fc.showSaveDialog(frame);
				// File selectedDirectory=fc.getSelectedFile();

			}
		});

		frame.getContentPane().add(selectPathButton, BorderLayout.SOUTH);

	}

}
