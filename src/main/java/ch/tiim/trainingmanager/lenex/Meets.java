package ch.tiim.trainingmanager.lenex;

import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "MEETS")
public class Meets {

    @XmlElement(name = "MEET")
    private List<Meet> meets;
}
