package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "TIMESTANDARDS")
public class TimeStandards {
    @XmlElement(name = "TIMESTANDARD")
    List<TimeStandard> timeStandards;
}
