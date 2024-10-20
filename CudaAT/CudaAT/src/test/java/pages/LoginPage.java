package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage {

	AndroidDriver driver;

	public LoginPage(AndroidDriver d) {
		driver = d;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//android.widget.EditText[@text='email@address.com']")
	WebElement inputEmail;

	@FindBy(xpath = "//android.widget.EditText[@text='Password']")
	WebElement inputPassword;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Login']")
	WebElement clickLoginButton;

	@FindBy(xpath = "//android.widget.TextView[@text='Agree']")
	WebElement clickAgreeButton;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Agree']")
	WebElement clickSecondAgreeButton;

	public void EnterEmailAddress(String emailAddress) {
		inputEmail.sendKeys(emailAddress);
	}

	public void EnterPassword(String userPassword) {
		inputPassword.sendKeys(userPassword);
	}

	public void ClickLoginButton() {
		clickLoginButton.click();
	}

	public void scrollToAcceptAgreementButton() {

		Assert.assertTrue(
				driver.findElement(By.xpath("//android.widget.TextView[@text='Terms & Conditions']")).isDisplayed());

		driver.executeScript("mobile: scroll", ImmutableMap.of("direction", "down", "strategy", "-android uiautomator",
				"selector", "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Agree\"))"));

		clickAgreeButton.click();

	}

	public void scrollToAcceptPrivacyPolicy() {

		Assert.assertTrue(
				driver.findElement(By.xpath("//android.widget.TextView[@text='Privacy Policy']")).isDisplayed());

		driver.executeScript("mobile: scroll", ImmutableMap.of("direction", "down", "strategy", "-android uiautomator",
				"selector", "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Agree\"))"));

		clickSecondAgreeButton.click();
	}

}
