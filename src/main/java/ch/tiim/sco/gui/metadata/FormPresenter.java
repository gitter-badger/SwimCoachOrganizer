package ch.tiim.sco.gui.metadata;

import ch.tiim.inject.Inject;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.SetForm;
import ch.tiim.sco.gui.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.SQLException;

public class FormPresenter extends Page {
    private static final Logger LOGGER = LogManager.getLogger(FormPresenter.class.getName());
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

    private void updateFormList() {
            int i = list.getSelectionModel().getSelectedIndex();
            forms.setAll(db.getTblSetForm().getAllForms());
            list.getSelectionModel().select(i);
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
        } else {
            fieldName.setText("");
            fieldAbbr.setText("");
            fieldNotes.setText("");
        }
    }

    @FXML
    void onBtnSave() {
        if (!validate()) return;
        SetForm f = list.getSelectionModel().getSelectedItem();
        if (f == null) {
            onBtnNew();
        } else {
            String name = fieldName.getText();
            String abbr = fieldAbbr.getText();
            String notes = fieldNotes.getText();
                db.getTblSetForm().updateSetForm(new SetForm(f.getId(), name, abbr, notes));
            updateFormList();
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

    @FXML
    void onBtnNew() {
        if (!validate()) return;
        String name = fieldName.getText();
        String abbr = fieldAbbr.getText();
        String notes = fieldNotes.getText();
            db.getTblSetForm().addSetForm(new SetForm(name, abbr, notes));
        updateFormList();
    }

    @FXML
    void onBtnDelete() {
        SetForm f = list.getSelectionModel().getSelectedItem();
        if (f != null) {
                db.getTblSetForm().deleteSetForm(f);
            updateFormList();
        }
    }

    @Override
    public void opened() {

    }

    @Override
    public InputStream getIcon() {
        return FormPresenter.class.getResourceAsStream("form.png");
    }

    @Override
    public String getName() {
        return "Set Form";
    }
}
