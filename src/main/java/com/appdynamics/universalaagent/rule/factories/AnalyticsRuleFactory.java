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

import com.appdynamics.universalagent.agentconfig.AnalyticsAgentConfig;
import com.appdynamics.universalagent.rules.AnalyticsRule;

/**
 * Class AnalyticsRuleFactory creates an AnalyticsRule object based on a map of
 * key value attributes
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class AnalyticsRuleFactory {
	public AnalyticsRule createAnalyticsRule(HashMap<String, String> attributes) {

		AnalyticsRule resultRule = new AnalyticsRule();
		AnalyticsAgentConfig resultAgentConfig = new AnalyticsAgentConfig();

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

			case "mode":
				resultAgentConfig.setMode(attribute);
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

			case "global_account_name":
				resultAgentConfig.setGlobal_account_name(attribute);
				break;

			case "events_services_host":
				resultAgentConfig.setEvents_services_host(attribute);
				break;
			case "events_services_port":
				resultAgentConfig.setEvents_services_port(attribute);
				break;
			case "force_recycle":
				resultAgentConfig.setForce_recycle(attribute);
				break;

			case "deploy_in":
				String[] deploy_inAttributes = attribute.split("\\s*,\\s*");
				for (int i = 0; i < deploy_inAttributes.length; i++) {
					resultAgentConfig.setDeploy_in(deploy_inAttributes[i]);
				}
				break;
			case "props":
				String[] all_props = attribute.split("\\s*,\\s*");
				for (int i = 0; i < all_props.length; i++) {
					String[] property = all_props[i].split("\\s*:\\s*");
					if (property.length == 2) {
						resultAgentConfig.setProps(property[0], property[1]);
					}
				}
			case "vmoptions":
				String[] vmoptionsAttributes = attribute.split("\\s*,\\s*");
				for (int i = 0; i < vmoptionsAttributes.length; i++) {
					resultAgentConfig.setVmoption(vmoptionsAttributes[i]);
				}
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
