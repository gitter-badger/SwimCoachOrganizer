package ch.tiim.sco.database.mapper;

import ch.tiim.sco.database.model.IndexedSet;
import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.SetFocus;
import ch.tiim.sco.database.model.SetForm;
import org.jooq.Record;
import org.jooq.RecordMapper;

public class IndexedSetMapper implements RecordMapper<Record, IndexedSet> {


    @Override
    public IndexedSet map(Record record) {
        SetFocus focus = new SetFocus(
                (Integer) record.getValue("set_focus.focus_id"),
                (String) record.getValue("set_focus.name"),
                (String) record.getValue("set_focus.abbr"),
                (String) record.getValue("set_focus.notes")
        );

        SetForm form = new SetForm(
                (Integer) record.getValue("set_form.form_id"),
                (String) record.getValue("set_form.name"),
                (String) record.getValue("set_form.abbr"),
                (String) record.getValue("set_form.notes")
        );

        Set set = new Set(
                (Integer) record.getValue("sets.set_id"),
                (String) record.getValue("sets.name"),
                (String) record.getValue("sets.content"),
                (Integer) record.getValue("sets.distance_f1"),
                (Integer) record.getValue("sets.distance_f2"),
                (Integer) record.getValue("sets.distance_f3"),
                (Integer) record.getValue("sets.intensity"),
                focus,
                form,
                (String) record.getValue("sets.notes"),
                (Integer) record.getValue("sets.interval"),
                (boolean) record.getValue("sets.is_pause")
        );

        return new IndexedSet((Integer) record.getValue("training_content.indx"), set);
    }
}
