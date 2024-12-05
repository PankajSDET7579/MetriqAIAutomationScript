package com.qa.MetriqAI.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
public class CreateProjectTemplatePageTest extends BaseTest{
	
	
	@BeforeClass
	public void setUpCreateProjectTemplatePage() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		surveyListPage = homePage.clickOnSurveyListButton();
		createProjectTemplatePage =  surveyListPage.clickOnCreateProjectButton();
	}
	
	@Test
	public void tilesCount() {
		
		int titleCount = createProjectTemplatePage.templateCount();
		System.out.println(titleCount);
		Assert.assertEquals(titleCount, 8);
	}

}





