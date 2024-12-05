package com.qa.MetriqAI.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	private int count = 0;
	private static int maxTry = 3;

	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if (count < maxTry) {
				count++;
				result.setStatus(result.FAILURE);
				return true;
			} else {
				result.setStatus(result.FAILURE);
			}
		} else {
			result.setStatus(result.SUCCESS);
		}
		return false;
	}
}
