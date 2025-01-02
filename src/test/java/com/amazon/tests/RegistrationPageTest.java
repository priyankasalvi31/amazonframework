package com.amazon.tests;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.baseTests.BaseTest;

public class RegistrationPageTest extends BaseTest {
	@BeforeMethod(alwaysRun = true)
	public void setUpPage() throws InterruptedException
	{
		//open the registration page from the HomePage
		
		homePage.openRegistrationPage();
		//signInPage.setUpCorrectRegistrationPage();
	}
	
	@Test(groups= {"sanity"})
	public void isLogoPresent()
	{
		try {
		Assert.assertTrue(registrationPage.verifyLogo(),"ERROR- logo is not present on the page");}
		catch(AssertionError ae)
		{
			logger.error(ae.getMessage());
			throw ae;
		}
	}

	
	@Test(groups= {"regression"})
	public void pageTitleTest()
	{
		try {
			Assert.assertEquals(registrationPage.getTitleOfThePage(),"Amazon Registration","ERROR- Page title does not match Expected: Amazon Registration");
		}
			catch(AssertionError ae)
			{
				logger.error(ae.getMessage());
				throw ae;
			}
		}
	
	
	@Test(groups= {"sanity"},enabled = false)
	public void verifyMobileNumberTest() {
		
		try {
			registrationPage.verifyMobileNumberFunctionality("priyanka","000000000","abcd@test");
			Assert.assertEquals(registrationPage.getTitleOfThePage(),"Amazon Registration");		}
			catch(AssertionError ae)
			{
				logger.error(ae.getMessage());
				throw ae;
			}
		}
	

	@Test(groups= {"regression"})
	public void signInLinkTest() {

		
		
			}
	
	@Test(groups= {"sanity"})
	public void createBusinessAccountTest() {
		try {
			registrationPage.clickOnCreateBusinessAccountLink();
			Assert.assertEquals(businessPage.getTitleOfThePage(),"Amazon Registration","ERROR- Amazon Business Page title does not match Expected: Amazon Business");
	}
			catch(AssertionError ae)
			{
				logger.error(ae.getMessage());
				throw ae;
			}
			}
	
	@Test(groups= {"regression"})
	public void fieldsOnThePageTest() throws IOException
	{
		try {
			List<String> fieldNames =excelReader.getFieldNamesFromExcel("Sheet1");
			Assert.assertTrue(registrationPage.verifyPresenceOfElementsOnThePage(fieldNames),"Some elements are not present on the registration page.");
	}
			catch(AssertionError ae)
			{
				logger.error(ae.getMessage());
				throw ae;
			}
		
					}
}
