package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "TIMESTANDARDREFS")
public class TimeStandardRefs {
    @XmlElement(name = "TIMESTANDARDREF")
    private List<TimeStandardRef> timeStandardRefs;
}
