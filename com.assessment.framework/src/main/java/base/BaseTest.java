package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.github.bonigarcia.wdm.WebDriverManager;
import reports.ExtentReportManager;
import utils.ConfigReader;
import utils.ExcelReader;

import pages.HomePage;
import pages.CardsPage;
import pages.GoldCardPage;
import pages.ApplicationPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class BaseTest {
	protected WebDriver driver;
	protected ExtentReports extent;
	protected ExtentTest test;
	protected ConfigReader configReader;
	protected ExcelReader excelReader;
	protected HomePage homePage;
	protected CardsPage cardsPage;
	protected GoldCardPage goldCardPage;
	protected ApplicationPage applicationPage;
	

	@BeforeMethod
	public void setUpTest() {
		// WebDriver Initialization
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*", "--disable-dev-shm-usage", "--no-sandbox");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		//Configurations
		configReader = new ConfigReader();
		driver.get(configReader.getProperty("baseURL"));
		homePage = new HomePage(driver);
		cardsPage = new CardsPage(driver);
		goldCardPage = new GoldCardPage(driver);
		applicationPage = new ApplicationPage(driver);
		extent = ExtentReportManager.getInstance();

	}

	@AfterMethod
	public void tearDownTest(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail("Test Failed: " + result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(result.getName(), "FAIL")).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed");
		} else {
			test.skip("Test Skipped");
		}

		ExtentReportManager.flushReports();

		if (driver != null) {
			driver.quit();
		}
	}

	public String takeScreenshot(String testName, String status) { String
	  filePath = "screenshots/" + testName + "_" + status + ".png"; try { File
	  srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  File destFile = new
	  File(filePath); if (destFile.exists()) { destFile.delete(); }
	  Files.createDirectories(Paths.get("screenshots"));
	 Files.copy(srcFile.toPath(), destFile.toPath()); } catch (IOException e) {
	 e.printStackTrace(); } return filePath; 
	 }
	 

}