package name.marmac.tutorials.java.jaxb.xmlclientserver.server;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 22/05/2013
 * Time: 13:14
 * To change this template use File | Settings | File Templates.
 */
public class XMLServerThread extends Thread {

        private XMLEventReader xer = null;
        private Unmarshaller unmarshaller = null;
        private final XMLInputFactory xif;

        /**
         * Default Constructor
         * @param socket
         * @param context
         * @throws java.io.IOException
         * @throws javax.xml.bind.JAXBException
         * @throws javax.xml.stream.XMLStreamException
         */
        XMLServerThread(Socket socket, JAXBContext context) throws IOException, JAXBException, XMLStreamException {
            System.out.println(" XMLServerThread constructor: " + socket.toString());
            this.xif = XMLInputFactory.newInstance();

            //synchronized(XMLServer.this) {
                System.out.println(" Create the XMLEventReader ...");
                xer = xif.createXMLEventReader(socket.getInputStream());
            //}

            System.out.println(" Set up the unmarshaler ...");
            this.unmarshaller = context.createUnmarshaller();

        }

        public void run() {
            try {
                xer.nextEvent();    // read the start document
                xer.nextTag(); // get to the first <conversation> tag, and skip

                while( xer.peek().isStartElement() ) {
                    // unmarshal a new object
                    JAXBElement<String> msg = (JAXBElement<String>)unmarshaller.unmarshal(xer);
                    System.out.println("SRV - Message Received : "+ msg.getValue());
                }
                System.out.println("Bye!");
                xer.close();

                die();

            } catch( Exception e ) {
                e.printStackTrace();
            }
        }


    private void die() {
        // notify the driver that we are done processing

    }

}
