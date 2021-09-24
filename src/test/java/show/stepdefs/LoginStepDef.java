package show.stepdefs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;

import show.constants.FeatureNameConstants;
import show.constants.LogConstants;
import show.ui.LoginPage;
import show.util.UtilFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Managed;

/**
 * @author Parth Moradiya
 * 
 * 
 * 
 * 
 * 
 *
 */
public class LoginStepDef extends PageObject {

	private static Logger logger = LoggerFactory.getLogger(LoginStepDef.class);

	@Managed
	public WebDriver driver;
	LoginPage loginPageBusinessLogic;
	UtilFactory utilFactory;

	public LoginStepDef() {
		driver = super.getDriver();
		new UtilFactory(driver);
	}

	@Given("^User is on the SRK home page$")
	public void navigateToLink() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("User is on home page");
		loginPageBusinessLogic.openUrl();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@When("^User enters username and password$")
	public void clickOnLogin(List<List<String>> loginCredential) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		loginPageBusinessLogic.enterUserCredentials(
				utilFactory.readJSON(FeatureNameConstants.LOGIN, loginCredential.get(0).get(0), "username",
						"login.json"),
				utilFactory.readJSON(FeatureNameConstants.LOGIN, loginCredential.get(0).get(0), "password",
						"login.json"));
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@And("^User click the 'Login' button$")
	public void loginClick() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		loginPageBusinessLogic.getLoginButton();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Then("^User gets validation message$")
	public void assertMessage1(List<List<String>> message) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("Scenario Name  :: " + message.get(0).get(0));
		String expectedMessage = utilFactory.readJSON(FeatureNameConstants.LOGIN, message.get(0).get(0),
				"message", "login.json");
		assertEquals(expectedMessage, loginPageBusinessLogic.getMessage(expectedMessage));
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

}