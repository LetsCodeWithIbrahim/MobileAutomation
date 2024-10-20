package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class PickupRoutePage {
	AndroidDriver driver;
	WebDriverWait wait;

	public PickupRoutePage(AndroidDriver d) {
		driver = (AndroidDriver) d;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Start Route, ']")
	private WebElement startRouteButton;
	
	@FindBy(xpath = "//android.widget.EditText[@text=\"Enter PRO #\"]")
	WebElement typePRONumber;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Submit']")
	WebElement clickOnSubmitButton;
	
	public void clickStartRouteButton() {
		startRouteButton.click();		
	}
	
	public void EnterPRONumber(String PRONumber) {
		typePRONumber.sendKeys(PRONumber);
	}
	
	public void clickOnSubmitButton() {
		clickOnSubmitButton.click();
	}

}