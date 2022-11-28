package com.phpTravels.pageObjects;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelPage {
	
	WebDriver driver;
	public String validationMessage = "Showing Properties in Dubai";
	public String panValidation = "Please enter PAN card number";
	
	public HotelPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='https://www.makemytrip.com/hotels/']")
	WebElement hotelLink;
	
	@FindBy(xpath="//label[@for='city']")
	WebElement cityName;
	
	@FindBy(xpath="//input[@placeholder='Enter city/ Hotel/ Area/ Building']")
	WebElement cityText;
	
	@FindBy(xpath = "//p[contains(text(),'Dubai, United Arab Emirates')]")
	WebElement dubai;
	
	@FindBy(xpath="//label[@for='checkin']")
	WebElement checkIn ;
	
	@FindBy(xpath = "//div[@class='DayPicker-Caption']/div")
	WebElement monthElement;

	@FindBy(xpath = "//div[@class='DayPicker-Caption']/div/span")
	WebElement yearElement;

	@FindBy(xpath = "//span[@aria-label='Next Month']")
	WebElement nextButton;
	
	@FindBy(xpath="(//div[@class='gstSlct'])[1]")
	WebElement rooms;
	
	@FindBy(xpath="(//div[@class='gstSlct'])[2]")
	WebElement numberOfAdults;
	
	@FindBy(xpath="//button[text()='Apply']")
	WebElement apply;
	
	@FindBy(xpath="//button[text()='Search']")
	WebElement submit;
	
	@FindBy(xpath="//label[@for='guest']")
	WebElement roomAndGuestModify;
	
	@FindBy(xpath="(//span[@class='arrow arrowDown'])[2]")
	WebElement currencyDropdown;
	
	@FindBy(xpath="(//ul[@class='latoBold font18']/li)[1]")
	WebElement indCurrency;
	
	@FindBy(xpath = "//p[text()='Showing Properties in Dubai']")
	WebElement propertiesValidation;
	
	@FindBy(xpath="//img[@alt='Luxe International']")
	WebElement luxuryInternationalHotels;

	@FindBy(xpath="//div[@id='Listing_hotel_0']")
	WebElement firstHotelInList;
	
	@FindBy(xpath="//button[text()='BOOK THIS NOW']")
	WebElement bookThisNowButton;
	
	@FindBy(xpath="//input[@id='fName']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@id='lName']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement emailAddress;
	
	@FindBy(xpath="//input[@id='mNo']")
	WebElement mobileNumber;
	
	@FindBy(xpath="//a[text()='Pay Now']")
	WebElement payNowButton;
	
	@FindBy(xpath="//input[@id='panToken']")
	WebElement panNumberfield;
	
	@FindBy(xpath="//a[text()='confirm']")
	WebElement confirmPan;
	
	@FindBy(xpath="//span[text()='Please enter PAN card number']")
	WebElement panWarning;
	
	

	
	public void hotelLink()
	{
		hotelLink.click();
		
	}
	
	public void cityName(String city)
	{
		cityName.click();
		cityText.sendKeys(city);
		dubai.click();
		
	}
	
	public void selectDate(String uDate, String uMonth, String uYear)

	{
		String monthVal = monthElement.getText().replaceAll("\\d", "");
		String yearVal = yearElement.getText();
		while (!(monthVal.equals(uMonth) && yearVal.equals(uYear))) {
			nextButton.click();
			monthVal = monthElement.getText().replaceAll("\\d", "");
			yearVal = yearElement.getText();
		}

		driver.findElement(By.xpath("//div[@class='DayPicker-Day' and text()='"+uDate+"']")).click();
	}
	
	public void numberOfRooms(int NumberOfRooms)
	{
		int numberofrooms = NumberOfRooms-1;
		rooms.click();
		driver.findElement(By.xpath("(//li[@class=''])["+numberofrooms+"]")).click();
	}
	
	public void numberOfAdults(int NumberOfAdults)
	{
		int numberofadults = NumberOfAdults-1;
		numberOfAdults.click();
		driver.findElement(By.xpath("(//li[@class=''])["+numberofadults+"]")).click();
	}
	
	public void numberOfAdultsModify(int existingNumberOfAdults, int modifiedNumberOfAdults)
	{
		int adultsNeededtoBeAdded = modifiedNumberOfAdults-existingNumberOfAdults;
		numberOfAdults.click();
		driver.findElement(By.xpath("(//li[@class=''])["+adultsNeededtoBeAdded+"]")).click();
	}
	
	public void applyClick()
	{
		apply.click();
	}
	
	public void searchButtonClick()
	{
		submit.click();
	}
	
	public void roomAndGuestModify()
	{
		roomAndGuestModify.click();
	}
	
	public void selectCurrecy()
	{
		currencyDropdown.click();
		indCurrency.click();
	}
	
	public String propertiesValidation()
	{
		String validationText=propertiesValidation.getText();
		return validationText;
		
	}
	
	public void selectStarCategory(String starCategory)
	{
		driver.findElement(By.xpath("//div[@class='makeFlex hrtlCenter']/span[text()='"+starCategory+"']")).click();
	}
	
	public void selectFirstHotelInList()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Listing_hotel_0']")));
		firstHotelInList.click();
	}
	
	public void addPersonalInfo(String fName, String lName, String email, String mNumber)
	{
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		emailAddress.sendKeys(email);
		mobileNumber.sendKeys(mNumber);
	}
	
	public void clickPayNow() 
	{
		payNowButton.click();
	}
	
	public void enterPanNumber(String panNumber)
	{
		panNumberfield.sendKeys(panNumber);
		confirmPan.click();
	}
	
	public void bookThisNowButton() 
	{
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
		bookThisNowButton.click();
	}
	
	public String panWarningMessage() 
	{
		String panvalidationMessage=panWarning.getText();
		return panvalidationMessage;
		
	}

}
