package com.qa.MetriqAI.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
import com.qa.MetriqAI.Utils.StringUtils;

public class PublishSurveyPageTest extends BaseTest {

	String pageName1 = "Product Related Questions";
	String pageName2 = "Services Related  Questions";
	String themeName = "Universal Theme";

	String name = "Pankaj";
	String[] emails = { "pankaj.rawat1@evalueserve.com", "bkpankaj92588@gmail.com" };

	@BeforeClass
	public void DatabasePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		surveyListPage = homePage.clickOnSurveyListButton();
		createProjectTemplatePage = surveyListPage.clickOnCreateProjectButton();
		createProjectGeneralDetailPage = createProjectTemplatePage.clickCustomerExperienceTemplate();
		createProjectGeneralDetailPage.fillInGeneralDetails(StringUtils.generateRandomString(),
				StringUtils.generateRandomString());
		createProjectGeographyPage = createProjectGeneralDetailPage.clickOnNextButton();
		createProjectGeographyPage.fillInGeographyDetails("India", 2, 2);
		createSurveySelectMetricsPage = createProjectGeographyPage.clickOnGeographyContinueButton();
		createSurveySelectMetricsPage.selectMetrics(prop.getProperty("metrics"));
		questionBuilderPage = createSurveySelectMetricsPage.clickOnMetricsContinueButton();
		manageSurveyFlowPage = questionBuilderPage.hitQuestionBuilderNextButton();
		manageSurveyFlowPage.addPages();
		manageSurveyFlowPage.addQuestionsInPages();
		themePage = manageSurveyFlowPage.clickOnManageSurveyFlowNextButton();
		databasePage = themePage.clickOnNextButtonThemePage();
		databasePage.fillDatabase(name, emails);
		emailPage = databasePage.clickOnNextButton();
		emailPage.composeEmail();
		quotaPage = emailPage.clickOnEmailSubmitButton();
		quotaPage.enterQuota();
		publishpage = quotaPage.clickOnQuotaPageNextButton();

	}

	@Test
	public void publishSurveyTest() {
		boolean flag = publishpage.clickOnPublishPageNextButton();
		Assert.assertTrue(flag);

	}
}
