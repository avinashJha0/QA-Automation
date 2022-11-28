package com.phpTravels.testCases;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import com.phpTravels.pageObjects.HotelPage;
import com.phpTravels.utilities.ReadConfig;

import junit.framework.Assert;

public class HotelTest extends BaseClass {
	
	Logger log = LogManager.getLogger("HotelTest");

	@SuppressWarnings("deprecation")
	@Test
	public void hotelTest() {
		HotelPage hp = new HotelPage(driver);
		hp.hotelLink();
		log.info("clicked on hotel link");
		hp.cityName("Dubai");
		log.info("selected city");
		hp.selectDate("12", "December", "2022");
		log.info("selected check in date");
		hp.selectDate("13", "December", "2022");
		log.info("selected checkout date");
		hp.numberOfAdults(3);
		log.info("selected number of adults");
		hp.numberOfRooms(3);
		log.info("selected number of rooms");
		hp.applyClick();
		log.info("clicked on apply");
		hp.searchButtonClick();
		log.info("clicked on search button");
		hp.roomAndGuestModify();
		log.info("clicked on modify rooms and guest");
		hp.numberOfRooms(2);
		log.info("modified number of rooms");
		hp.numberOfAdults(2);
		log.info("modified number of adults");
		hp.applyClick();
		log.info("clicked on apply");
		//hp.selectCurrecy();
		hp.searchButtonClick();
		log.info("search button");
	    Assert.assertEquals(hp.validationMessage, hp.propertiesValidation());
		
		hp.selectStarCategory("5 Star");
		log.info("selected star category");
		hp.selectFirstHotelInList();
		hp.bookThisNowButton();
		hp.addPersonalInfo("Avinash", "Jha", "avi96053@gmail.com", "8360080937");
		hp.clickPayNow();
		hp.enterPanNumber(" ");
		Assert.assertEquals(hp.panValidation, hp.panWarningMessage());
	}
	
	public void starHotelBookTest() 
	{
		
	}

}
