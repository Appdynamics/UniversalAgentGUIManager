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

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class HistoryTableModel extends DefaultTableModel {

	public HistoryTableModel() {
		super(new String[] { "Action", "Export?" }, 0);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class clazz = String.class;
		switch (columnIndex) {
		case 0:
			clazz = String.class;
			break;
		case 1:
			clazz = Boolean.class;
			break;
		}
		return clazz;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 1;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (aValue instanceof Boolean && column == 1) {

			Vector rowData = (Vector) getDataVector().get(row);
			rowData.set(1, (boolean) aValue);
			fireTableCellUpdated(row, column);
		}
	}

}
