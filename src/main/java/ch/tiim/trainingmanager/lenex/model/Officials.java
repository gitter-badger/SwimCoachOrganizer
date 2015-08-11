package ch.tiim.trainingmanager.lenex.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "OFFICIALS")
public class Officials {
    @XmlElement(name = "OFFICIAL")
    private List<Official> officials;
}
