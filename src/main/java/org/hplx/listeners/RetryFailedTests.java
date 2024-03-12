package org.hplx.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static org.hplx.config_manager.ConfigFactory.getConfig;

public class RetryFailedTests implements IRetryAnalyzer {
    private static final int MAX_RETRY_COUNT = getConfig().MAX_RETRY_COUNT();
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            System.out.println("Retrying test case: " + result.getMethod()
                    + ", Retry attempt: " + retryCount + " out of " + MAX_RETRY_COUNT);
            return true; // Retry the test
        }
        return false; // Do not retry the test
    }
}