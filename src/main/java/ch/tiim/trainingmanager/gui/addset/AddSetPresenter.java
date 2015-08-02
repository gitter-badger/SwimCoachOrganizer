package ch.tiim.trainingmanager.gui.addset;

import ch.tiim.inject.Inject;
import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.DatabaseController;
import ch.tiim.trainingmanager.database.model.Set;
import ch.tiim.trainingmanager.database.model.SetFocus;
import ch.tiim.trainingmanager.database.model.SetForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.logging.Logger;


public class AddSetPresenter {
    private static final Log LOGGER = new Log(AddSetPresenter.class);
    @FXML
    private ListView<Set> listSets;

    @FXML
    private Label labelName;
    @FXML
    private Label labelDistance;
    @FXML
    private Label labelIntensity;
    @FXML
    private Label labelFocus;
    @FXML
    private Label labelForm;
    @FXML
    private Label labelPause;
    @FXML
    private Parent root;

    @Inject(name = "main-stage")
    private Stage mainStage;

    @Inject(name = "db-controller")
    private DatabaseController db;

    private Stage stage;
    private boolean okPressed;
    private ObservableList<Set> sets = FXCollections.observableArrayList();


    @Inject
    private void injected() {
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Select Set");
        try {
            sets.setAll(db.getTblSet().getAllSets());
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
    }

    @FXML
    private void initialize() {
        listSets.itemsProperty().setValue(sets);
        listSets.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectedSet(newValue)
        );
    }

    @FXML
    private void onBtnOk() {
        okPressed = true;
        stage.close();
    }

    @FXML
    private void onBtnCancel() {
        stage.close();
    }

    public boolean showAndWait() {
        stage.showAndWait();
        return okPressed;
    }

    public Set getSelectedSet() {
        return listSets.getSelectionModel().getSelectedItem();
    }

    private void selectedSet(Set newValue) {
        labelName.setText(newValue.getName());
        labelDistance.setText(newValue.getDistance());
        labelIntensity.setText(String.valueOf(newValue.getIntensity()));
        SetFocus f = newValue.getFocus();
        if (f == null) {
            labelFocus.setText("-");
        } else {
            labelFocus.setText(f.toString());
        }
        SetForm fr = newValue.getForm();
        if (f == null) {
            labelForm.setText("-");
        } else {
            labelForm.setText(fr.toString());
        }
        labelPause.setText(newValue.getIntervalString());
    }
}
