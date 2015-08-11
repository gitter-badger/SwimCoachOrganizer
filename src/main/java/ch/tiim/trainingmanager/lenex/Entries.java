package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ENTRIES")
public class Entries {
    @XmlElement(name = "ENTRY")
    private List<Entry> entries;
}
