package name.marmac.tutorials.java.cxf.services.web.rest.tests.clients.webclient;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import name.marmac.tutorials.java.cxf.model.to.customers.CustomersTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.ObjectFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcomaccio on 27/08/2014.
 */
public abstract class AbstractWebClient {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(AbstractWebClient.class);

    protected ObjectFactory   mCustomersTOTypeObjectFactory;
    protected WebClient       mCxfWebClient = null;

    public AbstractWebClient(String url){
        //Set up the JAXB ObjectFactory to create the Java Classes to serialize/deserialize the xml
        mCustomersTOTypeObjectFactory = new ObjectFactory();
        //Set up the cxf WebClient
        List<Object> providers = new ArrayList<Object>();
        providers.add( new JacksonJaxbJsonProvider() );
        providers.add( new JAXBElementProvider<CustomersTOType>());
        mCxfWebClient = WebClient.create(url, providers);
    }
}
