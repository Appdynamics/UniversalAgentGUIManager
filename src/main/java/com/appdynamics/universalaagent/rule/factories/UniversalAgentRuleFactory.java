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
import com.appdynamics.universalagent.rules.UniversalRule;
import com.appdynamics.universalagent.agentconfig.UniversalAgentConfig;

/**
 * Class UniversalRuleFactory creates an UniversalRule object based on a map of
 * key value attributes
 * 
 * @author nikolaos.papageorgiou
 *
 */

public class UniversalAgentRuleFactory {
	public UniversalRule createUniversalAgentRule(HashMap<String, String> attributes) {

		UniversalRule resultRule = new UniversalRule();
		UniversalAgentConfig resultAgentConfig = new UniversalAgentConfig();

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
