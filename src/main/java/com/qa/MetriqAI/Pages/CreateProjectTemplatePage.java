package com.qa.MetriqAI.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.MetriqAI.Utils.ElementUtil;

public class CreateProjectTemplatePage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	public CreateProjectTemplatePage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}
	
	private By templateTiles = By.xpath("//p[@class='px-2 text-sm']");
	private By blankSurveyTile = By.xpath("(//div[@class='grow']//h2)[1]");
	private By customerExperienceTile = By.xpath("(//p[@class='px-2 text-sm'])[3]");

	public String CreateProjectTemplatePageURL() {
		return eutil.getURL();
	}
	
	public int templateCount() {
		System.out.println("*******************");
		List<WebElement> list = eutil.getElements(templateTiles,30);
		for(WebElement element: list) {
			System.out.println(element.getText());
		}
		return list.size();
	}
	
	public boolean isBlankSurveyTileEnabled() {
		return eutil.elementVisibility(blankSurveyTile, 10).isDisplayed();
	}
	
	public boolean isCustomerExperienceTileEnabled() {
		return eutil.elementVisibility(customerExperienceTile, 10).isDisplayed();
	}
	
	public CreateProjectGeneralDetailPage clickCustomerExperienceTemplate() {
		eutil.doClickOnElement(customerExperienceTile,10);
		return new CreateProjectGeneralDetailPage(driver);
	}
}
