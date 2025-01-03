package com.amazon.reports;

import com.amazon.utils.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
 private static	ExtentReports extent;
 private static ExtentTest extentTest;
 private static ExtentSparkReporter sparkReporter;
 private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public static void setUpExtentReport()
	{	 ConfigReader configReader = new ConfigReader();
		 sparkReporter = new ExtentSparkReporter("./extentreports/extent.html");
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 sparkReporter.config().setDocumentTitle("Automation Report");
		 sparkReporter.config().setReportName("Amazon Automation report");
		 extent.setSystemInfo("Browser", configReader.getBrowser());
		 extent.setSystemInfo("OS", System.getProperty("os.name"));
		 
		 
		 }
	public static void createTest(String testName, String groups, String className,String xmlTestName,String author)
	{	ConfigReader configReader = new ConfigReader();
		 extentTest = extent
										.createTest("<b>"+testName+"</b>")
										.assignCategory(groups,className,xmlTestName)
										.assignAuthor(author)
										.assignDevice(configReader.getBrowser());
		 
		 test.set(extentTest);
	}

	public static ExtentTest getTest()
	{
		return test.get();
	}
	public static ExtentReports getReport()
	{
		return extent;
	}
	
	public static void flushReport()
	{
		extent.flush();
	}
	
	public static void createCustomtable(String totalTests, String passedTests, String failedTests, String skippedTests, String passedPercentage) {
		String customTable="<table style='width:100%; border:2px solid Black; border-collapse: collapse;'>"
							+"<tr>"
							+"<th>Total tests</th>"
							+"<th>passed</th>"
							+"<th>failed</th>"
							+"<th>skipped</th>"
							+"<th>passed%</th>"
							+"</tr>"
							+"<tr>"
							+"<td>"+totalTests+"</td>"
							+"<td>"+passedTests+"</td>"
							+"<td>"+failedTests+"</td>"
							+"<td>"+skippedTests+"</td>"
							+"<td>"+passedPercentage+"</td>"
							+"</tr>"
							+"</table>";
		extent.setSystemInfo("Test summary", customTable);
	}
}
