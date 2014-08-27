package name.marmac.tutorials.java.cxf.services.web.rest.clients.webclient;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import name.marmac.tutorials.java.cxf.model.to.customers.CustomersTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.ObjectFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
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

    private ObjectFactory   mCustomersTOTypeObjectFactory;
    private WebClient       mCxfWebClient = null;


    public CustomerProvisioningWebClient(String url){
        //Set up the JAXB ObjectFactory to create the Java Classes to serialize/deserialize the xml
        mCustomersTOTypeObjectFactory = new ObjectFactory();
        //Set up the cxf WebClient
        List<Object> providers = new ArrayList<Object>();
        providers.add( new JacksonJaxbJsonProvider() );
        providers.add( new JAXBElementProvider<CustomersTOType>());
        mCxfWebClient = WebClient.create(url, providers);
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
