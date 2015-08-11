package ch.tiim.trainingmanager.lenex;


import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "RECORDS")
public class Records {
    @XmlElement(name = "RECORD")
    private List<Record> records;
}
