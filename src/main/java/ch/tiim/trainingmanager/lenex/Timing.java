package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Timing {
    AUTOMATIC, MANUAL3, MANUAL1
}
