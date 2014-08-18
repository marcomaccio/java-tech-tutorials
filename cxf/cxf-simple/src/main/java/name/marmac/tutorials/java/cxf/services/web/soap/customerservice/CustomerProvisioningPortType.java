package name.marmac.tutorials.java.cxf.services.web.soap.customerservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 27/11/2013
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */
@WebService(targetNamespace = "http://marmac.name/tutorials/java/cxf/services/web/soap/personprovisioning", name = "CustomerProvisioningPortType")
//@XmlSeeAlso({name.marmac.tutorials.java.cxf.services.web.soap.customerservice.model.to.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface CustomerProvisioningPortType {

    @WebResult(name = "GetCustomerResponseElement", targetNamespace = "http://marmac.name/tutorials/java/cxf/services/web/soap/customerservice/model/to/", partName = "customerTO")
        @WebMethod(action = "http://marmac.name/tutorials/java/cxf/web/services/soap/customerservice/getCustomer")
        public name.marmac.tutorials.java.cxf.services.web.soap.customerservice.model.to.CustomerTO getCustomer(
            @WebParam(partName = "searchparams", name = "GetCustomerRequestElement", targetNamespace = "http://marmac.name/tutorials/java/cxf/services/web/soap/customerservice/model/to/")
            name.marmac.tutorials.java.cxf.services.web.soap.customerservice.model.to.SearchParamsType searchparams
        ) throws UnknownCustomerFaultException;

}
