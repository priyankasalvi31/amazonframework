package com.amazon.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.baseTests.BaseTest;

public class SignInPageTest extends BaseTest {
	
	@BeforeMethod(alwaysRun = true)
	public void setUpPage() throws InterruptedException
	{
		//open the registration page from the HomePage
		
		homePage.openSignInPage();
	}

	
	@Test(groups= {"sanity"})
	public void isLogoPresent()
	{
		try {
			Assert.assertTrue(signInPage.verifyLogo(),"ERROR- logo is not present on the page");	}
			catch(AssertionError ae)
			{
				logger.error(ae.getMessage());
				throw ae;
			}
		
		
	}

	
	@Test(groups= {"sanity"})
	public void pageTitleTest()
	{
		try {
			Assert.assertEquals(signInPage.getTitleOfThePage(),"Amazon Sign In","ERROR- Page title does not match Expected: Amazon Registration");
}
			catch(AssertionError ae)
			{
				logger.error(ae.getMessage());
				throw ae;
			}
			}
	
	@Test(groups= {"regression"})
	public void backAndForthScenarioTest()
	{
		try {
			basePage.navigateBack();
			Assert.assertEquals(homePage.getTitleOfThePage(),"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in","ERROR- Page title does not match Expected: Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
			basePage.navigateForward();
			Assert.assertEquals(homePage.getTitleOfThePage(),"Amazon Sign In","ERROR- Page title does not match Expected: Amazon Sign In");
			basePage.refreshPage();
			Assert.assertEquals(homePage.getTitleOfThePage(),"Amazon Sign In","ERROR- Page title does not match Expected: Amazon Sign In");}
			catch(AssertionError ae)
			{
				logger.error(ae.getMessage());
				throw ae;
			}
		
	
	}
	
	@Test(groups= {"regression"},enabled =false)
	public void signInWithUnRegisteredEmailId()
	{
		
		try {
			signInPage.enterEmailId("tess@test.com");
			signInPage.clickOnContinueButton();
			signInPage.enterPassword("erty");
			signInPage.clickOnSignInButton();
			Assert.assertEquals(signInPage.getUnRegisteredEmailIdError(),"We cannot find account associated with that email address");
			}
			catch(AssertionError ae)
			{
				logger.error(ae.getMessage());
				throw ae;
			}
		

		
			}
	
	@Test(groups= {"regression","sanity"})
	public void signInWithInValidEmailIdTest()
	{
		try {
			signInPage.enterEmailId("abcd");
			signInPage.clickOnContinueButton();
			Assert.assertEquals(signInPage.getInValidEmailIdError(),"Enter a valid email address or phone number");
			}
			catch(AssertionError ae)
			{
				logger.error(ae.getMessage());
				throw ae;
			}
		
	}
	
		@Test(groups= {"sanity"},enabled = false)
	public void signInWithValidCredentialsTest()
	{
			try {
				signInPage.signInWithValidCredentials("abcd@test.com","abcd");
				Assert.assertEquals(signInPage.getTitleOfThePage(),"Authentication required","ERROR- Page title does not match Expected: Amazon Registration");
				}
				catch(AssertionError ae)
				{
					logger.error(ae.getMessage());
					throw ae;
				}
			}
	
	
		@Test(groups= {"regression"})
	public void fieldsOnThePageTest()
	{
			try {
				signInPage.verifyPresenceOfElementsOnThePage();
				Assert.assertEquals(signInPage.getTitleOfThePage(),"Amazon Sign In","ERROR- Page title does not match Expected: Amazon Registration");
				}
				catch(AssertionError ae)
				{
					logger.error(ae.getMessage());
					throw ae;
				}
			}
	
}
