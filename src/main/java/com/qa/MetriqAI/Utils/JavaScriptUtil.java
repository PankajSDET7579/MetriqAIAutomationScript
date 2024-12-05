package com.qa.MetriqAI.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver= driver;
		eutil = new ElementUtil(driver);
	}
	
	public void scrollToElement(By locator, int timeUnit) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", eutil.getElement(locator));
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public void flash(WebElement element) {
		String bgColor = element.getCssValue("background-color");
		for(int i =0;i<5;i++) {
			changeColor("rgb(0,200,0)",element);
			changeColor(bgColor, element);
		}
	}

	private void changeColor(String color, WebElement element) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor = '"+ color + "'" , element);
		
		TimeUtil.shortTime();
	}
}
