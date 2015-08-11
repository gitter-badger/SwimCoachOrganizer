package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "FEE")
public class Fee {
    @XmlAttribute(name = "currency")
    private Currency currency;
    @XmlAttribute(name = "type", required = true)
    private Type type;
    @XmlAttribute(name = "value", required = true)
    private int value;

    @XmlType
    @XmlEnum
    private enum Type {
        @XmlEnumValue("CLUB")CLUB,
        @XmlEnumValue("ATHLETE")ATHLETE,
        @XmlEnumValue("RELAY")RELAY,
        @XmlEnumValue("TEAM")TEAM,
        @XmlEnumValue("LATEENTRY.INDIVIDUAL")LATEENTRY_INDIVIDUAL,
        @XmlEnumValue("LATEENTRY.RELAY")LATEENTRY_RELAY
    }
}
