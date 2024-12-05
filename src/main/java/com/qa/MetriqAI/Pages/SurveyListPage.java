package com.qa.MetriqAI.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.MetriqAI.Exceptions.ElementException;
import com.qa.MetriqAI.Utils.ElementUtil;

public class SurveyListPage {

	private WebDriver driver;
	private ElementUtil eutil;

	public SurveyListPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	private By surveyListWelcomeText = 
			By.xpath("//section[contains(@class,'flex flex-grow flex-col')]/h1");
	private By filterButtons = 
			By.xpath("//span[contains(@class,'first-letter:uppercase whitespace-nowrap text-ellipsis overflow-hidden undefined')]");
	private By searchSurveyBar = 
			By.xpath("//input[@placeholder='Search...']");
	private By searchSurveyList =
			By.xpath("//a[@class='text-primary']");
	private By surveyStatus = 
			By.xpath("//a[@class='text-primary']//ancestor::td/following-sibling::td[5]");
	private By createSurveyButton=
			By.xpath("//button[normalize-space(text())='Create Project']");
	
	private By allProjectNamesInList =
			By.xpath("//a[@class='text-primary']");
	
	public String getWelcomeText() {
		String data = eutil.doGetText(surveyListWelcomeText);
		System.out.println("Survey List screen text : "+ data);
		return data;
	}

	public String getSurveyListPageURL() {
		String url = eutil.getURL();
		System.out.println("Survey list url : " +url);
		return url;
	}

	public List<String> filterButtonsExist() {
		List<WebElement> filterslist = eutil.allElementsVisibility(filterButtons, 10);
		List<String> filterList = new ArrayList<String>();
		for(WebElement element: filterslist) {
			String button = element.getText();
			filterList.add(button);
		}
		return filterList;
	}
	
	public List<String> doSearch(String keyword) {
		eutil.doSendKeys(searchSurveyBar,10, keyword);
		List<WebElement> results=eutil.allElementsVisibility(searchSurveyList, 20);
		
		List<String> resultData = new ArrayList<String>();
		
		for(WebElement result: results) {
			 String data= result.getText();
			 resultData.add(data);
		}
		return resultData;
	}
	
	public boolean isCreateSurveyButtonVisible() {
		return eutil.elementVisibility(createSurveyButton, 10).isDisplayed();
	}
	
	public CreateProjectTemplatePage clickOnCreateProjectButton() {
		eutil.doClickOnElement(createSurveyButton);
		return new CreateProjectTemplatePage(driver);
	}
	
	public String checkStausOfSurvey(String projectName) {
		By status =By.xpath("//a[text()='"+projectName+"']/ancestor::td/following-sibling::td[5]");
		String surveyStatus = eutil.elementVisibility(status, 10).getText();
		return surveyStatus;
	}
	
	public ProjectDetailPage clickOnProjectName(String projectName) {
		List<WebElement> projectNamesList = eutil.allElementsVisibility(allProjectNamesInList, 10);
		for(WebElement name: projectNamesList) {
			if(name.equals(projectName)) {
				name.click();
				break;
			}
			throw new ElementException("No such Project found in first page");
		}
		return new ProjectDetailPage();	
	}

}
