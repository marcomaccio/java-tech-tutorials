package name.marmac.tutorials.java.jaxb.jaxbsimple.example2;


import com.sun.org.apache.xml.internal.serialize.OutputFormat;

import com.sun.xml.bind.marshaller.XMLWriter;
//import name.marmac.tutorials.java.jaxb.jaxbsimple.NamespaceFilter;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.bind.*;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.sax.SAXSource;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 25/07/2013
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class XMLParser2 {


    public static void main( String[] args ) {

        //Example without Namespace Filter
        PersonInfoType personInfoType = (new ObjectFactory()).createPersonInfoType();
        personInfoType.setCountry("CH");
        personInfoType.setCustomerId("123456");
        personInfoType.setFirstName("Marco");
        personInfoType.setLastName("Maccio");

        JAXBElement<PersonInfoType> customerElement = (new ObjectFactory()).createCustomer(personInfoType);
        // create a Marshaller and marshal to System.out
        JAXB.marshal(customerElement, System.out);

        XMLParser2 parser2 = new XMLParser2();
        parser2.marshallJavaObject();
    }



    private void marshallJavaObject(){
        //Prepare JAXB objects
        JAXBContext jc = null;
        Marshaller marshaller = null;

        try {
            jc = JAXBContext.newInstance("name.marmac.tutorials.java.jaxb.jaxbsimple.example2");
            marshaller = jc.createMarshaller();

        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        //Create a filter that will remove the xmlns attribute
        //NamespaceFilter outFilter = new NamespaceFilter(null, false);

        //Define an output file
        File outputFile = null;
        FileOutputStream fileOutputStream = null;
        try {
            outputFile = new File("target/test-01.xml");
            fileOutputStream = new FileOutputStream(outputFile,true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Writer writer = new OutputStreamWriter(fileOutputStream);

        //Create a new org.dom4j.io.XMLWriter that will serve as the
        //ContentHandler for our filter.
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

        XMLEventWriter xmlEventWriter = null;
        try {

            xmlEventWriter = xmlOutputFactory.createXMLEventWriter(writer);

        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        //Attach the writer to the filter
        //outFilter.setContentHandler((ContentHandler) writer);

        //Example without Namespace Filter
        PersonInfoType personInfoType = (new ObjectFactory()).createPersonInfoType();
        personInfoType.setCountry("CH");
        personInfoType.setCustomerId("123456");
        personInfoType.setFirstName("Marco");
        personInfoType.setLastName("Maccio");

        JAXBElement<PersonInfoType> customerElement = (new ObjectFactory()).createCustomer(personInfoType);

        //Tell JAXB to marshall to the filter which in turn will call the writer
        try {
            marshaller.marshal(customerElement, xmlEventWriter);// outFilter);
        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
