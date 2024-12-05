package com.qa.MetriqAI.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
import com.qa.MetriqAI.Utils.StringUtils;

public class ThemePageTest extends BaseTest{
	
	String pageName1 = "Product Related Questions";
	String pageName2= "Services Related  Questions";
	String themeName= "Universal Theme";
	
	
	@BeforeClass
	public void setUpThemePage() {
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
	}
	
	
	@Test(priority=1)
	public void selectThemeTest() {
		String actualTheme = themePage.selectTheme(themeName);
		System.out.println(actualTheme);
		Assert.assertEquals(actualTheme, themeName);
	}
	
	@Test(priority=2)
	public void previewSurveyTest() {		
		boolean flag =themePage.previewSurvey();
		Assert.assertTrue(flag);
	}
	

}
