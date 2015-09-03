package ch.tiim.sco.database;

import ch.tiim.sco.database.model.SetFocus;

import java.util.List;

public interface TableSetFocus {
    void addSetFocus(SetFocus focus) throws Exception;

    void updateSetFocus(SetFocus focus) throws Exception;

    void deleteSetFocus(SetFocus focus) throws Exception;

    List<SetFocus> getAllFoci() throws Exception;
}
