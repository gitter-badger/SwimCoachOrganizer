package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "AGEGROUP")
public class AgeGroup {

    @XmlAttribute(name = "agegroupid", required = true)
    public int agegroup;
    @XmlAttribute(name = "agemax", required = true)
    public int agemax;
    @XmlAttribute(name = "agemin", required = true)
    public int agemin;
    @XmlAttribute(name = "gender")
    public Gender gender;
    @XmlAttribute(name = "calculate")
    public Calculate calculate;
    /**
     * Only 1-15, 20,34,49 allowed
     */
    @XmlAttribute(name = "handicap")
    public int handicap;
    @XmlAttribute(name = "levelmax")
    public String levelmax;
    @XmlAttribute(name = "levelmin")
    public String levelmin;
    @XmlAttribute(name = "levels")
    public String levels;
    @XmlAttribute(name = "mame")
    public String name;
    @XmlElement(name = "RANKINGS")
    public Rankings rankings;

    @XmlType
    @XmlEnum
    public enum Calculate {
        @XmlEnumValue("SINGLE")SINGLE,
        @XmlEnumValue("TOTAL")TOTAL
    }
}
