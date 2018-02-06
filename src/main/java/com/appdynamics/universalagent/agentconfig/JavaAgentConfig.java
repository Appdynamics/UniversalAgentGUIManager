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

/**
 * Class AgentConfig (POJO), contains all properties that specify a java agent's
 * configuration
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class JavaAgentConfig extends AgentConfig {

	// <agent version>
	private String version;
	// installed | started | attached
	private String state;
	// name of host running controller
	private String controller_host;
	// controller primary port
	private String controller_port;
	// controller account name

	private String controller_ssl_enabled;

	public String getController_ssl_enabled() {
		return controller_ssl_enabled;
	}

	public void setController_ssl_enabled(String controller_ssl_enabled) {
		this.controller_ssl_enabled = controller_ssl_enabled;
	}

	private String account_name;
	// controller account access key
	private String account_access_key;
	// business application
	private String application_name;
	// tier name
	private String tier_name;
	// node name
	private String node_name;
	// node name prefix for auto-naming
	private String node_name_prefix;
	// regular expression matching Java command line
	private String deploy_cmd;
	// regular expression matching JVM environmental variables
	private String deploy_env_vars;
	// regular expression matching Java command line to be excluded
	private String do_not_deploy_cmd;
	// prevents installation to JVMs with recent crash log
	private String crash_age_threshold_days;
	// identifies the runtime directory for the Java agent
	private String runtime_directory;
	// additional properties to be passed to the JVM
	private String additional_props;
	// indicates whether or not IBM version of Java agent is to be installed
	private String kind;

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

	public String getNode_name_prefix() {
		return node_name_prefix;
	}

	public void setNode_name_prefix(String node_name_prefix) {
		this.node_name_prefix = node_name_prefix;
	}

	public String getDeploy_cmd() {
		return deploy_cmd;
	}

	public void setDeploy_cmd(String deploy_cmd) {
		this.deploy_cmd = deploy_cmd;
	}

	public String getDeploy_env_vars() {
		return deploy_env_vars;
	}

	public void setDeploy_env_vars(String deploy_env_vars) {
		this.deploy_env_vars = deploy_env_vars;
	}

	public String getDo_not_deploy_cmd() {
		return do_not_deploy_cmd;
	}

	public void setDo_not_deploy_cmd(String do_not_deploy_cmd) {
		this.do_not_deploy_cmd = do_not_deploy_cmd;
	}

	public String getCrash_age_threshold_days() {
		return crash_age_threshold_days;
	}

	public void setCrash_age_threshold_days(String crash_age_threshold_days) {
		this.crash_age_threshold_days = crash_age_threshold_days;
	}

	public String getRuntime_directory() {
		return runtime_directory;
	}

	public void setRuntime_directory(String runtime_directory) {
		this.runtime_directory = runtime_directory;
	}

	public String getAdditional_props() {
		return additional_props;
	}

	public void setAdditional_props(String additional_props) {
		this.additional_props = additional_props;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return "Config [version=" + version + ", state=" + state + ", controller_host=" + controller_host
				+ ", controller_port=" + controller_port + ", account_name=" + account_name + ", account_access_key="
				+ account_access_key + ", application_name=" + application_name + ", tier_name=" + tier_name
				+ ", node_name=" + node_name + ", node_name_prefix=" + node_name_prefix + ", deploy_cmd=" + deploy_cmd
				+ ", deploy_env_vars=" + deploy_env_vars + ", do_not_deploy_cmd=" + do_not_deploy_cmd
				+ ", crash_age_threshold_days=" + crash_age_threshold_days + ", runtime_directory=" + runtime_directory
				+ ", additional_props=" + additional_props + ", kind=" + kind + "]";
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

}
