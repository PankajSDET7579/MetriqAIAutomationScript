package com.qa.MetriqAI.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.MetriqAI.Utils.ElementUtil;
import com.qa.MetriqAI.Utils.TimeUtil;

public class EmailPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	public EmailPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}
	
	By displayName = By.xpath("(//input[@placeholder='Enter display name'])[1]");
	By displayNameReply = By.xpath("(//input[@placeholder='Enter display name'])[2]");
	By enterSenderEmail = By.xpath("//input[@placeholder='Enter sender email']");
	By replyToEmail = By.xpath("//input[@placeholder='Enter reply to email']");
	
	By selectDomain1 = By.xpath("(//button[@title='Select Domain'])[1]");
	By selectDomain2 = By.xpath("(//button[@title='Select Domain'])[2]");
	
	By domainDropdownValues = By.xpath("//button[@role='menuitem']");
	By subject = By.xpath("//input[@placeholder='Enter subject']");
	By description = By.xpath("//div[@data-placeholder='Enter your email body here...']");
	By submitButton = By.xpath("//button[@type='submit']");
	
	public boolean composeEmail() {
		
		eutil.doSendKeys(displayName, 10, "no-reply");
		eutil.doSendKeys(displayNameReply, "no-reply");
		eutil.doSendKeys(enterSenderEmail, "no-reply");
		eutil.doSendKeys(replyToEmail, "no-reply");
		
		eutil.doClickOnElement(selectDomain1, 10);
		List<WebElement> options=eutil.allElementsVisibility(domainDropdownValues, 10);
		for(WebElement option: options) {
			String value = option.getText();
			if(value.equals("mrim-dev.apps-evalueserve.com")) {
				option.click();
				break;
			}
		}
		
		TimeUtil.defaultTime();
		eutil.doClickOnElement(selectDomain1, 10);
		options=eutil.allElementsVisibility(domainDropdownValues, 10);
		System.out.println(options.size());
		for(WebElement option: options) {
			String value = option.getText();
			if(value.equals("mrim-dev.apps-evalueserve.com")) {
				option.click();
				break;
			}
		}
		eutil.doSendKeys(subject, "Automated Survey");
		eutil.doSendKeys(description, "Hi, \n  Please take out your valuable time to fill in the survey. \n");
		
//		Actions act = new Actions(driver);
//		act.moveToElement(eutil.getElement(description))
//			.keyDown("#")
//				.sendKeys("surveyLink")
//					.build()
//						.perform();
		eutil.pressKey(description, "#", "surveyLink");
			
		boolean flag = eutil.elementVisibility(submitButton, 10).isDisplayed();
		return flag;
	}
	
	public QuotaPage clickOnEmailSubmitButton() {
		eutil.elementVisibility(submitButton, 10).click();
		return new QuotaPage(driver);
	}
	
	
}
