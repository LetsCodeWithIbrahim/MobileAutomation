package pages;

import java.time.Duration;
import java.util.Collections;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class MyRoutePage {

	private AndroidDriver driver;
	private WebDriverWait wait;

	// Constants for commonly used XPaths
	private static final String NEXT_BUTTON_XPATH = "//android.widget.TextView[@text='Next']";

	public MyRoutePage(AppiumDriver d) {
		driver = (AndroidDriver) d;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@FindBy(xpath = "//android.widget.EditText[@text='000000000']")
	private WebElement enterReading;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Start Route, ']")
	private WebElement startRouteButton;

	@FindBy(xpath = "//android.widget.TextView[@text='Submit']")
	private WebElement submitInitialOdometerReading;

	@FindBy(xpath = "//android.view.View[@content-desc='']/android.view.ViewGroup")
	private WebElement clickOnStartedRoutes;

	@FindBy(xpath = "//android.widget.TextView[@text='Complete Shipment']")
	private WebElement clickOnCompleteShipment;

	@FindBy(xpath = "//android.widget.TextView[@text='Yes']")
	private WebElement clickOnYesButton;

	@FindBy(xpath = "(//android.widget.TextView[@text=''])[1]")
	private WebElement clickOnFirstFreightCamIcon;

	@FindBy(xpath = "//android.widget.TextView[@text='']")
	private WebElement clickToTakePicture;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='']")
	private WebElement clickToSavePicture;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Crop']")
	private WebElement saveCroppedPicture;

	@FindBy(xpath = "(//android.widget.TextView[@text=''])[5]")
	private WebElement clickOnTakePodImage;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='']")
	private WebElement clickToTakeImage;

	@FindBy(xpath = "//android.widget.TextView[@text='']")
	private WebElement savePodImage;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Crop']")
	private WebElement saveCroppedPodImage;

	@FindBy(xpath = "//android.widget.TextView[@text='Next']")
	private WebElement clickOnNextButton3;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Signatures, Typed Name Required, First Name, Last Name\"]/android.view.ViewGroup/android.widget.EditText[1]")
	private WebElement typeFirstName;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Submit']")
	private WebElement clickOnSubmitButton;

	public WebElement getMyRouteInfo(String referenceText) {
		String xpath = String.format("//android.widget.TextView[@text='%s']", referenceText);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public String getMyRouteInfoText(String referenceText) {
		WebElement element = getMyRouteInfo(referenceText);
		return element.getText(); // Extract the text from the element
	}

	public void verifyMyRouteInfo(String expectedText) {
		String actualText = getMyRouteInfoText(expectedText);
		System.out.println("Reference Number Retrieved Using Appium: " + actualText);
		System.out.println("Reference Number Retrieved Using Cypress: " + expectedText);

		Assert.assertEquals(actualText, expectedText, "The route info text does not match.");

		// Print message if assertion passes
		System.out.println("Assertion Passed: Route info text matches the expected text.");
	}

	public void clickStartRouteButton(String initialReading) throws InterruptedException {
		Assert.assertTrue(startRouteButton.isDisplayed(), "Start Route button is not displayed.");

		// Log to console if assertion passes
		System.out.println("Assertion Passed: Start Route button is displayed.");

		startRouteButton.click();

		String textToCheck = "Vehicle Inspection";

		Thread.sleep(5000);

		if (isElementInDOM(textToCheck)) {
			System.out.println("'" + textToCheck + "' is present in the DOM.");
			enterReading.sendKeys(initialReading);
			
			Thread.sleep(3000);
			
			submitInitialOdometerReading.click();
			System.out.println("Initial odometer reading entered successfully.");

			Thread.sleep(3000);
			
			clickOnStartedRoutes.click();
			System.out.println("Clicked on 'Start Route' successfully.");

		} else {
			System.out.println("'" + textToCheck + "' is NOT present in the DOM.");
			clickOnStartedRoutes.click();
			System.out.println("Clicked on 'Start Route' successfully.");
		}
	}

	public boolean isElementInDOM(String elementText) {
		String pageSource = driver.getPageSource();
		return pageSource.contains(elementText); // Modify to check a relevant part of the page
	}

	public void swipeUpToCompleteRoute() {
		Dimension size = driver.manage().window().getSize();
		int startX = size.getWidth() / 2;
		int startY = (int) (size.getHeight() * 0.87);
		int endY = 0;

		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence sequence = new Sequence(finger1, 1)
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(200)))
				.addAction(
						finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX, endY))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singleton(sequence));
	}

	public void clickOnCompleteShipment() {
		clickOnCompleteShipment.click();
	}

	public void clickOnYesButton() {
		clickOnYesButton.click();
	}

	public void clickNextButtonOnFirstScreen() {
		clickNextButton(NEXT_BUTTON_XPATH);
	}

	public void clickNextButtonOnSecondScreen() {
		clickNextButton(NEXT_BUTTON_XPATH);
	}

	private void clickNextButton(String xpath) {
		WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		nextButton.click();
	}

	public void addFirstFreightImage() {
		clickOnFirstFreightCamIcon.click();
	}

	public void clickToTakePicture() {
		clickToTakePicture.click();
	}

	public void clickToSavePicture() {
		clickToSavePicture.click();
	}

	public void saveCroppedImage() {
		saveCroppedPicture.click();
	}

	public void clickOnTakePodImageCamIcon() {
		clickOnTakePodImage.click();
	}

	public void clickToTakePodImage() {
		clickToTakeImage.click();
	}

	public void clickToSavePodImage() {
		savePodImage.click();
	}

	public void saveCroppedPodImage() {
		saveCroppedPodImage.click();
	}

	public void clickNextButtonOnThirdScreen() {
		clickNextButton(NEXT_BUTTON_XPATH);
	}

	public void firstName(String firstName) {
		typeFirstName.sendKeys(firstName);
	}

	public void clickOnSubmitButton() {
		clickOnSubmitButton.click();
	}
}
