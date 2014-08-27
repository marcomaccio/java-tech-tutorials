package name.marmac.tutorials.java.cxf.services.web.rest.tests.clients.webclient;

import name.marmac.tutorials.java.cxf.model.to.customers.CustomerTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.CustomersTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.ObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.GregorianCalendar;

/**
 * Created by marcomaccio on 20/08/2014.
 */
public class CustomerProvisioningTest extends AbstractWebClient {

    private ObjectFactory customerObjectFactory = new ObjectFactory();



    /**
     *
     */
    @Test
    public void testGetCustomerByQuery(){
        CustomersTOType customersByQuery = customerProvisioningProxyService.getCustomersByQuery(0,0,"0");
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

        //Call the CustomerProvisioningService by the proxy
        CustomersTOType retrievedCustomer = customerProvisioningProxyService.createCustomers(customersTOType);

        //Verify the result
        Assert.assertEquals(customersTOType, retrievedCustomer);
    }
}
