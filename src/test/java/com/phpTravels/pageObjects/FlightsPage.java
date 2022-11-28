package com.phpTravels.pageObjects;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightsPage {

	WebDriver driver;
	public String WarningMessage = "Kindly add all travellers before proceeding";

	public FlightsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href='https://www.makemytrip.com/flights/']")
	WebElement flight;

	@FindBy(xpath = "//label[@for='travellers']")
	WebElement passengers;

	@FindBy(xpath = "//button[@id='currency']")
	WebElement currency;

	@FindBy(xpath = "//li[@data-cy='roundTrip']")
	WebElement roundTrip;

	@FindBy(xpath = "//label[@for='fromCity']")
	WebElement flyingFrom;

	@FindBy(xpath = "//input[@placeholder='From']")
	WebElement fromText;

	@FindBy(xpath = "//li[@data-suggestion-index='0']")
	WebElement firstIndexSelection;

	@FindBy(xpath = "//label[@for='toCity']")
	WebElement toDestination;

	@FindBy(xpath = "//input[@placeholder='To']")
	WebElement toText;

	@FindBy(xpath = "//label[@for='departure']")
	WebElement departure;

	@FindBy(xpath = "//button[@data-cy='travellerApplyBtn']")
	WebElement applyButton;

	@FindBy(xpath = "//a[text()='Search']")
	WebElement search;

	@FindBy(xpath = "//button[text()='Book Now']")
	WebElement bookNow;

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement continueButton;

	@FindBy(xpath = "//font[text()='Kindly add all travellers before proceeding']")
	WebElement warning;

	@FindBy(xpath = "//div[@class='DayPicker-Caption']/div")
	WebElement monthElement;

	@FindBy(xpath = "//div[@class='DayPicker-Caption']/div/span")
	WebElement yearElement;

	@FindBy(xpath = "//span[@aria-label='Next Month']")
	WebElement nextButton;

	@FindBy(xpath = "//p[@class = 'blackFont fontSize18' and text()='Still unsure about this trip?']")
	WebElement textClick;
	
	@FindBy(xpath="//span[text()='Traveller Details']")
	WebElement travelDetails;

	public void flight() {
		flight.click();
	}
	
	public void travelDetails()
	{
		travelDetails.click();
	}

	public void FlightType(String flighttype) {
		driver.findElement(By.xpath("//li[@data-cy='travelClass-" + flighttype + "']")).click();
		// flightTypeBusiness.click();
	}

	public void passengersClick() {
		passengers.click();
	}

	public void numberOfAdults(String numberadults) {
		driver.findElement(By.xpath("//li[@data-cy='adults-" + numberadults + "']")).click();
		// numberOfAdults.click();

	}

	public void currency() {
		Select curr = new Select(currency);
		curr.selectByVisibleText("INR");
	}

	public void roundTrip() {
		roundTrip.click();

	}

	public void flyingFrom(String city) {
		flyingFrom.click();
		fromText.sendKeys(city);
		driver.findElement(By.xpath("//p[contains(text(),'" + city + "')]")).click();

	}

	public void toDestination(String city) {
		toDestination.click();
		toText.sendKeys(city);
		driver.findElement(By.xpath("//p[contains(text(),'" + city + "')]")).click();

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

		driver.findElement(By.xpath("//div[@class='dateInnerCell']/p[text()='" + uDate + "']")).click();
	}

	public void apply() {
		applyButton.click();
	}

	public void searchFlights() {
		search.click();
	}

	public void bookNow() {
		bookNow.click();
	}

	public void continueBook() {
		continueButton.click();
	}

	public void continueBookfinal() {
		
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    int size = tabs2.size();
	    driver.switchTo().window(tabs2.get(size-1));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//p[@class = 'blackFont fontSize18' and text()='Still unsure about this trip?']")));

		JavascriptExecutor js = (JavascriptExecutor) driver;
//Scroll down till the bottom of the page
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		continueButton.click();
	}
	
	public String warning()
	{
		String WarningText=warning.getText();
		return WarningText;
		
	}

}
