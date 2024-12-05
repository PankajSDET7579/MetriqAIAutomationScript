package com.qa.MetriqAI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.MetriqAI.Utils.ElementUtil;
import com.qa.MetriqAI.Utils.JavaScriptUtil;
import com.qa.MetriqAI.Utils.TimeUtil;

public class PublishPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	private JavaScriptUtil javaScript;
	private SurveyListPage surveyListPage;
	
	public PublishPage(WebDriver driver) {
		this.driver= driver;
		eutil = new ElementUtil(driver);
		javaScript = new JavaScriptUtil(driver);
		surveyListPage = new SurveyListPage(driver);
	}
	
	By publishButton = By.xpath("//button[text()='Publish']");
	
	public String getCurrentUrl() {
		return eutil.getURL();
	}
	
	public boolean clickOnPublishPageNextButton() {
		
			
			boolean flag = false;
			TimeUtil.shortTime();
			eutil.elementVisibility(publishButton, 20).click();
			flag = eutil.URLContains("workflow/project/list", 20);
			return flag;
		
	}

}
