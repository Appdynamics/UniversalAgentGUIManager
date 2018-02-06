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

import java.util.HashMap;
import java.util.List;

import com.appdynamics.universalagent.rules.Rule;
import com.google.gson.Gson;

/**
 * Class Rulebook POJO class to hold information of a Rulebook
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class Rulebook {
	// Name for this rulebook
	private String name;
	// An optional description for this rulebook.
	private String comments;
	private String version;
	private String controller_version;
	// This object contains runtime agent properties that apply to all runtime
	// agents in the rulebook.
	// The config object notably contains connection properties for the Controller.
	// See Controller Connection Fields for more information.
	private RulebookConfig config;
	private List<Rule> rules;

	public Rulebook() {

	}

	public Rulebook(String name, String comments, String version, String controllerVersion, RulebookConfig config,
			List<Rule> rules) {
		super();
		this.name = name;
		this.comments = comments;
		this.version = version;
		this.controller_version = controllerVersion;
		this.config = config;
		this.rules = rules;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getControllerVersion() {
		return controller_version;
	}

	public void setControllerVersion(String controllerVersion) {
		this.controller_version = controllerVersion;
	}

	public RulebookConfig getConfig() {
		return config;
	}

	public void setConfig(RulebookConfig config) {
		this.config = config;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules2) {
		this.rules = rules2;
	}

	public String rulesAsString() {
		String s = "";
		for (Rule rule : rules) {
			s += rule.toString();
		}
		return s;
	}

	@Override
	public String toString() {
		return "Rulebook [name=" + name + ", comments=" + comments + ", version=" + version + ", controllerVersion="
				+ controller_version + ", config=" + config.toString() + ", rules=" + rulesAsString() + "]";
	}

	public HashMap<String, String> getInstanciatedAttributes() {

		if (config != null) {
			return config.getInstanciatedAttributes();
		} else {
			return new HashMap<String, String>();
		}

	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
