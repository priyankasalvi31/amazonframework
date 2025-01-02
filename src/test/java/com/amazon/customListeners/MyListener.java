package com.amazon.customListeners;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.amazon.baseTests.BaseTest;
import com.amazon.reports.ExtentReportManager;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class MyListener extends BaseTest implements ITestListener,IAnnotationTransformer{

	

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(MyRetryAnalyzer.class);
	}



	@Override
	public void onTestStart(ITestResult result) {
		
	//	System.out.println("*********Starting Test********TestName"+result.getName());
		String className = result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1);
		String methodName = result.getMethod().getMethodName();
		String xmlTestName = result.getTestContext().getName();
		logger.info("*********Starting Test********"+className+" "+methodName);
		String groupsList[]=result.getTestContext().getIncludedGroups();
		String includedGroups =String.join(",", groupsList);
		ExtentReportManager.createTest(className+"."+methodName,includedGroups,className,xmlTestName, "John");
		ExtentReportManager.getTest().log(Status.INFO,"Test Started: "+className+"=>"+methodName);
		logger.info("**********Test Started********"+className+" "+methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String className = result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1);
		String methodName = result.getMethod().getMethodName();
		logger.info("**********Test Passed********"+className+" "+methodName);
		ExtentReportManager.getTest().log(Status.PASS,"Test Successfull: "+className+"=>"+methodName);
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String className = result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1);
		String methodName = result.getMethod().getMethodName();
		logger.info("**********Test Failed********"+className+" "+methodName);
		ExtentReportManager.getTest().log(Status.FAIL,"Test failed: "+className+"=>"+methodName);
		
		ExtentReportManager.getTest().log(Status.FAIL,result.getThrowable());
		//System.out.println("*********Test Failed********TestName"+result.getName());
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		String dynamicScreenshotName=result.getName()+formatter.format(now);
		File file = ((TakesScreenshot)getLocalDriver()).getScreenshotAs(OutputType.FILE);
		try {
			String screenShotPath =System.getProperty("user.dir")+"./src/test/resources/Screenshots/"+dynamicScreenshotName+".png";
			FileUtils .copyFile(file, new File(screenShotPath));
			ExtentReportManager.getTest().fail("Screenshot for Failure", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());	
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String className = result.getTestClass().getName().substring(result.getTestClass().getName().lastIndexOf('.')+1);
		String methodName = result.getMethod().getMethodName();
		logger.info("**********Test Skipped********"+className+" "+methodName);
		ExtentReportManager.getTest().log(Status.SKIP,"Test Skipped: "+className+"=>"+methodName);
	}



	
}
