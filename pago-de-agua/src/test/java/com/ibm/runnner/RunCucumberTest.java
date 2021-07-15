package com.ibm.runnner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/ibm/features", glue = {"com.ibm.seleniumgluecode"}, snippets = CucumberOptions.SnippetType.CAMELCASE)
public class RunCucumberTest {

}
