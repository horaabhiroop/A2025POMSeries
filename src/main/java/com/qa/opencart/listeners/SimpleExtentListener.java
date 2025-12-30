package com.qa.opencart.listeners;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.qa.opencart.factory.driverFactory.takeScreenshot;

public class SimpleExtentListener implements ITestListener{
	
	private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    static {
        ExtentSparkReporter reporter =
                new ExtentSparkReporter("./reports/TestReport.html");
        reporter.config().setReportName("OpenCart Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
    	test.get().pass("Test Passed");
    }
    
    @Override
    public void onTestStart(ITestResult result) {
    	ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
    	test.set(extentTest);
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
    	test.get().skip("Test Skipped");
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
    	test.get().fail(result.getThrowable()).addScreenCaptureFromPath(takeScreenshot(result.getMethod().getMethodName()));
    }
    
    @Override
    public void onFinish(org.testng.ITestContext context) {
    	extent.flush();
    }

}
