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
package com.appdynamics.universalaagent.rule.factories;

import java.util.HashMap;

import com.appdynamics.universalagent.agentconfig.NetworkAgentConfig;
import com.appdynamics.universalagent.rules.NetworkRule;

/**
 * Class NetworkRuleFactory creates an NetworkRule object based on a map of key
 * value attributes
 * 
 * @author nikolaos.papageorgiou
 *
 */

public class NetworkRuleFactory {
	public NetworkRule createNetworkAgentRule(HashMap<String, String> attributes) {

		NetworkRule resultRule = new NetworkRule();
		NetworkAgentConfig resultAgentConfig = new NetworkAgentConfig();

		for (String key : attributes.keySet()) {

			String attributeName = key;
			String attribute = attributes.get(key);

			switch (attributeName) {
			case "name":
				resultRule.setName(attribute);
				break;
			case "comments":
				resultRule.setComments(attribute);
				break;
			case "monitor":
				resultRule.setMonitor(attribute);
				break;
			case "condition":
				resultRule.setCondition(attribute);
				break;

			case "version":
				resultAgentConfig.setVersion(attribute);
				break;
			case "state":
				resultAgentConfig.setState(attribute);
				break;
			}

		}
		resultRule.setConfig(resultAgentConfig);
		return resultRule;
	}
}
