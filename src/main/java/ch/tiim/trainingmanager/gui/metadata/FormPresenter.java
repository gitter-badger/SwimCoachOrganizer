package ch.tiim.trainingmanager.gui.metadata;

import ch.tiim.inject.Inject;
import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.DatabaseController;
import ch.tiim.trainingmanager.database.model.SetForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class FormPresenter {
    private static final Log LOGGER = new Log(FormPresenter.class);
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldAbbr;
    @FXML
    private TextArea fieldNotes;
    @FXML
    private ListView<SetForm> list;
    @FXML
    private Label labelDesc;

    @Inject(name = "db-controller")
    private DatabaseController db;

    private ObservableList<SetForm> forms = FXCollections.observableArrayList();

    @Inject
    private void injected() {
        updateFormList();
    }

    @FXML
    private void initialize() {
        list.setItems(forms);
        list.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectedSetForm(newValue)
        );
        labelDesc.setText("The form of the set, like freestyle, fly or IM.");
    }

    private void selectedSetForm(SetForm newValue) {
        if (newValue != null) {
            fieldName.setText(newValue.getName());
            fieldAbbr.setText(newValue.getAbbr());
            fieldNotes.setText(newValue.getNotes());
        }
    }

    @FXML
    void onBtnNew() {
        if(!validate()) return;
        String name = fieldName.getText();
        String abbr = fieldAbbr.getText();
        String notes = fieldNotes.getText();
        try {
            db.getTblSetForm().addSetForm(new SetForm(-1, name, abbr, notes));
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
        updateFormList();
    }

    @FXML
    void onBtnSave() {
        if(!validate()) return;
        SetForm f = list.getSelectionModel().getSelectedItem();
        if (f == null) {
            onBtnNew();
        } else {
            String name = fieldName.getText();
            String abbr = fieldAbbr.getText();
            String notes = fieldNotes.getText();
            try {
                db.getTblSetForm().updateSetForm(new SetForm(f.getId(), name, abbr, notes));
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
            updateFormList();
        }
    }

    @FXML
    void onBtnDelete() {
        SetForm f = list.getSelectionModel().getSelectedItem();
        if (f != null) {
            try {
                db.getTblSetForm().deleteSetForm(f.getId());
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
            updateFormList();
        }
    }

    private void updateFormList() {
        try {
            int i = list.getSelectionModel().getSelectedIndex();
            forms.setAll(db.getTblSetForm().getAllForms());
            list.getSelectionModel().select(i);
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
    }

    private boolean validate() {
        if (!fieldName.getText().matches(FocusPresenter.PATTERN_NAME)) {
            fieldName.requestFocus();
            fieldName.selectAll();
            return false;
        }
        if (fieldAbbr.getText().isEmpty()) {
            fieldAbbr.setText(fieldName.getText());
        }
        return true;
    }
}
