package ch.tiim.trainingmanager.lenex;

import ch.tiim.trainingmanager.lenex.adapder.LocalDateAdapter;
import ch.tiim.trainingmanager.lenex.adapder.LocalTimeAdapter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@XmlRootElement(name = "SESSIONS")
public class Sessions {
    @XmlElement(name = "SESSION")
    private List<Session> sessions;
}
