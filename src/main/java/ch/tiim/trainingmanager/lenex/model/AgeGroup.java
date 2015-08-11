package ch.tiim.trainingmanager.lenex.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "AGEGROUP")
public class AgeGroup {

    @XmlAttribute(name = "agegroupid", required = true)
    private int agegroup;
    @XmlAttribute(name = "agemax", required = true)
    private int agemax;
    @XmlAttribute(name = "agemin", required = true)
    private int agemin;
    @XmlAttribute(name = "gender")
    private Gender gender;
    @XmlAttribute(name = "calculate")
    private Calculate calculate;
    /**
     * Only 1-15, 20,34,49 allowed
     */
    @XmlAttribute(name = "handicap")
    private int handicap;
    @XmlAttribute(name = "levelmax")
    private String levelmax;
    @XmlAttribute(name = "levelmin")
    private String levelmin;
    @XmlAttribute(name = "levels")
    private String levels;
    @XmlAttribute(name = "mame")
    private String name;
    @XmlElement(name = "RANKINGS")
    private Rankings rankings;

    @XmlType
    @XmlEnum
    public enum Calculate {
        @XmlEnumValue("SINGLE")SINGLE,
        @XmlEnumValue("TOTAL")TOTAL
    }
}