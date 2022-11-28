package com.phpTravels.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//p[@data-cy='LoginHeaderText']")
	WebElement loginHeader;
	@FindBy(xpath="//input[@id='username']")
	WebElement email;
	@FindBy(xpath="//button[@data-cy='continueBtn']")
	WebElement continuebutton;
	@FindBy(xpath="//li[text()='Personal Account']")
	WebElement personalAccount;
	@FindBy(xpath="//li[text()='MyBiz Account']")
	WebElement myBizAccount;
	@FindBy(xpath="//input[@id='password']")
	WebElement pw;
	@FindBy(xpath="//button[@data-cy='login']")
	WebElement login;
	
	
	@FindBy(xpath="//li[@data-cy='account']")
	WebElement account;
	
	@FindBy(xpath="//p[@data-cy='loggedInUser']")
	public WebElement loggedIn;
	
	@FindBy(xpath="//span[text()='Either Username or Password is incorrect.']")
	public WebElement errorMessage;
	
	
	
	public void clickAccount()
	{
		loginHeader.click();
	}
	
	public void setEmail(String username) throws InterruptedException
	{
		email.sendKeys(username);
		Thread.sleep(5000);
		
	}
	
	public void continueEmail() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@data-cy='continueBtn']")));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", continuebutton);
		continuebutton.sendKeys(Keys.RETURN);
		Thread.sleep(5000);
		Actions ac = new Actions(driver);
		ac.doubleClick(continuebutton);
		
	}
	
	public void setPassword(String password)
	{
		pw.sendKeys(password);
	}
	
	public void loginButton()
	{
		login.click();
	}	

}
