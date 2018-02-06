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

public class UniversalAgentConfig extends AgentConfig {

	// <agent version>
	private String version;
	// installed | started | attached
	private String state;

	// name of host running controller
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

	@Override
	public String toString() {
		return "UniversalAgentConfig [version=" + version + ", state=" + state + "]";
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
