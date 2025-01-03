package com.amazon.utils;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CustomWait {
	protected ConfigReader configReader;
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected boolean elementState;
	
	
	public CustomWait(WebDriver driver,Duration duration)
	{
		this.driver=driver;
		wait =new WebDriverWait(driver, duration);
		
	}


	public void waitForVisibilityOfElement(WebElement element)
	{
		
		try {
		wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch(TimeoutException e)
		{
			setElementPresentState(false);
			System.err.println("Element is not visible after waiting "+e.getMessage());
		}
	}
	public void setElementPresentState(boolean state) {
		
		elementState=state;
	}

public boolean getElementPresentState() {
		
		return elementState;
	}
	public void waitForElementToBeClickable(WebElement element)
	{
		try {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch(TimeoutException e)
		{
			System.err.println("Element is not clickable after waiting "+e.getMessage());
		}
	}
	
	public void waitForElementToBeInvisible(WebElement element)
	{
		try {
		wait.until(ExpectedConditions.invisibilityOf(element));
		}
		catch(TimeoutException e)
		{
			System.err.println("Element is not clickable after waiting "+e.getMessage());
		}
	}
}
