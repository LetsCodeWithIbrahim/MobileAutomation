package tests;

import java.io.IOException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import fixtures.DriverData;
import fixtures.OdometerData;
import fixtures.TestOrchestration;
import pages.MyRoutePage;

public class MyRouteTest extends BaseClass {

	@BeforeSuite
	public void setupSuite() throws InterruptedException {
		try {
			if (TestOrchestration.runCypressTests("npm run cypress:test")) {
				TestOrchestration.readResults();
			} else {
				throw new RuntimeException("Cypress tests failed. Cannot proceed with Appium tests.");
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to run Cypress tests or read results.", e);
		}
	}

	@Test
	public void testMyRouteInfo() throws IOException, InterruptedException {
		LoginTest loginTest = new LoginTest();
		loginTest.login();
		String expectedText = TestOrchestration.getForwardRefNumber();
		OdometerData data = OdometerData.readFromJson();
		DriverData driverData = DriverData.readFromJson();
		MyRoutePage myRoutePage = new MyRoutePage(driver);

		// Verify the route info
		myRoutePage.verifyMyRouteInfo(expectedText);

		// Click the Start Route button
		myRoutePage.clickStartRouteButton(data.getInitialtOdometerReading());

		Thread.sleep(5000);

		myRoutePage.swipeUpToCompleteRoute();
		myRoutePage.clickOnCompleteShipment();
		myRoutePage.clickOnYesButton();

		Thread.sleep(1500);

		myRoutePage.clickNextButtonOnFirstScreen();
		myRoutePage.clickNextButtonOnSecondScreen();
		myRoutePage.addFirstFreightImage();
		myRoutePage.clickToTakePicture();
		myRoutePage.clickToSavePicture();
		myRoutePage.saveCroppedImage();

		Thread.sleep(2000);

		myRoutePage.clickOnTakePodImageCamIcon();
		myRoutePage.clickToTakePodImage();
		myRoutePage.clickToSavePodImage();
		myRoutePage.saveCroppedPodImage();
		myRoutePage.clickNextButtonOnThirdScreen();

		Thread.sleep(1500);

		myRoutePage.firstName(driverData.getFirstName());
		myRoutePage.clickOnSubmitButton();

		Thread.sleep(10000);
	}
}
