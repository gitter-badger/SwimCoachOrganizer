package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "POINTTABLE")
public class PointTable {
    @XmlAttribute(name = "name", required = true)
    private String name;
    /**
     * http://www.swimrankings.net/files/Lenex_PointTable.txt
     */
    @XmlAttribute(name = "pointtableid")
    private int pointtableid;
    @XmlAttribute(name = "version", required = true)
    private String version;
}
