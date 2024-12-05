package com.qa.MetriqAI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.MetriqAI.Utils.ElementUtil;

public class HomePage {
	
	WebDriver driver;
	ElementUtil eutil;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}
	
	private By newProjectButton = By.xpath
			("//p[text()='Create a new project file and then get your project going.']/following-sibling::a");
	private By existingProjectButton = By.xpath
			("//p[text()='Start and explore a wide range of exciting project.']/following-sibling::button");
	private By surveyListBtn = By.xpath
			("//p[text()='You can add a project to your library and explore it']/following-sibling::a");
	private By libraryText = By.xpath
			("(//h1[contains(@class,'text-xl font-semibold')])[3]");
	
	
	public void clickOnCreateSurveyButton() {
		eutil.doClickOnElement(newProjectButton);
	}
	
	public String homepageURL() {
		return eutil.getURL();
	}
	
	public SurveyListPage clickOnSurveyListButton() {
		eutil.doClickOnElement(surveyListBtn,30);
		return new SurveyListPage(driver);
	}
	
	public String text() {
		return eutil.waitdogetText(libraryText, 20);
	}
}
