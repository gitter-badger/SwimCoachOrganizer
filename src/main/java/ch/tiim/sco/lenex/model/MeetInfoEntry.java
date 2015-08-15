package ch.tiim.sco.lenex.model;

import ch.tiim.sco.lenex.adapder.LocalDateAdapter;
import ch.tiim.sco.lenex.adapder.LocalTimeAdapter;
import ch.tiim.sco.lenex.adapder.SwimTimeAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;

@XmlRootElement(name = "MEETINFO")
public class MeetInfoEntry {
    @XmlAttribute(name = "approved")
    public String approved;
    @XmlAttribute(name = "city", required = true)
    public String city;
    @XmlAttribute(name = "course")
    public Course course;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "date", required = true)
    public LocalDate date;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlAttribute(name = "daytime")
    public LocalTime daytime;
    @XmlAttribute(name = "name")
    public String name;
    @XmlAttribute(name = "nation", required = true)
    public String nation;
    @XmlElement(name = "POOL")
    public Pool pool;
    @XmlJavaTypeAdapter(SwimTimeAdapter.class)
    @XmlAttribute(name = "qualificationtime")
    public SwimTime qualificationtime;
    @XmlAttribute(name = "state")
    public String state;
}
