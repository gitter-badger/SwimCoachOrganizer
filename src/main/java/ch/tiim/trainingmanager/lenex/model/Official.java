package ch.tiim.trainingmanager.lenex.model;


import ch.tiim.trainingmanager.lenex.Contact;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OFFICIAL")
public class Official {
    @XmlElement(name = "CONTACT")
    private Contact contact;
    @XmlAttribute(name = "firstname", required = true)
    private String firstname;
    @XmlAttribute(name = "gender")
    private Gender gender;
    @XmlAttribute(name = "grade")
    private String grade;
    @XmlAttribute(name = "lastname", required = true)
    private String lastname;
    @XmlAttribute(name = "license")
    private String license;
    @XmlAttribute(name = "nameprefix")
    private String nameprefix;
    @XmlAttribute(name = "nation")
    private Nation nation;
    @XmlAttribute(name = "officialid", required = true)
    private int officialid;
    @XmlAttribute(name = "passport")
    private String passport;
}
