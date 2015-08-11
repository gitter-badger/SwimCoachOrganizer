package ch.tiim.trainingmanager.lenex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "HEATS")
public class Heats {
    @XmlElement(name = "HEAT")
    private List<Heat> heats;
}
