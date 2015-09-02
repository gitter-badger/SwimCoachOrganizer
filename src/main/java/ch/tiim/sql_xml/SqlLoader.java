package ch.tiim.sql_xml;

import ch.tiim.sql_xml.model.Entry;
import ch.tiim.sql_xml.model.Module;
import ch.tiim.sql_xml.model.Modules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXB;
import java.io.InputStream;

public class SqlLoader {
    private static final Logger LOGGER = LogManager.getLogger(SqlLoader.class.getName());
    private final Modules modules;

    public SqlLoader(String file) {
        InputStream is = SqlLoader.class.getResourceAsStream(file);
        modules = JAXB.unmarshal(is, Modules.class);
    }

    public String getValue(String module, String name) {
        for (Module m : modules.getModules()) {
            if (m.getName().equals(module)) {
                for (Entry e : m.getEntries()) {
                    if (e.getName().equals(name)) {
                        return e.getValue();
                    }
                }
            }
        }
        LOGGER.warn("Can't find entry " + module + "." + name);
        return "";
    }
}
