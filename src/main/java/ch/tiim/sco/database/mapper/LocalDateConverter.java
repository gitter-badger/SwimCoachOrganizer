package ch.tiim.sco.database.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Converter;

import java.time.LocalDate;

public class LocalDateConverter implements Converter<String, LocalDate> {

    private static final Logger LOGGER = LogManager.getLogger(LocalDateConverter.class.getName());

    @Override
    public LocalDate from(String databaseObject) {
        LOGGER.trace("From String to object");
        return LocalDate.parse(databaseObject);
    }

    @Override
    public String to(LocalDate userObject) {
        LOGGER.trace("From Object to string");
        return userObject.toString();
    }

    @Override
    public Class<String> fromType() {
        return String.class;
    }

    @Override
    public Class<LocalDate> toType() {
        return LocalDate.class;
    }
}
