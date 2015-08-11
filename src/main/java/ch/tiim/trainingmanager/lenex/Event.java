package ch.tiim.trainingmanager.lenex;

import ch.tiim.trainingmanager.lenex.adapder.LocalTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;

@XmlRootElement(name = "EVENT")
public class Event {
    @XmlElement(name = "AGEGROUPS")
    private AgeGroups ageGroups;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
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
    private TypeEvent type;

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
    public enum TypeEvent {
        @XmlEnumValue("")EMPTY,
        @XmlEnumValue("MASTERS")MASTERS
    }
}
