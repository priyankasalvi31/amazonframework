package com.amazon.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class BusinessPage extends BasePage {
	
	@FindBy(xpath="//img[@alt='Amazon Business']")
	private WebElement businessPageLogo;
	

	public BusinessPage(WebDriver driver) throws IOException
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
}
