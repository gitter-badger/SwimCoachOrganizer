package ch.tiim.trainingmanager.lenex;

import ch.tiim.trainingmanager.lenex.adapder.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class RecordList {
    @XmlElement(name = "AGEGROUP")
    private AgeGroup ageGroup;
    @XmlAttribute(name = "course", required = true)
    private Course course;
    @XmlAttribute(name = "gender", required = true)
    private Gender gender;
    @XmlAttribute(name = "handicap")
    private Handicap handicap;
    @XmlAttribute(name = "name", required = true)
    private String name;
    @XmlAttribute(name = "nation")
    private String nation;
    @XmlAttribute(name = "order")
    private int order;
    @XmlElement(name = "RECORDS", required = true)
    private Records records;
    @XmlAttribute(name = "region")
    private String region;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "updated")
    private LocalDate updated;
    @XmlAttribute(name = "type")
    private String type;
}
