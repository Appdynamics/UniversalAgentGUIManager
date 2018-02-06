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
import com.appdynamics.universalagent.universalagent.Rulebook;
import com.appdynamics.universalagent.universalagent.RulebookConfig;

/**
 * Class RulebookFactory creates a Rulebook object based on a map of key value
 * attributes
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class RulebookFactory {

	public Rulebook createRulebook(HashMap<String, String> attributes) {

		Rulebook rulebook = new Rulebook();
		RulebookConfig rulebookConfig = new RulebookConfig();

		for (String key : attributes.keySet()) {

			String attributeName = key;
			String attribute = attributes.get(key);

			switch (attributeName) {
			case "name":
				rulebook.setName(attribute);
				break;
			case "comments":
				rulebook.setComments(attribute);
				break;

			case "rulebook_version":
				rulebook.setVersion(attribute);
				break;
			case "controller_version":
				rulebook.setControllerVersion(attribute);
				break;

			case "version":
				rulebookConfig.setVersion(attribute);
				break;
			case "controller_host":
				rulebookConfig.setController_host(attribute);
				break;
			case "controller_port":
				rulebookConfig.setController_port(attribute);
				break;
			case "account_name":
				rulebookConfig.setAccount_name(attribute);
				break;
			case "account_access_key":
				rulebookConfig.setAccount_access_key(attribute);
				break;
			case "application_name":
				rulebookConfig.setApplication_name(attribute);
				break;
			case "tier_name":
				rulebookConfig.setTier_name(attribute);
				break;
			case "node_name":
				rulebookConfig.setNode_name(attribute);
				break;
			case "agent_runtime_dir":
				rulebookConfig.setNode_name(attribute);
				break;

			case "controller_ssl_enabled":
				rulebookConfig.setController_ssl_enabled(attribute);
				break;
			case "credential_store_filename":
				rulebookConfig.setCredential_store_filename(attribute);
				break;

			case "credential_store_password":
				rulebookConfig.setCredential_store_password(attribute);
				break;

			case "enable_orchestration":
				rulebookConfig.setEnable_orchestration(attribute);
				break;
			case "force_agent_registration":
				rulebookConfig.setForce_agent_registration(attribute);
				break;

			case "machine_path":
				rulebookConfig.setMachine_path(attribute);
				break;
			case "use_encrypted_credentials":
				rulebookConfig.setUse_encrypted_credentials(attribute);
				break;
			case "use_simple_hostname":
				rulebookConfig.setUse_simple_hostname(attribute);
				break;

			default:
				attributeName = "";
				break;

			}

		}

		rulebook.setConfig(rulebookConfig);

		return rulebook;

	}

}
