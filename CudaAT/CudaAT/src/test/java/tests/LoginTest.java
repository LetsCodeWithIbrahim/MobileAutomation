package tests;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.Test;
import fixtures.Creds;
import pages.LoginPage;

public class LoginTest extends BaseClass {
	@Test
	public void login() throws InterruptedException, IOException {
		try {

			LoginPage loginPG = new LoginPage(driver);
			Creds creds = Creds.readFromJson();

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8000));
			loginPG.EnterEmailAddress(creds.getEmail());

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
			loginPG.EnterPassword(creds.getPassword());

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
			loginPG.ClickLoginButton();

			loginPG.scrollToAcceptAgreementButton();

			loginPG.scrollToAcceptPrivacyPolicy();

			Thread.sleep(10000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
