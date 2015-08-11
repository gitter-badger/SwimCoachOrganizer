package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "RECORDLISTS")
public class RecordLists {
    @XmlElement(name = "RECORDLIST")
    private List<RecordList> recordLists;
}
