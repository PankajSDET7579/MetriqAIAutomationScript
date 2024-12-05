package com.qa.MetriqAI.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.MetriqAI.Utils.ElementUtil;
import com.qa.MetriqAI.Utils.JavaScriptUtil;
import com.qa.MetriqAI.Utils.TimeUtil;

public class ManageSurveyFlowPage {

	private WebDriver driver;
	private ElementUtil eutil;
	private JavaScriptUtil javaScript ;

	public ManageSurveyFlowPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
		javaScript = new JavaScriptUtil(driver);
		
	}

	By sectionBuilderButton = By.xpath("//span[text()='Section Builder']");
	By cancelButtonSectionBuilderPage = By.xpath("(//button[text()='Cancel'])[1]");
	By addSectionButton = By.xpath("//span[text()='Add Section']");
	By nameTextBox = By.xpath("//input[@id='name']");
	By descriptionTextBox = By.xpath("//div[@data-placeholder='Enter Description...']/p");
	By saveSectionButton = By.xpath("//button[text()='Save Section']");
	By cancelButtonOverlayWindow = By.xpath("(//button[text()='Cancel'])[2]");

	By outerDiv = By.xpath("//div[contains(@class,'min-h-full')]");
	By addedPageText = By.xpath("//div[contains(@class,'bg-figmaBlue')]//div");

	By addedPagesRadioButton = By.xpath("//div[contains(@class,'bg-figmaBlue')]/input");
	By questionsList = By.xpath("//div[contains(@class,'w-full min-h-[10rem]')]/div");
	By rightArrow = By.xpath("(//button[contains(@class,'bg-figmaBlue')]//*[local-name()='svg'])[1]");
	By leftArrow = By.xpath("(//button[contains(@class,'bg-figmaBlue')]//*[local-name()='svg'])[1]");
	
	By nextButton = By.xpath("//button[text()='Next']");

	public boolean addPages() {
		boolean flag = false;
		eutil.doClickOnElement(sectionBuilderButton, 20);
		eutil.doClickOnElement(addSectionButton, 20);
		eutil.doSendKeys(nameTextBox, 10, "Product Related Questions");
		eutil.doSendKeys(descriptionTextBox,10, "Product Satisfaction and Quality");
		eutil.doClickOnElement(saveSectionButton);
		eutil.doClickOnElement(outerDiv, 10);

		eutil.doClickOnElement(sectionBuilderButton, 20);
		eutil.doClickOnElement(addSectionButton, 20);
		eutil.doSendKeys(nameTextBox, 10, "Services Related  Questions");
		eutil.doSendKeys(descriptionTextBox,10, "Customer Satisfaction Rates");
		eutil.doClickOnElement(saveSectionButton);
		eutil.doClickOnElement(outerDiv, 10);

		List<WebElement> pages = eutil.allElementsVisibility(addedPageText, 20);
		List<String> pageNames = new ArrayList<String>();
		System.out.println("Total Added Pages are : " + pages.size());

		for (WebElement page : pages) {
			String word = page.getText();
			pageNames.add(word);
		}
		if (pageNames.size() > 0) {
			flag = true;
		}
		return flag;
	}

	public int addedPagesCount() {

		List<WebElement> pages = eutil.allElementsVisibility(addedPageText, 20);
		System.out.println("Total Added Pages are : " + pages.size());
		return pages.size();
	}

	public void addQuestionsInPages() {
		List<WebElement> questions = eutil.getElements(questionsList, 10);
		List<WebElement> radioButton = eutil.allElementsVisibility(addedPagesRadioButton, 10);
		
		int questionsCount = questions.size();

		if (addedPagesCount() > 1 && questionsCount > 1) {

			int pageNumber = 0;
			int condition = questionsCount / 2;

			while (questionsCount != 0) {
				questions = eutil.getElements(questionsList, 10);
				radioButton = eutil.allElementsVisibility(addedPagesRadioButton, 10);
				for (int i = 0; i < condition; i++) {
					questions.get(i).click();
					questionsCount--;//8
				}
				radioButton.get(pageNumber).click();
				eutil.doClickOnElement(rightArrow, 10);
				condition = (questions.size() - questionsCount)+1; //7
				pageNumber++;
			}
		} else if (addedPagesCount() == 1) {
			for (int i = 0; i <= questionsCount; i++) {
				questions.get(i).click();
				questionsCount--;
			}
			radioButton.get(0).click();
			eutil.doClickOnElement(rightArrow, 10);
		}
	}

	public ArrayList<Integer> questionsCountInPages(String pageName1, String pageName2) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		By questionsPage1 = By.xpath("//div[text()='" + pageName1
				+ "']/parent::div/following-sibling::div//div[contains(@class,'line-clamp-1')]");

		By questionsPage2 = By.xpath("//div[text()='" + pageName2
				+ "']/parent::div/following-sibling::div//div[contains(@class,'line-clamp-1')]");
		
		
		List<WebElement> page1 =eutil.allElementsVisibility(questionsPage1, 10);
		System.out.println(page1.size());
		list.add(page1.size());
		
		List<WebElement> page2 =eutil.allElementsVisibility(questionsPage2, 10);
		
		System.out.println(page2.size());
		list.add(page2.size());
		
		return list;
	}

	public ThemePage clickOnManageSurveyFlowNextButton() {
		TimeUtil.defaultTime();
		WebElement button = eutil.elementVisibility(nextButton, 20);
		javaScript.scrollToElement(button);
		button.click();
		return new ThemePage(driver);
	}
}
