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

import java.util.ArrayList;

public class UniversalAgentCollection {

	private ArrayList<Agent> universalAgents;

	public UniversalAgentCollection() {

	}

	public UniversalAgentCollection(ArrayList<Agent> universalAgents) {
		this.universalAgents = universalAgents;

	}

	public void addUniversalAgetn(Agent universalAgent) {
		universalAgents.add(universalAgent);

	}

	public ArrayList<Agent> getUniversalAgents() {
		return universalAgents;
	}

	public Agent getUniversalAgent(int index) {
		return universalAgents.get(index);
	}

	public Agent getUniversalAgentByName(String name) {
		for (Agent agent : universalAgents) {
			if (agent.getAgentName().equals(name)) {
				return agent;
			}
		}
		return null;
	}

	public Agent getUniversalAgentById(String agentId) {
		for (Agent agent : universalAgents) {
			if (agent.getAgentId().equals(agentId)) {
				return agent;
			}
		}
		return null;
	}

	public void setUniversalAgents(ArrayList<Agent> universalAgents) {
		this.universalAgents = universalAgents;
	}

}
