package ch.tiim.trainingmanager.lenex.model;

import ch.tiim.trainingmanager.lenex.adapder.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "ATHLETE")
public class Athlete {
    @XmlAttribute(name = "athleteid", required = true)
    private int athleteid;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "birthdate", required = true)
    private LocalDate birthdate;
    @XmlElement(name = "CLUB")
    private Club club;
    @XmlElement(name = "ENTRIES")
    private Entries entries;
    @XmlAttribute(name = "firstname", required = true)
    private String firstname;
    @XmlAttribute(name = "firstname.en")
    private String firstnameEn;
    @XmlAttribute(name = "gender", required = true)
    private Gender gender;
    @XmlElement(name = "HANDICAP")
    private Handicap handicap;
    @XmlAttribute(name = "lastname", required = true)
    private String lastname;
    @XmlAttribute(name = "lastname.en")
    private String lastnameEn;
    @XmlAttribute(name = "level")
    private String level;
    @XmlAttribute(name = "license")
    private String license;
    @XmlAttribute(name = "nameprefix")
    private String nameprefix;
    @XmlAttribute(name = "nation")
    private Nation nation;
    @XmlAttribute(name = "passport")
    private String passport;
    @XmlElement(name = "RESULTS")
    private Results results;
    @XmlAttribute(name = "swrid")
    private int swrid;
}
