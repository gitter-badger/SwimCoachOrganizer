package ch.tiim.sql_xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "entry")
public class Entry {

    @XmlAttribute(name = "name")
    private String name;

    @XmlValue
    private String value;


    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
