package com.qa.MetriqAI.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.MetriqAI.Utils.ElementUtil;
import com.qa.MetriqAI.Utils.TimeUtil;

public class DatabasePage {

	private WebDriver driver;
	private ElementUtil eutil;

	public DatabasePage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	
	By rowTextbox = By.xpath("//div[contains(@class,'max-h-[40vh]')]/div[contains(@class,'grid')]/div");
	By name = By.xpath("//input[@placeholder='Enter Name']");
	By location = By.xpath("//button[@title='Select Location']");
	By countries = By.xpath("//button[@type='button' and @role='menuitem']/span");
	By email = By.xpath("//input[@placeholder='Enter Email']");
	By companyDropdown = By.xpath("//button[@aria-haspopup='listbox']");
	By companyDropdownValues = By.xpath("//ul[@role='listbox']/li");
	By addButton = By.xpath("(//*[local-name()='svg'])[21]");
	By nextButton = By.xpath("//button[text()='Next']");
	By uploadFile = By.xpath("//div[@data-tooltip-id='uploadfile']");
	
	By clickOutside = By.xpath("//div[contains(@class,'max-h-[40vh]')]");

	public int fillDatabase(String name, String[] email) {

		int i =0;
		eutil.elementVisibility(addButton, 20).click();
		TimeUtil.defaultTime();
		
		List<WebElement> element = eutil.allElementsVisibility(this.name, 10);
		for(WebElement username: element) {
			username.sendKeys(name);
		}
		
		element = eutil.allElementsVisibility(location, 10);
		for(WebElement location: element) {
			location.click();
			eutil.elementVisibility(countries, 10).click();
		}
		
		element = eutil.allElementsVisibility(this.email, 10);
		System.out.println(email[i]);
		for(WebElement location: element) {
			String data = email[i];
			location.sendKeys(data);
			i++;
		}
		
		element = eutil.allElementsVisibility(companyDropdown, 10);
		for(WebElement location: element) {
			location.click();
			List<WebElement> data=eutil.allElementsVisibility(companyDropdownValues, 10);
			for(WebElement d : data)
			{
				d.click();
				eutil.elementVisibility(clickOutside, 10).click();
				break;
			}
		}
		eutil.doClickOnElement(rowTextbox);
		return 2;
	}

	/**
	 * Skipped:- As we don't have attribute type as file in Upload button.
	 */
	public void uploadFile() {
		eutil.elementVisibility(uploadFile, 10).sendKeys("C:\\Users\\Pankaj.Rawat1\\Downloads\\recipient (11)");
	}
	public EmailPage clickOnNextButton() {
		eutil.doClickOnElement(nextButton,10);
		return new EmailPage(driver); 
	}
}
