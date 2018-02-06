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
import com.appdynamics.universalagent.universalagent.Agent;

/**
 * 
 * @author nikolaos.papageorgiou
 *
 */
@SuppressWarnings("serial")
public class AgentTableModel extends AbstractTableModel {

	private List<Agent> agents;
	private final String[] tableHeaders = { "Agent ID", "Agent Name", "Agent Version", "Rulebook Name" };

	public AgentTableModel(List<Agent> agents) {

		this.agents = new ArrayList<Agent>(agents);

	}

	public int getRowCount() {
		return agents.size();
	}

	public int getColumnCount() {
		return 4;
	}

	public String getColumnName(int columnIndex) {
		return tableHeaders[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Agent agent = agents.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = agent.getAgentId();
			break;
		case 1:
			value = agent.getAgentName();
			break;
		case 2:
			value = agent.getVersion();
			break;
		case 3:
			value = agent.getRuleBookName();
			break;

		}

		return value;

	}

	public Agent getUserAt(int row) {
		return agents.get(row);
	}

}
