package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "TIMESTANDARDREFS")
public class TimeStandardRefs {
    @XmlElement(name = "TIMESTANDARDREF")
    private List<TimeStandardRef> timeStandardRefs;
}
