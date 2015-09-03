package ch.tiim.sql_xml.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "modules")
public class Modules {

    @XmlElement(name = "module")
    List<Module> modules;

    public List<Module> getModules() {
        return modules;
    }
}
