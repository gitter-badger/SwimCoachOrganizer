package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CONTACT")
public class Contact {

    @XmlAttribute(name = "city")
    private String city;
    @XmlAttribute(name = "country")
    private String country;
    @XmlAttribute(name = "email", required = true)
    private String email;
    @XmlAttribute(name = "fax")
    private String fax;
    @XmlAttribute(name = "internet")
    private String internet;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "mobile")
    private String mobile;
    @XmlAttribute(name = "phone")
    private String phone;
    @XmlAttribute(name = "state")
    private String state;
    @XmlAttribute(name = "street")
    private String street;
    @XmlAttribute(name = "street2")
    private String street2;
    @XmlAttribute(name = "zip")
    private String zip;
}
