package tests;

import java.io.IOException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import fixtures.DriverData;
import fixtures.TestOrchestration;
import pages.MyRoutePage;
import pages.PickupRoutePage;

public class CompletePickupShipmentTest extends BaseClass {

	@BeforeSuite
	public void setupSuite() throws InterruptedException {
		try {
			if (TestOrchestration.runCypressTests("npm run cypress:pickuptest")) {
				TestOrchestration.readResults();
			} else {
				throw new RuntimeException("Cypress tests failed. Cannot proceed with Appium tests.");
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to run Cypress tests or read results.", e);
		}
	}

	@Test
	public void testPickupShipment() throws IOException, InterruptedException {
		LoginTest loginTest = new LoginTest();
		loginTest.login();
		String expectedText = TestOrchestration.getForwardRefNumber();
		MyRoutePage myRoutePage = new MyRoutePage(driver);
		PickupRoutePage pickupRoutePage = new PickupRoutePage(driver);
		DriverData data = DriverData.readFromJson();

		// Verify the route info
		myRoutePage.verifyMyRouteInfo(expectedText);

		// Click the Start Route button
		pickupRoutePage.clickStartRouteButton();

		Thread.sleep(5000);

		myRoutePage.swipeUpToCompleteRoute();
		myRoutePage.clickOnCompleteShipment();
		myRoutePage.clickOnYesButton();

		Thread.sleep(2000);

		myRoutePage.clickNextButtonOnFirstScreen();
		myRoutePage.clickNextButtonOnSecondScreen();
		pickupRoutePage.EnterPRONumber(data.getPRONumber());
		pickupRoutePage.clickOnSubmitButton();

		Thread.sleep(15000);
	}
}
