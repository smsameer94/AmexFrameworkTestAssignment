package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage  {

	private WebDriver driver;
    //private By acceptCookies = By.id("user-consent-management-granular-banner-accept-all-button");
    private By acceptCookies = By.xpath("//button[contains(text(),'Tout Accepter')]");
    private By cartesLink = By.xpath("//p[contains(text(),'Cartes American Express')]");
	
	  public HomePage(WebDriver driver)
	  { if (driver == null) {  throw new 
		  IllegalArgumentException("WebDriver instance is null"); 
	  } 
	  this.driver = driver; 
	  }
	  
 

    public void clickOnCartesLink() {
        driver.findElement(cartesLink).click();
    }

	public void acceptCookiesbtn() {
		
		try {
		driver.findElement(acceptCookies).click();}
		catch(Exception e)
		{
			System.out.println("No Cookie Banner Displayed");
		}
	}
}
