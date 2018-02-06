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

/**
 * Class ConnectionController holds all information required to establish a
 * connection with the AppDynamics Controller
 * 
 * @author nikolaos.papageorgiou
 *
 */
public class ConnectionController {

	private String username;
	private String password;
	private String url;
	private String port;
	private String account;
	private boolean isSSL;

	private String prefix;

	public ConnectionController() {

	}

	public ConnectionController(String username, String password, String url, String port, String account,
			boolean isSSL) {
		super();
		this.username = username;
		this.password = password;
		this.url = url;
		this.port = port;
		this.account = account;
		this.isSSL = false;
		this.prefix = "http://";
	}

	public boolean isSSL() {
		return isSSL;
	}

	public void setSSL(boolean isSSL) {
		this.isSSL = isSSL;
		if (this.isSSL) {
			setPrefix("https://");

		} else {
			setPrefix("http://");
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String urlToString() {
		String constructedUrl = "";
		constructedUrl = constructedUrl + this.prefix;
		constructedUrl = constructedUrl + this.url;
		constructedUrl = constructedUrl + ":";
		constructedUrl = constructedUrl + this.port;
		constructedUrl = constructedUrl + "/controller/";

		return constructedUrl;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public boolean authenticate() {
		String result;
		try {
			result = RestAgent.authenticate(this);
			if (result.equals("OK")) {
				return true;
			}

		} catch (IOException e) {
			return false;

		}

		return false;
	}

	@Override
	public String toString() {
		return "ConnectionController [username=" + username + ", password=" + password + ", url=" + url + ", port="
				+ port + ", account=" + account + ", isSSL=" + isSSL + ", prefix=" + prefix + "]";
	}

}
