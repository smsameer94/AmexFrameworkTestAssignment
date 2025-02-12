package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoldCardPage  {
	/*
	 * public GoldCardPage(WebDriver driver) { super(driver); acceptCookies(); //
	 * TODO Auto-generated constructor stub }
	 */
	private WebDriver driver;
    private By applyNowButton = By.xpath("//button[@id='compare']/parent::div[@class='sc_mo_call-to-actions-with-links_itemContainer sc_paddingTop_10']/parent::span/div/a[contains(text(),'Demandez votre Carte')]");

	
	  public GoldCardPage(WebDriver driver) { this.driver = driver; }
	 

    public void clickOnApplyNow() {
        driver.findElement(applyNowButton).click();
    }
}