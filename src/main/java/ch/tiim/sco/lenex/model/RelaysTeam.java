package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "RELAYS")
public class RelaysTeam {
    @XmlElement(name = "RELAY")
    private List<RelayMeet> relays;
}
