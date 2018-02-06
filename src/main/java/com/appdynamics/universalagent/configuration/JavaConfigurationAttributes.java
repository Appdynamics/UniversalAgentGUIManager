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

public class JavaConfigurationAttributes implements RuleConfigurationAttributes {

	private HashMap<String, String> attributesMap = new HashMap<String, String>();
	private ArrayList<String> attributeNames = new ArrayList<String>();

	public JavaConfigurationAttributes() {
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

		attributeNames.add("controller_host");
		attributesMap.put("controller_host", "controller host");

		attributeNames.add("controller_port");
		attributesMap.put("controller_port", "controller port");

		attributeNames.add("controller_ssl_enabled");
		attributesMap.put("controller_ssl_enabled", "controller_ssl_enabled");

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

		attributeNames.add("node_name_prefix");
		attributesMap.put("node_name_prefix", "node_name_prefix");

		attributeNames.add("deploy_cmd");
		attributesMap.put("deploy_cmd", "regular expression matching Java command line");

		attributeNames.add("deploy_env_vars");
		attributesMap.put("deploy_env_vars", "regular expression matching JVM environmental variables");

		attributeNames.add("do_not_deploy_cmd");
		attributesMap.put("do_not_deploy_cmd", "regular expression matching Java command line to be excluded");

		attributeNames.add("crash_age_threshold_days");
		attributesMap.put("crash_age_threshold_days", "prevents installation to JVMs with recent crash log");

		attributeNames.add("runtime_directory");
		attributesMap.put("runtime_directory", "identifies the runtime directory for the Java agent");

		attributeNames.add("additional_props");
		attributesMap.put("additional_props", "additional properties to be passed to the JVM");

		attributeNames.add("kind");
		attributesMap.put("kind", "indicates whether or not IBM version of Java agent is to be installed");

	}

}
