package com.phpTravels.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.phpTravels.pageObjects.FlightsPage;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class FlightTest extends BaseClass{
	
	
	@Test (dataProvider="FlightTestData")
	public void FlightBookTest(String flyfrom, String FlyTo, String BoardDate,String BoardMonth, String BoardYear, String ReturnDate, String ReturnMonth, String ReturnYear, String NumberofPassenger, String FlightType) 
	{

	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	FlightsPage fp= new FlightsPage(driver);
	fp.flight();
	log.info("clicked on flight link");
	fp.roundTrip();
	log.info("clicked on round trip radio button");
	fp.flyingFrom(flyfrom);
	log.info("Flying from city selected");
	fp.toDestination(FlyTo);
	log.info("Flying to city selected");
	fp.selectDate(BoardDate, BoardMonth, BoardYear);
	log.info("Flying from date selected");
	fp.selectDate(ReturnDate, ReturnMonth, ReturnYear);
	log.info("Flying to date selected");
	fp.passengersClick();
	fp.numberOfAdults(NumberofPassenger);
	log.info("Number of passengers selected");
	fp.FlightType(FlightType);
	log.info("Flying class selected");
	fp.apply();
	fp.searchFlights();
	log.info("Clicked on flight search");
	fp.bookNow();
	fp.continueBook();
	
	fp.continueBookfinal();
	
	//Assert.assertEquals(fp.WarningMessage, fp.warning());
	if (fp.WarningMessage.equals(fp.warning())) {
		Assert.assertTrue(true);
	}
	else {
		captureScreenshot(driver, "FlightTest");
		Assert.assertTrue(false);
	}
	
	}
	

}
