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
package com.appdynamics.universalagent.configuration;

import java.util.ArrayList;
import java.util.HashMap;

public class UniversalAgentConfigurationAttributes implements RuleConfigurationAttributes {
	private HashMap<String, String> attributesMap = new HashMap<String, String>();
	private ArrayList<String> attributeNames = new ArrayList<String>();

	public UniversalAgentConfigurationAttributes() {
		initializeAttributes();
	}

	public HashMap<String, String> getAttributesMap() {
		return attributesMap;
	}

	public void setAttributesMap(HashMap<String, String> javaAttributesMap) {
		this.attributesMap = javaAttributesMap;
	}

	public ArrayList<String> getAttributeNames() {
		return attributeNames;
	}

	public void setAttributeNames(ArrayList<String> javaAttributeNames) {
		this.attributeNames = javaAttributeNames;
	}

	public void initializeAttributes() {
		attributeNames.add("version");
		attributesMap.put("version", "agent version");

		attributeNames.add("state");
		attributesMap.put("state", "installed | started | attached");
	}
}
