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

public class RulebookConfigurationAttributes {

	private HashMap<String, String> rulebookAttributesMap = new HashMap<String, String>();
	private ArrayList<String> rulebookAttributeNames = new ArrayList<String>();

	public RulebookConfigurationAttributes() {
		initializeAttributes();
	}

	public HashMap<String, String> getAttributesMap() {
		return rulebookAttributesMap;
	}

	public void setAttributesMap(HashMap<String, String> rulebookAttributesMap) {
		this.rulebookAttributesMap = rulebookAttributesMap;
	}

	public ArrayList<String> getAttributeNames() {
		return rulebookAttributeNames;
	}

	public void setAttributeNames(ArrayList<String> rulebookAttributeNames) {
		this.rulebookAttributeNames = rulebookAttributeNames;
	}

	public void initializeAttributes() {
		rulebookAttributeNames.add("version");
		rulebookAttributesMap.put("version", "version");

		rulebookAttributeNames.add("controller_host");
		rulebookAttributesMap.put("controller_host", "controller_host");

		rulebookAttributeNames.add("controller_port");
		rulebookAttributesMap.put("controller_port", "controller_port");

		rulebookAttributeNames.add("account_name");
		rulebookAttributesMap.put("account_name", "account_name");

		rulebookAttributeNames.add("account_access_key");
		rulebookAttributesMap.put("account_access_key", "account_access_key");

		rulebookAttributeNames.add("agent_runtime_dir");
		rulebookAttributesMap.put("agent_runtime_dir", "agent_runtime_dir");

		rulebookAttributeNames.add(" application_name");
		rulebookAttributesMap.put("application_name", "application_name");

		rulebookAttributeNames.add("controller_ssl_enabled");
		rulebookAttributesMap.put("controller_ssl_enabled", "controller_ssl_enabled");

		rulebookAttributeNames.add("credential_store_filename");
		rulebookAttributesMap.put("credential_store_filename", "credential_store_filename");

		rulebookAttributeNames.add("credential_store_password");
		rulebookAttributesMap.put("credential_store_password", "credential_store_password");

		rulebookAttributeNames.add("enable_orchestration");
		rulebookAttributesMap.put("enable_orchestration", "enable_orchestration");

		rulebookAttributeNames.add("force_agent_registration");
		rulebookAttributesMap.put("force_agent_registration", "force_agent_registration");

		rulebookAttributeNames.add("machine_path");
		rulebookAttributesMap.put("machine_path", "machine_path");

		rulebookAttributeNames.add("node_name");
		rulebookAttributesMap.put("node_name", "node_name");

		rulebookAttributeNames.add("tier_name");
		rulebookAttributesMap.put("tier_name", "tier_name");

		rulebookAttributeNames.add("use_encrypted_credentials");
		rulebookAttributesMap.put("use_encrypted_credentials", "use_encrypted_credentials");

		rulebookAttributeNames.add("use_simple_hostname");
		rulebookAttributesMap.put("use_simple_hostname", "use_simple_hostname");

	}
}
