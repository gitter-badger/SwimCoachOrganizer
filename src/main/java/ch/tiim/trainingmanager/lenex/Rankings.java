package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "RANKINGS")
public class Rankings {
    @XmlElement(name = "RANKING")
    private List<Ranking> rankings;
}
