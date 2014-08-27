package name.marmac.java.tutorial.cukes.cukessimple.simpletest;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(format={"pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json"})
public class RunCuckesTest {

}
