package name.marmac.tutorials.java.cxf.services.web.rest.tests.clients.webclient;

import name.marmac.tutorials.java.cxf.model.to.customers.CustomersTOType;
import org.junit.Assert;
import org.junit.Test;
/**
 * Created by marcomaccio on 20/08/2014.
 */
public class CustomerProvisioningTest extends AbstractWebClient {

    @Test
    public void testConnect(){
        CustomersTOType customersByQuery = customerProvisioningProxyService.getCustomersByQuery(0,0,"0");
        Assert.assertEquals(2L,customersByQuery.getTotalRecords());
    }
}
