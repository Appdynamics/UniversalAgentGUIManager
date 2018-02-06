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

import com.appdynamics.universalagent.adapter.ItemTypePredicate;
import com.appdynamics.universalagent.adapter.RuntimeTypeAdapterFactory;
import com.appdynamics.universalagent.rules.AnalyticsRule;
import com.appdynamics.universalagent.rules.DotNetRule;
import com.appdynamics.universalagent.rules.JavaRule;
import com.appdynamics.universalagent.rules.MachineRule;
import com.appdynamics.universalagent.rules.NetworkRule;
import com.appdynamics.universalagent.rules.Rule;
import com.appdynamics.universalagent.rules.UniversalRule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Class RulebookJSON responsible to transform JSON objects to Rulebook objects
 * and vice versa
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class RulebookJSON {

	public ArrayList<Rulebook> convertJSON(String jsonStr) {
		ArrayList<Rulebook> rulebooksList = new ArrayList<Rulebook>();
		Gson gson = new Gson();
		Rulebook rulebooks[] = gson.fromJson(jsonStr, Rulebook[].class);
		for (int i = 0; i < rulebooks.length; i++) {
			rulebooksList.add(rulebooks[i]);
		}
		return rulebooksList;
	}

	public Rulebook convertSingleRulebook(String jsonStr) {
		RuntimeTypeAdapterFactory<Rule> itemAdapter = RuntimeTypeAdapterFactory.of(Rule.class, new ItemTypePredicate())
				.registerSubtype(JavaRule.class).registerSubtype(MachineRule.class).registerSubtype(AnalyticsRule.class)
				.registerSubtype(UniversalRule.class).registerSubtype(DotNetRule.class)
				.registerSubtype(NetworkRule.class);

		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().registerTypeAdapterFactory(itemAdapter)
				.create();

		Rulebook rulebook = gson.fromJson(jsonStr, Rulebook.class);

		return rulebook;
	}

}
