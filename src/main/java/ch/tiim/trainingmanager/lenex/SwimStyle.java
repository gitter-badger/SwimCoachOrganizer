package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "SWIMSTYLE")
public class SwimStyle {
    @XmlAttribute(name = "code")
    private String code;
    @XmlAttribute(name = "distance", required = true)
    private int distance;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "relaycount", required = true)
    private int relaycount;
    @XmlAttribute(name = "stroke", required = true)
    private Stroke stroke;
    @XmlAttribute(name = "swimstyleid")
    private int swimstyleid;
    @XmlAttribute(name = "technique")
    private Technique technique;

    @XmlType
    @XmlEnum
    public enum Technique {
        DIVE,
        GLIDE,
        KICK,
        PULL,
        START,
        TURN
    }
}
