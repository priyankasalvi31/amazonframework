package com.amazon.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class RegistrationPage extends BasePage{
	
	@FindBy(id="ap_customer_name")
	private WebElement yourName;
	@FindBy(id="ap_phone_number")
	private WebElement yourPhoneNumber;
	@FindBy(id="ap_password")
	private WebElement yourPassword;
	@FindBy(xpath="//h1[contains(text(),'Create Account')]")
	private WebElement createBusinessAccount;
	@FindBy(xpath="//div[contains(text(),'Enter at least 6 characters')]")
	private WebElement passwordInfoMessage;
	@FindBy(xpath="//span[contains(text(),'Verify mobile number')]")
	private WebElement verifyMobileNumberBtn;
	
	@FindBy(xpath="//*[contains(text(),'Sign in')]")
	private WebElement signInLink;
	
	
	
	@FindBy(id="mobileClaimDisclosureMessageRelaxed")
	private WebElement verifyMobileNumberMessageDisclosure;
	
	public WebElement getYourName()
	{
		wait.waitForVisibilityOfElement(yourName);
		return yourName;
	}
	
	public WebElement getYourPhoneNumber()
	{
		wait.waitForVisibilityOfElement(yourPhoneNumber);
		return yourPhoneNumber;
	}
	
	public WebElement getYourPassword()
	{
		wait.waitForVisibilityOfElement(yourPassword);
		return yourPassword;
	}
	
	
	public WebElement getcreateBusinessAccount()
	{
		wait.waitForVisibilityOfElement(createBusinessAccount);
		return createBusinessAccount;
	}
	
	public WebElement getpasswordInfoMessage()
	{
		wait.waitForVisibilityOfElement(passwordInfoMessage);
		return passwordInfoMessage;
	}
	
	public WebElement getverifyMobileNumberBtn()
	{
		wait.waitForVisibilityOfElement(verifyMobileNumberBtn);
		return verifyMobileNumberBtn;
	}
	public WebElement getverifyMobileNumberMessageDisclosure()
	{
		wait.waitForVisibilityOfElement(verifyMobileNumberMessageDisclosure);
		return verifyMobileNumberMessageDisclosure;
	}
	
	
	public WebElement getSignInLink()
	{
		wait.waitForVisibilityOfElement(signInLink);
		return signInLink;
	}
	public RegistrationPage(WebDriver driver) throws IOException
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
//page actions
	public void enterYourName(String name)
	{try
	{
		getYourName().sendKeys(name);
		logger.info("Entered Name "+name);
	}
			catch(Exception e)
	{
			logger.error("Failed to enter name in your Name field "+e);	
	}
	}
	
	public void enterYourMobileNumber(String mobileNumber)
	{
		try {
			getYourPhoneNumber().sendKeys(mobileNumber);   
			logger.info("Entered Name "+mobileNumber);   
		} catch (Exception e) {
			
			e.printStackTrace();    
			  logger.error("Failed to enter name in your mobileNumber field "+e);
		}                           	    
		
	}
	public void enterYourPassword(String pwd)
	{
		try {
			getYourPassword().sendKeys(pwd);
			logger.info("Entered pwd "+pwd);
		} catch (Exception e) {
			e.printStackTrace();    
			  logger.error("Failed to enter name in your pwd field "+e);
		}
		
	}
	
	public void clickOnVerifyMobileNumberFunctionality() {
		
		try {
			Thread.sleep(5000);
			getverifyMobileNumberBtn().click();
			logger.info("click on "+getverifyMobileNumberBtn());
		} catch (Exception e) {
			
			e.printStackTrace();
			  logger.error("Failed to click "+getverifyMobileNumberBtn()+""+e);
		}
	}

	
	public void verifyMobileNumberFunctionality(String name, String mobileNumber, String pwd) {

		enterYourName(name);
		enterYourMobileNumber(mobileNumber);
		enterYourPassword(pwd);
		clickOnVerifyMobileNumberFunctionality();
		
		
	}

	public void clickOnSignInLink() {
		try {
			getSignInLink().click();
			logger.info("click on "+getSignInLink());
		} catch (Exception e) {
			e.printStackTrace();
			  logger.error("Failed to click "+getSignInLink()+""+e);
		}
		
	}

	public void clickOnCreateBusinessAccountLink() {
	
		try {
			getcreateBusinessAccount().click();
			logger.info("click on "+getcreateBusinessAccount());
		} catch (Exception e) {
			e.printStackTrace();
			  logger.error("Failed to click "+getcreateBusinessAccount()+""+e);
		}
	}

	private WebElement getElementByFieldName(String fieldName)
	{
		WebElement 	element = null;
		switch(fieldName.toLowerCase())
		{
		case "your name":
			getYourName();
			element = yourName;
			break;
		case "mobile number":
			getYourPhoneNumber();
			element = yourPhoneNumber;
			break;
		case "password":
			getYourPassword();
			element = yourPassword;
			break;
			
		case "verify mobile number button":
			getverifyMobileNumberBtn();
			element =verifyMobileNumberBtn;
			break;
			
		case "create business account":
			getcreateBusinessAccount();
			element = createBusinessAccount;
			break;
			
		case "sign in link":
			getSignInLink();
			element = signInLink;
			break;
			
		default:
			throw new IllegalArgumentException("Invalid field name"+fieldName);
			
		
		}
		return element;
	}
	public boolean verifyPresenceOfElementsOnThePage(List<String> fieldNames) {
		boolean result = true;
		int count =0;
		for(String fieldName :fieldNames)
		{
			WebElement element= getElementByFieldName(fieldName);
			if(element ==null)
			{
				System.out.println("Element is null so make sure you have added corresponding WebElement case for this field"+fieldName+"in the excel");
				result = false;
				continue;
			}
			
			if(wait.getElementPresentState()== true)
			{
				if (element.isDisplayed() == true) {
					System.out.println(fieldName + "is present on the page");
				}
				else {
					System.out.println(fieldName + "is not present on the page");
					count++;
				}
			}
			
			if(count!=0)
			{
				result = false;
			}
			
			
		}
		return result;
		
	}


}
