package name.marmac.tutorials.java.cxf.services.web.rest.tests.clients.webclient;

import name.marmac.tutorials.java.cxf.services.web.rest.api.customerservice.CustomerProvisioningService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by marcomaccio on 20/08/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-test.xml" })
public abstract class AbstractWebClient {

    @Autowired
    @Qualifier("customerProvisioningClient")
    protected CustomerProvisioningService customerProvisioningProxyService;
}
