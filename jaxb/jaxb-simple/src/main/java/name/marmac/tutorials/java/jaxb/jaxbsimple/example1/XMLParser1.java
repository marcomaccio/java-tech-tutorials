package name.marmac.tutorials.java.jaxb.jaxbsimple.example1;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBElement;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 25/07/2013
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class XMLParser1 {

    /**
     *
     * @param args
     */
    public static void main( String[] args ) {

        Customer customerElement = (new ObjectFactory()).createCustomer();
        customerElement.setCountry("CH");
        customerElement.setCustomerId("123456");
        customerElement.setFirstName("Marco");
        customerElement.setLastName("Maccio");

        // create a Marshaller and marshal to System.out
        JAXB.marshal(customerElement, System.out);

    }
}
