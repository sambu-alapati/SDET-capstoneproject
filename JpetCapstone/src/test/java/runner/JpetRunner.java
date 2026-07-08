package runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/features/06_JpetDeleteOrder.feature",
		glue={"stepDef","hooks"}, monochrome=true,
		plugin = {"pretty", "html:target/cucumber-reports/JpetCapstone.html",
				            "json:target/cucumber-reports/Cucumber.json",
			                "junit:target/cucumber-reports/Cucumber.xml",
			                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
			                }
		)
public class JpetRunner extends AbstractTestNGCucumberTests {

	
}
