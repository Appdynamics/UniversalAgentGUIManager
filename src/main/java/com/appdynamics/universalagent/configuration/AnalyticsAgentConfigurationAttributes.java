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

public class AnalyticsAgentConfigurationAttributes implements RuleConfigurationAttributes {

	private HashMap<String, String> attributesMap = new HashMap<String, String>();
	private ArrayList<String> attributeNames = new ArrayList<String>();

	public AnalyticsAgentConfigurationAttributes() {
		initializeAttributes();
	}

	@Override
	public ArrayList<String> getAttributeNames() {
		return attributeNames;

	}

	@Override
	public HashMap<String, String> getAttributesMap() {
		return attributesMap;
	}

	public void initializeAttributes() {
		attributeNames.add("mode");
		attributesMap.put("mode", "machine-agent || standalone");

		attributeNames.add("version");
		attributesMap.put("version", "agent version");

		attributeNames.add("state");
		attributesMap.put("state", "installed | started ");

		attributeNames.add("controller_host");
		attributesMap.put("controller_host", "controller host");

		attributeNames.add("controller_port");
		attributesMap.put("controller_port", "controller port");

		attributeNames.add("controller_ssl_enabled");
		attributesMap.put("controller_ssl_enabled", "controller ssl enabled");

		attributeNames.add("account_name");
		attributesMap.put("account_name", "account name");

		attributeNames.add("global_account_name");
		attributesMap.put("global_account_name", "Example: customer1_5c5826ff-0cc6-4745-8f25-af757d3a510d");

		attributeNames.add("account_access_key");
		attributesMap.put("account_access_key", "Example: SJ5b2m7d1$$354");

		attributeNames.add("events_services_host");
		attributesMap.put("events_services_host", "Events Services Host");

		attributeNames.add("events_services_port");
		attributesMap.put("events_services_port", "Events Services Port");

		attributeNames.add("vmoptions");
		attributesMap.put("vmoptions", "vmoptions");

		attributeNames.add("deploy_in");
		attributesMap.put("deploy_in", "Machine Rule Name, Example: machine-agent-rule");

		attributeNames.add("props");
		attributesMap.put("props", "Contains one or more JVM options definitions. Example: -verbose:class");

		attributeNames.add("force_recycle");
		attributesMap.put("force_recycle", "true | false");

	}

}
