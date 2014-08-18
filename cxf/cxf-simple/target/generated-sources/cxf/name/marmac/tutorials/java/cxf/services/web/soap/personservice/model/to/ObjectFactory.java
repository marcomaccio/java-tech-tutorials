
package name.marmac.tutorials.java.cxf.services.web.soap.personservice.model.to;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the name.marmac.tutorials.java.cxf.services.web.soap.personservice.model.to package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPersonResponseElement_QNAME = new QName("http://marmac.name/tutorials/java/cxf/services/web/soap/personservice/model/to/", "GetPersonResponseElement");
    private final static QName _GetPersonRequestElement_QNAME = new QName("http://marmac.name/tutorials/java/cxf/services/web/soap/personservice/model/to/", "GetPersonRequestElement");
    private final static QName _UnknownPersonFaultElement_QNAME = new QName("http://marmac.name/tutorials/java/cxf/services/web/soap/personservice/model/to/", "UnknownPersonFaultElement");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: name.marmac.tutorials.java.cxf.services.web.soap.personservice.model.to
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchParamsType }
     * 
     */
    public SearchParamsType createSearchParamsType() {
        return new SearchParamsType();
    }

    /**
     * Create an instance of {@link PersonTO }
     * 
     */
    public PersonTO createPersonTO() {
        return new PersonTO();
    }

    /**
     * Create an instance of {@link UnknownPersonFaultType }
     * 
     */
    public UnknownPersonFaultType createUnknownPersonFaultType() {
        return new UnknownPersonFaultType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marmac.name/tutorials/java/cxf/services/web/soap/personservice/model/to/", name = "GetPersonResponseElement")
    public JAXBElement<PersonTO> createGetPersonResponseElement(PersonTO value) {
        return new JAXBElement<PersonTO>(_GetPersonResponseElement_QNAME, PersonTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchParamsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marmac.name/tutorials/java/cxf/services/web/soap/personservice/model/to/", name = "GetPersonRequestElement")
    public JAXBElement<SearchParamsType> createGetPersonRequestElement(SearchParamsType value) {
        return new JAXBElement<SearchParamsType>(_GetPersonRequestElement_QNAME, SearchParamsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownPersonFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marmac.name/tutorials/java/cxf/services/web/soap/personservice/model/to/", name = "UnknownPersonFaultElement")
    public JAXBElement<UnknownPersonFaultType> createUnknownPersonFaultElement(UnknownPersonFaultType value) {
        return new JAXBElement<UnknownPersonFaultType>(_UnknownPersonFaultElement_QNAME, UnknownPersonFaultType.class, null, value);
    }

}
