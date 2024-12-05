package com.qa.MetriqAI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.MetriqAI.Utils.ElementUtil;
import com.qa.MetriqAI.Utils.JavaScriptUtil;
import com.qa.MetriqAI.Utils.TimeUtil;

public class CreateSurveySelectMetricesPage {
	
	
	private WebDriver driver;
	private ElementUtil eutil;
	private JavaScriptUtil javascript;
	
	public CreateSurveySelectMetricesPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
		javascript = new JavaScriptUtil(driver);
	}
	
	By continueButton = By.xpath("//button[text()='Continue']");
	
	public void selectMetrics(String metricName) {
		By metrics = By.xpath("//span[text()='"+metricName+"']/preceding-sibling::button");
		
		eutil.elementVisibility(metrics, 20).click();
		//javascript.scrollToElement(metric, 20);
	}
	
	public QuestionBuilderPage clickOnMetricsContinueButton() {
		eutil.doClickOnElement(continueButton,10);
		javascript.scrollToElement(continueButton,10);
		return new QuestionBuilderPage(driver);
	}
	
	public String getMetricsPageURL() {
		return eutil.getURL();
	}
	
	public boolean isCheckBoxSelected(String metricName) {
		boolean flag = false;
		By metrics = By.xpath("//span[text()='"+metricName+"']/preceding-sibling::button");
		javascript.scrollToElement(metrics, 10);
		String result = eutil.getElement(metrics, 20).getAttribute("aria-checked");
		//TimeUtil.applyWait(1000);
		if(result.equalsIgnoreCase("true")) {
			flag = true;
		}
		else {
			flag = false;
		}
		return flag;
	}
	
	public boolean isContinueButtonDisplayed() {
		return eutil.getElement(continueButton,10).isDisplayed();
	}
	
}
