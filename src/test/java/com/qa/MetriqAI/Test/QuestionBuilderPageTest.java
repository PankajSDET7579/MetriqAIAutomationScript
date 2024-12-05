package com.qa.MetriqAI.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
import com.qa.MetriqAI.Utils.StringUtils;

public class QuestionBuilderPageTest extends BaseTest {
	

	@BeforeTest
	public void setUpQuestionBuilderPage() {
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
	}

	@Test(priority=1)
	public void questionCountInSelectedMetricTest() {
		int actualCount = questionBuilderPage.questionsCount();
		Assert.assertEquals(actualCount, 15);
	}
	
	@Test(priority=2)
	public void continueButtonIsClickableTest() {
		boolean flag = questionBuilderPage.isQuestionBuilderContinueButtonIsClickable();
		Assert.assertTrue(flag);
	}

	
	
}
