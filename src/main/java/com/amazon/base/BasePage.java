package com.amazon.base;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.utils.ConfigReader;
import com.amazon.utils.CustomWait;

public class BasePage {
	protected WebDriver driver;

	public CustomWait wait;
	public ConfigReader configReader;
	protected static final Logger logger = LogManager.getLogger(BasePage.class);
	public BasePage(WebDriver driver) throws IOException {
		configReader = new ConfigReader();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new CustomWait(driver, Duration.ofSeconds(configReader.getGlobalTimeOut()));
		
	
	}
	
	
	@FindBy(xpath="//i[@class='a-icon a-icon-logo' and @aria-label='Amazon']")
	public WebElement imgLogo;
	
	public WebElement getImgLogo()
	{
	
		wait.waitForVisibilityOfElement(imgLogo);
		return imgLogo;
	}
	public WebDriver getDriver(String browser)
	{
		switch(browser)
		{
		case "CHROME":
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Priyanka\\OneDrive\\Desktop\\Trainings\\chrome\\chromedriver\\chromedriver.exe");
						 driver = new ChromeDriver();
							driver.manage().window().maximize();
			 break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Priyanka\\OneDrive\\Desktop\\Trainings\\chrome\\firefoxdriver\\geckodriver.exe");
			driver= new FirefoxDriver();
			driver.manage().window().maximize();
			 break;	 
			 
		case "EDGE":
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\Priyanka\\OneDrive\\Desktop\\Trainings\\chrome\\Edgedriver\\msedgedriver.exe");
			driver= new EdgeDriver();
			 break;	 
			 
		default:
			System.out.println("no browser specified");
	}
	
		return driver;
	
	}
	
	public void quitDriver()
	{
		driver.quit();
	}
	
	public boolean verifyLogo()
	{
		
		return getImgLogo().isDisplayed();
	}
	
	public String getTitleOfThePage()
	{
		return driver.getTitle();
	}
	
	public String getCurrentPageURL()
	{
		return driver.getCurrentUrl();
	}

	public void navigateBack() {
		driver.navigate().back();
		
	}

	public void navigateForward() {
		driver.navigate().forward();
		
	}

	public void refreshPage() {
		driver.navigate().refresh();
		
	}
	

}
