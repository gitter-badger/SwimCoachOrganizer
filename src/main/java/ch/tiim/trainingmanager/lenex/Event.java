package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.*;
import java.time.LocalTime;

@XmlRootElement(name = "EVENT")
public class Event {
    @XmlElement(name = "AGEGROUPS")
    private AgeGroups ageGroups;
    @XmlAttribute(name = "daytime")
    private LocalTime daytime;
    @XmlAttribute(name = "eventid", required = true)
    private int eventid;
    @XmlElement(name = "FEE")
    private Fee fee;
    @XmlAttribute(name = "gender")
    private Gender gender;
    @XmlElement(name = "HEATS")
    private Heats heats;
    @XmlAttribute(name = "maxentries")
    private int maxentries;
    @XmlAttribute(name = "number", required = true)
    private int number;
    @XmlAttribute(name = "order")
    private int order;
    @XmlAttribute(name = "preveventid")
    private int preveventid;
    @XmlAttribute(name = "round")
    private Round round;
    @XmlAttribute(name = "run")
    private int run;
    @XmlElement(name = "SWIMSTYLE")
    private SwimStyle swimstyle;
    @XmlElement(name = "TIMESTANDARDREFS")
    private TimeStandardRefs timeStandardRefs;
    @XmlAttribute(name = "timing")
    private Timing timing;
    @XmlAttribute(name = "type")
    private Type type;

    @XmlType
    @XmlEnum
    public enum Round {
        TIM,
        FHT,
        FIN,
        SEM,
        QUA,
        PRE,
        SOP,
        SOS,
        SOQ
    }

    @XmlType
    @XmlEnum
    public enum Type {
        @XmlEnumValue("")EMPTY,
        @XmlEnumValue("MASTERS")MASTERS
    }
}
