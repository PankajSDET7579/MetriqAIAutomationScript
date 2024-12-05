package com.qa.MetriqAI.Test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;

public class SurveyListPageTest extends BaseTest {

	@BeforeClass
	public void SurveyListSetUp() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		surveyListPage = homePage.clickOnSurveyListButton();
	}

	@Test(priority=1)
	public void surveyListURLTest() {
		String actualURL = surveyListPage.getSurveyListPageURL();
		Assert.assertEquals(actualURL, "https://dev-mrim.apps-evalueserve.com/main/workflow/project/list/");
	}

	@Test(priority=2)
	public void surveyListfilterPresenceTest() {
		List<String>  filterButtons = surveyListPage.filterButtonsExist();
		boolean flag = filterButtons.contains("Solution - All");
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void searchResultsTest() {
		boolean flag = false;
		List<String> surveyResultList = surveyListPage.doSearch("Cross");
		for(String result: surveyResultList) {
			System.out.println(result);
			if(result.contains("cross") || result.contains("Cross")) {
				flag = true;
			}
		}
		Assert.assertTrue(flag);
	}

}
