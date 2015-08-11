package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LENEX")
public class Lenex {

    @XmlElement(name = "CONSTRUCTOR", required = true)
    private Constructor constructor;

    @XmlElement(name = "MEETS")
    private Meets meets;

    @XmlElement(name = "RECORDLISTS")
    private RecordLists recordLists;

    @XmlElement(name = "TIMESTANDARDLISTS")
    private TimeStandardLists timeStandardLists;

    @XmlAttribute(name = "version", required = true)
    private String version;


}
