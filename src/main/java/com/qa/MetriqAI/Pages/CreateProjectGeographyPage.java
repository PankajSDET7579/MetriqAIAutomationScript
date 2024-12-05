package com.qa.MetriqAI.Pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.MetriqAI.Exceptions.ElementException;
import com.qa.MetriqAI.Utils.ElementUtil;
import com.qa.MetriqAI.Utils.TimeUtil;

public class CreateProjectGeographyPage {

	private WebDriver driver;
	private ElementUtil eutil;

	public CreateProjectGeographyPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	private By geographyDropdown = By.xpath("(//button[contains(@id,'headlessui-menu-button')])[2]");
	private By geographyDropValues = By.xpath("//div[contains(@id,'headlessui-menu-items')]//button");
	private By customerDatabaseTextBox = By.xpath("//input[@placeholder ='Enter Database Size']");
	private By customerSampleSizeTextBox = By.xpath("//input[@placeholder ='Enter Target Sample Size']");
	private By continueButton = By.xpath("//button[text()='Continue']");

	private By countryValueCheck = By.xpath("//button[@title]");

	public void fillInGeographyDetails(String countryName, int databaseSize, int sampleSize) {

		
		eutil.elementVisibility(geographyDropdown, 20);
		eutil.doClickOnElement(geographyDropdown);
		selectCountry(countryName);
		eutil.doSendKeys(customerDatabaseTextBox, String.valueOf(databaseSize));
		eutil.doSendKeys(customerSampleSizeTextBox, String.valueOf(sampleSize));
	}

	public CreateSurveySelectMetricesPage clickOnGeographyContinueButton() {
		eutil.doClickOnElement(continueButton);
		return new CreateSurveySelectMetricesPage(driver);
	}

	public boolean continueButtonIsEnabled() {
		return eutil.elementVisibility(continueButton, 10).isDisplayed();
	}

	public String geographyPageURL() {
		return eutil.getURL();
	}

	public String getCountryValue() {
		return eutil.getElement(countryValueCheck).getAttribute("title");
	}

	public void selectCountry(String countryName) {
	
		List<WebElement> list = eutil.allElementsVisibility(geographyDropValues,20);

		for (WebElement data : list) {
			String value = data.getText();
			if (value.equals(countryName)) {
				System.out.println(value);
				data.click();
				break;
			}
		}
	}

}
