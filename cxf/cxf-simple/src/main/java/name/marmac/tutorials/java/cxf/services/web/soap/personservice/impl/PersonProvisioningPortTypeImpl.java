package name.marmac.tutorials.java.cxf.services.web.soap.personservice.impl;

import name.marmac.tutorials.java.cxf.services.web.soap.personservice.PersonProvisioningPortType;
import name.marmac.tutorials.java.cxf.services.web.soap.personservice.UnknownPersonFaultException;
import name.marmac.tutorials.java.cxf.services.web.soap.personservice.model.to.ObjectFactory;
import name.marmac.tutorials.java.cxf.services.web.soap.personservice.model.to.PersonTO;
import name.marmac.tutorials.java.cxf.services.web.soap.personservice.model.to.SearchParamsType;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 16/07/2013
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */
@WebService(targetNamespace = "http://marmac.name/tutorials/java/cxf/services/web/soap/customerservice/",
            portName="CustomerProvisioningPort",
            serviceName="CustomerProvisioningService",
            endpointInterface="name.marmac.tutorials.java.cxf.services.web.soap.customerservice.CustomerProvisioningPortType")
public class PersonProvisioningPortTypeImpl implements PersonProvisioningPortType {

    @Override
    public PersonTO getPerson(@WebParam(partName = "inputPayload",
                                        name = "GetPersonRequestElement",
                                        targetNamespace = "http://marmac.name/tutorials/java/cxf/services/web/soap/customerservice/model/to")
                                  SearchParamsType inputPayload) throws UnknownPersonFaultException {

        ObjectFactory objectFactory = new ObjectFactory();

        PersonTO personTO = objectFactory.createPersonTO();

        personTO.setPersonId(inputPayload.getValue());
        personTO.setFirstname("Mickey");
        personTO.setLastname("Mouse");

        return personTO;
    }
}
