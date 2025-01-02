package com.amazon.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class SignInPage extends BasePage{

	
	@FindBy(xpath="//h1[contains(text(),'Sign in')]")
	private WebElement signInText;
	
	@FindBy(id="signInSubmit")
	private WebElement signInBtn;
	
	@FindBy(id="ap_password")
	private WebElement yourPassword;
	
	@FindBy(xpath="//*[contains(text(),'Email or mobile phone number')]")
	private WebElement emailOrPhoneNumberText;
	
	@FindBy(id="ap_email")
	private WebElement emailInputBox;
	
	@FindBy(xpath="//input[@id='continue']")
	private WebElement ContinueBtn;
	
	@FindBy(id="continue")
	private WebElement SignInOrCreateAccount;
	
	@FindBy(id="legalTextRow")
	private WebElement LegalTaxRowMessage;
	
	@FindBy(id="createAccountSubmit")
	private WebElement accountButtonSubmit;
	
	@FindBy(xpath="//span[@class='a-list-item'and contains(text(),'We cannot find an account with that email address')]")
	private WebElement unRegisteredEmailIdText;
	
	@FindBy(xpath="//div[@class='a-alert-content' and contains(text(),'Enter a valid email address or phone number')]")
	private WebElement inValidEmailIdText;
	
	public WebElement getSignInText()
	{
		wait.waitForVisibilityOfElement(signInText);
		return signInText;
	}
	
	public WebElement getSignInOrCreateAccount()
	{
		wait.waitForVisibilityOfElement(SignInOrCreateAccount);
		return SignInOrCreateAccount;
	}
	public WebElement getSignInBtn()
	{
		wait.waitForVisibilityOfElement(signInBtn);
		return signInBtn;
	}
	public WebElement getYourPassword()
	{
		wait.waitForVisibilityOfElement(yourPassword);
		return yourPassword;
	}
	public WebElement getEmailOrPhoneNumberText()
	{
		wait.waitForVisibilityOfElement(emailOrPhoneNumberText);
		return emailOrPhoneNumberText;
	}
	public WebElement getEmailInputBox()
	{
		wait.waitForVisibilityOfElement(emailInputBox);
		return emailInputBox;
	}
	
	public WebElement getContinueBtn()
	{
		wait.waitForVisibilityOfElement(ContinueBtn);
		return ContinueBtn;
	}
	public WebElement getLegalTaxRowMessage()
	{
		wait.waitForVisibilityOfElement(LegalTaxRowMessage);
		return LegalTaxRowMessage;
	}
	
	public WebElement getAccountButtonSubmit()
	{
		wait.waitForVisibilityOfElement(accountButtonSubmit);
		return accountButtonSubmit;
	}
	
	public WebElement getUnRegisteredEmailIdText()
	{
		wait.waitForVisibilityOfElement(unRegisteredEmailIdText);
		return unRegisteredEmailIdText;
	}
	
	public WebElement getInValidEmailIdText()
	{
		wait.waitForVisibilityOfElement(inValidEmailIdText);
		return inValidEmailIdText;
	}
	
	public SignInPage(WebDriver driver) throws IOException
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailId(String string) {
	
		try {
			getEmailInputBox().sendKeys(string);
			logger.info("Entered pwd "+string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 logger.error("Failed to enter name in your emailid field "+e);
		}
	}
	
	public void enterPassword(String pwd)
	{
		try {
			getYourPassword().sendKeys(pwd);
			logger.info("Entered pwd "+pwd);
		} catch (Exception e) {
			e.printStackTrace();
			 logger.error("Failed to enter name in your pwd field "+e);
		}
	}

	public void clickOnSignInButton()
	{
		try {
			getSignInBtn().click();
			logger.info("click on "+getSignInBtn());
		} catch (Exception e) {
			e.printStackTrace();
			  logger.error("Failed to click "+getSignInBtn()+""+e);
		}
		
	}
	public void signInWithValidCredentials(String validemail, String validpwd) {
		
		enterEmailId(validemail);
		clickOnContinueButton();
		enterPassword(validpwd);
		clickOnSignInButton();
		
	}

	public void clickOnContinueButton() {
		try {
			getContinueBtn().click();
			logger.info("click on "+getContinueBtn());
		} catch (Exception e) {

			e.printStackTrace();
			  logger.error("Failed to click "+getContinueBtn()+""+e);
		}
		
	}

	public String getUnRegisteredEmailIdError() {
		
		return getUnRegisteredEmailIdText().getText();
	}

	public String getInValidEmailIdError() {
		
		return getInValidEmailIdText().getText();
	}

	public void verifyPresenceOfElementsOnThePage() {
		// TODO Auto-generated method stub
		
	}

	public void setUpCorrectRegistrationPage() throws InterruptedException {
	//	wait.waitForVisibilityOfElement(getSignInOrCreateAccount());
		Thread.sleep(5000);
		if(wait.getElementPresentState()==true)
		{
			System.out.println("You are inside if block");
		}
		
		
	}

	
	
}
