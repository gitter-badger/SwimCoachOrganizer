package ch.tiim.trainingmanager.lenex.model;

import ch.tiim.trainingmanager.lenex.adapder.LocalDateAdapter;
import ch.tiim.trainingmanager.lenex.adapder.LocalTimeAdapter;
import ch.tiim.trainingmanager.lenex.adapder.SwimTimeAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "date", required = true)
    private LocalDate date;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlAttribute(name = "daytime")
    private LocalTime daytime;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "nation", required = true)
    private String nation;
    @XmlElement(name = "POOL")
    private Pool pool;
    @XmlJavaTypeAdapter(SwimTimeAdapter.class)
    @XmlAttribute(name = "qualificationtime")
    private SwimTime qualificationtime;
    @XmlAttribute(name = "state")
    private String state;
}
