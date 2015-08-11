package ch.tiim.trainingmanager.lenex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "CLUB")
public class Club {
    @XmlElement(name = "ATHLETES")
    private Athletes athletes;
    @XmlAttribute(name = "code")
    private String code;
    @XmlElement(name = "CONTACT")
    private Contact contact;
    @XmlAttribute(name = "name", required = true)
    private String name;
    @XmlAttribute(name = "name.en")
    private String nameEn;
    @XmlAttribute(name = "nation")
    private Nation nation;
    @XmlAttribute(name = "number")
    private int number;
    @XmlElement(name = "OFFICIALS")
    private Officials officials;
    @XmlAttribute(name = "region")
    private String region;
    @XmlElement(name = "RELAYS")
    private RelaysTeam relays;
    @XmlAttribute(name = "shortname")
    private String shortname;
    @XmlAttribute(name = "shortname.en")
    private String shortnameEn;
    @XmlAttribute(name = "swrid")
    private String swrid;
    @XmlAttribute(name = "type")
    private Type type ;

    @XmlType
    @XmlEnum
    private enum Type {
        @XmlEnumValue("CLUB")CLUB,
        @XmlEnumValue("NATIONALTEAM")NATIONALTEAM,
        @XmlEnumValue("REGIONALTEAM")REGIONALTEAM,
        @XmlEnumValue("UNATTACHED")UNATTACHED
    }
}
