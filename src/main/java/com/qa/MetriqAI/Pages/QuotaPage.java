package com.qa.MetriqAI.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.MetriqAI.Utils.ElementUtil;
import com.qa.MetriqAI.Utils.JavaScriptUtil;
import com.qa.MetriqAI.Utils.TimeUtil;

public class QuotaPage {

	private WebDriver driver;
	private ElementUtil eutil;
	private JavaScriptUtil javaScript;
	private SurveyListPage surveyListPage;
	private PublishPage publishPage;

	public QuotaPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
		javaScript = new JavaScriptUtil(driver);
		surveyListPage = new SurveyListPage(driver);
		publishPage = new PublishPage(driver);
	}

	By addQuotaIcon = By.xpath("//*[local-name()='svg' and @height='30']");
	// By checkBoxes = By.xpath("//input[@type='checkbox']");
	By questionsAdded = By.xpath(
			"//div[text()='Quota Variable']/parent::div/following-sibling::div//div[contains(@class,'group-data-[hover]:text-figmaBlue/80')]");

	By checkBoxes = By.xpath("//div[contains(@class,'flex justify-between items-center ')]/following-sibling::div");
	By variableName = By.xpath("//input[@placeholder='Enter Variable Name']");
	By quotaTextBox = By.xpath("//input[@placeholder='Enter Quota']");

	By nextButton = By.xpath("//button[text()='Save & Next']");

	By clickOutside = By.xpath("//div[contains(@class,'flex min-h-full items-center')]");
	By clickOnArrow = By.xpath("//*[local-name()='svg' and @class='  ']");

	public void enterQuota() {
		boolean flag = false;
		eutil.elementVisibility(addQuotaIcon, 10).click();

		List<WebElement> checkboxes = eutil.allElementsVisibility(checkBoxes, 20);
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
		}
		int j = 0;
		eutil.getElement(clickOutside).click();

		List<WebElement> element = eutil.allElementsVisibility(questionsAdded, 10);
		for (WebElement data : element) {
			data.click();
			eutil.elementVisibility(variableName, 10).sendKeys("Test" + j);
			List<WebElement> quotas = eutil.allElementsVisibility(quotaTextBox, 10);
			for (WebElement e : quotas) {
				e.sendKeys("2");
			}
			data.click();
			j++;
		}

	}

	public PublishPage clickOnQuotaPageNextButton() {
		eutil.elementVisibility(nextButton, 10).click();
		return new PublishPage(driver);
	}

}
