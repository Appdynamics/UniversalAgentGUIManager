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
package com.appdynamics.universalagent.rules;

import java.util.HashMap;

import com.appdynamics.universalagent.agentconfig.UniversalAgentConfig;

/**
 * Class Universal Rule is the POJO class of a Universal agent
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class UniversalRule extends Rule {

	private UniversalAgentConfig config;

	public UniversalRule() {
		config = new UniversalAgentConfig();
	}

	public UniversalRule(String name, String comments, String monitor, UniversalAgentConfig config, String condition) {
		super(name, comments, monitor, condition);
		// this.name = name;
		// this.comments = comments;
		// this.monitor = monitor;
		this.config = config;
		// this.condition = condition;
	}

	public HashMap<String, String> getInstanciatedAttributes() {
		if (config != null) {
			if (super.getMonitor().equals("universal"))
				return config.getInstanciatedAttributes();
		}
		return new HashMap<String, String>();
	}

	public void setConfig(UniversalAgentConfig resultAgentConfig) {
		this.config = resultAgentConfig;

	}

	public UniversalAgentConfig getConfig() {
		return config;
	}

	@Override
	public String toString() {
		return "UniversalRule [config=" + config.toString() + "]";
	}

}
