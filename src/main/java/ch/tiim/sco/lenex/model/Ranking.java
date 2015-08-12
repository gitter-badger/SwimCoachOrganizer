package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RANKING")
public class Ranking {
    @XmlAttribute(name = "order")
    private int order;
    @XmlAttribute(name = "place", required = true)
    private int place;
    @XmlAttribute(name = "resultid", required = true)
    private int resultid;
}
