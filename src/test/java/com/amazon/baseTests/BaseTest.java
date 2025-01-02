package com.amazon.baseTests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.amazon.base.BasePage;
import com.amazon.customListeners.MyListener;
import com.amazon.pages.BusinessPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.RegistrationPage;
import com.amazon.pages.SignInPage;
import com.amazon.reports.ExtentReportManager;
import com.amazon.testDataReaders.ExcelReader;
import com.amazon.utils.ConfigReader;
import com.amazon.utils.CustomWait;
import com.aventstack.extentreports.Status;

public class BaseTest {

	
	private static ThreadLocal<WebDriver> localDriver = new ThreadLocal<WebDriver>();
	protected static final Logger logger = LogManager.getLogger(BaseTest.class);
	public WebDriver driver;
	public BasePage basePage;
	public RegistrationPage registrationPage;
	public SignInPage signInPage;
	public HomePage homePage;
	public BusinessPage businessPage;
	public ConfigReader configReader;
	public ExcelReader excelReader;
	public CustomWait customWait;
	public MyListener myListener;
	private static int totalTests=0;
	private static int passedTests=0;
	private static int failedTests=0;
	private static int skippedTests=0;
	public WebDriver getLocalDriver()
	{
		return localDriver.get();
	
	}
	
	@BeforeSuite(alwaysRun = true)
	public void setUpSuite(ITestContext context)
	{
		String suiteName =context.getSuite().getName();
		String groupsList[] = context.getIncludedGroups();
		String includedGroups =String.join(",", groupsList);
		logger.info("Started Execution for Test Suite=> "+suiteName+" with tag => "+includedGroups);
		ExtentReportManager.setUpExtentReport();
		ExtentReportManager.getReport().setSystemInfo("suiteName" ,suiteName);
	
	}
	@BeforeMethod(alwaysRun = true)
	public void baseSetUp() throws IOException
	{   configReader = new ConfigReader();  	
	 
		basePage = new BasePage(getLocalDriver());
		WebDriver driver = basePage.getDriver(configReader.getBrowser());
		logger.info("Started Execution on Browser=> "+configReader.getBrowser());
		localDriver.set(driver);
		driver.get(configReader.getUrl());
		long ThreadID=	Thread.currentThread().getId();
		String sessionId= ((RemoteWebDriver) getLocalDriver()).getSessionId().toString();
		System.out.println("ThreadID "+ThreadID+" Starting SessionId "+sessionId);
		registrationPage = new RegistrationPage(getLocalDriver());
		signInPage = new SignInPage(getLocalDriver());
		homePage = new HomePage(getLocalDriver());
		businessPage=new BusinessPage(getLocalDriver());
		String test =	"C:\\Users\\Priyanka\\eclipse-workspace\\amazonframework\\src\\test\\resources\\Testdata\\"+ configReader.getExcelFilePath();
		excelReader = new ExcelReader(test);
	}
	@BeforeTest(alwaysRun = true)
	public void setUpTest(ITestContext context)
	{
		String testName = context.getName();
		logger.info("Started Execution for Test Name=> "+testName);
		ExtentReportManager.getReport().setSystemInfo("testName" ,testName);
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser(ITestResult result)
	
	{	totalTests++;
		if(result.getStatus()==ITestResult.FAILURE)
		{
			failedTests++;
			ExtentReportManager.getTest().fail(result.getThrowable());
		}
		else if (result.getStatus()==ITestResult.SKIP)
		{	skippedTests++;
			
			ExtentReportManager.getTest().skip(result.getThrowable());
		}
		else
		{  passedTests++;
			ExtentReportManager.getTest().pass("Test passed");
		}
		ExtentReportManager.getTest().log(Status.INFO,"Test Method Execution completed");
		getLocalDriver().quit();
		localDriver.remove();
		logger.info("Closing Execution on Browser=> "+configReader.getBrowser());
		
	}
	
	@AfterSuite(alwaysRun = true)
	public void generateReport()
	{	//	ExtentReportManager.getReport().setSystemInfo("Total tests executed", String.valueOf(totalTests-skippedTests));
//			ExtentReportManager.getReport().setSystemInfo(" tests passed", String.valueOf(passedTests));
//			ExtentReportManager.getReport().setSystemInfo(" tests failed", String.valueOf(failedTests));
//			ExtentReportManager.getReport().setSystemInfo(" tests skipped", String.valueOf(skippedTests));
			double passPercentage =(double)passedTests/(totalTests-skippedTests);
		passPercentage=passPercentage*100;
//			ExtentReportManager.getReport().setSystemInfo(" passed %", String.format("%.2f",passPercentage));
			
			ExtentReportManager.createCustomtable(
					String.valueOf(totalTests-skippedTests),
					String.valueOf(passedTests),
					 String.valueOf(failedTests),
					 String.valueOf(skippedTests),
					 String.format("%.2f",passPercentage)
					);
			ExtentReportManager.flushReport();
		
	}
}
