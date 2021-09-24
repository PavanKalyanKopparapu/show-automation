package show.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import show.constants.LogConstants;
import show.constants.URLConstants;
import show.ui.locators.LoginLocators;
import show.util.UtilFactory;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * @author Parth Moradiya
 * 
 *
 *
 **/

public class LoginPage extends PageObject {

	private static Logger logger = LoggerFactory.getLogger(LoginPage.class);

	public WebDriver driver;
	Common common;
	private UtilFactory utilFactory;

	public LoginPage() {
		driver = super.getDriver();
		utilFactory = new UtilFactory(driver);
	}

	@FindBy(css = LoginLocators.USERNAME_TXT_BOX)
	private WebElement usernameTxtBox;

	@FindBy(css = LoginLocators.PASSWORD_TXT_BOX)
	private WebElement passwordTxtBox;

	@FindBy(css = LoginLocators.LOGIN_BUTTON)
	private WebElement loginBtn;

	public void openUrl() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.debug(URLConstants.setUrl());
		super.openUrl(URLConstants.setUrl());
		common.waitForPageLoaded(super.getDriver());
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void enterUserCredentials(String email, String password) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		utilFactory.highlight(usernameTxtBox);
		usernameTxtBox.clear();
		usernameTxtBox.sendKeys(email);
		utilFactory.highlight(passwordTxtBox);
		passwordTxtBox.clear();
		passwordTxtBox.sendKeys(password);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void getLoginButton() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		utilFactory.highlight(loginBtn);
		loginBtn.click();
		try {
			java.util.concurrent.TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		common.waitForPageLoaded(super.getDriver());
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public String getMessage(String messageData) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		utilFactory.waitForElementToBeVisible(LoginLocators.LOGIN_VALIDATION_MSG, "xpath");
		WebElementFacade message = find(By.xpath(LoginLocators.LOGIN_VALIDATION_MSG));
		utilFactory.highlight(message);
		String messageText = message.getText();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
		return messageText;
	}
}
