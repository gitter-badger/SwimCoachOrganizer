package ch.tiim.trainingmanager.lenex.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RELAY")
public class RelayMeet {
    @XmlAttribute(name = "agemax", required = true)
    private int agemax;
    @XmlAttribute(name = "agemin", required = true)
    private int agemin;
    @XmlAttribute(name = "agetotalmax", required = true)
    private int agetotalmax;
    @XmlAttribute(name = "agetotalmin", required = true)
    private int agetotalmin;
    @XmlElement(name = "ENTRIES")
    private Entries entries;
    @XmlAttribute(name = "gender", required = true)
    private Gender gender;
    /**
     * Only 0, 20, 34, 49 allowed.
     */
    @XmlAttribute(name = "handicap")
    private int handicap;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "number")
    private int number;
    @XmlElement(name = "RESULTS")
    private Results results;
}
