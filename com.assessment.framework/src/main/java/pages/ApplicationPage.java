package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplicationPage {

	public ApplicationPage(WebDriver driver) {
		this.driver = driver;
	}

	// formpage 1

	private WebDriver driver;

	private By GenderField = By.xpath("//label[@for='MR']");
	private By firstNameField = By.name("firstName");
	private By lastNameField = By.name("lastName");
	private By dateofBirth = By.id("fieldControl-input-dateOfBirth");
	private By emailField = By.id("fieldControl-input-email");
	private By telephonofield = By.id("fieldControl-input-mobilePhoneNumber");
	private By submitButton = By.xpath("//button[@type='submit']");
	private By ValidationPersonallbl = By.xpath("//h2[contains(text(),'Vos informations personnelles')]");

	// form page 2
	private By sameNameChkBox = By.xpath(
			"//input[@id=\"fieldControl-input-birthNameCheck\"]/following::label[@for='fieldControl-input-birthNameCheck']");
	private By placeofBirthField = By.xpath("//input[@name='placeOfBirth']");
	private By birthDepartmentdrpdn = By.id("fieldControl-input-departmentOfBirth");
	private By addressField = By.id("fieldControl-input-residentialAddressLine2");
	private By pincodeField = By.id("fieldControl-input-postcode");
	private By cityField = By.id("fieldControl-input-cityTown");
	private By residentialstatusdrpdn = By.id("fieldControl-input-personalResidentialStatus");

	public void fillApplicationForm(String firstName, String lastName, String email, String DOB, String telephono) {
		driver.findElement(GenderField).click();
		driver.findElement(firstNameField).sendKeys(firstName);
		driver.findElement(lastNameField).sendKeys(lastName);
		driver.findElement(dateofBirth).sendKeys(DOB);
		driver.findElement(emailField).sendKeys(email);
		driver.findElement(telephonofield).sendKeys(telephono);
		// clickWhenReady(submitButton);
		jsClick(submitButton);


	}

	public void fillApplicationForm2(String placeofBirth, String department, String address, String pincode,
			String city, String residentialStatus) {
		driver.findElement(sameNameChkBox).click();
		driver.findElement(placeofBirthField).sendKeys(placeofBirth);
		selectBirthdepartmentByText(department);
		driver.findElement(addressField).sendKeys(address);
		driver.findElement(pincodeField).sendKeys(pincode);
		driver.findElement(cityField).sendKeys(city);
		selectResidentialStatusByText(residentialStatus);
		jsClick(submitButton);
	}

	public void selectBirthdepartmentByText(String department) {
		WebElement dropdownElement = driver.findElement(birthDepartmentdrpdn);
		Select select = new Select(dropdownElement);
		select.selectByVisibleText(department);
	}

	public void selectResidentialStatusByText(String residentialStatus) {
		WebElement dropdownElement = driver.findElement(residentialstatusdrpdn);
		Select select = new Select(dropdownElement);
		select.selectByVisibleText(residentialStatus);
	}

	public void clickWhenReady(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public void jsClick(By locator) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

}
