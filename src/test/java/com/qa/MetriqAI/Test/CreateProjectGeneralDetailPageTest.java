package com.qa.MetriqAI.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
import com.qa.MetriqAI.Utils.StringUtils;

public class CreateProjectGeneralDetailPageTest extends BaseTest{
	
	@BeforeClass
	public void CreateProjectGeneralDetailPageTestSetUp() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		surveyListPage = homePage.clickOnSurveyListButton();
		createProjectTemplatePage =  surveyListPage.clickOnCreateProjectButton();
		createProjectGeneralDetailPage = createProjectTemplatePage.clickCustomerExperienceTemplate();
	}
	
	@Test
	public void generalDetailsTest(){
		createProjectGeneralDetailPage.fillInGeneralDetails( StringUtils.generateRandomString()
		,StringUtils.generateRandomString());
		
		boolean flag = createProjectGeneralDetailPage.isNextButtonEnabled();
		Assert.assertTrue(flag);
	}

}
