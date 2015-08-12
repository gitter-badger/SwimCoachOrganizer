package ch.tiim.sco.lenex.model;

import ch.tiim.sco.lenex.adapder.ReactionTimeAdapter;
import ch.tiim.sco.lenex.adapder.SwimTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlRootElement(name = "RESULT")
public class Result {
    @XmlAttribute(name = "comment")
    private String comment;
     @XmlAttribute(name = "eventid", required = true)
    private int eventid;
     @XmlAttribute(name = "heatid")
    private int heatid;
     @XmlAttribute(name = "lane")
    private int lane;
     @XmlAttribute(name = "points")
    private int points;
    @XmlJavaTypeAdapter(ReactionTimeAdapter.class)
    @XmlAttribute(name = "reactiontime")
    private ReactionTime reactionTime;
    @XmlElement(name = "RELAYPOSITIONS")
    private RelayPositions relayPositions;
    @XmlAttribute(name = "resultid", required = true)
    private int resultid;
    @XmlAttribute(name = "status")
    private StatusResult status;
    @XmlElement(name = "SPLITS")
    private Splits splits;
    @XmlJavaTypeAdapter(SwimTimeAdapter.class)
    @XmlAttribute(name = "swimtime")
    private SwimTime swimTime;

    @XmlType
    @XmlEnum
    private enum StatusResult {
        EXH,
        DSQ,
        DNS,
        DNF,
        SICK,
        WDR
    }
}
