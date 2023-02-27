package show.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Parth Moradiya
 *
 *
 *         This class contains url constants of websites we deal in this project
 *
 */
public class URLConstants {

	private static Logger logger = LoggerFactory.getLogger(URLConstants.class);

	public static String setUrl() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		String launchUrl = "";
		if (System.getProperty("env").equals("dev")) {
			launchUrl = "http://devpure.srk.best/login";
		} else if (System.getProperty("env").equals("qa")) {
			launchUrl = "http://qapure.srk.best:7007/login";
		} else if (System.getProperty("env").equals("preprod")) {
			launchUrl = "http://preprod/login";
		} else if (System.getProperty("env").equals("stage")) {
			launchUrl = "http://10.0.2.9/login";
		} else {
			logger.debug("Incorrect environment.");
		}

		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
		return launchUrl;

	}

}
