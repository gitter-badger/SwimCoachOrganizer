package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "EVENTS")
public class Events {
    @XmlElement(name = "EVENT")
    private List<Event> events;
}
