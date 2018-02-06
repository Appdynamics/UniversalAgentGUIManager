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
package com.appdynamics.universalagent.universalagent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Class RulebookConfig POJO class to hold information of a RulebookConfig, this
 * is the general rulebook configuration can be overriden from the agent's
 * config
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class RulebookConfig {

	private String version;
	private String controller_host;
	private String controller_port;
	private String account_name;
	private String account_access_key;
	private String agent_runtime_dir;
	private String application_name;
	private String controller_ssl_enabled;
	private String credential_store_filename;
	private String credential_store_password;
	private String enable_orchestration;
	private String force_agent_registration;
	private String machine_path;
	private String node_name;
	private String tier_name;
	private String use_encrypted_credentials;
	private String use_simple_hostname;

	public String getAgent_runtime_dir() {
		return agent_runtime_dir;
	}

	public void setAgent_runtime_dir(String agent_runtime_dir) {
		this.agent_runtime_dir = agent_runtime_dir;
	}

	public String getApplication_name() {
		return application_name;
	}

	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}

	public String getController_ssl_enabled() {
		return controller_ssl_enabled;
	}

	public void setController_ssl_enabled(String controller_ssl_enabled) {
		this.controller_ssl_enabled = controller_ssl_enabled;
	}

	public String getCredential_store_filename() {
		return credential_store_filename;
	}

	public void setCredential_store_filename(String credential_store_filename) {
		this.credential_store_filename = credential_store_filename;
	}

	public String getCredential_store_password() {
		return credential_store_password;
	}

	public void setCredential_store_password(String credential_store_password) {
		this.credential_store_password = credential_store_password;
	}

	public String getEnable_orchestration() {
		return enable_orchestration;
	}

	public void setEnable_orchestration(String enable_orchestration) {
		this.enable_orchestration = enable_orchestration;
	}

	public String getForce_agent_registration() {
		return force_agent_registration;
	}

	public void setForce_agent_registration(String force_agent_registration) {
		this.force_agent_registration = force_agent_registration;
	}

	public String getMachine_path() {
		return machine_path;
	}

	public void setMachine_path(String machine_path) {
		this.machine_path = machine_path;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public String getTier_name() {
		return tier_name;
	}

	public void setTier_name(String tier_name) {
		this.tier_name = tier_name;
	}

	public String getUse_encrypted_credentials() {
		return use_encrypted_credentials;
	}

	public void setUse_encrypted_credentials(String use_encrypted_credentials) {
		this.use_encrypted_credentials = use_encrypted_credentials;
	}

	public String getUse_simple_hostname() {
		return use_simple_hostname;
	}

	public void setUse_simple_hostname(String use_simple_hostname) {
		this.use_simple_hostname = use_simple_hostname;
	}

	public RulebookConfig() {

	}

	public RulebookConfig(String version, String controller_host, String controller_port, String account_name,
			String account_access_key) {
		super();
		this.version = version;
		this.controller_host = controller_host;
		this.controller_port = controller_port;
		this.account_name = account_name;
		this.account_access_key = account_access_key;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	@Override
	public String toString() {
		return "RulebookConfig [version=" + version + ", controller_host=" + controller_host + ", controller_port="
				+ controller_port + ", account_name=" + account_name + ", account_access_key=" + account_access_key
				+ "]";
	}

	public HashMap<String, String> getInstanciatedAttributes() {
		HashMap<String, String> rulebookConfigAttributes = new HashMap<String, String>();
		for (Field f : this.getClass().getDeclaredFields()) {
			if (f.getType() != null || !f.getType().isPrimitive()) {
				for (Method method : this.getClass().getMethods()) {
					if ((method.getName().startsWith("get"))
							&& (method.getName().length() == (f.getName().length() + 3))) {
						if (method.getName().toLowerCase().endsWith(f.getName().toLowerCase())) {
							// MZ: Method found, run it
							try {
								if (method.invoke(this) != null) {
									rulebookConfigAttributes.put(f.getName(), ((String) method.invoke(this)));
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

		return rulebookConfigAttributes;

	}

}
