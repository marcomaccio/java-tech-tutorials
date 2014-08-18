package name.marmac.tutorials.java.jaxb.xmlclientserver;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 31/05/2013
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */
public class TestClass
{
    public static void main(String[] args) throws Exception
    {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(new FileInputStream(new File("/Users/marcomaccio/Documents/Development/sourceCode/java/tutorials/java-tech-tutorial/trunk/java-tech-tutorial/jaxb/xmlclientserver/src/main/data/test_message.xml")));

        while(xmlEventReader.hasNext())
        {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            System.out.println("Event: "            + xmlEvent.toString());
            System.out.println("Event Type: "       + xmlEvent.getEventType());
            System.out.println("Event Location: "   + xmlEvent.getLocation());
        }
    }


}
