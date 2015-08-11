package ch.tiim.trainingmanager.lenex.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TIMESTANDARDREF")
public class TimeStandardRef {
    @XmlAttribute(name = "timestandardlistid", required = true)
    private int timestandardlistid;
    @XmlElement(name = "FEE")
    private Fee fee;
    @XmlAttribute(name = "marker")
    private String marker;
}
