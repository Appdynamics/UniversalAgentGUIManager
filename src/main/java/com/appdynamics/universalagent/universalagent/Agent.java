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
package com.appdynamics.universalagent.universalagent;

/**
 * Class Agent (POJO) for the universal agent
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class Agent {
	private String agentName;
	private String agentId;
	private String version;
	private String ruleBookName;

	public Agent() {

	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRuleBookName() {
		return ruleBookName;
	}

	public void setRuleBookName(String ruleBookName) {
		this.ruleBookName = ruleBookName;
	}

	@Override
	public String toString() {
		return "UniversalAgent [agentName=" + agentName + ", agentId=" + agentId + ", version=" + version
				+ ", ruleBookName=" + ruleBookName + "]";
	}

}
