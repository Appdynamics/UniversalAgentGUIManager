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

import com.google.gson.Gson;

/**
 * Class GroupJSON responsible to transform JSON objects to Java objects and
 * vice versa
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class GroupJSON {

	public ArrayList<Group> convertJSON(String jsonStr) {
		ArrayList<Group> groupArrayList = new ArrayList<Group>();
		Gson gson = new Gson();
		Group groups[] = gson.fromJson(jsonStr, Group[].class);
		for (int i = 0; i < groups.length; i++) {
			groupArrayList.add(groups[i]);
		}
		return groupArrayList;
	}

	public Group convertSignleGroupJSON(String jsonStr) {
		Gson gson = new Gson();
		Group group = gson.fromJson(jsonStr, Group.class);
		return group;

	}
}
