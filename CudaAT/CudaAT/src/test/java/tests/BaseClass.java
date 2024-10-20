package tests;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;

public class BaseClass {

	static AndroidDriver driver;

	@BeforeTest
	public void setup() throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "CudaAT");
		capabilities.setCapability("udid", "emulator-5554");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "13");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("appPackage", "com.eeldatasystems.cudaexpo");
		capabilities.setCapability("appActivity", "com.eeldatasystems.cudaexpo.MainActivity");
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("printPageSourceOnFindFailure", true);

		URL url = URI.create("http://127.0.0.1:4723/").toURL();

		driver = new AndroidDriver(url, capabilities);

	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
