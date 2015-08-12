package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "RELAYPOSITIONS")
public class RelayPositions {
    @XmlElement(name = "RELAYPOSITION")
    private List<RelayPosition> relayPositions;
}
