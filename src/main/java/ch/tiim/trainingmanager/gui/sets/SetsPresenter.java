package ch.tiim.trainingmanager.gui.sets;

import ch.tiim.inject.Inject;
import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.DatabaseController;
import ch.tiim.trainingmanager.database.model.Set;
import ch.tiim.trainingmanager.database.model.SetFocus;
import ch.tiim.trainingmanager.database.model.SetForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;


public class SetsPresenter {
    private static final Log LOGGER = new Log(SetsPresenter.class);
    @FXML
    private ListView<Set> listSets;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldDistance1;
    @FXML
    private TextField fieldDistance2;
    @FXML
    private TextField fieldDistance3;
    @FXML
    private Slider sliderIntencity;
    @FXML
    private ChoiceBox<SetFocus> choiceFocus;
    @FXML
    private ChoiceBox<SetForm> choiceForm;
    @FXML
    private TextField fieldPause;
    @FXML
    private TextArea areaContent;
    @FXML
    private TextArea areaNotes;

    @Inject(name = "db-controller")
    private DatabaseController db;

    private final ObservableList<Set> sets = FXCollections.observableArrayList();
    private final ObservableList<SetFocus> foci = FXCollections.observableArrayList();
    private final ObservableList<SetForm> forms = FXCollections.observableArrayList();

    public void opened() {
        try {
            foci.setAll(db.getTblSetFocus().getAllFoci());
            foci.add(0, new SetFocus(0, "Nothing", "-", ""));
            forms.setAll(db.getTblSetForm().getAllForms());
            forms.add(0, new SetForm(0, "Nothing", "-", ""));
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
    }

    @Inject
    private void injected() {
        updateSetList();
    }

    @FXML
    private void initialize() {
        listSets.itemsProperty().setValue(sets);
        listSets.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            selectedNewSet(newVal);
        });

        choiceFocus.itemsProperty().setValue(foci);
        choiceForm.itemsProperty().setValue(forms);
    }

    @FXML
    private void onBtnNew() {
        LOGGER.info("new set created");
        Set set = getSetFromFields();
        try {
            db.getTblSet().addSet(set);
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
        updateSetList();
    }

    @FXML
    private void onBtnSave() {
        Set set = listSets.getSelectionModel().getSelectedItem();
        if (set == null) {
            onBtnNew();
        } else {
            Set newSet = getSetFromFields();
            newSet.setId(set.getId());
            try {
                db.getTblSet().updateSet(newSet);
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
            updateSetList();
        }
    }

    @FXML
    private void onBtnDelete() {
        LOGGER.info("Delete set");
        try {
            db.getTblSet().deleteSet(listSets.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
        updateSetList();
    }

    private void selectedNewSet(Set newVal) {
        if (newVal != null) {
            fieldName.setText(newVal.getName());
            fieldDistance1.setText(Integer.toString(newVal.getDistance1()));
            fieldDistance2.setText(Integer.toString(newVal.getDistance2()));
            fieldDistance3.setText(Integer.toString(newVal.getDistance3()));
            sliderIntencity.setValue(((double) newVal.getIntensity()));
            fieldPause.setText(Integer.toString(newVal.getInterval()));
            if (newVal.getFocus() == null) {
                choiceFocus.getSelectionModel().select(0);
            } else {
                choiceFocus.getSelectionModel().select(newVal.getFocus());
            }
            if (newVal.getForm() == null) {
                choiceForm.getSelectionModel().select(0);
            } else {
                choiceForm.getSelectionModel().select(newVal.getForm());
            }
            areaContent.setText(newVal.getContent());
            areaNotes.setText(newVal.getNotes());
        }

    }

    private void updateSetList() {
        try {
            int i = listSets.getSelectionModel().getSelectedIndex();
            sets.setAll(db.getTblSet().getAllSets());
            listSets.getSelectionModel().select(i);
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
    }

    private Set getSetFromFields() {
        return new Set(
                -1,
                fieldName.getText(),
                areaContent.getText(),
                Integer.parseInt(fieldDistance1.getText()),
                Integer.parseInt(fieldDistance2.getText()),
                Integer.parseInt(fieldDistance3.getText()),
                (int) sliderIntencity.getValue(),
                choiceFocus.getValue(),
                choiceForm.getValue(),
                areaNotes.getText(),
                Integer.parseInt(fieldPause.getText()),
                true //TODO: Pause/Intervall
        );
    }

}
