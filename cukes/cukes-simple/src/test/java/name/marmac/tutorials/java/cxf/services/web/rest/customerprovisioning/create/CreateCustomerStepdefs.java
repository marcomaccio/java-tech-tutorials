package name.marmac.tutorials.java.cxf.services.web.rest.customerprovisioning.create;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import name.marmac.tutorials.java.cxf.model.to.customers.CustomerTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.CustomersTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.ObjectFactory;
import name.marmac.tutorials.java.cxf.services.web.rest.clients.webclient.CustomerProvisioningWebClient;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by marcomaccio on 19/08/2014.
 */
public class CreateCustomerStepdefs {

    private ObjectFactory                   mCustomerObjectFactory;
    private CustomersTOType                 mCustomersTOType;
    private CustomersTOType                 mResponseCustomersTOType;
    private CustomerProvisioningWebClient   mCustomerProvisioningWebClient;

    public CreateCustomerStepdefs(){
        mCustomerObjectFactory = new ObjectFactory();
        mCustomerProvisioningWebClient = new CustomerProvisioningWebClient("http://localhost:9092/services/rest/customerservice");
    }

    @Given("^I have a user with this info")
    public void I_have_a_user_with_this_info(List<Customer> customers) {
        Customer customer = customers.get(0);
        String name = customer.firstname;
        String lastname = customer.lastname;

        CustomerTOType customerTOType = mCustomerObjectFactory.createCustomerTOType();
        customerTOType.setFirstname(customer.firstname);
        customerTOType.setLastname(customer.lastname);
        customerTOType.setCustomerId(customer.customerId);
        customerTOType.setCreationDate(new GregorianCalendar());
        customerTOType.setLastUpdateDate(new GregorianCalendar());

        mCustomersTOType = mCustomerObjectFactory.createCustomersTOType();
        mCustomersTOType.getCustomers().add(customerTOType);
    }

    @When("^I call the customer provisioning service")
    public void I_call_the_customer_provisioning_service() {
        mResponseCustomersTOType = mCustomerProvisioningWebClient.createCustomers(mCustomersTOType);
    }

    @Then("^I should retrieve the http response 201")
    public void I_should_retrieve_the_http_response_201() {
        assertEquals(mCustomersTOType, mResponseCustomersTOType);
    }

    public class Customer {

        public String firstname;
        public String lastname;
        public String customerId;
    }
}
