package show.testutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import show.constants.LogConstants;
import show.constants.ResourcePathConstants;
import show.util.UtilFactory;

import net.thucydides.core.webdriver.DriverSource;

/**
 * 
 * @author Parth Moradiya
 * 
 *         This class is used for os detection and based on it drivers are
 *         picked as per name of browser from properties file
 *
 */
public class BeforeUtil implements DriverSource {
	public WebDriver driver;
	String browser;
	DesiredCapabilities capability = new DesiredCapabilities();
	private static Logger logger = LoggerFactory.getLogger(UtilFactory.class);

	@SuppressWarnings("deprecation")
	@Override
	public WebDriver newDriver() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		String os = System.getProperty("os.name").toUpperCase();
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("serenity.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			logger.error(LogConstants.FILE_NOT_FOUND_EXCEPTION + e
					+ Thread.currentThread().getStackTrace()[1].getMethodName());
		} catch (IOException e) {
			logger.error(LogConstants.IO_EXCEPTION + e + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		browser = prop.getProperty("browser");
		logger.info("Browser: " + browser);
		if (os.contains("WINDOWS") || os.contains("LINUX") || os.contains("MAC")) {
			if (browser.equalsIgnoreCase("chrome")) {
				if (os.contains("WINDOWS")) {
					System.out.println("@Windows : " + ResourcePathConstants.CHROME_DRIVER_WINDOWS);
					logger.debug(ResourcePathConstants.CHROME_DRIVER_WINDOWS);
					System.setProperty("webdriver.chrome.driver", ResourcePathConstants.CHROME_DRIVER_WINDOWS);
				} else if (os.contains("MAC")) {
					System.setProperty("webdriver.chrome.driver", ResourcePathConstants.CHROME_DRIVER_MAC);
				} else if (os.contains("LINUX")) {
					System.setProperty("webdriver.chrome.driver", ResourcePathConstants.CHROME_DRIVER_LINUX);
					System.out.println("@LINUX : " + ResourcePathConstants.CHROME_DRIVER_LINUX);
					logger.debug(ResourcePathConstants.CHROME_DRIVER_LINUX);
				}
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("-incognito");
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				WebDriver chromedriver = new ChromeDriver(capabilities);
				return chromedriver;

			} else if (browser.equalsIgnoreCase("firefox")) {
				if (os.contains("WINDOWS")) {
					System.setProperty("webdriver.gecko.driver", ResourcePathConstants.FIREFOX_DRIVER_WINDOWS);
				} else if (os.contains("MAC")) {
					System.setProperty("webdriver.gecko.driver", ResourcePathConstants.FIREFOX_DRIVER_MAC);
				} else if (os.contains("LINUX")) {
					System.setProperty("webdriver.gecko.driver", ResourcePathConstants.FIREFOX_DRIVER_LINUX);
				}
				capability = DesiredCapabilities.firefox();
				capability.setCapability("marionette", true);
				driver = new FirefoxDriver(capability);
				logger.info("FireFox Driver Initailized" + driver);
				return driver;
			} else if (browser.equalsIgnoreCase("ie")) {
				if (os.contains("WINDOWS")) {
					System.setProperty("webdriver.ie.driver", ResourcePathConstants.IE_DRIVER_WINDOWS);
				}
				capability = DesiredCapabilities.internetExplorer();
				capability.setCapability("marionette", true);
				driver = new InternetExplorerDriver(capability);
				logger.info("IE Driver Initailized" + driver);
				return driver;
			}
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
		return null;
	}

	@Override
	public boolean takesScreenshots() {
		return true;
	}

}
