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

import java.util.ArrayList;
import java.util.HashMap;

public class AnalyticsAgentConfig extends AgentConfig {
	// indicated whether the analytics agent is to run as a machine
	// agent(machine-agent) or as a standalone JVM(standalone)
	private String mode;
	private String version;
	private String state;
	private String controller_host;
	private String controller_port;
	private String controller_ssl_enabled;

	public String getController_ssl_enabled() {
		return controller_ssl_enabled;
	}

	public void setController_ssl_enabled(String controller_ssl_enabled) {
		this.controller_ssl_enabled = controller_ssl_enabled;
	}

	private String account_name;
	private String global_account_name;
	private String account_access_key;
	private String events_services_host;
	private String events_services_port;
	/*
	 * Applies only when the mode is "machine-agent". This property identifies the
	 * Machine Agent where the Analytics Agent runs as an extension. The value is a
	 * list of one or more strings, each of which contains the name of a Machine
	 * Agent rule within the Universal Agent rulebook. This argument is required if
	 * "mode" : "machine-agent" is specified. "deploy_in": [ "machine-agent-1",
	 * "machine-agent-2"
	 */
	private ArrayList<String> deploy_in;
	/*
	 * Contains one or more property definitions. Each property definition is added
	 * (or modified, if the property already exists) in the conf/analytics-a
	 * gent.properties file for the analytics agent. "props": {
	 * "http.event.error.retryAttempts": "500" }
	 */
	private HashMap<String, String> props;
	/*
	 * Contains one or more JVM options definitions. Each JVM options is added to
	 * the conf/analytics-age nt.vmoptions file "vmoptions": [ "-verbose:class" ]
	 */
	private ArrayList<String> vmoptions;
	/*
	 * If "true" is specified, then the Universal Agent restarts any Machine Agents
	 * hosting this Analytics Agent if any of the configuration arguments change.
	 * The default is "false". Applicable only if "mode": "machine-agent" is
	 * specified. "force_recycle": "true" Boolean
	 */
	private String force_recycle;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public String getGlobal_account_name() {
		return global_account_name;
	}

	public void setGlobal_account_name(String global_account_name) {
		this.global_account_name = global_account_name;
	}

	public String getAccount_access_key() {
		return account_access_key;
	}

	public void setAccount_access_key(String account_access_key) {
		this.account_access_key = account_access_key;
	}

	public String getEvents_services_host() {
		return events_services_host;
	}

	public void setEvents_services_host(String events_services_host) {
		this.events_services_host = events_services_host;
	}

	public String getEvents_services_port() {
		return events_services_port;
	}

	public void setEvents_services_port(String events_services_port) {
		this.events_services_port = events_services_port;
	}

	public String getDeploy_in() {
		String stringDeploy = "";

		for (String key : deploy_in) {
			stringDeploy += key + ",";

		}
		stringDeploy = stringDeploy.substring(0, stringDeploy.length() - 1);

		return stringDeploy;
	}

	public void setDeploy_in(String deploy_in) {
		if (this.deploy_in == null) {
			this.deploy_in = new ArrayList<String>();
		}
		this.deploy_in.add(deploy_in);
	}

	public String getProps() {
		String stringProps = "";

		for (String key : props.keySet()) {
			stringProps += key + ":" + props.get(key) + ",";

		}
		stringProps = stringProps.substring(0, stringProps.length() - 1);

		return stringProps;
	}

	public void setProps(String props, String temp) {
		if (this.props == null) {
			this.props = new HashMap<String, String>();
		}
		this.props.put(props, temp);
	}

	public String getVmoption() {
		String stringVmoptions = "";

		for (String s : vmoptions) {
			stringVmoptions += s + ",";
		}
		stringVmoptions = stringVmoptions.substring(0, stringVmoptions.length() - 1);
		return stringVmoptions;
	}

	public void setVmoption(String vmoptions) {
		if (this.vmoptions == null) {
			this.vmoptions = new ArrayList<String>();
		}
		this.vmoptions.add(vmoptions);
	}

	public String getForce_recycle() {
		return force_recycle;
	}

	public void setForce_recycle(String force_recycle) {
		this.force_recycle = force_recycle;
	}

}
