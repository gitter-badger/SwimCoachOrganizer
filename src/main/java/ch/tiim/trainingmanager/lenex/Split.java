package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SPLIT")
public class Split {
     @XmlAttribute(name = "distance", required = true)
    private int distance;
     @XmlAttribute(name = "swimtime", required = true)
    private SwimTime swimTime;
}
