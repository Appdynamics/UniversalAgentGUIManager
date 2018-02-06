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

import java.io.IOException;
import java.util.ArrayList;
import com.appdynamics.universalagent.exceptions.NoAgentsException;

/**
 * Class RestController is responsible to process the REST Api Calls
 * 
 * @author nikolaos.papageorgiou
 *
 */

public class RestController {
	private ConnectionController connectionController = new ConnectionController();
	private RestAgent restAgent = new RestAgent();

	public ConnectionController getConnectionController() {
		return connectionController;
	}

	public void setConnectionController(ConnectionController connectionController) {
		this.connectionController = connectionController;
	}

	public RestController() {
	}

	public RestController(ConnectionController controllerDetails) {
		connectionController = controllerDetails;
	}

	public ArrayList<Agent> getAllAgents() throws NoAgentsException {
		String agentsAsString = restAgent.getAllUniversalAgents(connectionController);
		AgentJSON agentJSON = new AgentJSON();
		ArrayList<Agent> agents = agentJSON.convertJSON(agentsAsString);
		return agents;
	}

	public ArrayList<Agent> getAllAgentsAssociatedWithAGroup(String groupName) {
		String agentsAsString;
		ArrayList<Agent> agents = new ArrayList<Agent>();
		try {
			agentsAsString = restAgent.getAgentsAssociatedWithGroup(connectionController, groupName);

			AgentJSON agentJSON = new AgentJSON();
			agents = agentJSON.convertJSON(agentsAsString);
		} catch (NoAgentsException e) {

		}
		return agents;

	}

	public ArrayList<Rulebook> getAllRulebooks() throws NoAgentsException {
		String rulebooksAsString = restAgent.getAllRulebooks(connectionController);
		RulebookCollection rulebookCollection = new RulebookCollection();
		rulebookCollection.setRulebookCollection(new RulebookJSON().convertJSON(rulebooksAsString));
		return rulebookCollection.getAllRulebooks();
	}

	public ArrayList<Rulebook> getAllRulebooksAssociatedWithAGroup(String groupName) throws NoAgentsException {
		String rulebooksAsString = restAgent.getRulebooksAssociatedWithGroup(connectionController, groupName);
		if (!rulebooksAsString.startsWith("[")) {
			rulebooksAsString = "[" + rulebooksAsString + "]";
		}
		RulebookCollection rulebookCollection = new RulebookCollection();
		rulebookCollection.setRulebookCollection(new RulebookJSON().convertJSON(rulebooksAsString));
		return rulebookCollection.getAllRulebooks();
	}

	public Rulebook getRulebookByName(String rulebookName) throws NoAgentsException {
		String result = restAgent.getRulebookByName(connectionController, rulebookName);
		Rulebook rulebook = new RulebookJSON().convertSingleRulebook(result);
		return rulebook;
	}

	public ArrayList<Group> getAllGroups() throws NoAgentsException {
		String groupsAsString = restAgent.getAllGroups(connectionController);
		GroupCollection groupCollection = new GroupCollection();
		groupCollection.setGroups(new GroupJSON().convertJSON(groupsAsString));
		return groupCollection.getGroups();
	}

	public Group getGroupByName(String groupName) throws NoAgentsException {
		String groupsAsString = restAgent.getGroupByName(connectionController, groupName);

		Group group = new GroupJSON().convertSignleGroupJSON(groupsAsString);
		return group;
	}

	public int createOrUpdateGroup(Group group) {
		int result = restAgent.createOrUpdateGroup(connectionController, group);
		return result;
	}

	public int deleteGroup(String groupName) {
		int result = restAgent.deleteGroup(connectionController, groupName);
		return result;
	}

	public int deleteRulebookFromGroup(String groupName) {
		int result = restAgent.deleteRulebookFromGroup(connectionController, groupName);
		return result;
	}

	public int deleteAgentFromGroup(String agentName, String groupName) {
		int result = restAgent.deleteAgentFromGroup(connectionController, agentName, groupName);
		return result;
	}

	public int deleteAgentsFromGroup(ArrayList<String> agentNames, String groupName) {
		int result = 200;

		for (int i = 0; i < agentNames.size(); i++) {
			int tempResult = restAgent.deleteAgentFromGroup(connectionController, agentNames.get(i), groupName);

			if (tempResult > 399) {
				result = 399;
			}
		}

		return result;

	}

	public int assignAgentsFromGroup(ArrayList<String> agentNames, String groupName) {
		int result = 200;

		for (int i = 0; i < agentNames.size(); i++) {
			int tempResult = restAgent.assignAgentToGroup(connectionController, agentNames.get(i), groupName);

			if (tempResult > 399) {
				result = 399;
			}
		}

		return result;

	}

	public String getControllerUrl() {
		return connectionController.getUrl();
	}

	@SuppressWarnings("static-access")
	public boolean authenticate() {
		String result;
		try {
			result = restAgent.authenticate(connectionController);
			if (result.equals("OK")) {
				return true;
			}

		} catch (IOException e) {
			return false;
		}

		return false;
	}

	public int assignRulebookToGroup(String groupName, String rulebookName) {
		int result = restAgent.assignRulebookToGroup(connectionController, rulebookName, groupName);
		return result;
	}

	public int assignAgentToGroup(String groupName, String agentName) {
		int result = restAgent.assignAgentToGroup(connectionController, agentName, groupName);
		return result;
	}

	public int createRulebook(Rulebook rulebook) {

		int result = restAgent.createRulebook(connectionController, rulebook);
		return result;
	}

	public int deleteRulebook(String rulebookName) {
		int result = restAgent.deleteRulebook(connectionController, rulebookName);
		return result;
	}

	public int deleteAgent(ArrayList<String> selectedAgentNames) {

		int result = 200;

		for (int i = 0; i < selectedAgentNames.size(); i++) {
			int output = restAgent.deleteAgent(connectionController, selectedAgentNames.get(i));

			if (output > 399) {
				result = 400;
			}

		}

		return result;
	}

}
