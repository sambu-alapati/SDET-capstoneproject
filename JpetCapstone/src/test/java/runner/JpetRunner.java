package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/features",
		glue={"stepDef","hooks"}, monochrome=true,
		plugin = {"pretty", "html:target/cucumber-reports/JpetCapstone.html",
				            "json:target/cucumber-reports/Cucumber.json",
			                "junit:target/cucumber-reports/Cucumber.xml",
			                }
		)
public class JpetRunner extends AbstractTestNGCucumberTests {

	 @Override
	    @DataProvider(parallel = false)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
}
