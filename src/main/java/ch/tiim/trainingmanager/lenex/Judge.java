package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "JUDFE")
public class Judge {

    @XmlAttribute(name = "number")
    private int number;
    @XmlAttribute(name = "officialid")
    private int officialid;
    @XmlAttribute(name = "role")
    private Role role;

    @XmlType
    @XmlEnum
    public enum Role {
        OTH,
        MDR,
        TDG,
        REF,
        STA,
        ANN,
        JOS,
        CTIK,
        TIK,
        CFIN,
        FIN,
        CIOT,
        IOT,
        FSR,
        COC,
        CREC,
        REC,
        CRS,
        CR,
        MED
    }
}
