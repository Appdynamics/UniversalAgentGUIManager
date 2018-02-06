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

import com.appdynamics.universalagent.agentconfig.DotNetAgentConfig;

/**
 * Class DotNet Rule is the POJO class of a DotNet agent
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class DotNetRule extends Rule {
	private DotNetAgentConfig config;
	// A statement that must be true to be applied by a particular Universal Agent.
	// Typically a condition tests an environment variable or system property on the
	// host on which the Universal Agent runs.
	// private String condition;

	public DotNetRule() {
		config = new DotNetAgentConfig();
		super.setCondition("platform_family == 'windows'");
	}

	public DotNetRule(String name, String comments, String monitor, DotNetAgentConfig config, String condition) {
		super(name, comments, monitor, condition);
		// this.name = name;
		// this.comments = comments;
		// this.monitor = monitor;
		this.config = config;
		// this.condition = condition;
	}

	public DotNetAgentConfig getConfig() {
		return config;
	}

	public void setConfig(DotNetAgentConfig config) {
		this.config = config;
	}

	@Override
	public String toString() {
		return "Rule [name=" + super.getName() + ", comments=" + super.getComments() + ", monitor=" + super.getMonitor()
				+ ", config=" + config.toString() + ", condition=" + super.getCondition() + "]";
	}

	public HashMap<String, String> getInstanciatedAttributes() {
		if (config != null) {
			if (super.getMonitor().equals("dotnet"))
				return config.getInstanciatedAttributes();

		}

		return new HashMap<String, String>();

	}
}
