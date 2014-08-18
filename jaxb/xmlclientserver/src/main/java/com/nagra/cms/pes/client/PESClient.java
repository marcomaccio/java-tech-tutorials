package com.nagra.cms.pes.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;

/**
 * This Class implements a version of the Irdeto PESClient hiding the XML Client Server complexity
 * and exposing basic functionalities
 *
 * User: marco.maccio@nagra.com
 * Date: 03/07/2013
 * Time: 16:19
 *
 */
public class PESClient  {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(PESClient.class);

    public static final String CMD_NEW_ASSET                = "newAsset";
    public static final String CMD_SCRAMBLE_ASSET           = "scrambleAsset";
    public static final String CMD_GET_TODO_LIST            = "getToDolist";

    private boolean             connected                   = false;
    private int                 protocolStatus              = 0;
    //ProtocolStatus VALUES
    private static final int WAITING                        = 0;
    private static final int READY_TO_SEND_COMMANDS         = 1;
    private static final int READING_SERVER_RESULT          = 2;

    private static final String ELEMENT_PES_SERVER_SESSION  = "PESServerSession";
    private static final String ELEMENT_RESULT              = "Result";
    private static final String ELEMENT_NOTIFY              = "Notify";
    private static final String ELEMENT_NEWASSET            = "newAsset";
    private static final String ELEMENT_SCRAMBLEASSET       = "scrambleAsset";
    private static final String ELEMENT_GETTODOLIST         = "getToDolist";
    private static final String ELEMENT_DESCRIPTION         = "Description";
    private static final String ELEMENT_TODOLISTITEM        = "ToDolistitem";

    private static final String ATTRIBUTE_COMMAND           = "Command";
    private static final String ATTRIBUTE_CODE              = "Code";
    private static final String ATTRIBUTE_ASSETNAME         = "AssetName";
    private static final String ATTRIBUTE_STATUS         = "Status";

    private String              hostname;
    private int                 port;
    private String              version;

    private Socket              socketClient;
    private InputStream         socketInputStream;
    private OutputStream        socketOutputStream;

    private XMLInputFactory     xmlInputFactory;
    private XMLOutputFactory    xmlOutputFactory;

    private Reader              reader;
    private Writer              writer;

    private XMLEventReader      xmlEventReader;
    private XMLEventWriter      xmlEventWriter;



    private PESClient() {}

    /**
     * Public constructor with hostname and port
     * @param hostname
     * @param port
     */
    public PESClient(String hostname, int port, String version) {
        this.hostname   = hostname;
        this.port       = port;
        this.version    = version;
    }

    /**
     * This method will connect the PES Client with the PES Manager Server
     * after the socket connection has been established:
     * - it will readPESServerSessionOpening that the server replies with PESSession and PESServerSession elements
     * - it will send the server the PESSession and PESClientSession elements
     * done this the PES Client will be ready to send commands to the server.
     *
     * @throws UnknownHostException
     * @throws IOException
     */
    public void connect() throws UnknownHostException, IOException {

        if (LOGGER.isDebugEnabled()){LOGGER.debug("Attempting to connect to " + hostname + ":" + port);}

        socketClient = new Socket(hostname,port);

        if (LOGGER.isDebugEnabled()){LOGGER.debug("Connection Established " + socketClient.getLocalSocketAddress().toString() );}
        connected = true;
        //Create the socketInput & OutputStream
        socketInputStream = socketClient.getInputStream();
        socketOutputStream = socketClient.getOutputStream();

        //XMLFactories Initialization
        //Initialize the xmlFactory to read the InputStream coming from the PES Server
        if (LOGGER.isDebugEnabled()) {LOGGER.debug("Initialize the xmlFactory to read the InputStream coming from the PES Server");}
        xmlInputFactory = XMLInputFactory.newInstance();
        //Initialize the xmlFactory to write on the OutputStream to the PES Server
        if (LOGGER.isDebugEnabled()) {LOGGER.debug("Initialize the xmlFactory to write on the OutputStream to the PES Server");}
        xmlOutputFactory = XMLOutputFactory.newInstance();

        //reader on the inputStream
        if (LOGGER.isDebugEnabled()) {LOGGER.debug("Initialize the reader on the inputStream");}
        reader = new InputStreamReader(socketInputStream);
        //writer on the outputStream
        if (LOGGER.isDebugEnabled()) {LOGGER.debug("Initialize the writer on the outputStream");}
        writer = new OutputStreamWriter(socketOutputStream);

        try {
            //XMLEvent Reader from the XML InputFactory that reads the reader
            if (LOGGER.isDebugEnabled()) {LOGGER.debug("Initialize the xmlEventReader ");}
            xmlEventReader  = xmlInputFactory.createXMLEventReader(reader);

            if (LOGGER.isDebugEnabled()) {LOGGER.debug("Initialize the xmlEventWriter ");}
            //XMLEvent Writer based on the XML OutputFactory that writes on the writer
            xmlEventWriter  = xmlOutputFactory.createXMLEventWriter(writer);

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        //Calls the validation of the PES Server  First Response
        this.readPESServerSessionOpening();
    }

    /**
     * This method disconnects the client from the PES Manager Server.
     */
    public void disconnect() {
        //TODO: write the closing tag for PESClientSession, PESSession
        if (LOGGER.isDebugEnabled()) {LOGGER.debug("Disconnect the client ");}
        try {

            this.writePESClientSessionClosure();
            socketClient.close();

        } catch (SocketException soex ) {
            LOGGER.info("The Socket has been closed by the server after writing closing the PESClientSession and PESSession ");
            soex.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        connected = false;
    }

    /**
     * This method allows verifying whether the client is or not connected to the server.
     * @return
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     *
     */
    public void scrambleAsset() {
        //TODO: insert the parameter needed for newAsset and scrambleAsset command
        //TODO: change the method's signature in order to reply with an object that gives information on the progress

        //TODO: check that the asset is not already present in the ToDolist
        //TODO: create the newAsset command
        //this.writeCommandNewAsset(.....);
        //TODO: read the server result
        //Result newAssetResult = readResultElement();
        //TODO: create the scrambleAsset command
        //this.writeCommandScrambleAsset(....);
        //TODO: read the Server result
        //Result scrambleResult = readResultElement();
        //TODO: Analyse the two results and prepare the object that give the information about the progress
    }

    /**
     * This Method verifies the Execution Status of PES Client Command on a certain content.
     * - the commandName refers to the submitted command by the PES Client
     * - the contentName refers to the content object of the command.
     * this two parameters are used to parse the result of  PES GetToDolist command.
     * @param commandName
     * @param contentName
     */
    public void getCommandStatus(String commandName, String contentName){

        //Check the connection and the protocolStatus (= READY_TO_SEND_COMMANDS)
        if (connected && protocolStatus == READY_TO_SEND_COMMANDS){
            //Create the GetToDolist command and send it
            this.writeCommandGetToDolist();
            //Parse the PESServerResponse, then the Result and then ToDolistitem:
            //      matching the AssetName with ContentName and getting the Status.
            Result result = readResultElement();
            //TODO: parse the ToDolistitem to pick up the right content.

        }

    }

    /**
     *
     *
     */
    private void readPESServerSessionOpening() {
        if (LOGGER.isDebugEnabled()) {LOGGER.debug("Reading the PESServer response ");}
        try {

            while(xmlEventReader.hasNext() && protocolStatus != READY_TO_SEND_COMMANDS){

                XMLEvent event = xmlEventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_DOCUMENT:
                        LOGGER.debug("XML Event type " + event.getEventType() + " -  START DOCUMENT ");

                        break;
                    case XMLStreamConstants.DTD:
                        LOGGER.debug("XML Event type " + event.getEventType() + " -  DTD ");
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        LOGGER.debug("XML Event type " + event.getEventType() +
                                    " -  START ELEMENT : " +
                                    startElement.getName().getLocalPart());
                        if (startElement.getName().getLocalPart().equals(ELEMENT_PES_SERVER_SESSION)){

                            //Create the Start PESClientSession XML block
                            // to start the communication with the PES Server
                            this.writePESClientSessionOpening();

                            //Sets the ProtocolStatus variable to Ready to send command
                            // so that the PES Client can sends commands
                            protocolStatus = READY_TO_SEND_COMMANDS;

                        }
                        break;
                    default:
                        LOGGER.debug("XML Event type " + event.getEventType());
                        break;
                }
            }

        } catch (XMLStreamException xmlstex) {
                xmlstex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }



    /**
     *
     * @return
     */
    private Result readResultElement() {
        LOGGER.debug("Reading the PESServer response ");
        //Set the protocolStatus variable to READING_SERVER_RESULT
        protocolStatus = READING_SERVER_RESULT;

        Result resultElement = new Result();

        try {
            //DO Events until event is and endElement
            while(xmlEventReader.hasNext() && protocolStatus != READY_TO_SEND_COMMANDS ){

                XMLEvent event = xmlEventReader.nextEvent();
                if(event.getEventType() == XMLStreamConstants.START_ELEMENT){
                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().getLocalPart().equals(ELEMENT_RESULT)) {
                        // We read the attributes from this tag and add the date
                        // attribute to our object
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {

                          Attribute attribute = attributes.next();
                          if (attribute.getName().toString().equals(ATTRIBUTE_COMMAND)) {
                              resultElement.setCommand(attribute.getValue());

                          } else if (attribute.getName().toString().equals(ATTRIBUTE_CODE)) {
                              resultElement.setCode(Integer.parseInt(attribute.getValue()));
                          }
                        }

                        LOGGER.debug("Result: " + resultElement.getCommand() + " " + resultElement.getCode());

                    } else if (startElement.getName().getLocalPart().equals(ELEMENT_DESCRIPTION)) {

                        LOGGER.debug("DESCRIPTION TAG" + startElement.getName().getLocalPart());

                    } else if (startElement.getName().getLocalPart().equals(ELEMENT_TODOLISTITEM)) {
                        Iterator<Attribute> attributes = startElement.getAttributes();

                        ToDolistitem toDolistitem = new ToDolistitem();

                        //Parsing the Attributes of the ToDolistitem
                        while (attributes.hasNext()) {

                          Attribute attribute = attributes.next();
                          if (attribute.getName().toString().equals(ATTRIBUTE_ASSETNAME)) {

                              toDolistitem.setAssetName(attribute.getValue());

                          } else if (attribute.getName().toString().equals(ATTRIBUTE_STATUS)) {

                              toDolistitem.setStatus(attribute.getValue());

                          }
                        }
                        resultElement.getToDolist().add(toDolistitem);
                    }
                } else if(event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                    EndElement endElement = event.asEndElement();

                    if (endElement.getName().getLocalPart().equals(ELEMENT_RESULT)) {
                        //set the protocolStatus variable to READY_TO_SEND_COMMANDS
                        protocolStatus = READY_TO_SEND_COMMANDS;
                    }
                }


            }

        } catch (XMLStreamException xmlstex) {

            LOGGER.error("ERROR: " + xmlstex.getMessage());
            xmlstex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return resultElement;
    }

    /**
     *
     * @return
     */
    private Notify readNotifyElement(StartElement startElement) {
        Notify notifyElement = new Notify();

        return notifyElement;
    }

    private void writePESClientSessionOpening() {

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("Preparing the PESClientSession ");
        }

        try {
            XMLEventFactory xmlEventFactory = XMLEventFactory.newInstance();
            //Starting the XML Document
            xmlEventWriter.add(xmlEventFactory.createStartDocument("UTF-8", "1.0"));
            xmlEventWriter.add(xmlEventFactory.createIgnorableSpace("\n"));
            xmlEventWriter.flush();

            //Creating the DTD

            xmlEventWriter.add(xmlEventFactory.createDTD("<!DOCTYPE PESSession SYSTEM \"PESSession.dtd\">"));
            xmlEventWriter.add(xmlEventFactory.createIgnorableSpace("\n"));
            xmlEventWriter.flush();


            //Creating the PESSession Element with the version attribute
            xmlEventWriter.add(xmlEventFactory.createStartElement("", "", "PESSession"));
            xmlEventWriter.add(xmlEventFactory.createAttribute("Version", this.version));
            xmlEventWriter.add(xmlEventFactory.createIgnorableSpace("\n"));
            xmlEventWriter.flush();

            //Creating the PESClientSession Element
            xmlEventWriter.add(xmlEventFactory.createStartElement("", "", "PESClientSession"));
            xmlEventWriter.add(xmlEventFactory.createIgnorableSpace("\n"));
            xmlEventWriter.flush();



        } catch (XMLStreamException xmlex) {
            LOGGER.error("ERROR: " + xmlex.getMessage() + xmlex.getCause());
            xmlex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * This method write the two closing elements for :
     * PESClientSession and PESSession.
     * In this way the server will close the connection.
     *
     */
    private void writePESClientSessionClosure() {

        LOGGER.debug("Preparing the PESClientSession ");


        try {
            XMLEventFactory xmlEventFactory = XMLEventFactory.newInstance();

            //Creating the PESClientSession closing Element
            xmlEventWriter.add(xmlEventFactory.createIgnorableSpace("\n"));
            xmlEventWriter.add(xmlEventFactory.createEndElement("", "", "PESClientSession"));
            xmlEventWriter.add(xmlEventFactory.createIgnorableSpace("\n"));

            //Creating the PESSession closing Element
            xmlEventWriter.add(xmlEventFactory.createEndElement("", "", "PESSession"));
            xmlEventWriter.add(xmlEventFactory.createIgnorableSpace("\n"));
            xmlEventWriter.flush();


        } catch (XMLStreamException xmlex) {
            LOGGER.error("ERROR: " + xmlex.getMessage() + xmlex.getCause() );
            //xmlex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    private void writeCommandGetToDolist(){
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("writing the GetToDoList Command ... ");
        }

        try {
            XMLEventFactory xmlEventFactory = XMLEventFactory.newInstance();

            //Starting the XML Element
            xmlEventWriter.add(xmlEventFactory.createStartElement("", "", ELEMENT_GETTODOLIST ));
            xmlEventWriter.add(xmlEventFactory.createIgnorableSpace("\n"));

            xmlEventWriter.add(xmlEventFactory.createEndElement("", "", ELEMENT_GETTODOLIST));
            xmlEventWriter.flush();

        } catch (XMLStreamException xmlex) {
            LOGGER.error("ERROR: " + xmlex.getMessage() + xmlex.getCause() );
            //xmlex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }



    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.exit(1);
        }
        String remoteHost   = args[0];
        int remoteHostPort  = Integer.parseInt(args[1]);
        String version      = args[2];

            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("Remote Host: " + remoteHost);
                LOGGER.debug("Remote Host Port: " + remoteHostPort);
                LOGGER.debug("PES SERVER Version: " + version);
            }

            //Simulate the call stacks from jBPMAction

            PESClient client = new PESClient(remoteHost, remoteHostPort, version);
            client.connect();
            if (client.isConnected()){
                //client.scrambleAsset();
                String commandName = PESClient.CMD_GET_TODO_LIST;
                String contentName = "Bond 0007";
                client.getCommandStatus(commandName, contentName);
            }
            client.disconnect();
        }
}
