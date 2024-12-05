package com.qa.MetriqAI.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.MetriqAI.Utils.ElementUtil;

public class QuestionBuilderPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	public QuestionBuilderPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}
	
	By numberOfQuestions = By.xpath("//section[contains(@class,'flex-row flex-wrap')]/div");
	By saveAndNextButton = By.xpath("//button[text()='Save & Next']");
	
	public int questionsCount() {
		List<WebElement> list =eutil.allElementsVisibility(numberOfQuestions, 20);
		System.out.println(list.size());
		return list.size();
	}
	
	public ManageSurveyFlowPage hitQuestionBuilderNextButton() {
		eutil.getElement(saveAndNextButton,20).click();
		return new ManageSurveyFlowPage(driver);
	}
	
	public boolean isQuestionBuilderContinueButtonIsClickable() {
		return eutil.elementVisibility(saveAndNextButton, 10).isEnabled();
	}

}
