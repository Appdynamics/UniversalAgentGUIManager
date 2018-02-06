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

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import com.appdynamics.universalagent.events.SaveEventListener;

public class RulebookPanel extends JPanel {

	private ArrayList<String> attributeNames;
	private HashMap<String, String> attributeNamesMap;

	private HashMap<String, String> rulebookMap = new HashMap<String, String>();

	private List<SaveEventListener> listeners = new ArrayList<SaveEventListener>();

	public RulebookPanel(GridLayout gridLayout, ArrayList<String> attributeNames,
			HashMap<String, String> attributeNamesMap) {
		super(gridLayout);
		this.attributeNames = attributeNames;
		this.attributeNamesMap = attributeNamesMap;
	}

	public RulebookPanel(ArrayList<String> attributeNames2, HashMap<String, String> attributeNamesMap2) {
		this.attributeNames = attributeNames2;
		this.attributeNamesMap = attributeNamesMap2;
	}

	public ArrayList<String> getAttributeNames() {
		return attributeNames;
	}

	public void setAttributeNames(ArrayList<String> attributeNames) {
		this.attributeNames = attributeNames;
	}

	public HashMap<String, String> getAttributeNamesMap() {
		return attributeNamesMap;
	}

	public void setAttributeNamesMap(HashMap<String, String> attributeNamesMap) {
		this.attributeNamesMap = attributeNamesMap;
	}

	public HashMap<String, String> getRulebookMap() {
		return rulebookMap;
	}

	public void setRulebookMap(HashMap<String, String> ruleMap) {
		this.rulebookMap = ruleMap;
	}

	public void addListener(SaveEventListener toAdd) {
		listeners.add(toAdd);
	}

	public void fireEvent() {

		for (SaveEventListener l : listeners)
			l.save();
	}

}
