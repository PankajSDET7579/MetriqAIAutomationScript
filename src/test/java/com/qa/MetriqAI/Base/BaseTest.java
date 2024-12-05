package com.qa.MetriqAI.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.MetriqAI.Factory.DriverFactory;
import com.qa.MetriqAI.Pages.CreateProjectGeneralDetailPage;
import com.qa.MetriqAI.Pages.CreateProjectGeographyPage;
import com.qa.MetriqAI.Pages.CreateProjectTemplatePage;
import com.qa.MetriqAI.Pages.CreateSurveySelectMetricesPage;
import com.qa.MetriqAI.Pages.DatabasePage;
import com.qa.MetriqAI.Pages.EmailPage;
import com.qa.MetriqAI.Pages.HomePage;
import com.qa.MetriqAI.Pages.LoginPage;
import com.qa.MetriqAI.Pages.ManageSurveyFlowPage;
import com.qa.MetriqAI.Pages.PublishPage;
import com.qa.MetriqAI.Pages.QuestionBuilderPage;
import com.qa.MetriqAI.Pages.QuotaPage;
import com.qa.MetriqAI.Pages.SurveyListPage;
import com.qa.MetriqAI.Pages.ThemePage;
import com.qa.MetriqAI.Utils.ElementUtil;

public class BaseTest {
	
	DriverFactory df;
	protected WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected SurveyListPage surveyListPage;
	protected CreateProjectTemplatePage createProjectTemplatePage;
	protected CreateProjectGeneralDetailPage createProjectGeneralDetailPage;
	protected CreateProjectGeographyPage createProjectGeographyPage;
	protected CreateSurveySelectMetricesPage createSurveySelectMetricsPage;
	protected QuestionBuilderPage questionBuilderPage;
	protected ManageSurveyFlowPage manageSurveyFlowPage;
	protected ThemePage themePage;
	protected DatabasePage databasePage;
	protected EmailPage emailPage;
	protected QuotaPage quotaPage;
	protected PublishPage publishpage;
	protected SoftAssert softAssert;
	
	//Cross Browser Login
	@Parameters({"browser"})
	@BeforeTest
	
	public void launchBrowser(String browserName) {
		df = new DriverFactory();
		prop = df.initProperties();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initBrowser(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
