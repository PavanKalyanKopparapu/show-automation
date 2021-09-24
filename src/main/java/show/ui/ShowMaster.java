package show.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import show.constants.LogConstants;
import show.constants.URLConstants;
import show.ui.locators.ShowMasterLocators;
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

public class ShowMaster extends PageObject {

	private static Logger logger = LoggerFactory.getLogger(ShowMaster.class);

	public WebDriver driver;
	Common common;
	private UtilFactory utilFactory;

	public ShowMaster() {
		driver = super.getDriver();
		utilFactory = new UtilFactory(driver);
	}

	@FindBy(xpath = ShowMasterLocators.SHOW_RADIO_BUTTON)
	private WebElementFacade showRadioButton;

	public void showRadioButtonClick() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		utilFactory.highlight(showRadioButton);
		showRadioButton.waitUntilVisible();
		clickOn(showRadioButton);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
}
