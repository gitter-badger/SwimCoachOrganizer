package ch.tiim.trainingmanager.lenex.adapder;

import ch.tiim.trainingmanager.lenex.SwimTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwimTimeAdapter extends XmlAdapter<String, SwimTime> {

    private static final Pattern PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})\\.(\\d{1,2})");
    //HH:MM:SS.ss

    @Override
    public SwimTime unmarshal(String v) throws Exception {
        Matcher m = PATTERN.matcher(v);
        if (!m.matches()) throw new IllegalArgumentException();
        return new SwimTime(Integer.parseInt(m.group(0)), Integer.parseInt(m.group(1)),
                Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)));
    }

    @Override
    public String marshal(SwimTime v) throws Exception {
        return v.toString();
    }
}
