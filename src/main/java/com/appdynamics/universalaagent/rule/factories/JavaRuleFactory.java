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

import com.appdynamics.universalagent.agentconfig.JavaAgentConfig;
import com.appdynamics.universalagent.rules.JavaRule;

/**
 * Class JavaRuleFactory creates an JavaRule object based on a map of key value
 * attributes
 * 
 * @author nikolaos.papageorgiou
 *
 */

public class JavaRuleFactory {

	public JavaRule createJavaRule(HashMap<String, String> attributes) {

		JavaRule resultRule = new JavaRule();
		JavaAgentConfig resultAgentConfig = new JavaAgentConfig();

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
			case "node_name_prefix":
				resultAgentConfig.setNode_name_prefix(attribute);
				break;
			case "deploy_cmd":
				resultAgentConfig.setDeploy_cmd(attribute);
				break;
			case "deploy_env_vars":
				resultAgentConfig.setDeploy_cmd(attribute);
				break;
			case "do_not_deploy_cmd":
				resultAgentConfig.setDo_not_deploy_cmd(attribute);
				break;
			case "crash_age_threshold_days":
				resultAgentConfig.setCrash_age_threshold_days(attribute);
				break;
			case "runtime_directory":
				resultAgentConfig.setRuntime_directory(attribute);
				break;
			case "additional_props":
				resultAgentConfig.setAdditional_props(attribute);
				break;
			case "kind":
				resultAgentConfig.setKind(attribute);
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
