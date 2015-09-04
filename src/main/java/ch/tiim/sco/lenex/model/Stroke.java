package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Stroke {
    APNEA,
    BACK,
    BREAST,
    FLY,
    FREE,
    IMMERSION,
    MEDLEY,
    SURFACE,
    UNKNOWN;

    public ch.tiim.sco.database.model.Stroke toStroke() {
        switch (this) {
            case BACK: return ch.tiim.sco.database.model.Stroke.BACKSTROKE;
            case BREAST: return ch.tiim.sco.database.model.Stroke.BREASTSTROKE;
            case FLY: return ch.tiim.sco.database.model.Stroke.BUTTERFLY;
            case FREE: return ch.tiim.sco.database.model.Stroke.FREE;
            default: return ch.tiim.sco.database.model.Stroke.OTHER;
        }
    }
}
