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
package com.appdynamics.universalagent.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Class ItemTypePredicate identifies and returns the class that should be used
 * to decrypt the json rule as this has been given from the json file
 * 
 * @author nikolaos.papageorgiou
 *
 */

public class ItemTypePredicate extends RuntimeTypeAdapterPredicate {

	public ItemTypePredicate() {
	}

	@Override
	public String process(JsonElement element) {
		JsonObject obj = element.getAsJsonObject();

		if (obj.toString().contains("\"monitor\":\"java\"")) {
			return "JavaRule";
		} else if (obj.toString().contains("\"monitor\":\"machine\"")) {
			return "MachineRule";
		} else if (obj.toString().contains("\"monitor\":\"analytics\"")) {
			return "AnalyticsRule";
		} else if (obj.toString().contains("\"monitor\":\"universal\"")) {
			return "UniversalRule";
		} else if (obj.toString().contains("\"monitor\":\"dotnet\"")) {
			return "DotNetRule";
		} else if (obj.toString().contains("\"monitor\":\"network\"")) {
			return "NetworkRule";
		}

		return "JavaRule";
	}
}
