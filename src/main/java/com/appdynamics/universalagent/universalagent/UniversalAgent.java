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

public class UniversalAgent {
	public UniversalAgent(String agentName, Integer agentId, String version, String ruleBookName) {
		super();
		this.agentName = agentName;
		this.agentId = agentId;
		this.version = version;
		this.ruleBookName = ruleBookName;
	}

	private String agentName;

	private Integer agentId;

	private String version;

	private String ruleBookName;

	public Integer getAgentId() {
		return agentId;
	}

	public String getAgentName() {
		return new String(agentName);
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public void setAgentId(Integer agentId) {
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

}
