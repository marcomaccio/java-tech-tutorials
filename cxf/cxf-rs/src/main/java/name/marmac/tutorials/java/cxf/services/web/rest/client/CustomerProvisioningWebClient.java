package name.marmac.tutorials.java.cxf.services.web.rest.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import name.marmac.tutorials.java.cxf.model.to.customers.CustomerTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.CustomersTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.ObjectFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcomaccio on 13/08/2014.
 */

public class CustomerProvisioningWebClient {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(CustomerProvisioningWebClient.class);

    private static ObjectFactory   mCustomersTOTypeObjectFactory;
    private WebClient       mCxfWebClient = null;

    public static void main(String[] args){

        CustomerProvisioningWebClient provisioningClient = new CustomerProvisioningWebClient();

        //Calling the retrieve of all customers  (HTTP - GET)
        CustomersTOType customers = provisioningClient.getCustomers();
        LOGGER.info("HTTP - GET /customers/ Customers N." + customers.getCustomers().size());
        customers = null;
        //Calling the retrieve a specific customer (HTTP - GET with id)


        //Create a customer
        customers = mCustomersTOTypeObjectFactory.createCustomersTOType();
        //Create a CustomerTOType to be passed to the server to be created
        CustomerTOType customerA = mCustomersTOTypeObjectFactory.createCustomerTOType();
        customerA.setFirstname("Pippo");
        customerA.setLastname("Disney");
        //Add the customer to the customers
        customers.getCustomers().add(customerA);
        //Calling the create of a new customers (HTTP - POST)
        CustomersTOType retrievedCustomersPOST = provisioningClient.createCustomers(customers);
        LOGGER.info("HTTP - POST /customers/ Customers N." + customers.getCustomers().size());

        //Calling the update of an existent customers (HTTP - PUT)

    }

    public CustomerProvisioningWebClient(){
        //Set up the JAXB ObjectFactory to create the Java Classes to serialize/deserialize the xml
        mCustomersTOTypeObjectFactory = new ObjectFactory();
        //Set up the cxf WebClient
        List<Object> providers = new ArrayList<Object>();
        providers.add( new JacksonJaxbJsonProvider() );
        mCxfWebClient = WebClient.create("http://localhost:9092/services/rest/customerservice",providers);
    }


    public CustomersTOType createCustomers(CustomersTOType customersTOType) {
        Response r = mCxfWebClient.accept("application/json").type("application/json").post(customersTOType);
        LOGGER.info("Response status: " + r.getStatus());

        return customersTOType;
    }

    public CustomersTOType getCustomers() {
        CustomersTOType customers = mCxfWebClient.accept("application/json").path("/customers").get(CustomersTOType.class);

        return customers;
    }
}
