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
package com.appdynamics.universalagent.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.appdynamics.universalagent.universalagent.Rulebook;

/**
 * 
 * @author nikolaos.papageorgiou
 *
 */
@SuppressWarnings("serial")
public class RulebookTableModel extends AbstractTableModel {
	
	private List<Rulebook> rulebooks;
	private final String[] tableHeaders = { "Rulebook Name", "Rulebook Comment", "Rulebook Version" };

	public RulebookTableModel(List<Rulebook> rulebooks) {

		this.rulebooks = new ArrayList<Rulebook>(rulebooks);

	}

	public int getRowCount() {
		return rulebooks.size();
	}

	public int getColumnCount() {
		return tableHeaders.length;
	}

	public String getColumnName(int columnIndex) {
		return tableHeaders[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Rulebook rulebook = rulebooks.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = rulebook.getName();
			break;
		case 1:
			value = rulebook.getComments();
			break;
		case 2:
			value = rulebook.getVersion();
			break;

		}

		return value;

	}

	public Rulebook getUserAt(int row) {
		return rulebooks.get(row);
	}

}
