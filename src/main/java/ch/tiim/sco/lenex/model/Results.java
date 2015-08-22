package ch.tiim.sco.lenex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "RESULTS")
public class Results {
    @XmlElement(name = "RESULT")
    public List<Result> results;
}
