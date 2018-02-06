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
package com.appdynamics.universal.session;

import java.util.HashMap;

public class Session {

	private static Session instance = null;
	private static HashMap<String, String> history = new HashMap<String, String>();

	protected Session() {

	}

	public static Session getInstance() {
		if (instance == null) {
			synchronized (Session.class) {
				if (instance == null) {
					instance = new Session();
				}
			}
		}
		return instance;

	}

	public static void addAction(String header, String action) {
		history.put(header, action);
	}

	public static void print() {

		for (String key : history.keySet()) {

		}

	}

	public HashMap<String, String> getSessionMap() {
		return history;
	}
}
