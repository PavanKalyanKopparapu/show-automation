package show.runner;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.format.DateTimeFormatter;

import org.junit.runners.model.InitializationError;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.reports.junit.JUnitXMLConverter;

/**
 * 
 * @author Disha Mehta
 * 
 *
 *
 **/

public class CustomCucumberWithSerenity extends CucumberWithSerenity {

	public CustomCucumberWithSerenity(@SuppressWarnings("rawtypes") Class clazz)
			throws InitializationError, IOException {
		super(clazz);
	}

	static {
		try {
			Field timestampFormat = JUnitXMLConverter.class.getDeclaredField("TIMESTAMP_FORMAT");
			timestampFormat.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(timestampFormat, timestampFormat.getModifiers() & ~Modifier.FINAL);

			timestampFormat.set(null, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
