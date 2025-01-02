package com.amazon.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class HomePage extends BasePage {
	private Actions action;
	@FindBy(id="nav-link-accountList")
	public WebElement accountAndLists;
	
	@FindBy(xpath="//*[text()='Sign in']")
	public WebElement signInButton;
	
	@FindBy(xpath="//div[@id='nav-flyout-ya-signin']//*[text()='Start here.']")
	public WebElement newCustomerStartsHere;
	
	public HomePage(WebDriver driver) throws IOException
	{
		super(driver);
		PageFactory.initElements(driver, this);
		action= new Actions(driver);
	}
	public void openRegistrationPage() throws InterruptedException
	{
	
		action.moveToElement(getAccountAndListElement()).perform();
	
		getsStartHereElement().click();
	}

	public void openSignInPage() throws InterruptedException {
		action.moveToElement(getAccountAndListElement()).perform();
		
		getSignInButtonElement().click();
		
	}

	public WebElement getAccountAndListElement() throws InterruptedException
	{
		wait.waitForVisibilityOfElement(accountAndLists);
		
		return accountAndLists;
	}
	
	
	public WebElement getsStartHereElement() throws InterruptedException
	{
		Thread.sleep(5000);
	wait.waitForVisibilityOfElement(newCustomerStartsHere);
		return newCustomerStartsHere;
	}
	
	
	public WebElement getSignInButtonElement()
	{
		wait.waitForVisibilityOfElement(signInButton);
		return signInButton;
	}
}
