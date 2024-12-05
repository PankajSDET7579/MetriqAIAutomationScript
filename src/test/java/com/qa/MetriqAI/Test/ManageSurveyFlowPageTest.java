package com.qa.MetriqAI.Test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
import com.qa.MetriqAI.Utils.StringUtils;
import com.qa.MetriqAI.Utils.TimeUtil;

public class ManageSurveyFlowPageTest extends BaseTest{
	
	String pageName1 = "Product Related Questions";
	String pageName2= "Services Related  Questions";
	
	@BeforeTest
	public void setUpQuestionBuilderPage() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		surveyListPage = homePage.clickOnSurveyListButton();
		createProjectTemplatePage = surveyListPage.clickOnCreateProjectButton();
		createProjectGeneralDetailPage = createProjectTemplatePage.clickCustomerExperienceTemplate();
		createProjectGeneralDetailPage.fillInGeneralDetails( StringUtils.generateRandomString()
				,StringUtils.generateRandomString());
		createProjectGeographyPage = createProjectGeneralDetailPage.clickOnNextButton();
		createProjectGeographyPage.fillInGeographyDetails("India", 2, 2);
		createSurveySelectMetricsPage = createProjectGeographyPage.clickOnGeographyContinueButton();
		createSurveySelectMetricsPage.selectMetrics(prop.getProperty("metrics"));
		questionBuilderPage = createSurveySelectMetricsPage.clickOnMetricsContinueButton();
		manageSurveyFlowPage =questionBuilderPage.hitQuestionBuilderNextButton();
	}

	
	@Test
	public void createPagesAndAddQuestionsInPagesTest() {
		boolean flag =  manageSurveyFlowPage.addPages();
		Assert.assertTrue(flag);
		manageSurveyFlowPage.addQuestionsInPages();
		ArrayList<Integer> counts = manageSurveyFlowPage.questionsCountInPages(pageName1,pageName2);
		for(Integer data : counts) {
			System.out.println("Data : "+ data);
		}
		Assert.assertEquals(counts.get(0), 7);
		Assert.assertEquals(counts.get(1), 8);
	}
}
