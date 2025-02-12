package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CardsPage  {
	/*
	 * public CardsPage(WebDriver driver) { super(driver); acceptCookies(); // TODO
	 * Auto-generated constructor stub }
	 */

	private WebDriver driver;
    private By goldCardLearnMore = By.xpath("//a[@href=\"https://www.americanexpress.com/fr/carte-de-paiement/gold-card-americanexpress/?intlink=fr-proprietary-gold\"]//span[contains(text(),'En savoir plus')]");

	
	 public CardsPage(WebDriver driver) { this.driver = driver; }
	

    public void clickOnGoldCardLearnMore() {
        driver.findElement(goldCardLearnMore).click();
    }
}