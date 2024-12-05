package com.qa.MetriqAI.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
import com.qa.MetriqAI.Errors.AppError;
import com.qa.MetriqAI.Utils.StringUtils;
import com.qa.MetriqAI.Utils.TimeUtil;

public class CreateSurveySelectMetricesPageTest extends BaseTest{
	
	
	@BeforeTest
	public void setUpCreateSurveySelectMetricesPage() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		surveyListPage =homePage.clickOnSurveyListButton();
		createProjectTemplatePage = surveyListPage.clickOnCreateProjectButton();
		createProjectGeneralDetailPage =createProjectTemplatePage.clickCustomerExperienceTemplate();
		createProjectGeneralDetailPage.fillInGeneralDetails( StringUtils.generateRandomString()
				,StringUtils.generateRandomString());
		createProjectGeographyPage	= createProjectGeneralDetailPage.clickOnNextButton();
		createProjectGeographyPage.fillInGeographyDetails("India", 3, 2);
		createSurveySelectMetricsPage = createProjectGeographyPage.clickOnGeographyContinueButton();
		
	}
	
	@Test(priority=1)
	public void selectMetricTest(){
		String data = prop.getProperty("metrics");
		createSurveySelectMetricsPage.selectMetrics(data);
		boolean flag = createSurveySelectMetricsPage.isCheckBoxSelected(prop.getProperty("metrics"));
		System.out.println("Checkbox metrics button is enabled: " +flag);
		Assert.assertTrue(flag,AppError.METRICS_NOT_SELECTED);
	}

	@Test(priority=2)
	public void metricPageContinueButtonEnabledTest() {
		boolean flag = createSurveySelectMetricsPage.isContinueButtonDisplayed();
		System.out.println("Continue button metrics page is enabled: " +flag);
		Assert.assertTrue(flag);
	}

}
