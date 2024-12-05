package com.qa.MetriqAI.Test;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
import com.qa.MetriqAI.Utils.StringUtils;
import com.qa.MetriqAI.Utils.TimeUtil;

public class DatabasePageTest extends BaseTest{
	
	String pageName1 = "Product Related Questions";
	String pageName2= "Services Related  Questions";
	String themeName= "Universal Theme";
	
	@BeforeClass
	public void DatabasePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		surveyListPage = homePage.clickOnSurveyListButton();
		createProjectTemplatePage = surveyListPage.clickOnCreateProjectButton();
		createProjectGeneralDetailPage = createProjectTemplatePage.clickCustomerExperienceTemplate();
		createProjectGeneralDetailPage.fillInGeneralDetails( StringUtils.generateRandomString()
				,StringUtils.generateRandomString());
		createProjectGeographyPage = createProjectGeneralDetailPage.clickOnNextButton();
		createProjectGeographyPage.fillInGeographyDetails("India", 3, 2);
		createSurveySelectMetricsPage = createProjectGeographyPage.clickOnGeographyContinueButton();
		createSurveySelectMetricsPage.selectMetrics(prop.getProperty("metrics"));
		questionBuilderPage = createSurveySelectMetricsPage.clickOnMetricsContinueButton();
		manageSurveyFlowPage =questionBuilderPage.hitQuestionBuilderNextButton();
		manageSurveyFlowPage.addPages();
		manageSurveyFlowPage.addQuestionsInPages();
		themePage= manageSurveyFlowPage.clickOnManageSurveyFlowNextButton();
		databasePage = themePage.clickOnNextButtonThemePage();
	}
	
	@Test
	public void addEmailInDatabaseModule() {
		TimeUtil.defaultTime();
		String[] emails= {"pankaj.rawat1@evalueserve.com","bkpankaj92588@gmail.com"};
		int count = databasePage.fillDatabase("Pankaj", emails);
		Assert.assertEquals(count, 2);
		
	}
}
