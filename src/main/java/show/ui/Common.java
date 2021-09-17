package show.ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ibm.icu.impl.Assert;
import show.constants.LogConstants;
import show.ui.locators.CommonLocators;
import show.util.UtilFactory;
import cucumber.api.Scenario;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * This contains the common utilities
 * 
 * @author Parth Moradiya
 * 
 *
 **/
public class Common extends PageObject {

	UtilFactory utilFactory = new UtilFactory(super.getDriver());
	private static Logger logger = LoggerFactory.getLogger(Common.class);

	@FindBy(xpath = CommonLocators.GROWL_MESSAGE)
	private WebElementFacade growlDialog;

	@FindBy(css = CommonLocators.SALES_MASTER_DASHBOARD)
	private WebElementFacade salesMasterDashboard;

	@FindBy(xpath = CommonLocators.GROWL_MESSAGE_CLOSE)
	private WebElementFacade growlDialogClose;

	@FindBy(xpath = CommonLocators.REDIRECT_TO_DASHBOARD)
	private WebElementFacade dashboard;

	@FindBy(xpath = CommonLocators.CLOSE_POPUP)
	private WebElementFacade closePopup;

	@FindBy(xpath = CommonLocators.PAGE_LOADER)
	private static WebElementFacade pageLoader;
	
	@FindBy(xpath = CommonLocators.FILTER_ICON)
	private WebElementFacade filterIcon;
	
	@FindBy(xpath = CommonLocators.TEXT_FILTER)
	private WebElementFacade textFilter;
	
	@FindBy(xpath = CommonLocators.FILTER_TYPE)
	private WebElementFacade filterType;

	public String getGrowlMessage() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		utilFactory.waitForElementToBeVisible(CommonLocators.GROWL_MESSAGE, "xpath");
		if (growlDialog.isCurrentlyVisible()) {
			utilFactory.highlight(growlDialog);
			String messageText = growlDialog.getText();
			utilFactory.highlight(growlDialogClose);
			growlDialogClose.waitUntilClickable();
			growlDialogClose.click();
			return messageText;
		} else {
			utilFactory.highlight(growlDialog);
			logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
			return "No Meesage found";
		}
	}

	public String getGrowlMessageIfHavingExactText(String messageText) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			growlDialog.waitUntilVisible();
			WebElementFacade growlMessageExtended = find(By.xpath(CommonLocators.GROWL_MESSAGE
					+ CommonLocators.GROWL_DIALOG_EXTENDED.replace("messageText", messageText)));
			growlMessageExtended.waitUntilVisible();
			if (growlMessageExtended.isCurrentlyVisible()) {
				utilFactory.highlight(growlMessageExtended);
				logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
				return growlMessageExtended.getText();
			} else {
				logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
				return "No Message Found";
			}
		} catch (WebDriverException e) {
			return e.getMessage();
		}
	}

	public boolean getGrowlMessageIfContainsText(String messageText) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		WebElementFacade growlMessageExtended = find(
				By.xpath(CommonLocators.GROWL_CONTAINS_DIALOG_EXTENDED.replace("messageText", messageText)));
		growlMessageExtended.waitUntilVisible();
		if (growlMessageExtended.isCurrentlyVisible()) {
			utilFactory.highlight(growlMessageExtended);
			utilFactory.highlight(growlDialogClose);
			growlDialogClose.click();
			logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
			return true;
		} else {
			logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
			return false;
		}
	}

	public void clickNoPopup() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		List<WebElementFacade> deleteConfirmation = findAll(By.xpath(CommonLocators.CLICK_NO_POPUP));
		for (WebElementFacade delete : deleteConfirmation) {
			if (delete.isCurrentlyVisible()) {
				utilFactory.highlight(delete);
				delete.click();
			}
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void clickYesPopup() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		List<WebElementFacade> deleteConfirmation = findAll(By.xpath(CommonLocators.CLICK_YES_POPUP));
		for (WebElementFacade delete : deleteConfirmation) {
			if (delete.isCurrentlyVisible()) {
				utilFactory.highlight(delete);
				delete.click();
			}
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void gotoLinkFromCss(String linkName) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		WebElementFacade link = find(By.xpath(CommonLocators.REDIRECT_TO_LINK.replace("linkText", linkName)));
		link.waitUntilVisible();
		utilFactory.scrollToElement(link);
		utilFactory.highlight(link);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			logger.error(
					LogConstants.INTERRUPT_EXCEPTION + e + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		link.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error(
					LogConstants.INTERRUPT_EXCEPTION + e + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void labelForCss(String labelName) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		WebElementFacade label = find(By.xpath(CommonLocators.LABEL_NAME.replace("labeltext", labelName)));
		label.waitUntilVisible();
		utilFactory.scrollToElement(label);
		utilFactory.highlight(label);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error(
					LogConstants.INTERRUPT_EXCEPTION + e + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		label.click();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void goToMaster(String tabType) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		WebElementFacade mastersPage = find(By.xpath(CommonLocators.NAVIGATE_MASTER_PAGE.replace("tabType", tabType)));
		mastersPage.waitUntilVisible();
		utilFactory.highlight(mastersPage);
		mastersPage.click();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	/*
	 * Wait for page load
	 */
	public void waitForPageLoaded(WebDriver driver) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			logger.debug(error.toString());
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	/**
	 * 
	 * To retrieve the current execution feature filename
	 * 
	 * @param scenario current cucumber execution scenario
	 * @return feature file name
	 */
	public String getFeatureName(Scenario scenario) {
		String featureFilePath = scenario.getUri();
		String featureFileName = featureFilePath.substring((featureFilePath.lastIndexOf("/") + 1),
				featureFilePath.indexOf("."));
		return featureFileName;
	}

	public void goToSalesMasterDashboard() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		salesMasterDashboard.waitUntilVisible();
		utilFactory.scrollToTop();
		utilFactory.scrollToElement(salesMasterDashboard);
		utilFactory.highlight(salesMasterDashboard);
		salesMasterDashboard.click();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public boolean checkInputValidations(String messageText) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		WebElementFacade inputValidation = find(
				By.xpath(CommonLocators.INPUT_VALIDATION.replace("errorText", messageText)));
		utilFactory.scrollToElement(inputValidation);
		utilFactory.executeScript(super.getDriver(), "arguments[0].classList.remove('alert-danger');", inputValidation);
		utilFactory.highlight(inputValidation);
		if (inputValidation.isCurrentlyVisible()) {
			logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
			return true;
		} else {
			logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
			return false;
		}
	}

	/**
	 * To select the value for the drop down - her drop down is not the Select class
	 * its combination of inputbox and list of elements
	 * 
	 * @param listOptions : list of option available to select
	 * @param selectValue : value to be selected from the list
	 */
	public void selectFromList(List<WebElementFacade> saleGroupNameList, String saleTypeGroupName) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		Boolean findFlag = false;
		for (WebElementFacade element : saleGroupNameList) {
			if (element.getText().trim().equalsIgnoreCase(saleTypeGroupName)) {
				utilFactory.highlight(element);
				clickOn(element);
				findFlag = true;
				break;
			}
		}
		if (findFlag == false) {
			logger.debug("Element " + saleTypeGroupName + " not found in list.");
			Assert.fail("Element " + saleTypeGroupName + " not found in list.");
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	/**
	 * To perform the add/edit/delete click operation in grid
	 * 
	 * @param startTag   : start of parent element locator
	 * @param inputTag   : add/edit/delete icon
	 * @param endTag     : end of parent element locator
	 * @param checkValue
	 */
	public void clickInGrid(String startTag, String inputTag, String endTag, String checkValue) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		Boolean clickFlag = false;
		List<WebElement> gridList = super.getDriver().findElements(By.xpath(startTag + inputTag));
		int row = 1;
		for (WebElement childElements : gridList) {
			if (childElements.getText().trim().equalsIgnoreCase(checkValue)) {
				WebElement clickElement = childElements.findElement(By.xpath(startTag + "[" + row + "]" + endTag));
				utilFactory.scrollToElement(clickElement);
				utilFactory.highlight(clickElement);
				clickOn(clickElement);
				clickFlag = true;
				break;
			}
			row++;
		}
		if (clickFlag == false) {
			logger.debug("Search Value " + checkValue + " not found");
			Assert.fail("Search Value " + checkValue + " not found");
		}
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	/**
	 * To perform the add/edit/delete click operation in grid
	 * 
	 * @param startTag   : start of parent element locator
	 * @param inputTag   : add/edit/delete icon
	 * @param endTag     : end of parent element locator
	 * @param checkValue
	 */
	public void clickInGridByValue(String startTag, String inputTag, String endTag, String checkValue) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		Boolean clickFlag = false;
		List<WebElement> gridList = super.getDriver().findElements(By.xpath(startTag + inputTag));
		for (WebElement childElements : gridList) {
			if (childElements.getAttribute("value").equalsIgnoreCase(checkValue)) {
				WebElement clickElement = childElements.findElement(By.xpath(startTag + endTag));
				utilFactory.highlight(clickElement);
				clickOn(clickElement);
				clickFlag = true;
				break;
			}
		}
		if (clickFlag == false) {
			Assert.fail("Search Value " + checkValue + " not found");
		}
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	/**
	 * To perform the search, verify and edit operation in grid
	 * 
	 * @param startTag          : Start of parent locator
	 * @param inputTag          : End of parent locator
	 * @param                   saleGroupDisplayName:- search the element in grid
	 * @param saleGroupEditName Edit value to the enter
	 */
	public void editInGridByValue(String startTag, String inputTag, String saleGroupDisplayName,
			String saleGroupEditName) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		Boolean clickFlag = false;
		List<WebElement> editList = super.getDriver().findElements(By.xpath(startTag + inputTag));
		for (WebElement childElements : editList) {
			if (childElements.getAttribute("value").equalsIgnoreCase(saleGroupDisplayName)) {
				utilFactory.highlight(childElements);
				typeInto(childElements, saleGroupEditName);
				clickFlag = true;
				break;
			}
		}
		if (clickFlag == false) {
			Assert.fail("Search Value " + saleGroupDisplayName + " not found");
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	/***
	 * To edit the grid drop down value
	 * 
	 * @param scenario    : scenario name/description from feture file
	 * @param scenarioId  : scenario value
	 * @param startTag    : parent element to uniquely identify grid elements, used
	 *                    to create dynamic xpath
	 * @param inputTag    : child element to uniquely identify grid elements, used
	 *                    to create dynamic xpath
	 * @param searchVlaue
	 * @param editVaue
	 */
	public void selectValueToEditInGrid(Scenario scenario, String scenarioId, String startTag, String inputTag,
			List<WebElementFacade> optionList, String searchVlaue, String editVaue) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		Boolean clickFlag = false;
		List<WebElement> editList = super.getDriver().findElements(By.xpath(startTag + inputTag));
		int row = 1;
		for (WebElement childElements : editList) {
			if (childElements.getText().trim().equalsIgnoreCase(searchVlaue)) {
				WebElement editElement = childElements.findElement(By.xpath(startTag + "[" + row + "]" + inputTag));
				utilFactory.highlight(editElement);
				clickOn(editElement);
				selectFromList(optionList, editVaue);
				clickFlag = true;
				break;
			}
			row++;
		}
		if (clickFlag == false) {
			Assert.fail("Search Value " + searchVlaue + " not found");
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	/***
	 * To check value present in grid
	 * 
	 * @param gridListValues : list of grid values
	 * @param searchValue    : search value in grid
	 * @return success/failure flag
	 */
	public boolean checkInGrid(List<WebElementFacade> gridListValues, String searchValue) {
		boolean searchFound = false;
		for (WebElementFacade searchElement : gridListValues) {
			if (searchElement.getText().trim().equalsIgnoreCase(searchValue)) {
				searchFound = true;
				utilFactory.scrollToElement(searchElement);
				utilFactory.highlight(searchElement);
				break;
			}
		}
		return searchFound;
	}

	/***
	 * To check value present in grid and click on it
	 * 
	 * @param gridListValues : list of grid values
	 * @param searchValue    : search value in grid
	 * @return success/failure flag
	 */
	public void clickInGrid(List<WebElementFacade> gridListValues, String searchValue) {
		boolean searchFound = false;
		for (WebElementFacade searchElement : gridListValues) {
			if (searchElement.getText().trim().equalsIgnoreCase(searchValue)) {
				searchFound = true;
				searchElement.waitUntilVisible();
				utilFactory.scrollToElement(searchElement);
				utilFactory.highlight(searchElement);
				clickOn(searchElement);
				break;
			}
		}
		if (!searchFound) {
			logger.debug("Search value= " + searchValue + " not found in grid");
			Assert.fail("Search value= " + searchValue + " not found in grid");
		}
	}

	public void gotoLinkFromDashboard() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		utilFactory.waitUntilLoaderGoes();
		dashboard.waitUntilVisible();
		utilFactory.highlight(dashboard);
		dashboard.waitUntilClickable();
		clickOn(dashboard);
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void clickClosePopup() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		closePopup.waitUntilVisible();
		utilFactory.highlight(closePopup);
		clickOn(closePopup);
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void clickCloseTab(String tabName) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		WebElementFacade closeTab = find(By.xpath(CommonLocators.CLOSE_TAB.replace("tabName", tabName)));
		closeTab.waitUntilVisible();
		utilFactory.highlight(closeTab);
		clickOn(closeTab);
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public static void loadingGoes() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		pageLoader.waitUntilNotVisible();
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void closeModule() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		List<WebElementFacade> modules = findAll(By.xpath(CommonLocators.MODULE_CLOSE_ICON));
		for (WebElementFacade module : modules) {
			if (module.isCurrentlyVisible()) {
				module.waitUntilVisible();
				utilFactory.highlight(module);
				module.click();
			}
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void clickFilterIcon() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		filterIcon.waitUntilVisible();
		utilFactory.highlight(filterIcon);
		clickOn(filterIcon);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	public void applyFilterAndSearch(String searchName, String columnName) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		WebElementFacade columnFilter = find(By.xpath(CommonLocators.COLUMN_NAME.replace("columnName", columnName)));
		columnFilter.waitUntilVisible();
		utilFactory.highlight(columnFilter);
		clickOn(columnFilter);
		textFilter.waitUntilVisible();
		utilFactory.highlight(textFilter);
		clickOn(textFilter);
		filterType.waitUntilVisible();
		utilFactory.highlight(filterType);
		clickOn(filterType);
		WebElementFacade serachBox = find(By.xpath(CommonLocators.SEARCH_BOX.replace("columnName", columnName)));
		serachBox.waitUntilVisible();
		utilFactory.highlight(serachBox);
		typeInto(serachBox, searchName);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());		
	}

	public void buttonFilterIcon(String buttonType) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		WebElementFacade filterTypeButton = find(By.xpath(CommonLocators.FILTER_BUTTON_TYPE.replace("buttonType", buttonType)));
		filterTypeButton.waitUntilVisible();
		utilFactory.highlight(filterTypeButton);
		clickOn(filterTypeButton);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());		
	}
}
