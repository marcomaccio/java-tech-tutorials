package name.marmac.tutorials.java.cxf.services.web.rest.tests.clients.webclient;

import name.marmac.tutorials.java.cxf.model.to.customers.CustomerTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.CustomersTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.ObjectFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.GregorianCalendar;

/**
 * Created by marcomaccio on 20/08/2014.
 */
public class CustomerProvisioningTest extends AbstractWebClient {

    private ObjectFactory customerObjectFactory = new ObjectFactory();

    public CustomerProvisioningTest()
    {
        super("http://localhost:9092/cxf-rs/services/rest/customerservice");
    }

    /**
     *
     */
    @Test
    public void testGetCustomerByQuery(){
        CustomersTOType customersByQuery = mCxfWebClient.accept("application/json").path("/customers").get(CustomersTOType.class);
        Assert.assertEquals(2L, customersByQuery.getTotalRecords());
    }

    /**
     *
     */
    @Test
    public void testCreateCustomer(){

        //Create the CustomersTOType by the factory
        CustomersTOType customersTOType = customerObjectFactory.createCustomersTOType();

        //Create the CustomerTOType by the factory
        CustomerTOType customerTOType = customerObjectFactory.createCustomerTOType();

        //Set the Customer's attributes
        customerTOType.setFirstname("Test-01-firstname");
        customerTOType.setLastname("Test-01-lastname");
        customerTOType.setCustomerId("IT-001");
        customerTOType.setCreationDate(new GregorianCalendar());
        customerTOType.setLastUpdateDate(new GregorianCalendar());

        //Add the customerTOTYpe to the customersTOType (singular to plural)
        customersTOType.getCustomers().add(customerTOType);

        WebClient.getConfig(mCxfWebClient).getHttpConduit().getClient().setReceiveTimeout(10000000);

        //Call the CustomerProvisioningService by the proxy
        Response response = mCxfWebClient.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).path("/customers").post(customersTOType);

        //CustomersTOType retrievedCustomer = (CustomersTOType)response.getEntity();
        //Verify the result
        Assert.assertEquals(HttpServletResponse.SC_CREATED, response.getStatus());
    }
}
