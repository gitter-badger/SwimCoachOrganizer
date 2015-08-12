package ch.tiim.updater.metadata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "metadata")
public class Metadata {

    @XmlElement(name = "launch", required = true)
    private Launch launchCommand;

    @XmlElement(name = "build")
    private Build build;


    public Launch getLaunch() {
        return launchCommand;
    }

    public Build getBuild() {
        return build;
    }
}
