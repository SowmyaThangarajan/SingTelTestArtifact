package com.hooks;

import java.io.IOException;
import java.util.Properties;

import com.drivers.Driver;
import com.utilities.PropertiesFile;
import com.utilities.Utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	private Driver d;
	//private WebDriver webdriver;
	private PropertiesFile p;
	Properties properties;

	@Before(order=0)
	public void getProperty() {
		p = new PropertiesFile();
		properties=p.readPropertiesFile();
	}

	@Before(order=1)
	public void lauchBrwsr() {
		String brwsrNme = properties.getProperty("browser");
		d = new Driver();
		d.initializeDriver(brwsrNme);
	}
	
	@After(order=0)
	public void tearDownAftHook() {
		d = new Driver();
		Driver.tearDown();
	}
	
	@After(order=1)
	public void teardown(Scenario s) throws IOException {
		//if(s.isFailed()) {
			String sName = s.getName().replaceAll(" ", "_");
			Driver.takeScreenshot(sName);
			s.attach(System.getProperty("user.dir")+"/target/SaveResults/Screenshots/" + sName + ".jpg", "image/png", sName);
		//}
	}
}
