package com.qa.MetriqAI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.MetriqAI.Exceptions.ElementException;
import com.qa.MetriqAI.Utils.ElementUtil;
import com.qa.MetriqAI.Utils.TimeUtil;

public class LoginPage {
	
	WebDriver driver;
	ElementUtil eutil;
	
	private By username= By.cssSelector("input#username");
	private By password= By.cssSelector("input#password");
	private By loginBtn= By.cssSelector("button[type='submit']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}
	
	public String currentURL() {
		return eutil.getURL();
	}
	
	public HomePage doLogin(String uname, String userpwd) {
		eutil.getElement(username,20).sendKeys(uname);
		eutil.getElement(password,20).sendKeys(userpwd);
		eutil.getElement(loginBtn).click();
		return new HomePage(driver);
	}

}
