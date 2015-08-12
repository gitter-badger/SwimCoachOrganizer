package ch.tiim.updater.metadata;

import javax.xml.bind.annotation.XmlAttribute;

public class Launch {

    @XmlAttribute(name = "arg")
    private String arg;

    public String getArg() {
        return arg;
    }

}
