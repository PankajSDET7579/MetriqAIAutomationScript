package com.qa.MetriqAI.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener ;
import org.testng.ITestResult;

import com.qa.MetriqAI.Factory.DriverFactory;

import io.qameta.allure.Attachment;	

public class TestAllureReport implements ITestListener{
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment(value = "Page Screenshot", type="images/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value="{0}",type="text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	@Attachment(value="{0}",type="text/html")
	public static String attachHTML(String html) {
		return html;
	}
	
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am onStart method "+ iTestContext.getName());
	}
	
	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am onFinish method "+ iTestContext.getName());
	}
	
	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am onTestStart method "+ getTestMethodName(iTestResult) + " start");
	}
	
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am onTestSuccess method "+ getTestMethodName(iTestResult) + " succeed");
		Object testClass= iTestResult.getInstance();
		if(DriverFactory.getDriver() instanceof WebDriver) {
			System.out.println("Screenshot capture for test case:" +getTestMethodName(iTestResult));
			saveScreenshotPNG(DriverFactory.getDriver());
		}
		// Save a log on allure
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!!");
	}
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am onTestFailure method "+ getTestMethodName(iTestResult) + " failed");
		Object testClass= iTestResult.getInstance();
		if(DriverFactory.getDriver() instanceof WebDriver) {
			System.out.println("Screenshot capture for test case:" +getTestMethodName(iTestResult));
			saveScreenshotPNG(DriverFactory.getDriver());
		}
		// Save a log on allure
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!!");
	}
	
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am on testTestSkipped method " +getTestMethodName(iTestResult) + " skipped");
		Object testClass= iTestResult.getInstance();
		if(DriverFactory.getDriver() instanceof WebDriver) {
			System.out.println("Screenshot capture for test case:" +getTestMethodName(iTestResult));
			saveScreenshotPNG(DriverFactory.getDriver());
		}
		// Save a log on allure
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!!");
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined sucess ratio " +getTestMethodName(iTestResult));
	}
	

}
