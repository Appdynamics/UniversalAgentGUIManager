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
 * Class AgentConfig is the top level class of the agent hierarchy
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class AgentConfig {

	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAccountAccessKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getApplicationName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getState() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Method getInstanciatedAttributes returns the attributes that have been
	 * initialised with values different than the default ones
	 */
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
