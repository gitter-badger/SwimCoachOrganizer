package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
    @XmlElement(name = "SWIMSTYLE")
    private SwimStyle swimStyle;
    @XmlAttribute(name = "swimtime")
    private SwimTime swimtime;
    @XmlAttribute(name = "status")
    private String status;
}
