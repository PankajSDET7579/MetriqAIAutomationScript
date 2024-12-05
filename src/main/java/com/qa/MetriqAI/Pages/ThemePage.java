package com.qa.MetriqAI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.MetriqAI.Utils.ElementUtil;
import com.qa.MetriqAI.Utils.JavaScriptUtil;
import com.qa.MetriqAI.Utils.TimeUtil;

public class ThemePage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	private JavaScriptUtil javascript;
	
	public ThemePage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
		javascript = new JavaScriptUtil(driver);
	}
	
	By selectThemeButton = By.xpath("//button[@title='Select theme']");
	By selectTheme = By.xpath("//div[contains(@class,'px-1')]/button");
	
	By previewButton = By.xpath("//button[text()='Preview']");
	By nextButton = By.xpath("//button[text()='Next']");
	
	By exitButtonInPreviewPage = By.xpath("//button[text()='Exit']");
	
	public String selectTheme(String themeName) {
		eutil.elementVisibility(selectThemeButton, 20).click();
		By theme = By.xpath("//span[text()='"+themeName+"']");
		eutil.doClickOnElement(theme);
		By selectedTheme = By.xpath("//button[@title='"+themeName+"']/span");
		String data = eutil.doGetText(selectedTheme);
		return data;
	}
	
	public boolean previewSurvey() {
		boolean flag = false;
		eutil.doClickOnElement(previewButton, 10);
		flag = eutil.elementVisibility(exitButtonInPreviewPage, 10).isDisplayed();
		return flag;
	}
	
	public DatabasePage clickOnNextButtonThemePage() {
		TimeUtil.mediumTime();
		WebElement button = eutil.elementVisibility(nextButton, 20);
		javascript.scrollToElement(button);
		button.click();
		return new DatabasePage(driver);
	}
	
}











