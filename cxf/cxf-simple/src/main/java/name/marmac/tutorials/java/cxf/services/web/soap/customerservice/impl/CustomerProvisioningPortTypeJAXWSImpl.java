package name.marmac.tutorials.java.cxf.services.web.soap.customerservice.impl;

import name.marmac.tutorials.java.cxf.services.web.soap.customerservice.CustomerProvisioningPortType;
import name.marmac.tutorials.java.cxf.services.web.soap.customerservice.UnknownCustomerFaultException;
import name.marmac.tutorials.java.cxf.services.web.soap.customerservice.model.to.CustomerTO;
import name.marmac.tutorials.java.cxf.services.web.soap.customerservice.model.to.SearchParamsType;

import javax.jws.WebParam;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 27/11/2013
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */
public class CustomerProvisioningPortTypeJAXWSImpl implements CustomerProvisioningPortType {
    @Override
    public CustomerTO getCustomer(@WebParam(partName = "searchparams",
                name = "GetCustomerRequestElement",
                targetNamespace = "http://marmac.name/tutorials/java/cxf/services/web/soap/customerservice/model/to/")
                                      SearchParamsType searchparams) throws UnknownCustomerFaultException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
