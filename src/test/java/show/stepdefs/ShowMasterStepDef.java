package show.stepdefs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;

import show.constants.FeatureNameConstants;
import show.constants.LogConstants;
import show.ui.LoginPage;
import show.ui.ShowMaster;
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
public class ShowMasterStepDef extends PageObject {

	private static Logger logger = LoggerFactory.getLogger(ShowMasterStepDef.class);

	@Managed
	public WebDriver driver;
	ShowMaster showMasterLogic;
	UtilFactory utilFactory;

	public ShowMasterStepDef() {
		driver = super.getDriver();
		new UtilFactory(driver);
	}

	@Given("^User click on show radio button$")
	public void showRadioButton() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		showMasterLogic.showRadioButtonClick();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

}
