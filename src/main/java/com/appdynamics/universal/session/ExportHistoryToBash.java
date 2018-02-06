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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

public class ExportHistoryToBash {

	public void export(HashMap<String, String> actions, File selectedDirectory) {
		StringBuilder builder = new StringBuilder();
		Session session = Session.getInstance();

		HashMap<String, String> sessionMap = actions;

		builder.append("#!/bin/bash/");
		builder.append(System.getProperty("line.separator"));

		builder.append("#Script Arguments to authenticate User while executing script");
		builder.append(System.getProperty("line.separator"));
		builder.append("username=$1");
		builder.append(System.getProperty("line.separator"));
		builder.append("accountname=$2");
		builder.append(System.getProperty("line.separator"));
		builder.append("password=$3");
		builder.append(System.getProperty("line.separator"));

		for (String key : sessionMap.keySet()) {

			String header = "#" + key;
			builder.append(header);
			builder.append(System.getProperty("line.separator"));
			builder.append(sessionMap.get(key));
			builder.append(System.getProperty("line.separator"));
		}

		String fileName = selectedDirectory.getPath();

		StringBuilder pathBuilder = new StringBuilder();

		pathBuilder.append(fileName);
		pathBuilder.append(File.separator);
		pathBuilder.append("history.sh");

		String path = pathBuilder.toString();

		Writer writer = null;

		try {

			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
			writer.write(builder.toString());
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */}
		}

	}

}
