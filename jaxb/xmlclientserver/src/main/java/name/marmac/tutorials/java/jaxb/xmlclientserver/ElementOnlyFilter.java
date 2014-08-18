package name.marmac.tutorials.java.jaxb.xmlclientserver;

import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 31/05/2013
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class ElementOnlyFilter implements EventFilter, StreamFilter
{
    public boolean accept(XMLEvent event) {
        return accept(event.getEventType());
    }

    public boolean accept(XMLStreamReader reader) {
        return accept(reader.getEventType());
    }

    private boolean accept(int eventType) {
        return eventType == XMLStreamConstants.START_ELEMENT
            || eventType == XMLStreamConstants.END_ELEMENT;
    }
}
