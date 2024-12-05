package com.qa.MetriqAI.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;

import com.qa.MetriqAI.Errors.AppError;
import com.qa.MetriqAI.Exceptions.BrowserException;
import com.qa.MetriqAI.Exceptions.FrameworkException;
import com.qa.MetriqAI.Logger.Log;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();

	public WebDriver initBrowser(Properties prop) {

		String browserName = prop.getProperty("browser");
		Log.info("Browser name is : " + browserName);

		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			threadLocal.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;

		case "firefox":
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			threadLocal.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;

		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			threadLocal.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
			Log.error("Please pass the right browser name" + browserName);
			throw new BrowserException("Please pass the right browser name");

		}
		getDriver().get(prop.getProperty("devURL"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();

		return getDriver();
	}

	public static WebDriver getDriver() {
		return threadLocal.get();
	}

	public Properties initProperties() {

		FileInputStream fis = null;
		prop = new Properties();
		String envName = System.getProperty("env");

		try {
			if (envName == null) {
				Log.info("No env is giv...hence running on Dev Env....");
				fis = new FileInputStream(".\\src\\test\\resource\\config\\config.properties");

			} else {
				switch (envName.toLowerCase().trim()) {
				case "dev":
					fis = new FileInputStream(".\\src\\test\\resource\\config\\config.properties");
					break;
				case "prod":
					fis = new FileInputStream(".\\src\\test\\resource\\config\\config.prod.properties");
					break;
				case "qa":
					fis = new FileInputStream(".\\src\\test\\resource\\config\\config.qa.properties");
					break;

				default:
					// System.out.println("Please pass the right environment name....." + envName);
					Log.error("Please pass the right environment name....." + envName);
					throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND + " : " + envName);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenShot(String methodName) {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+ "/screenshot/" + methodName + "_" +
						System.currentTimeMillis() + ".png";
		
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
