package com.qa.MetriqAI.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.MetriqAI.Utils.ElementUtil;
import com.qa.MetriqAI.Utils.JavaScriptUtil;
import com.qa.MetriqAI.Utils.TimeUtil;

public class CreateProjectGeneralDetailPage {
	
	
	private WebDriver driver;
	private ElementUtil eutil;
	private JavaScriptUtil javascript;
	
	public CreateProjectGeneralDetailPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
		javascript = new JavaScriptUtil(driver);
		
	}
	
	private By projectName = By.xpath("//input[@id='name']");
	private By projectBrief = By.xpath("//textarea[@id='brief']");
	
	private By clientDropdown = By.xpath("//button[@title = 'Select Client']");
	private By clientDropdownValues = By.xpath("//span[contains(@class,'first-letter:uppercase text-left')]");
	
	private By resourceSourceType = By.xpath("//button[contains(@title,'Select Recipient Source Type')]");
	private By resourceSourceTypeValues = By.xpath("//span[contains(@class,'first-letter:uppercase text-left')]");
	
	private By industryDropdown = By.xpath("//span[text()='Select Industry']");
	private By industryDropdownValues = By.xpath("//ul[contains(@id,'headlessui-listbo')]/li");
	
	private By researchArea = By.xpath("//span[text()='Select Research Area']");
	private By researchAreaDrodpwonvalues = By.xpath("//ul[contains(@id,'headlessui-listbox')]/li");
	
	private By marketType = By.xpath("//button[@title='Select Market']");
	private By marketTypeDropdownValues = By.xpath("//span[@class='first-letter:uppercase text-left']");
	
	private By surveyStructure = By.xpath("//span[text()='Select Structure of Survey']");
	private By surveyStructureDropdown = By.xpath("//span[@class='first-letter:uppercase text-left']");
	
	
	private By cancelButton = By.xpath("//button[text()='Cancel']");
	private By nextButton = By.xpath("//button[text()='Continue']");
	
	public void fillInGeneralDetails(String projectNameValue,String projectDesc) {
		eutil.doSendKeys(projectName,10, projectNameValue);
		eutil.doSendKeys(projectBrief,10 , projectDesc);
		selectClient();
		selectResourceType();
		selectIndustry();
		javascript.scrollToElement(nextButton, 10);
		selectResearchArea();
		selectmarketType();
		selectSurveyStructure();
	}
	
	public void selectClient() {
		
		TimeUtil.defaultTime();
		eutil.elementVisibility(clientDropdown, 10);
		eutil.doClickOnElement(clientDropdown);
		TimeUtil.defaultTime();
		List<WebElement>  list = eutil.allElementsVisibility(clientDropdownValues, 20);
		for(WebElement option : list) {
			option.click();
			break;
		}
	}
	public void selectResourceType()  {
		eutil.elementVisibility(resourceSourceType, 20);
		eutil.doClickOnElement(resourceSourceType);
		TimeUtil.defaultTime();
		//eutil.allElementsVisibility(resourceSourceTypeValues, 20);
		List<WebElement>  list = eutil.allElementsVisibility(resourceSourceTypeValues, 20);
		for(WebElement option : list) {
			option.click();
			break;
		}
	}
	
	public void selectIndustry() {
		eutil.elementVisibility(industryDropdown, 20);
		eutil.doClickOnElement(industryDropdown);
		TimeUtil.defaultTime();
		//eutil.allElementsVisibility(industryDropdownValues, 20);
		List<WebElement>  list = eutil.allElementsVisibility(industryDropdownValues, 20);
		for(WebElement option : list) {
			option.click();
			break;
		}
	}
	public void selectResearchArea(){
		eutil.elementVisibility(researchArea, 20);
		eutil.doClickOnElement(researchArea);
		TimeUtil.defaultTime();
		//eutil.allElementsVisibility(researchAreaDrodpwonvalues, 20);
		List<WebElement>  list = eutil.allElementsVisibility(researchAreaDrodpwonvalues, 20);
		for(WebElement option : list) {
			option.click();
			break;
		}
	}
	
	
	public void selectmarketType(){
		eutil.elementVisibility(marketType, 20);
		eutil.doClickOnElement(marketType);
		TimeUtil.defaultTime();
		//eutil.allElementsVisibility(marketTypeDropdownValues, 20);
		List<WebElement>  list = eutil.allElementsVisibility(marketTypeDropdownValues,20);
		for(WebElement option : list) {
			option.click();
			break;
		}
	}
	
	public void selectSurveyStructure() {
		eutil.elementVisibility(surveyStructure, 20);
		eutil.doClickOnElement(surveyStructure);
		TimeUtil.defaultTime();
		//eutil.allElementsVisibility(surveyStructureDropdown, 20);
		List<WebElement>  list = eutil.allElementsVisibility(surveyStructureDropdown, 20);
		for(WebElement option : list) {
			option.click();
			break;
		}
	}
	
	public CreateProjectGeographyPage clickOnNextButton() {
		eutil.doClickOnElement(nextButton);
		TimeUtil.mediumTime();
		return new CreateProjectGeographyPage(driver);
	}
	
	public void discardGeneralDetailsPage() {
		eutil.doClickOnElement(cancelButton);
	}
	
	public boolean isNextButtonEnabled() {
		return eutil.elementVisibility(nextButton, 10).isEnabled();
	}
	
}
