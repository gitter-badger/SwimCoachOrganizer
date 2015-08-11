package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;

@XmlRootElement(name = "AGEDATE")
public class AgeDate {
    @XmlAttribute(name = "type", required = true)
    private Type type;
    @XmlAttribute(name = "value", required = true)
    private LocalDate value;

    @XmlType
    @XmlEnum
    private enum Type {
        @XmlEnumValue("YEAR")YEAR,
        @XmlEnumValue("DATE")DATE,
        @XmlEnumValue("POR")POR,
        @XmlEnumValue("CAN.FNQ")CAN_FNQ,
        @XmlEnumValue("LUX")LUX
    }
}
