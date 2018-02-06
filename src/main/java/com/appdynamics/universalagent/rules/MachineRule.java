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

import com.appdynamics.universalagent.agentconfig.MachineAgentConfig;

/**
 * Class Machine Rule is the POJO class of a Machine agent
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class MachineRule extends Rule {
	// Name for this rule.
	// private String name;
	// An optional description for this rule.
	// private String comments;
	// The runtime agent type. Valid values are:
	// java for the Java App Agent
	// machine for the Machine Agent
	// private String monitor;
	/*
	 * Agent-level configuration properties such as operating state, node name, and
	 * so on. These can be specified per runtime agent in the rule (as illustrated
	 * by the state and application_name fields in the sample) or globally (as
	 * illustrated by the version field). Global fields enable you to avoid
	 * repeating the field in each rule. Any value specified in the rule overrides
	 * the global value. The config properties include: version: The AppDynamics
	 * version of the app agent to use state: The action to be applied to the agent,
	 * such as installed, which simply installs the agent, or started, which both
	 * installs and starts it. For agent specific state information, see Java Agent
	 * Rules and Standalone Machine Agent Rules. application_name, tier_name, and
	 * node_name: These properties are equivalent to application-name, tier-name and
	 * node-name in controller-info.xml for the traditional agent configuration.
	 * They specify the business application, tier, and node by which the current
	 * monitored process is identified in the Controller UI. For additional
	 * connection related properties, see Controller Connection Fields.
	 */
	private MachineAgentConfig config;
	// A statement that must be true to be applied by a particular Universal Agent.
	// Typically a condition tests an environment variable or system property on the
	// host on which the Universal Agent runs.
	// private String condition;

	public MachineRule() {
		config = new MachineAgentConfig();
	}

	public MachineRule(String name, String comments, String monitor, MachineAgentConfig config, String condition) {
		super(name, comments, monitor, condition);
		// this.name = name;
		// this.comments = comments;
		// this.monitor = monitor;
		// this.config = config;
		// this.condition = condition;
	}

	public MachineAgentConfig getConfig() {
		return config;
	}

	public void setConfig(MachineAgentConfig config) {
		this.config = config;
	}

	@Override
	public String toString() {
		return "MachineRule";
	}

	public HashMap<String, String> getInstanciatedAttributes() {
		if (config != null) {
			if (super.getMonitor().equals("machine"))
				return config.getInstanciatedAttributes();
		}
		return new HashMap<String, String>();

	}
}
