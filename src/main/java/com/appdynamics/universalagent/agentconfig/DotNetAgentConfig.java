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
import java.util.ArrayList;
import java.util.HashMap;

public class DotNetAgentConfig extends AgentConfig {

	@Override
	public String toString() {
		return "DotNetAgentConfig [version=" + version + ", state=" + state + ", controller_host=" + controller_host
				+ ", controller_port=" + controller_port + ", account_name=" + account_name + ", account_access_key="
				+ account_access_key + ", application_name=" + application_name + ", tier_name=" + tier_name
				+ ", node_name=" + node_name + ", controller_ssl_enabled=" + controller_ssl_enabled
				+ ", controller_secure=" + controller_secure + ", controller_enable_tls12=" + controller_enable_tls12
				+ ", config_xml=" + config_xml + "]";
	}

	private String version;
	// installed, started
	private String state;
	private String controller_host;
	private String controller_port;
	private String account_name;
	private String account_access_key;
	private String application_name;
	// tier name
	private String tier_name;
	// node name
	private String node_name;
	// node name prefix for auto-naming

	// boolean
	private String controller_ssl_enabled;
	// boolean
	private String controller_secure;

	private String controller_enable_tls12;

	/*
	 * Specifies, in JSON format, a list of repository URLs where config.xml,
	 * related scripts, and binaries are located. For each .NET Agent version, you
	 * can specify a corresponding config.xml. This config file is downloaded from
	 * the repository specified in the .NET Agent rule. The location can be local to
	 * the machine in a shareddirectory, or external.
	 */
	private ArrayList<String> config_xml;

	public String getApplication_name() {
		return application_name;
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

	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}

	public String getController_enable_tls12() {
		return controller_enable_tls12;
	}

	public void setController_enable_tls12(String controller_enable_tls12) {
		this.controller_enable_tls12 = controller_enable_tls12;
	}

	public DotNetAgentConfig() {

	}

	public DotNetAgentConfig(String version, String state, String controller_host, String controller_port,
			String account_name, String account_access_key, String controller_ssl_enabled, String controller_secure,
			ArrayList<String> config_xml) {
		super();
		this.version = version;
		this.state = state;
		this.controller_host = controller_host;
		this.controller_port = controller_port;
		this.account_name = account_name;
		this.account_access_key = account_access_key;
		this.controller_ssl_enabled = controller_ssl_enabled;
		this.controller_secure = controller_secure;
		this.config_xml = config_xml;
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

	public String getController_ssl_enabled() {
		return controller_ssl_enabled;
	}

	public void setController_ssl_enabled(String controller_ssl_enabled) {
		this.controller_ssl_enabled = controller_ssl_enabled;
	}

	public String getController_secure() {
		return controller_secure;
	}

	public void setController_secure(String controller_secure) {
		this.controller_secure = controller_secure;
	}

	public String getConfig_xml() {
		String stringConfig = "";
		for (String key : config_xml) {
			stringConfig += key + ",";
		}
		stringConfig = stringConfig.substring(0, stringConfig.length() - 1);
		return stringConfig;
	}

	public void setConfig_xml(String config_xml) {
		if (this.config_xml == null) {
			this.config_xml = new ArrayList<String>();
		}
		this.config_xml.add(config_xml);
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
							try {
								if (method.invoke(this) != null) {
									agentConfigAttributes.put(f.getName(), ((String) method.invoke(this)));
								}
							} catch (IllegalAccessException e) {

							} catch (InvocationTargetException e) {
							}
						}
					}
				}
			}
		}
		return agentConfigAttributes;
	}

}
