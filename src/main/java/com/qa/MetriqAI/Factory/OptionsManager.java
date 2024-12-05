package com.qa.MetriqAI.Factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.MetriqAI.Logger.Log;

public class OptionsManager {
	
	private Properties prop;
	
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop) {
		this.prop= prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			Log.info("Running chrome in incognito mode");
			co.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running chrome in headless mode");
			co.addArguments("--headless");
		}
		return co;
	}
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			Log.info("Running firefox in incognito mode");
			fo.addArguments("--private");
		}
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running firefox in headless mode");
			fo.addArguments("--headless");
		}
		return fo;
	}
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			Log.info("Running edge in incognito mode");
			eo.addArguments("incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running edge in headless mode");
			eo.addArguments("headless");
		}
		return eo;
	}
	

}
