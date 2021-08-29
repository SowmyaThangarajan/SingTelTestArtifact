package com.utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

	public Properties readPropertiesFile() {
		Properties p = new Properties();
		String projectPath = System.getProperty("user.dir");
		try {
			InputStream i = new FileInputStream(projectPath + "/src/test/resources/config/configuration.properties");
			System.out.println("Property file is read");
			p.load(i);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
}
