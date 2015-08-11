package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "CLUBS")
public class Clubs {
    @XmlElement(name = "CLUB")
    private List<Club> clubs;
}
