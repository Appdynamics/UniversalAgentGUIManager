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

import com.appdynamics.universalagent.agentconfig.AnalyticsAgentConfig;

/**
 * Class Analytics Rule is the POJO class of a Analytics agent
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class AnalyticsRule extends Rule {

	private AnalyticsAgentConfig config;

	public AnalyticsRule(String name, String comments, String monitor, AnalyticsAgentConfig config, String condition) {
		super(name, comments, monitor, condition);
	}

	public AnalyticsRule() {
		this.config = new AnalyticsAgentConfig();
	}

	public AnalyticsAgentConfig getConfig() {
		return config;
	}

	public void setConfig(AnalyticsAgentConfig config) {
		this.config = config;
	}

	public HashMap<String, String> getInstanciatedAttributes() {
		if (config != null) {
			if (super.getMonitor().equals("analytics"))
				return config.getInstanciatedAttributes();
		}
		return new HashMap<String, String>();

	}

}
