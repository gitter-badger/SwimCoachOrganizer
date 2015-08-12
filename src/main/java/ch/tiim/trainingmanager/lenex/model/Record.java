package ch.tiim.trainingmanager.lenex.model;

import ch.tiim.trainingmanager.lenex.adapder.SwimTimeAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "RECORD")
public class Record {
    @XmlElement(name = "ATHLETE")
    private Athlete athlete;
    @XmlAttribute(name = "comment")
    private String comment;
    @XmlElement(name = "MEETINFO")
    private MeetInfoRecord meetInfo;
    @XmlElement(name = "RELAY")
    private RelayRecord relay;
    @XmlElement(name = "SPLITS")
    private Splits splits;
    @XmlElement(name = "SWIMSTYLE", required = true)
    private SwimStyle swimStyle;
    @XmlJavaTypeAdapter(SwimTimeAdapter.class)
    @XmlAttribute(name = "swimtime", required = true)
    private SwimTime swimtime;
    @XmlAttribute(name = "status")
    private String status;
}
