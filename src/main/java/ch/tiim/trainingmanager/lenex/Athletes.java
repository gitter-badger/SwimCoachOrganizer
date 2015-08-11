package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ATHLETES")
public class Athletes {
    @XmlElement(name = "ATHLETE")
    private List<Athlete> athletes;
}
