package com.qa.MetriqAI.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
import com.qa.MetriqAI.Pages.HomePage;

public class HomePageTest extends BaseTest{
	
	@BeforeClass
	public void homeSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void surveyListButtonTest() {
		surveyListPage= homePage.clickOnSurveyListButton();
		String url = surveyListPage.getSurveyListPageURL();
		Assert.assertEquals(url, "https://dev-mrim.apps-evalueserve.com/main/workflow/project/list/");
	}	
	
}
