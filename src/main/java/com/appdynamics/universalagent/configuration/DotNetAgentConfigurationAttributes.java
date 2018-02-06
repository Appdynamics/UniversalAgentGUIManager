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

public class DotNetAgentConfigurationAttributes implements RuleConfigurationAttributes {
	private HashMap<String, String> attributesMap = new HashMap<String, String>();
	private ArrayList<String> attributeNames = new ArrayList<String>();

	public DotNetAgentConfigurationAttributes() {
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
		attributesMap.put("version", "The version of the .NET Agent to run.");

		attributeNames.add("state");
		attributesMap.put("state", "installed | started ");

		attributeNames.add("controller_host");
		attributesMap.put("controller_host", "controller host");

		attributeNames.add("controller_port");
		attributesMap.put("controller_port", "controller port");

		attributeNames.add("controller_ssl_enabled");
		attributesMap.put("controller_ssl_enabled", "SSL is enabled, true | false");

		attributeNames.add("controller_enable_tls12");
		attributesMap.put("controller_enable_tls12", "tls12 is enabled, true | false");

		attributeNames.add("account_name");
		attributesMap.put("account_name", "account name");

		attributeNames.add("account_access_key");
		attributesMap.put("account_access_key", "account access key");

		attributeNames.add("application_name");
		attributesMap.put("application_name", "application name");

		attributeNames.add("tier_name");
		attributesMap.put("tier_name", "tier name");

		attributeNames.add("node_name");
		attributesMap.put("node_name", "node_name");

		attributeNames.add("controller_secure");
		attributesMap.put("controller_secure", "Secure is enabled, true | false");

		attributeNames.add("config_xml");
		attributesMap.put("config_xml",
				"Specifies, in JSON format, a list of repository URLs where config.xml, related scripts, and binaries are located.");

	}

}
