package com.qa.MetriqAI.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.MetriqAI.Base.BaseTest;
import com.qa.MetriqAI.Errors.AppError;
import com.qa.MetriqAI.Utils.TimeUtil;

public class LoginPageTest extends BaseTest{

	
	@Test(priority=1)
	public void loginURLTest() {
		String url = loginPage.currentURL();
		Assert.assertEquals(url, "https://dev-mrim.apps-evalueserve.com/");
	}
	
	@Test(priority=2)
	public void userLoginTest() throws InterruptedException {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		TimeUtil.longTime();
		String actualURL = homePage.homepageURL();
		Assert.assertEquals(actualURL, "https://dev-mrim.apps-evalueserve.com/main/",AppError.URL_NOT_FOUND);
	}
}
