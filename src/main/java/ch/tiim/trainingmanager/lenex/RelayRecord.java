package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RELAY")
public class RelayRecord {
    @XmlElement(name = "CLUB")
    private Club club;
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "RELAYPOSITIONS")
    private RelayPositions relayPositions;
}
