package ch.tiim.sco.lenex.model;

import ch.tiim.sco.lenex.adapder.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class RecordList {
    @XmlElement(name = "AGEGROUP")
    public AgeGroup ageGroup;
    @XmlAttribute(name = "course", required = true)
    public Course course;
    @XmlAttribute(name = "gender", required = true)
    public Gender gender;
    /**
     * Only 1-15, 20, 34,49 allowed.
     */
    @XmlAttribute(name = "handicap")
    public int handicap;
    @XmlAttribute(name = "name", required = true)
    public String name;
    @XmlAttribute(name = "nation")
    public String nation;
    @XmlAttribute(name = "order")
    public int order;
    @XmlElement(name = "RECORDS", required = true)
    public Records records;
    @XmlAttribute(name = "region")
    public String region;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "updated")
    public LocalDate updated;
    @XmlAttribute(name = "type")
    public String type;
}
