package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;

@XmlRootElement(name = "MEETINFO")
public class MeetInfoRecord {
    @XmlAttribute(name = "approved")
    private String approved;
    @XmlAttribute(name = "city", required = true)
    private String city;
    @XmlAttribute(name = "course")
    private Course course;
    @XmlAttribute(name = "date", required = true)
    private LocalDate date;
    @XmlAttribute(name = "daytime")
    private LocalTime daytime;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "nation", required = true)
    private String nation;
    @XmlElement(name = "POOL")
    private Pool pool;
    @XmlAttribute(name = "qualificationtime")
    private SwimTime qualificationtime;
    @XmlAttribute(name = "state")
    private String state;
}
