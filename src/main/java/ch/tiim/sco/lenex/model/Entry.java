package ch.tiim.sco.lenex.model;

import ch.tiim.sco.lenex.adapder.SwimTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "ENTRY")
public class Entry {
    @XmlAttribute(name = "agegroupid")
    private int agegroupid;
    @XmlAttribute(name = "entrycourse")
    private Course entrycourse;
    @XmlJavaTypeAdapter(SwimTimeAdapter.class)
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
    private StatusEntry status;

    @XmlType
    @XmlEnum
    public enum StatusEntry {
        EXH,
        RJC,
        SICK,
        WDR
    }
}
