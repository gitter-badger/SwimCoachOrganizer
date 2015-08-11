package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "JUDGES")
public class Judges {
    
    @XmlElement(name = "JUDGE")
    private List<Judge> judges;
}
