package ch.tiim.sco.lenex.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "RECORDS")
public class Records {
    @XmlElement(name = "RECORD")
    private List<Record> records;
}
