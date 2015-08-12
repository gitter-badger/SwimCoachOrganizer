package ch.tiim.trainingmanager.lenex.model;


import ch.tiim.trainingmanager.lenex.adapder.LocalDateAdapter;
import ch.tiim.trainingmanager.lenex.adapder.LocalTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;

@XmlRootElement(name = "MEET")
public class Meet {

    @XmlElement(name = "AGEDATE")
    private AgeDate ageDate;
    @XmlAttribute(name = "altitude")
    private int altitude;
    @XmlAttribute(name = "city", required = true)
    private String city;
    @XmlAttribute(name = "city.en")
    private String cityEn;
    @XmlElement(name = "CLUBS")
    private Clubs clubs;
    @XmlElement(name = "CONTACT")
    private Contact contact;
    @XmlAttribute(name = "course")
    private Course course;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "deadline")
    private LocalDate deadline;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlAttribute(name = "deadlinetime")
    private LocalTime deadlineTime;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "entrystartdate")
    private LocalDate entryStartDate;
    @XmlAttribute(name = "entrytype")
    private EntryType entryType;
    @XmlElement(name = "FEES")
    private Fees fees;
    @XmlAttribute(name = "hostclub")
    private String hostclub;
    @XmlAttribute(name = "hostclub.url")
    private String hostclubUrl;
    @XmlAttribute(name = "maxentries")
    private int maxEntries;
    @XmlAttribute(name = "name", required = true)
    private String name;
    @XmlAttribute(name = "name.en")
    private String nameEn;
    @XmlAttribute(name = "nation", required = true)
    private Nation nation;
    @XmlAttribute(name = "number")
    private String number;
    @XmlAttribute(name = "organizer")
    private String organizer;
    @XmlAttribute(name = "organizer.url")
    private String organizerUrl;
    @XmlElement(name = "POINTTABLE")
    private PointTable pointTable;
    @XmlElement(name = "POOL")
    private Pool pool;
    @XmlElement(name = "QUALIFY")
    private Qualify qualify;
    @XmlAttribute(name = "result.url")
    private String resultUrl;
    @XmlElement(name = "SESSIONS", required = true)
    private Sessions sessions;
    @XmlAttribute(name = "state")
    private String state;
    @XmlAttribute(name = "swrid")
    private String uid;
    @XmlAttribute(name = "timing")
    private Timing timing;
    @XmlAttribute(name = "type")
    private String type;

    @XmlType
    @XmlEnum
    public enum EntryType {
        @XmlEnumValue("OPEN")
        OPEN,
        @XmlEnumValue("INVITATION")
        INVITATION
    }
}
