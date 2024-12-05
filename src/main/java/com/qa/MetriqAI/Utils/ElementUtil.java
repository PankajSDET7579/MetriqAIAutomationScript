package com.qa.MetriqAI.Utils;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public String fetchTitle() {
		return driver.getTitle();
	}

	public WebElement getElement(By locator,int timeUnit) {
		WebElement element = null;
		try {
			element = elementVisibility(locator, timeUnit);
		} catch (Exception e) {
			System.out.println("Unable to find such element" + locator);
			e.printStackTrace();
		}
		return element;
	}
	
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Unable to find such element" + locator);
			e.printStackTrace();
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> element = null;
		try {
			element = getElements(locator);
		} catch (Exception e) {
			System.out.println("Unable to find such element" + locator);
			e.printStackTrace();
		}
		return element;
	}
	
	public List<WebElement> getElements(By locator,int timeUnit) {
		List<WebElement> element = null;
		try {
			element = allElementsVisibility(locator, timeUnit);
		} catch (Exception e) {
			System.out.println("Unable to find such element" + locator);
			e.printStackTrace();
		}
		return element;
	}

	public String getURL() {
		return driver.getCurrentUrl();
	}

	public boolean URLContains(String urlFraction, int timeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeUnit));
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}
	
	public WebElement elementVisibility(By locator, int timeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeUnit));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public List<WebElement> allElementsVisibility(By locator,int timeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeUnit));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public List<WebElement> allElementsPresence(By locator,int timeUnit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeUnit));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public void doClickOnElement(By locator, int timeUnit) {
		getElement(locator,timeUnit).click();
	}
	
	public void doClickOnElement(By locator) {
		getElement(locator).click();
	}
	
	
	public String waitdogetText(By locator,int timeUnit) {
		return elementVisibility(locator, timeUnit).getText();
	}
	
	public String doGetText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	public void doSendKeys(By locator,int timeUnit, String value) {
		getElement(locator,timeUnit).sendKeys(value);
	}
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	//**********Use of Actions Class************************************************
	
	public void pressKey(By locator,String key, String word) {
		
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator))
			.keyDown(key)
				.sendKeys(word)
					.build()
						.perform();
		
	}

}
