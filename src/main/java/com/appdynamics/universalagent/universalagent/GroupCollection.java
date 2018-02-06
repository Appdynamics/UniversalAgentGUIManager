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

/**
 * GroupCollection class controls a list of groups
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class GroupCollection {

	private ArrayList<Group> groups;

	public GroupCollection() {

	}

	public GroupCollection(ArrayList<Group> groups) {
		this.groups = groups;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}

	public void addGroup(Group group) {
		groups.add(group);
	}

	public Group getGroup(int index) {
		return groups.get(index);
	}

	public Group getGroupByName(String name) {
		for (Group group : groups) {
			if (group.getName().equals(name)) {
				return group;
			}
		}
		return null;
	}
}
