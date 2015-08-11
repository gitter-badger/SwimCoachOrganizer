package ch.tiim.trainingmanager.lenex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "AGEGROUPS")
public class AgeGroups {
    @XmlElement(name = "AGEGROUP")
    private List<AgeGroup> ageGroups;
}
