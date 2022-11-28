package com.phpTravels.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	public void onStart(ITestContext testContest) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss");
		String timeStamp = dtf.format(now);
		String repName = "Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + repName);
		htmlReporter.loadConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "LocalHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Avinash Jha");

		htmlReporter.config().setDocumentTitle("Make My Trip Test Project");
		htmlReporter.config().setReportName("QA Automation Report for MMT Website");
		htmlReporter.config().setTheme(Theme.DARK);

	}

	public void onTestSuccess(ITestResult tr) {
		test = extent.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult tr) {
		test = extent.createTest(tr.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
        
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";

		File file = new File(screenshotPath);

		if (file.exists()) {
			try {
				test.fail("Screenshot is below : " + test.addScreenCaptureFromPath(screenshotPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult tr) {
		test = extent.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
