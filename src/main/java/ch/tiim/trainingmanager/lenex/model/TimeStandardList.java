package ch.tiim.trainingmanager.lenex.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "TIMESTANDARDLIST")
public class TimeStandardList {
    @XmlElement(name = "AGEGROUP")
    private AgeGroup ageGroup;
    @XmlAttribute(name = "course", required = true)
    private Course course;
    @XmlAttribute(name = "gender", required = true)
    private Gender gender;
    /**
     * Only 1-15,20,34,49 allowed.
     */
    @XmlAttribute(name = "handicap")
    private int handicap;
    @XmlAttribute(name = "name", required = true)
    private String name;
    @XmlAttribute(name = "timestandardlistid", required = true)
    private int timeStandardListId;
    @XmlElement(name = "TIMESTANDARDS", required = true)
    private TimeStandards timeStandards;
    @XmlAttribute(name = "type")
    private TypeTimeStandardList type;

    @XmlType
    @XmlEnum
    public enum TypeTimeStandardList {
        DEFAULT,
        MAXIMUM,
        MINIMUM
    }

}
