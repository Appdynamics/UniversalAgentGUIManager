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
 * RulebookCollection class controls a list of rulebooks
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class RulebookCollection {

	private ArrayList<Rulebook> rulebooks;

	public RulebookCollection() {

	}

	public void setRulebookCollection(ArrayList<Rulebook> rulebooks) {
		this.rulebooks = rulebooks;
	}

	public void addRulebook(Rulebook rulebook) {
		rulebooks.add(rulebook);

	}

	public ArrayList<Rulebook> getAllRulebooks() {
		return rulebooks;
	}

	public void removeRulebook(int index) {

		rulebooks.remove(index);

	}

}
