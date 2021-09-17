package show.ui.locators;

/**
 * This file contains the locators for the different master pages
 * 
 * @author Parth Moradiya
 * 
 *
 *
 **/
public class CommonLocators {

	public static final String GROWL_MESSAGE = "//div[contains(@class,'ui-toast-detail')]";
	public static final String GROWL_DIALOG_EXTENDED = "//p[text()='messageText']";
	public static final String GROWL_CONTAINS_DIALOG_EXTENDED = "//div[contains(text(),'messageText')]";
	public static final String CLICK_YES_POPUP = "//span[contains(text(),'Yes')]";
	public static final String CLICK_NO_POPUP = "//span[contains(text(),'No')]";
	public static final String REDIRECT_TO_LINK = "//div[@class='quick_link']//a[contains(text(),'linkText')]";
	public static final String LABEL_NAME = "//*[self::h3 or self::h4][contains(text(),'labeltext')]";
	public static final String NAVIGATE_MASTER_PAGE = "//a[contains(text(),'tabType')]";
	public static final String SALES_MASTER_DASHBOARD = "#sales_master_dashboardTabButton";
	public static final String INPUT_VALIDATION = "//div[contains(text(),'errorText')]";
	public static final String GROWL_MESSAGE_CLOSE = "//a[contains(@class,'ui-toast-close-icon')]";
	public static final String SCROLL_HARIZONTAL = "(//igx-horizontal-virtual-helper)[2]";
	public static final String LOADER_PATH = "//div[contains(@class,'ui-block-loading-message-panel')]";
	public static final String REDIRECT_TO_DASHBOARD = "//em[contains(@class,'fa-desktop')]";
	public static final String CLOSE_POPUP = "//a[contains(@class,'ui-dialog-titlebar-close')]";
	public static final String CLOSE_TAB = "//span[contains(text(),'tabName')]//parent::div//following-sibling::div//i";
	public static final String PAGE_LOADER = "//div[contains(@class,'loading-message')]";
	public static final String MODULE_CLOSE_ICON = "//i[contains(@id,'ModuleCloseButton')]";
	public static final String FILTER_ICON = "//div[contains(@class,'css-business-summary-filter')]//a[@ptooltip='Show filter']";
	public static final String COLUMN_NAME = "//span[text()='columnName']//ancestor::igx-grid-header//igx-icon[contains(@class,'excel-filter')]";
	public static final String TEXT_FILTER = "//igx-grid-excel-style-filtering//span[text()='Text filter']";
	public static final String FILTER_TYPE = "//igx-drop-down-item//span[text()='Equals']";
	public static final String SEARCH_BOX = "//input[@placeholder='Add filter value']";
	public static final String FILTER_BUTTON_TYPE = "//article[contains(@class,'secondary')]//button[contains(text(),'buttonType')]";
	
}
