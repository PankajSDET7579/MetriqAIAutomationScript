package com.qa.MetriqAI.Test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
import com.qa.MetriqAI.Utils.StringUtils;

public class CreateProjectGeographyPageTest extends BaseTest{
	
	private WebDriver driver;
	
	@BeforeClass
	public void setUpCreateProjectGeographyPage() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		surveyListPage =homePage.clickOnSurveyListButton();
		createProjectTemplatePage = surveyListPage.clickOnCreateProjectButton();
		createProjectGeneralDetailPage =createProjectTemplatePage.clickCustomerExperienceTemplate();
		createProjectGeneralDetailPage.fillInGeneralDetails( StringUtils.generateRandomString()
				,StringUtils.generateRandomString());
		createProjectGeographyPage	= createProjectGeneralDetailPage.clickOnNextButton();
	}
	
	@Test(priority=1)
	public void enterGeographyDetailsTest() {
		createProjectGeographyPage.fillInGeographyDetails("India", 2, 2);
		String selectedCountry = createProjectGeographyPage.getCountryValue();
		System.out.println("Selected Country : "+ selectedCountry);
		boolean flag = selectedCountry.equalsIgnoreCase("India");
		Assert.assertTrue(flag);
	
	}
	
	@Test(priority=2)
	public void nextButtonGeographyPageTest() {
		boolean flag = createProjectGeographyPage.continueButtonIsEnabled();
		Assert.assertTrue(flag);
	}
}
