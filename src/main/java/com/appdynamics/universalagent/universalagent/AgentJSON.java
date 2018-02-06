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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Class AgentJSON responsible to transform JSON objects to Java objects and
 * vice versa
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class AgentJSON {

	public ArrayList<Agent> convertJSON(String jsonStr) {
		ArrayList<Agent> agentsList = new ArrayList<Agent>();
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonStr);
		Gson gson = new Gson();

		for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
			String serverKey = entry.getKey();
			Agent agent = gson.fromJson(entry.getValue(), Agent.class);
			agent.setAgentName(serverKey);
			agentsList.add(agent);
		}

		return agentsList;

	}

	public ArrayList<Agent> parseUAAgents(String jsonString) {
		ArrayList<Agent> universalAgents = new ArrayList<Agent>();
		jsonString = jsonString.substring(1, jsonString.length() - 1);
		jsonString = jsonString.replace("\"", "");
		String agents[] = jsonString.split("(?<=},)");

		for (int i = 0; i < agents.length; i++) {
			char lastChar = agents[i].charAt(agents[i].length() - 1);
			if (lastChar == ',') {
				agents[i] = agents[i].substring(0, agents[i].length() - 1);

			}
		}
		for (int i = 0; i < agents.length; i++) {
			String agent = agents[i];
			String agentName = agent.split("\\:\\{")[0];
			String agentId = "";
			String agentVersion = "";
			String agentRulebook = "";
			String pattern = "(\\{)(.*)(\\})";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(agent);
			String agentProperties = "";

			if (m.find()) {
				agentProperties = m.group(0);
			}

			agentProperties = agentProperties.substring(1, agentProperties.length() - 1);

			String agentProps[] = agentProperties.split(",");

			if (agentProps.length > 2) {

				agentId = agentProps[0].split(":")[1];
				agentVersion = agentProps[1].split(":")[1];
				agentRulebook = agentProps[2].split(":")[1];

			}

			Agent universalAgent = new Agent();

			universalAgent.setAgentId(agentId);
			universalAgent.setAgentName(agentName);
			universalAgent.setRuleBookName(agentRulebook);
			universalAgent.setVersion(agentVersion);

			universalAgents.add(universalAgent);

		}

		return universalAgents;
	}

}
