package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TIMESTANDARD")
public class TimeStandard {
    @XmlElement(name = "SWIMSTYLE", required = true)
    private SwimStyle swimStyle;
    @XmlAttribute(name = "swimtime", required = true)
    private SwimTime swimTime;
}
