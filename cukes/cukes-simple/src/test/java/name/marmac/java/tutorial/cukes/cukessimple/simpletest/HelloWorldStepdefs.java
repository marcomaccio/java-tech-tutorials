package name.marmac.java.tutorial.cukes.cukessimple.simpletest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import name.marmac.java.tutorial.cukes.cukessimple.helloworld.Hello;

import static org.junit.Assert.assertEquals;

/**
 * Created by maccio on 29/01/14.
 */
public class HelloWorldStepdefs
{

    private Hello hello;
    private String  hi;

    @Given("I have a hello app with \"([^\"]*)\"$")
    public void i_have_a_hello_app_with(String greeting)
    {
        hello = new Hello(greeting);

    }

    @When("^I ask it to say hi$")
    public void I_ask_it_to_say_hi() {
        hi = hello.sayHi();
    }

    @Then("^it should answer with \"([^\"]*)\"$")
    public void it_should_answer_with(String expectedHi) {
        assertEquals(expectedHi, hi);
    }
}
