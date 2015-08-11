package ch.tiim.trainingmanager.lenex.model;

import ch.tiim.trainingmanager.lenex.adapder.ReactionTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class RelayPosition {
    @XmlElement(name = "ATHLETE")
    private Athlete athlete;
    @XmlAttribute(name = "athleteid")
    private int athleteid;
    @XmlElement(name = "MEETINFO")
    private MeetInfoEntry meetinfo;
    @XmlAttribute(name = "number", required = true)
    private int number;
    @XmlJavaTypeAdapter(ReactionTimeAdapter.class)
    @XmlAttribute(name = "reactiontime")
    private ReactionTime reactionTime;
    @XmlAttribute(name = "status")
    private StatusRelayPosition status;

    @XmlType
    @XmlEnum
    public enum StatusRelayPosition {
        DSQ, DNF
    }
}
