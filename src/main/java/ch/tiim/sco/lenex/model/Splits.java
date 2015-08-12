package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "SPLITS")
public class Splits {
    @XmlElement(name = "SPLIT")
    private List<Split> splits;
}
