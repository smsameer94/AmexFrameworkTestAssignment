package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void acceptCookies() {
		try {
			By cookiesBanner = By.xpath("//button[contains(text(),'Tout Accepter')]"); // Update locator as per your
																						// site
			WebElement acceptButton = driver.findElement(cookiesBanner);
			if (acceptButton.isDisplayed()) {
				acceptButton.click();
			}
		} catch (Exception e) {
			System.out.println("Cookies banner not displayed.");
		}
	}

	public void clickWhenReady(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
}
