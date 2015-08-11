package ch.tiim.trainingmanager.lenex;

import ch.tiim.trainingmanager.lenex.adapder.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;

@XmlRootElement(name = "HEAT")
public class Heat {
    @XmlAttribute(name = "agegroupid")
    private int agegroupid;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    @XmlAttribute(name = "daytime")
    private LocalTime daytime;
    @XmlAttribute(name = "final")
    private Final finalType;
    @XmlAttribute(name = "heatid", required = true)
    private int heatid;
    @XmlAttribute(name = "number", required = true)
    private int number;
    @XmlAttribute(name = "order")
    private int order;
    @XmlAttribute(name = "status")
    private Status status;

    @XmlType
    @XmlEnum
    private enum Final {
        A, B, C, D
    }

    @XmlType
    @XmlEnum
    private enum Status {
        SEEDED, INOFFICIAL, OFFICIAL
    }
}
