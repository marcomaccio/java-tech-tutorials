package name.marmac.tutorials.java.cxf.services.web.rest.tests.clients.proxy;

import name.marmac.tutorials.java.cxf.model.to.customers.CustomersTOType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by marcomaccio on 20/08/2014.
 */
public class ProxyClientTest extends AbstractProxyClient {

    /**
     *
     */
    @Test
    public void testGetCustomerByQuery(){
        CustomersTOType customersByQuery = customerProvisioningProxyService.getCustomersByQuery(0,0,null);
        Assert.assertEquals(3L, customersByQuery.getTotalRecords());
    }
}
