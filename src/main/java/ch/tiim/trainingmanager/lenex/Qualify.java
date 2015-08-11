package ch.tiim.trainingmanager.lenex;

import ch.tiim.trainingmanager.lenex.adapder.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "QUALIFY")
public class Qualify {
    @XmlAttribute(name = "conversion")
    private Conversion conversion;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "from", required = true)
    private LocalDate from;
    @XmlAttribute(name = "percent")
    private int percent;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "until")
    private LocalDate until;

    @XmlType
    @XmlEnum
    private enum Conversion {
        NONE, FINA_POINTS, PERCENT_LINEAR, NON_CONFORMING_LAST
    }
}
