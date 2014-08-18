package name.marmac.tutorials.java.cxf.services.web.rest.server.impl.jaxrs.customerservice;

import name.marmac.tutorials.java.cxf.model.to.customers.CustomerTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.CustomersTOType;
import name.marmac.tutorials.java.cxf.model.to.customers.ObjectFactory;
import name.marmac.tutorials.java.cxf.model.to.customers.UnKnownCustomerTOType;
import name.marmac.tutorials.java.cxf.services.web.rest.api.customerservice.CustomerProvisioningService;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 02/12/2013
 * Time: 10:43
 * To change this template use File | Settings | File Templates.
 */
@Path("/customerservice")
@Produces({"application/xml","application/json"})
public class CustomerServiceJAXRSImpl implements CustomerProvisioningService {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(CustomerServiceJAXRSImpl.class);

    //JAX-RS and JAX-WS context
    @javax.ws.rs.core.Context
    private MessageContext  response            = null;
    private ObjectFactory   customerTOFactory   = null;
    private CustomersTOType mCustomerList       = null;



    public CustomerServiceJAXRSImpl() {
        //Initialize the CustomerFactory class
        LOGGER.debug("Initialize the CustomerObjectFactory");
        customerTOFactory = new ObjectFactory();
        initialize();
    }

    @Override
    @POST
    @Consumes({"application/xml", "application/json" })
    @Produces({"application/xml", "application/json" })
    @Path("/customers")
    public CustomersTOType createCustomers(CustomersTOType customerstotype) {
        LOGGER.debug("HTTP METHOD - POST createCustomers has been called ");
        customerstotype.getCustomers().get(0).setId(Long.valueOf(mCustomerList.getCustomers().size() + 1));
        customerstotype.getCustomers().get(0).setCreationDate(new GregorianCalendar());
        customerstotype.getCustomers().get(0).setLastUpdateDate(new GregorianCalendar());
        mCustomerList.getCustomers().add(customerstotype.getCustomers().get(0));

        mCustomerList.setTotalRecords(mCustomerList.getCustomers().size());
        setHTTPResponseCode(HttpServletResponse.SC_CREATED);
        return mCustomerList;
    }

    @Override
    @GET
    @Produces({"application/xml", "application/json" })
    @Path("/customers")
    public CustomersTOType getCustomersByQuery(@QueryParam("limit") Integer limit,
                                               @QueryParam("offset") Integer offset,
                                               @QueryParam("id") String id) {
        LOGGER.debug("HTTP METHOD - GET getCustomersByQuery has been called ");

        //Set the HTTP STATUS CODE to 200 according to the HTTP specifications
        setHTTPResponseCode(HttpServletResponse.SC_OK);

        return mCustomerList;
    }

    @GET
    @Produces({"application/xml", "application/json" })
    @Path("/customers/{id}")
    @Override
    public CustomersTOType getCustomerById(@PathParam("id") String id) {
        LOGGER.debug("HTTP METHOD - GET getCustomerById has been called ");

        CustomersTOType         customerList            = customerTOFactory.createCustomersTOType();
        CustomerTOType          customerA               = customerTOFactory.createCustomerTOType();
        UnKnownCustomerTOType   unKnownCustomerTOType   = customerTOFactory.createUnKnownCustomerTOType();

        if (Long.parseLong(id) == 1) {
            //Create a CustomerA and set its properties
            customerA.setId(Long.valueOf(id));
            customerA.setFirstname("Marco");
            customerA.setLastname("Maccio");
            LOGGER.debug("The Customer " + customerA.toString() + " has been created ");

            //Add the CustomerA to the Customers list that will be return and serialized by the
            customerList.getCustomers().add(customerA);
            LOGGER.debug("The Customer " + customerA.toString() + " has been added to the list  " + customerList.getCustomers().size());

            //Set the HTTP STATUS CODE to 200 according to the HTTP specifications
            setHTTPResponseCode(HttpServletResponse.SC_OK);
        }
        else
        {
            unKnownCustomerTOType.setPersonId(id);
            setHTTPResponseCode(404);
        }
        return customerList;
    }

    private void initialize() {
        mCustomerList            = customerTOFactory.createCustomersTOType();
        CustomerTOType customerA = customerTOFactory.createCustomerTOType();
        //Create a CustomerA and set its properties
        customerA.setId(1L);
        customerA.setFirstname("Marco");
        customerA.setLastname("Maccio");
        customerA.setCustomerId("001");
        customerA.setCreationDate(new GregorianCalendar());
        customerA.setLastUpdateDate(new GregorianCalendar());
        LOGGER.debug("The Customer " + customerA.toString() + " has been created ");

        //Add the CustomerA to the Customers list that will be return and serialized by the
        mCustomerList.getCustomers().add(customerA);
        LOGGER.debug("The Customer " + customerA.toString() + " has been added to the list  " + mCustomerList.getCustomers().size());

        CustomerTOType          customerB               = customerTOFactory.createCustomerTOType();
        //Create a CustomerB and set its properties
        customerB.setId(2L);
        customerB.setFirstname("Catherine");
        customerB.setLastname("Price");
        customerB.setCustomerId("002");
        customerB.setCreationDate(new GregorianCalendar());
        customerB.setLastUpdateDate(new GregorianCalendar());
        LOGGER.debug("The Customer " + customerB.toString() + " has been created ");

        //Add the CustomerA to the Customers list that will be return and serialized by the
        mCustomerList.getCustomers().add(customerB);
        mCustomerList.setTotalRecords(mCustomerList.getCustomers().size());

        LOGGER.debug("The Customer " + customerB.toString() + " has been added to the list  " + mCustomerList.getCustomers().size());
    }

    private void setHTTPResponseCode(int statusCode) {

        // HttpServletResponse.SC_OK            --> Status code (201)
        // HttpServletResponse.SC_CREATED       --> Status code (201)
        // HttpServletResponse.SC_NO_CONTENT    --> Status code (204)
        // HttpServletResponse.SC_BAD_REQUEST   --> Status code (400)
        // HttpServletResponse.SC_UNAUTHORIZED  --> Status code (401)
        // HttpServletResponse.SC_NOT_FOUND     --> Status code (404)
        // HttpServletResponse.SC_CONFLICT      --> Status code (409)
        HttpServletResponse httpResponseCode = response.getHttpServletResponse();
        httpResponseCode.setStatus(statusCode);
    }
}
