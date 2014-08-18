package name.marmac.tutorials.java.jaxb.xmlclientserver.client;

import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;

import name.marmac.tutorials.java.jaxb.xmlclientserver.session.client.NewAssetType;
import name.marmac.tutorials.java.jaxb.xmlclientserver.session.client.ObjectFactory;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 16/05/2013
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public class XMLClient implements Runnable {

    private ObjectFactory of;
    private Marshaller marshaller;
    private String      hostname;
    private int         port;

    public XMLClient(String hostname, int port) {
        this.hostname = hostname;
        this.port     = port;

        try
        {
            JAXBContext jc = JAXBContext.newInstance("name.marmac.tutorials.java.jaxb.xmlclientserver.session");
            marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
            of = new ObjectFactory();

        }
        catch( JAXBException e )
        {
            e.printStackTrace(); // impossible
        }
    }

    public void run()
    {
        try
        {
            // create a socket connection and start conversation
            Socket socket = new Socket(hostname,port);
            System.out.println(" Socket connected to " + socket.toString());
            XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(socket.getOutputStream());

            // write the dummy start tag
            xsw.writeStartDocument();
            xsw.writeStartElement("XMLSession");
            xsw.writeStartElement("XMLClientSession");

            for( int i=1; i<=10; i++ )
            {
                Thread.sleep(1000);
                sendMessage(xsw,"message " + i);
            }

            Thread.sleep(1000);

            xsw.writeEndElement();
            xsw.writeEndElement();
            xsw.writeEndDocument();
            xsw.close();


        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    private void sendMessage( XMLStreamWriter xsw, String msg ) throws JAXBException, XMLStreamException {

        NewAssetType newAssetType = of.createNewAssetType();
        newAssetType.setUrl("http://www.marmac.name/_/rsrc/1349444128344/home/1342895964_code.png");
        newAssetType.setDescription("website icon");
        newAssetType.setType("icon/jpg");

        System.out.println(" jaxbelement by the client: " + newAssetType.toString());
        marshaller.marshal(newAssetType,xsw);

        xsw.flush();    // send it now
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.exit(1);
        }
        String remoteHost = args[0];
        int remoteHostPort = Integer.parseInt(args[1]);

        System.out.println("Remote Host: " + remoteHost);
        System.out.println("Remote Host Port: " + remoteHostPort);

        XMLClient client = new XMLClient(remoteHost, remoteHostPort);
        client.run();
    }
}
