package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "RELAYPOSITIONS")
public class RelayPositions {
    @XmlElement(name = "RELAYPOSITION")
    private List<RelayPosition> relayPositions;
}
