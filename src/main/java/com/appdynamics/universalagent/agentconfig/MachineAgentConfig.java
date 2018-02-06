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
package com.appdynamics.universalagent.agentconfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MachineAgentConfig extends AgentConfig {

	// <agent version>
	private String version;
	// installed | started | attached
	private String state;
	// name of host running controller
	private String controller_host;
	// controller primary port
	private String controller_port;
	private String controller_ssl_enabled;

	public String getController_ssl_enabled() {
		return controller_ssl_enabled;
	}

	// controller account name
	private String account_name;
	// controller account access key
	private String account_access_key;
	// business application
	private String application_name;
	// tier name
	private String tier_name;
	// node name
	private String node_name;

	// sim_enabled
	private String sim_enabled;

	private String dotnet_compatibility_mode;
	private String unique_host_id;

	@Override
	public String toString() {
		return "MachineAgentConfig [version=" + version + ", state=" + state + ", controller_host=" + controller_host
				+ ", controller_port=" + controller_port + ", account_name=" + account_name + ", account_access_key="
				+ account_access_key + ", application_name=" + application_name + ", tier_name=" + tier_name
				+ ", node_name=" + node_name + ", sim_enabled=" + sim_enabled + ", dotnet_compatibility_mode="
				+ dotnet_compatibility_mode + ", unique_host_id=" + unique_host_id + "]";
	}

	public String getSim_enabled() {
		return sim_enabled;
	}

	public void setSim_enabled(String sim_enabled) {
		this.sim_enabled = sim_enabled;
	}

	public String getDotnet_compatibility_mode() {
		return dotnet_compatibility_mode;
	}

	public void setDotnet_compatibility_mode(String dotnet_compatibility_mode) {
		this.dotnet_compatibility_mode = dotnet_compatibility_mode;
	}

	public String getUnique_host_id() {
		return unique_host_id;
	}

	public void setUnique_host_id(String unique_host_id) {
		this.unique_host_id = unique_host_id;
	}

	public String getUniqueHostId() {
		return unique_host_id;
	}

	public void setUniqueHostId(String unique_host_id) {
		this.unique_host_id = unique_host_id;
	}

	public String getDotNetCompatibilityMode() {
		return dotnet_compatibility_mode;
	}

	public void setDotNetCompatibilityMode(String dotnet_compatibility_mode) {
		this.dotnet_compatibility_mode = dotnet_compatibility_mode;
	}

	public String getSimEnabled() {
		return sim_enabled;
	}

	public void setSimEnabled(String sim_enabled) {
		this.sim_enabled = sim_enabled;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getController_host() {
		return controller_host;
	}

	public void setController_host(String controller_host) {
		this.controller_host = controller_host;
	}

	public String getController_port() {
		return controller_port;
	}

	public void setController_port(String controller_port) {
		this.controller_port = controller_port;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_access_key() {
		return account_access_key;
	}

	public void setAccount_access_key(String account_access_key) {
		this.account_access_key = account_access_key;
	}

	public String getApplication_name() {
		return application_name;
	}

	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}

	public String getTier_name() {
		return tier_name;
	}

	public void setTier_name(String tier_name) {
		this.tier_name = tier_name;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	@Override
	public HashMap<String, String> getInstanciatedAttributes() {
		HashMap<String, String> agentConfigAttributes = new HashMap<String, String>();
		for (Field f : this.getClass().getDeclaredFields()) {
			if (f.getType() != null || !f.getType().isPrimitive()) {
				for (Method method : this.getClass().getMethods()) {
					if ((method.getName().startsWith("get"))
							&& (method.getName().length() == (f.getName().length() + 3))) {
						if (method.getName().toLowerCase().endsWith(f.getName().toLowerCase())) {
							// MZ: Method found, run it
							try {
								if (method.invoke(this) != null) {
									agentConfigAttributes.put(f.getName(), ((String) method.invoke(this)));
								}

							} catch (IllegalAccessException e) {
								System.out.println("Could not determine method: " + method.getName());
							} catch (InvocationTargetException e) {
								System.out.println("Could not determine method: " + method.getName());
							}

						}
					}

				}
			}
		}
		return agentConfigAttributes;
	}

	public void setController_ssl_enabled(String attribute) {
		// TODO Auto-generated method stub

	}

}
