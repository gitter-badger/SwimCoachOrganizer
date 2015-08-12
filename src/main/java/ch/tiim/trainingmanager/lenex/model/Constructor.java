package ch.tiim.trainingmanager.lenex.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CONSTRUCTOR")
public class Constructor {

    @XmlElement(name = "CONTACT", required = true)
    private Contact contact;
    @XmlAttribute(name = "name", required = true)
    private String name;
    @XmlAttribute(name = "registration")
    private String registration;
    @XmlAttribute(name = "version", required = true)
    private String version;
}
