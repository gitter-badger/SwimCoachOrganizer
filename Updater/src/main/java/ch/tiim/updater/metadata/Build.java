package ch.tiim.updater.metadata;

import javax.xml.bind.annotation.XmlAttribute;

public class Build {

    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "date")
    private String date;

    public String getDate() {
        return date;
    }

    public String getVersion() {
        return version;
    }
}
