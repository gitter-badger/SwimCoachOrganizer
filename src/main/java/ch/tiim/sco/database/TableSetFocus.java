package ch.tiim.sco.database;

import ch.tiim.sco.database.model.SetFocus;

import java.util.List;

public interface TableSetFocus {
    void addSetFocus(SetFocus focus);

    void updateSetFocus(SetFocus focus);

    void deleteSetFocus(SetFocus focus);

    List<SetFocus> getAllFoci();
}
