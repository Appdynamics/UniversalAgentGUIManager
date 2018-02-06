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

import com.appdynamics.universalagent.rules.Rule;

/**
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class RuleTableModel {
	private List<Rule> rules;
	private final String[] tableHeaders = { "Rule Name", "Rule Comments", "Rule  Monitor", "Rule Condition" };

	public RuleTableModel(List<Rule> rules) {

		this.rules = new ArrayList<Rule>(rules);

	}

	public int getRowCount() {
		return rules.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int columnIndex) {
		return tableHeaders[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Rule rule = rules.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = rule.getName();
			break;
		case 1:
			value = rule.getComments();
			break;
		case 2:
			value = rule.getMonitor();
			break;
		case 3:
			value = rule.getCondition();
			break;

		}

		return value;

	}

	public Rule getUserAt(int row) {
		return rules.get(row);
	}

}
