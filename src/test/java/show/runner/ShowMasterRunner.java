package show.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

/**
 * 
 * @author Parth Moradiya
 * 
 *
 *
 **/
@RunWith(CustomCucumberWithSerenity.class)
@CucumberOptions(plugin = { "pretty" }, features = { "src/test/resources/features/show_master.feature" }, glue = {
		"show.stepdefs" })
public class ShowMasterRunner {

}
