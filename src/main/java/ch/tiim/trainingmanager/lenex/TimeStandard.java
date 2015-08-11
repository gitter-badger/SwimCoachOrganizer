package ch.tiim.trainingmanager.lenex;

import ch.tiim.trainingmanager.lenex.adapder.SwimTimeAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "TIMESTANDARD")
public class TimeStandard {
    @XmlElement(name = "SWIMSTYLE", required = true)
    private SwimStyle swimStyle;
    @XmlJavaTypeAdapter(SwimTimeAdapter.class)
    @XmlAttribute(name = "swimtime", required = true)
    private SwimTime swimTime;
}
