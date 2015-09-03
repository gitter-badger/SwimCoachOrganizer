package ch.tiim.sql_xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "module")
public class Module {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "entry")
    private List<Entry> entries;

    public String getName() {
        return name;
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
