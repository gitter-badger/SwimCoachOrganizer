package ch.tiim.trainingmanager.lenex.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "FEE")
public class Fee {
    @XmlAttribute(name = "currency")
    private Currency currency;
    @XmlAttribute(name = "type", required = true)
    private TypeFee type;
    @XmlAttribute(name = "value", required = true)
    private int value;

    @XmlType
    @XmlEnum
    public enum TypeFee {
        @XmlEnumValue("CLUB")CLUB,
        @XmlEnumValue("ATHLETE")ATHLETE,
        @XmlEnumValue("RELAY")RELAY,
        @XmlEnumValue("TEAM")TEAM,
        @XmlEnumValue("LATEENTRY.INDIVIDUAL")LATEENTRY_INDIVIDUAL,
        @XmlEnumValue("LATEENTRY.RELAY")LATEENTRY_RELAY
    }
}
