package com.phpTravels.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.*;
import org.junit.Assert;
import org.testng.annotations.Test;
import com.phpTravels.pageObjects.LoginPage;

public class LoginTest extends BaseClass{
	
	Logger log = LogManager.getLogger("LoginTest");
	@SuppressWarnings("deprecation")
	@Test(dataProvider="LoginTestData")
	public void loginTest(String username, String password, String validity) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.clickAccount();
		log.info("Clicked on account section");
		lp.setEmail(username);
		log.info("Entered Username");
		lp.continueEmail();
		lp.setPassword(password);
		log.info("Entered Password");
		lp.loginButton();
		
		if (validity.contains("Valid"))
		{
			Assert.assertTrue(lp.loggedIn.isDisplayed());
		}
		
		else if (validity.contains("Invalid"))
		{
			Assert.assertTrue(lp.errorMessage.isDisplayed());
		}
	}

}
