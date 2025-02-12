package tests;

import base.BaseTest;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import pages.HomePage;
import reports.ExtentReportManager;
import pages.CardsPage;
import pages.GoldCardPage;
import pages.ApplicationPage;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.Helper;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;

public class FRCardApplicationTest extends BaseTest {

	@Test(dataProvider = "testData")
	public void testFRCardApplication(String firstName, String lastName, String email, String DOB, String telephono,
			String placeofBirth, String department, String address, String pincode, String city,
			String residentialStatus) {
		test = ExtentReportManager.createTest("FR Card Application - " + firstName + " " + lastName);
		homePage.clickOnCartesLink();
		test.info("Clicked on Cartes Link");
		homePage.acceptCookiesbtn();
		cardsPage.clickOnGoldCardLearnMore();
		test.info("Clicked on Gold Card Learn More");
		goldCardPage.clickOnApplyNow();
		takeScreenshot("FRCardApplicationTestapplynow", "Success");
		homePage.acceptCookiesbtn();
		applicationPage.fillApplicationForm(firstName, lastName, email, DOB, telephono);
		test.info("Filled Application Form1");
		// homePage.acceptCookiesbtn();
		applicationPage.fillApplicationForm2(placeofBirth, department, address, pincode, city, residentialStatus);
		test.info("Filled Application Form2");
		takeScreenshot("FRCardApplicationTest", "Success");
	}

	@DataProvider(name = "testData")
	public Object[][] getData() throws IOException {

		return new ExcelReader("testdata/testdata.xlsx").getTestData("Sheet1");
	}

}