/**
 * Created by Apache CXF WadlToJava code generator
**/
package name.marmac.tutorials.java.cxf.services.web.rest.api.customerservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import name.marmac.tutorials.java.cxf.model.to.customers.CustomersTOType;

@Path("/customerservice")
public interface CustomerProvisioningService {

    @POST
    @Consumes({"application/xml", "application/json" })
    @Produces({"application/xml", "application/json" })
    @Path("/customers")
    CustomersTOType createCustomers(CustomersTOType customerstotype);

    @GET
    @Produces({"application/xml", "application/json" })
    @Path("/customers")
    CustomersTOType getCustomersByQuery(@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset, @QueryParam("id") String id);

    @GET
    @Produces({"application/xml", "application/json" })
    @Path("/customers/{id}")
    CustomersTOType getCustomerById(@PathParam("id") String id);

}