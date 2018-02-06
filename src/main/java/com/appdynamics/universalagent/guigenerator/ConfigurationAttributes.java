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

import java.util.ArrayList;
import java.util.HashMap;

public class ConfigurationAttributes {

	private HashMap<String, String> javaAttributesMap = new HashMap<String, String>();
	private ArrayList<String> javaAttributeNames = new ArrayList<String>();

	public HashMap<String, String> getJavaAttributesMap() {
		return javaAttributesMap;
	}

	public void setJavaAttributesMap(HashMap<String, String> javaAttributesMap) {
		this.javaAttributesMap = javaAttributesMap;
	}

	public ArrayList<String> getJavaAttributeNames() {
		return javaAttributeNames;
	}

	public void setJavaAttributeNames(ArrayList<String> javaAttributeNames) {
		this.javaAttributeNames = javaAttributeNames;
	}

	public void initializeJavaAttributes() {
		javaAttributeNames.add("version");
		javaAttributesMap.put("version", "agent version");

		javaAttributeNames.add("state");
		javaAttributesMap.put("state", "installed | started | attached");

		javaAttributeNames.add("controller_host");
		javaAttributesMap.put("controller_host", "controller host");

		javaAttributeNames.add("controller_port");
		javaAttributesMap.put("controller_port", "controller port");

		javaAttributeNames.add("account_name");
		javaAttributesMap.put("account_name", "account name");

		javaAttributeNames.add("account_access_key");
		javaAttributesMap.put("account_access_key", "account access key");

		javaAttributeNames.add("application_name");
		javaAttributesMap.put("application_name", "application name");

		javaAttributeNames.add("tier_name");
		javaAttributesMap.put("tier_name", "tier name");

		javaAttributeNames.add("node_name");
		javaAttributesMap.put("node_name", "node_name");

		javaAttributeNames.add("node_name_prefix");
		javaAttributesMap.put("node_name_prefix", "node_name_prefix");

		javaAttributeNames.add("deploy_cmd");
		javaAttributesMap.put("deploy_cmd", "regular expression matching Java command line");

		javaAttributeNames.add("deploy_env_vars");
		javaAttributesMap.put("deploy_env_vars", "regular expression matching JVM environmental variables");

		javaAttributeNames.add("do_not_deploy_cmd");
		javaAttributesMap.put("do_not_deploy_cmd", "regular expression matching Java command line to be excluded");

		javaAttributeNames.add("crash_age_threshold_days");
		javaAttributesMap.put("crash_age_threshold_days", "prevents installation to JVMs with recent crash log");

		javaAttributeNames.add("runtime_directory");
		javaAttributesMap.put("runtime_directory", "identifies the runtime directory for the Java agent");

		javaAttributeNames.add("additional_props");
		javaAttributesMap.put("additional_props", "additional properties to be passed to the JVM");

		javaAttributeNames.add("kind");
		javaAttributesMap.put("kind", "indicates whether or not IBM version of Java agent is to be installed");

	}

}
