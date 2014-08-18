package name.marmac.tutorials.java.jaxb.xmlclientserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 16/05/2013
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */
public class XMLServer extends Thread {

    ServerSocket serverSocket = null;
    boolean listening   = true;


    public XMLServer() {
        System.out.println("Server Initialization...");

    }


    public void start() {
        try {

            int portNumber = 38200;

            System.out.println("    Socket initialization...");
            serverSocket = new ServerSocket(portNumber);

            System.out.println("    JAXB Context initialization...");
            JAXBContext context = JAXBContext.newInstance("name.marmac.tutorials.java.jaxb.xmlclientserver.session");

            while(listening) {
                System.out.println("    Starting a new XMLServerThread...");
                new XMLServerThread(serverSocket.accept(),context).start();
            }

            serverSocket.close();

        } catch( IOException ioex ) {
            ioex.printStackTrace();
        } catch (JAXBException e) {
             e.printStackTrace();
        } catch (XMLStreamException e) {
             e.printStackTrace();
        }
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        XMLServer server = new XMLServer();
        server.start();
    }

}
