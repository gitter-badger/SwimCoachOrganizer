package ch.tiim.sco.database;

import ch.tiim.sco.database.model.SetForm;

import java.util.List;

public interface TableSetForm {
    void addSetForm(SetForm form)throws Exception;

    void updateSetForm(SetForm form)throws Exception;

    void deleteSetForm(SetForm form)throws Exception;

    List<SetForm> getAllForms()throws Exception;
}
