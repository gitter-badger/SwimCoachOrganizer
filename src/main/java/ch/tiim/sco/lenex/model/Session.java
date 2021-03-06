package ch.tiim.sco.lenex.model;

import ch.tiim.sco.lenex.adapder.LocalDateAdapter;
import ch.tiim.sco.lenex.adapder.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;

@XmlRootElement(name = "SESSION")
public class Session {
    @XmlAttribute(name = "course")
    public Course course;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "date", required = true)
    public LocalDate date;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlAttribute(name = "daytime")
    public LocalTime daytime;
    @XmlElement(name = "EVENTS", required = true)
    public Events events;
    @XmlElement(name = "FEES")
    public Fees fees;
    @XmlElement(name = "JUDGES")
    public Judges judges;
    @XmlAttribute(name = "name")
    public String name;
    @XmlAttribute(name = "number", required = true)
    public int number;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlAttribute(name = "officialmeeting")
    public LocalTime officialmeeting;
    @XmlElement(name = "POOL")
    public Pool pool;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlAttribute(name = "teamleadermeeting")
    public LocalTime teamleadermeeting;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlAttribute(name = "warmupfrom")
    public LocalTime warmupfrom;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlAttribute(name = "warmupuntil")
    public LocalTime warmupuntil;

}
