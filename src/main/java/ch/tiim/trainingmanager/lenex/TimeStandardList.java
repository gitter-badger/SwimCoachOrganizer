package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "TIMESTANDARDLIST")
public class TimeStandardList {
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
    @XmlAttribute(name = "timestandardlistid", required = true)
    private int timeStandardListId;
    @XmlElement(name = "TIMESTANDARDS", required = true)
    private TimeStandards timeStandards;
    @XmlAttribute(name = "type")
    private Type type;

    @XmlType
    @XmlEnum
    public enum Type {
        DEFAULT,
        MAXIMUM,
        MINIMUM
    }

    @XmlType
    @XmlEnum
    public enum Handicap {
        @XmlEnumValue("1")C1,
        @XmlEnumValue("2")C2,
        @XmlEnumValue("3")C3,
        @XmlEnumValue("4")C4,
        @XmlEnumValue("5")C5,
        @XmlEnumValue("6")C6,
        @XmlEnumValue("7")C7,
        @XmlEnumValue("8")C8,
        @XmlEnumValue("9")C9,
        @XmlEnumValue("10")C10,
        @XmlEnumValue("11")C11,
        @XmlEnumValue("12")C12,
        @XmlEnumValue("13")C13,
        @XmlEnumValue("14")C14,
        @XmlEnumValue("15")C15,
        @XmlEnumValue("20")C20,
        @XmlEnumValue("34")C34,
        @XmlEnumValue("49")C49,
    }
}
