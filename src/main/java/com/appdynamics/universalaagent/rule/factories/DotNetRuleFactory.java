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

import com.appdynamics.universalagent.agentconfig.DotNetAgentConfig;
import com.appdynamics.universalagent.rules.DotNetRule;

/**
 * Class DotNetRuleFactory creates an DotNetRule object based on a map of key
 * value attributes
 * 
 * @author nikolaos.papageorgiou
 *
 */

public class DotNetRuleFactory {

	public DotNetRule createDotNetRule(HashMap<String, String> attributes) {

		DotNetRule resultRule = new DotNetRule();
		DotNetAgentConfig resultAgentConfig = new DotNetAgentConfig();

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
			case "controller_host":
				resultAgentConfig.setController_host(attribute);
				break;
			case "controller_port":
				resultAgentConfig.setController_port(attribute);
				break;
			case "controller_ssl_enabled":
				resultAgentConfig.setController_ssl_enabled(attribute);
				break;
			case "account_name":
				resultAgentConfig.setAccount_name(attribute);
				break;
			case "account_access_key":
				resultAgentConfig.setAccount_access_key(attribute);
				break;
			case "application_name":
				resultAgentConfig.setApplication_name(attribute);
				break;
			case "tier_name":
				resultAgentConfig.setTier_name(attribute);
				break;
			case "node_name":
				resultAgentConfig.setNode_name(attribute);
				break;
			case "controller_enable_tls12":
				resultAgentConfig.setController_enable_tls12(attribute);
				break;

			case "config_xml":
				String[] config_xmlAttributes = attribute.split("\\s*,\\s*");
				for (int i = 0; i < config_xmlAttributes.length; i++) {
					resultAgentConfig.setConfig_xml(config_xmlAttributes[i]);
				}
				break;
			case "controller_secure":
				resultAgentConfig.setController_secure(attribute);
				break;
			default:
				attributeName = "";
				break;

			}

		}

		resultRule.setConfig(resultAgentConfig);

		return resultRule;

	}
}
