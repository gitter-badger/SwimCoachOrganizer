package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "POOL")
public class Pool {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "lanemax")
    private int lanemax;
    @XmlAttribute(name = "lanemin")
    private int lanemin;
    @XmlAttribute(name = "temperature")
    private int temperature;
    @XmlAttribute(name = "type")
    private TypePool type;

    public enum TypePool {
        INDOOR, OUTDOOR, LAKE, OCEAN
    }
}
