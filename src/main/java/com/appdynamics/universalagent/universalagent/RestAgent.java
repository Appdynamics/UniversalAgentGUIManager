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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import com.appdynamics.universal.session.Session;
import com.appdynamics.universalagent.exceptions.NoAgentsException;
import com.google.gson.Gson;

/**
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class RestAgent {

	public RestAgent() {

	}

	/**
	 * Method to collect all universal agents deployed of the specified controller
	 * 
	 * @param connectionController
	 *            (Connections details to connect to the controller)
	 * @return
	 * @throws NoAgentsException
	 *             (If there are no agents it will throw an exception)
	 */
	public String getAllUniversalAgents(ConnectionController connectionController) throws NoAgentsException {
		String constructedUrl = connectionController.urlToString();
		String getAllAgents = "universalagent/v1/user/agents/summary?output=JSON";

		String result = "";
		constructedUrl += getAllAgents;

		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());

		try {

			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb1 = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb1.append(charArray, 0, numCharsRead);
			}
			result = sb1.toString();
			int resultCode = conn.getResponseCode();

			if (result.length() < 4 && conn.getResponseCode() >= 200 && conn.getResponseCode() < 250) {
				throw new NoAgentsException("no agents registered with the controller");
			} else if (resultCode > 199 && resultCode < 399) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Get All Universal Agents ";
				String action = "curl -i -X GET -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Method to collect all groups of the specified controller
	 * 
	 * @param connectionController
	 *            (Connections details to connect to the controller)
	 * @return
	 * @throws NoAgentsException
	 *             (If there are no agents it will throw an exception)
	 */
	public String getAllGroups(ConnectionController connectionController) throws NoAgentsException {
		String constructedUrl = connectionController.urlToString();
		String getAllGroups = "universalagent/v1/user/groups";

		String result = "";
		constructedUrl += getAllGroups;

		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());

		try {

			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb1 = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb1.append(charArray, 0, numCharsRead);
			}
			result = sb1.toString();
			int resultCode = conn.getResponseCode();

			if (result.length() < 4 && resultCode >= 200 && resultCode < 250) {
				throw new NoAgentsException("no agents registered with the controller");
			} else if (resultCode > 199 && resultCode < 399) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Get All Groups ";
				String action = "curl -i -X GET -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Method to collect all rulebooks of the specified controller
	 * 
	 * @param connectionController
	 *            (Connections details to connect to the controller)
	 * @return
	 * @throws NoAgentsException
	 */
	public String getAllRulebooks(ConnectionController connectionController) throws NoAgentsException {
		String constructedUrl = connectionController.urlToString();
		String getAllRulebooks = "universalagent/v1/user/rulebooks";

		String result = "";
		constructedUrl += getAllRulebooks;

		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());

		try {

			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb1 = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb1.append(charArray, 0, numCharsRead);
			}
			result = sb1.toString();
			int resultCode = conn.getResponseCode();
			if (result.length() < 4 && resultCode >= 200 && resultCode < 250) {
				throw new NoAgentsException("no agents registered with the controller");
			} else if (resultCode > 199 && resultCode < 399) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Get All Rulebooks ";
				String action = "curl -i -X GET -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public StringBuilder getStringBuilder(ConnectionController connectionController) {
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());

		return sb;

	}

	/**
	 * Method to collect details of the specified rulebook
	 * 
	 * @param connectionController
	 * @param rulebookName
	 * @return
	 * @throws NoAgentsException
	 */
	public String getRulebookByName(ConnectionController connectionController, String rulebookName)
			throws NoAgentsException {
		String constructedUrl = connectionController.urlToString();
		String getAllRulebooks = "universalagent/v1/user/rulebooks/byName/" + rulebookName;

		String result = "";
		constructedUrl += getAllRulebooks;
		StringBuilder sb = getStringBuilder(connectionController);
		try {

			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb1 = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb1.append(charArray, 0, numCharsRead);
			}
			result = sb1.toString();
			int resultCode = conn.getResponseCode();
			if (result.length() < 4 && conn.getResponseCode() >= 200 && conn.getResponseCode() < 250) {
				throw new NoAgentsException("no agents registered with the controller");
			} else if (resultCode > 199 && resultCode < 399) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Get Rulebook By Name: " + rulebookName;
				String action = "curl -i -X GET -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Method to create or update a rulebook
	 * 
	 * @param connectionController
	 * @param rulebook
	 * @return
	 */
	public int createRulebook(ConnectionController connectionController, Rulebook rulebook) {
		String constructedUrl = connectionController.urlToString();
		String getAllRulebooks = "universalagent/v1/user/rulebooks/byName/" + rulebook.getName();
		int resultCode = 0;
		constructedUrl += getAllRulebooks;
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {
			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			Gson gson = new Gson();
			out.write(gson.toJson(rulebook));
			out.flush();
			resultCode = conn.getResponseCode();

			if (resultCode > 199 && resultCode < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Create Rulebook " + rulebook.getName();
				String action = "curl -i -X PUT -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' -H 'Accept:application/json' "
						+ url.toString() + " -d" + " '" + gson.toJson(rulebook) + "'";
				Session.addAction(header, action);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultCode;
	}

	/**
	 * Method to collect all groups of the specified controller
	 * 
	 * @param connectionController
	 *            (Connections details to connect to the controller)
	 * @return
	 * @throws NoAgentsException
	 *             (If there are no agents it will throw an exception)
	 */
	public String getGroupByName(ConnectionController connectionController, String groupName) throws NoAgentsException {
		String constructedUrl = connectionController.urlToString();
		String getAllGroups = "universalagent/v1/user/groups/byName/" + groupName;

		String result = "";
		constructedUrl += getAllGroups;

		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());

		try {

			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb1 = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb1.append(charArray, 0, numCharsRead);
			}
			result = sb1.toString();
			int resultCode = conn.getResponseCode();

			if (result.length() < 4 && resultCode >= 200 && resultCode < 250) {
				throw new NoAgentsException("no agents registered with the controller");
			} else if (resultCode > 199 && resultCode < 399) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Get group by name: " + groupName;
				String action = "curl -i -X GET -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Method to create or update existing group
	 * 
	 * @param connectionController
	 * @param group
	 * @return
	 */
	public int createOrUpdateGroup(ConnectionController connectionController, Group group) {
		String constructedUrl = connectionController.urlToString();
		String urlSuffix = "universalagent/v1/user/groups/byName/" + group.getName();
		int result = 0;
		constructedUrl += urlSuffix;
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {
			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			Gson gson = new Gson();

			out.write(gson.toJson(group));
			out.flush();
			result = conn.getResponseCode();

			if (result > 199 && result < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Create group " + group.getName();
				String action = "curl -i -X PUT -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' -H 'Accept:application/json' "
						+ url.toString() + " -d" + " '" + gson.toJson(group) + "'";
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteGroup(ConnectionController connectionController, String groupName) {
		String constructedUrl = connectionController.urlToString();
		String urlSuffix = "universalagent/v1/user/groups/byName/" + groupName;
		int result = 0;
		constructedUrl += urlSuffix;
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {
			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			result = conn.getResponseCode();

			if (result > 199 && result < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Delete group " + groupName;
				String action = "curl -i -X DELETE -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method to delete a rulebook from the specified controller
	 * 
	 * @param connectionController
	 * @param rulebookName
	 *            (The string name of the rulebook to be deleted)
	 * @return
	 */
	public int deleteRulebook(ConnectionController connectionController, String rulebookName) {
		String constructedUrl = connectionController.urlToString();
		String urlSuffix = "universalagent/v1/user/rulebooks/byName/" + rulebookName;
		int result = 0;
		constructedUrl += urlSuffix;
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {
			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			result = conn.getResponseCode();

			if (result > 199 && result < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Delete Rulebook " + rulebookName;
				String action = "curl -i -X DELETE -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Remove rulebook from group
	 * 
	 * @param connectionController
	 * @param rulebookName
	 * @return
	 */
	public int deleteRulebookFromGroup(ConnectionController connectionController, String groupName) {
		String constructedUrl = connectionController.urlToString();
		String urlSuffix = "universalagent/v1/user/rulebooks/current/" + groupName;
		int result = 0;
		constructedUrl += urlSuffix;
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {
			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			result = conn.getResponseCode();

			if (result > 199 && result < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Delete Rulebook From Group: " + groupName;
				String action = "curl -i -X DELETE -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Removes an Agent from group
	 * 
	 * @param connectionController
	 * @param rulebookName
	 * @return
	 */
	public int deleteAgentFromGroup(ConnectionController connectionController, String agentName, String groupName) {
		String constructedUrl = connectionController.urlToString();
		String urlSuffix = "universalagent/v1/user/agents/membership/" + agentName + "/groups/" + groupName;
		int result = 0;
		constructedUrl += urlSuffix;
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {
			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			result = conn.getResponseCode();

			if (result > 199 && result < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Delete Agent: " + agentName + " From Group: " + groupName;
				String action = "curl -i -X DELETE -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int assignRulebookToGroup(ConnectionController connectionController, String rulebookName, String groupName) {
		String constructedUrl = connectionController.urlToString();
		String getAllRulebooks = "universalagent/v1/user/rulebooks/current/" + groupName;
		int result = 0;
		constructedUrl += getAllRulebooks;
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {
			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			Gson gson = new Gson();
			RulebookName rulebook = new RulebookName(rulebookName);
			out.write(gson.toJson(rulebook));
			out.flush();
			result = conn.getResponseCode();

			if (result > 199 && result < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Assign  Rulebook: " + rulebook.getRuleBookName() + " to Group: " + groupName;
				String action = "curl -i -X PUT -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' -H 'Accept:application/json' "
						+ url.toString() + " -d" + " '" + gson.toJson(rulebook) + "'";
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method that retrieves all Rulebooks Associated with a selected group
	 * 
	 * @param connectionController
	 * @param groupName
	 * @return
	 * @throws NoAgentsException
	 */
	public String getRulebooksAssociatedWithGroup(ConnectionController connectionController, String groupName)
			throws NoAgentsException {
		String constructedUrl = connectionController.urlToString();
		String getAllRulebooks = "universalagent/v1/user/rulebooks/current/" + groupName;
		String result = "";
		constructedUrl += getAllRulebooks;
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {
			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb1 = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb1.append(charArray, 0, numCharsRead);
			}
			result = sb1.toString();

			int resultCode = conn.getResponseCode();

			if (result.length() < 4 && conn.getResponseCode() >= 200 && conn.getResponseCode() < 250) {
				throw new NoAgentsException("no agents registered with the controller");
			} else if (resultCode > 199 && resultCode < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Get Rulebook Associated with Group: " + groupName;
				String action = "curl -i -X GET -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json'  "
						+ url.toString();
				Session.addAction(header, action);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String getAgentsAssociatedWithGroup(ConnectionController connectionController, String groupName)
			throws NoAgentsException {
		String constructedUrl = connectionController.urlToString();
		String getAllUrl = "universalagent/v1/user/agents/?groups=" + groupName;
		String result = "";
		constructedUrl += getAllUrl;
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {
			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb1 = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb1.append(charArray, 0, numCharsRead);
			}
			int resultCode = conn.getResponseCode();
			result = sb1.toString();
			if (result.length() < 4 && conn.getResponseCode() >= 200 && conn.getResponseCode() < 250) {
				throw new NoAgentsException("no agents registered with the controller");
			} else if (resultCode > 199 && resultCode < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Get Agents Associated with Group: " + groupName;
				String action = "curl -i -X GET -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json'  "
						+ url.toString();
				Session.addAction(header, action);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static final String authenticate(ConnectionController connection) throws IOException {
		String constructedUrl = connection.urlToString() + "rest/applications";
		String result = "";
		StringBuilder sb = new StringBuilder();
		sb.append(connection.getUsername()).append("@").append(connection.getAccount()).append(":")
				.append(connection.getPassword());

		URL url = new URL(constructedUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization",
				"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
		conn.setRequestProperty("Content-type", "application/json");
		conn.setConnectTimeout(5000);
		// InputStream is = conn.getInputStream();
		// InputStreamReader isr = new InputStreamReader(is);
		result = conn.getResponseMessage();
		conn.disconnect();
		return result;

	}

	public String getAllUniversalAgentsByGroup(ConnectionController connectionController, String groupName)
			throws NoAgentsException {
		String constructedUrl = connectionController.urlToString();
		String getAllAgents = "universalagent/v1/user/agents?groups=" + groupName;

		String result = "";
		constructedUrl += getAllAgents;

		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {

			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb1 = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb1.append(charArray, 0, numCharsRead);
			}
			result = sb1.toString();
			int resultCode = conn.getResponseCode();
			if (result.length() < 4 && conn.getResponseCode() >= 200 && conn.getResponseCode() < 250) {
				throw new NoAgentsException("no agents registered with the controller");
			} else if (resultCode > 199 && resultCode < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Get Agents Associated with Group: " + groupName;
				String action = "curl -i -X GET -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json'  "
						+ url.toString();
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public int assignAgentToGroup(ConnectionController connectionController, String agentName, String groupName) {
		String constructedUrl = connectionController.urlToString();
		String getAllRulebooks = "universalagent/v1/user/agents/membership/" + agentName + "/groups/" + groupName;

		int result = 0;
		constructedUrl += getAllRulebooks;

		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());

		try {

			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");

			result = conn.getResponseCode();

			if (result > 199 && result < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Assign Agent: " + agentName + "to group: " + groupName;
				String action = "curl -i -X PUT -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' -H 'Accept:application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public int deleteAgent(ConnectionController connectionController, String agentName) {
		String constructedUrl = connectionController.urlToString();
		String urlSuffix = "universalagent/v1/user/agents/byName/" + agentName;
		int result = 0;
		constructedUrl += urlSuffix;
		StringBuilder sb = new StringBuilder();
		sb.append(connectionController.getUsername()).append("@").append(connectionController.getAccount()).append(":")
				.append(connectionController.getPassword());
		try {
			URL url = new URL(constructedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Authorization",
					"Basic " + new String(Base64.getEncoder().encode((sb.toString().getBytes()))));
			conn.setRequestProperty("Content-type", "application/json");
			result = conn.getResponseCode();

			if (result > 199 && result < 350) {
				/**
				 * Adds this command to history
				 */
				Session.getInstance();
				String header = "Delete Agent By Name " + agentName;
				String action = "curl -i -X DELETE -u ''\"$username\"'@'\"$accountname\"':'\"$password\"'' -H 'Content-type: application/json' "
						+ url.toString();
				Session.addAction(header, action);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
