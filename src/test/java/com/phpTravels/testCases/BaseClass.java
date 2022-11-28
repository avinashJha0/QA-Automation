package com.phpTravels.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.phpTravels.utilities.ExcelUtility;
import com.phpTravels.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig rc = new ReadConfig();
	
	public String baseURL = rc.getApplicationURL();
	public String username = rc.getUsername();
	public String password = rc.getPassword();
	public static WebDriver driver;
	public static Logger log;
	
	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) throws IOException
	{
		log = LogManager.getLogger("BaseClass");
		if(br.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver",rc.getChromepath());
		driver = new ChromeDriver();
		log.info("Chrome Browser selected");
		}
		else if (br.equals("firefox")){
			System.setProperty("webdriver.gecko.driver",rc.getFirefoxpath());
			driver = new FirefoxDriver();
			log.info("Firefox Browser selected");
		}
		else if(br.equals("edge")){
			System.setProperty("webdriver.edge.driver",rc.getEdgepath());
			driver = new EdgeDriver();
			log.info("Edge Browser selected");
		}
		
		else 
		{
			System.setProperty("webdriver.chrome.driver",rc.getChromepath());
			driver = new ChromeDriver();
		}
		
		
		driver.get(baseURL);
		log.info("MMT website opened");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@DataProvider(name="FlightTestData")
	public String [][] getFlightTestData() throws IOException
	{
		String path = ".//DataFiles/DataProvider.xlsx";
		ExcelUtility eu = new ExcelUtility(path);
		int totalRows = eu.getRowCount("Sheet1");
		int totalColumns = eu.getCellCount("Sheet1", 1);
		
		String ExcelData[][]=new String[totalRows][totalColumns];
		
		for(int i=1; i<=totalRows ;i++)
		{
			for(int j=0; j<totalColumns; j++)
			{
				ExcelData[i-1][j]=eu.getCellData("Sheet1", i, j);
			}
		}
		System.out.println("Excel data saved is : "+ExcelData);
		return ExcelData;
		
	}
	
	@DataProvider(name="LoginTestData")
	public String [][] getLoginTestData() throws IOException
	{
		String path = ".//DataFiles/LoginData.xlsx";
		ExcelUtility eu = new ExcelUtility(path);
		int totalRows = eu.getRowCount("Sheet1");
		int totalColumns = eu.getCellCount("Sheet1", 1);
		
		String ExcelData[][]=new String[totalRows][totalColumns];
		
		for(int i=1; i<=totalRows ;i++)
		{
			for(int j=0; j<totalColumns; j++)
			{
				ExcelData[i-1][j]=eu.getCellData("Sheet1", i, j);
			}
		}
		System.out.println("Excel data saved is : "+ExcelData);
		return ExcelData;
		
	}
	
	public void captureScreenshot(WebDriver driver, String tname)
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		try {
			FileUtils.copyFile(source, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    //@AfterClass
	public void teardown()
	{
		driver.quit();
		log.info("Browser closed");
	}

}
