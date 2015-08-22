package ch.tiim.sco.database.mapper;

import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.SetFocus;
import ch.tiim.sco.database.model.SetForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Record16;
import org.jooq.RecordMapper;

public class SetMapper implements RecordMapper<Record16<Integer, String, String, Integer, Integer, Integer, Integer, String, Integer, Boolean, Integer, String, String, Integer, String, String>, Set> {

    private static final Logger LOGGER = LogManager.getLogger(SetMapper.class.getName());

    @Override
    public Set map(Record16<Integer, String, String, Integer, Integer, Integer, Integer, String, Integer, Boolean, Integer, String, String, Integer, String, String> record) {
        SetFocus focus = record.getValue("focus_id") == null ? null : new SetFocus(
                (Integer) record.getValue("focus_id"),
                (String) record.getValue("focus_name"),
                (String) record.getValue("focus_abbr"),
                (String) record.getValue("focus_notes")
        );

        SetForm form =record.getValue("form_id") == null ? null : new SetForm(
                (Integer) record.getValue("form_id"),
                (String) record.getValue("form_name"),
                (String) record.getValue("form_abbr"),
                (String) record.getValue("form_notes")
        );

        return new Set(
                (Integer) record.getValue("set_id"),
                (String) record.getValue("name"),
                (String) record.getValue("content"),
                (Integer) record.getValue("distance_f1"),
                (Integer) record.getValue("distance_f2"),
                (Integer) record.getValue("distance_f3"),
                (Integer) record.getValue("intensity"),
                focus,
                form,
                (String) record.getValue("notes"),
                (Integer) record.getValue("interval"),
                (boolean) record.getValue("is_pause")
        );
    }
}
