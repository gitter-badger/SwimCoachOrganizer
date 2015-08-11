package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ENTRY")
public class Entry {
    @XmlAttribute(name = "agegroupid")
    private int agegroupid;
    @XmlAttribute(name = "entrycourse")
    private Course entrycourse;
    @XmlAttribute(name = "entrytime")
    private SwimTime entrytime;
    @XmlAttribute(name = "eventid", required = true)
    private int eventid;
    @XmlAttribute(name = "heatid")
    private int heatid;
    @XmlAttribute(name = "lane")
    private int lane;
    @XmlElement(name = "MEETINFO")
    private MeetInfoEntry meetinfo;
    @XmlElement(name = "RELAYPOSITIONS")
    private RelayPositions relayPositions;
    @XmlAttribute(name = "status")
    private Status status;

    @XmlType
    @XmlEnum
    public enum Status {
        EXH,
        RJC,
        SICK,
        WDR
    }
}
