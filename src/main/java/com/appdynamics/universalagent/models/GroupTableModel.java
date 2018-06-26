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

import com.appdynamics.universalagent.universalagent.Group;

/**
 * 
 * @author nikolaos.papageorgiou
 *
 */
@SuppressWarnings("serial")
public class GroupTableModel extends AbstractTableModel {
	private List<Group> groups;
	private final String[] tableHeaders = { "Group Name", "Group Comment" };

	public GroupTableModel(List<Group> groups) {

		this.groups = new ArrayList<Group>(groups);

	}

	public void setGroups(List<Group> groups) {
		this.groups = new ArrayList<Group>(groups);
	}

	public int getRowCount() {
		return groups.size();
	}

	public int getColumnCount() {
		return tableHeaders.length;
	}

	public String getColumnName(int columnIndex) {
		return tableHeaders[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Group group = groups.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = group.getName();
			break;
		case 1:
			value = group.getComments();
			break;

		}

		return value;

	}

	public Group getUserAt(int row) {
		return groups.get(row);
	}

}
