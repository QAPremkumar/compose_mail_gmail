package org.hplx.listeners;

import org.hplx.email_manager.EmailSendUtils;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {

    private static int count_totalTCs;
    private static int count_passedTCs;
    private static int count_skippedTCs;
    private static int count_failedTCs;
   // private final Logger LOGGER = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ISuite iSuite) {
        System.out.println("");
        System.out.println("========= INSTALLING CONFIGURATION DATA =========");
        System.out.println("Starting Suite: " + iSuite.getName());
       // LOGGER.info("Start suite: " + iSuite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        //LOGGER.info("End suite testing " + suite.getName());
        EmailSendUtils.sendEmail(count_totalTCs, count_passedTCs, count_failedTCs, count_skippedTCs);
    }

    private String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getMethodName().toUpperCase();
    }

    private String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
       // LOGGER.info("Test case: " + getTestName(iTestResult) + " is starting...");
        count_totalTCs++;
       // LOGGER.info(iTestResult.getMethod().getMethodName() + " started!");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
       // LOGGER.info("Test case: " + getTestName(iTestResult) + " is passed with success percentage : " + iTestResult.getMethod().getSuccessPercentage());
        count_passedTCs++;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //LOGGER.fatal("Test case: " + getTestName(iTestResult) + " is failed with failure percentage: " + iTestResult.getMethod().getSuccessPercentage());
        count_failedTCs++;
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
       // LOGGER.info("Test case: " + iTestResult.getName() + " is skipped with success percentage: " + iTestResult.getMethod().getSuccessPercentage());
        count_skippedTCs++;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
       // LOGGER.info(iTestResult.getName() + "Test failed but it is within the defined success ratio " + iTestResult.getMethod().getSuccessPercentage());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}