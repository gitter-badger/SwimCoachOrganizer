package ch.tiim.trainingmanager.gui.training;

import ch.tiim.inject.Inject;
import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.DatabaseController;
import ch.tiim.trainingmanager.database.model.IndexedSet;
import ch.tiim.trainingmanager.database.model.SetFocus;
import ch.tiim.trainingmanager.database.model.SetForm;
import ch.tiim.trainingmanager.database.model.Training;
import ch.tiim.trainingmanager.gui.Page;
import ch.tiim.trainingmanager.gui.addset.AddSetPresenter;
import ch.tiim.trainingmanager.gui.addset.AddSetView;
import ch.tiim.trainingmanager.gui.newtraining.NewTrainingPresenter;
import ch.tiim.trainingmanager.gui.newtraining.NewTrainingView;
import ch.tiim.trainingmanager.print.PrinterNode;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;


public class TrainingPresenter implements Page {
    private static final Log LOGGER = new Log(TrainingPresenter.class);

    @FXML
    private TableColumn<IndexedSet, Number> colIndex;
    @FXML
    private TableColumn<IndexedSet, String> colSetDistance;
    @FXML
    private TableColumn<IndexedSet, String> colSetContent;
    @FXML
    private TableColumn<IndexedSet, String> colSetName;
    @FXML
    private TableColumn<IndexedSet, Number> colSetIntensity;
    @FXML
    private TableColumn<IndexedSet, String> colSetFocus;
    @FXML
    private TableColumn<IndexedSet, String> colSetForm;
    @FXML
    private TableColumn<IndexedSet, String> colSetPause;

    @FXML
    private TableView<IndexedSet> tableTrainingContent;
    @FXML
    private ListView<Training> listTrainings;

    @Inject(name = "db-controller")
    private DatabaseController db;

    private ObservableList<Training> trainings = FXCollections.observableArrayList();
    private ObservableList<IndexedSet> sets = FXCollections.observableArrayList();

    @Inject
    private void injected() {
        updateTrainingsList();
    }

    @Override
    public void opened() {
        updateTrainingsList();
    }

    @Override
    public String getName() {
        return "Training";
    }

    private void updateTrainingsList() {
        try {
            int i = listTrainings.getSelectionModel().getSelectedIndex();
            trainings.setAll(db.getTblTraining().getAllTrainings());
            listTrainings.getSelectionModel().select(i);
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
    }

    private void updateSetsList() {
        int i = tableTrainingContent.getSelectionModel().getSelectedIndex();
        trainingSelected(listTrainings.getSelectionModel().getSelectedItem());
        tableTrainingContent.getSelectionModel().select(i);
    }

    @FXML
    private void initialize() {
        listTrainings.itemsProperty().setValue(trainings);
        listTrainings.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldVal, newVal) -> trainingSelected(newVal)
        );
        tableTrainingContent.setItems(sets);

        colIndex.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getIndex()));
        colSetDistance.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSet().getDistance()));
        colSetContent.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSet().getContent()));
        colSetName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSet().getName()));
        colSetIntensity.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getSet().getIntensity()));
        colSetFocus.setCellValueFactory(d -> {
            SetFocus f = d.getValue().getSet().getFocus();
            return new SimpleStringProperty(f != null ? f.getAbbr() : "-");
        });
        colSetForm.setCellValueFactory(d -> {
            SetForm f = d.getValue().getSet().getForm();
            return new SimpleStringProperty(f != null ? f.getAbbr() : "-");
        });
        colSetPause.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSet().getIntervalString()));
    }

    @FXML
    private void onBtnDeleteTraining() {
        LOGGER.info("Delete training");
        Training t = listTrainings.getSelectionModel().getSelectedItem();
        if (t == null) {
            return;
        }
        try {
            db.getTblTraining().deleteTraining(t);
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
        updateTrainingsList();
    }

    @FXML
    private void onBtnNewTraining() {
        LOGGER.info("New Training");
        NewTrainingView view = new NewTrainingView();
        NewTrainingPresenter c = view.getController();
        if (c.showAndWait()) {
            try {
                db.getTblTraining().addTraining(new Training(0, c.getName()));
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
            updateTrainingsList();
        }
    }

    @FXML
    private void onBtnAddSet() {
        LOGGER.info("Add set");
        Training t = listTrainings.getSelectionModel().getSelectedItem();
        if (t == null) {
            return;
        }
        AddSetView v = new AddSetView();
        AddSetPresenter c = v.getController();
        if (c.showAndWait()) {
            int maxIndex = 0;
            for (IndexedSet s : sets) {
                if (maxIndex < s.getIndex()) {
                    maxIndex = s.getIndex();
                }
            }
            try {
                db.getTblTrainingContent().addSetToTraining(t, c.getSelectedSet(), maxIndex + 1);
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
            updateSetsList();
        }
    }

    @FXML
    private void btnSetMoveUp() {
        Training t = listTrainings.getSelectionModel().getSelectedItem();
        IndexedSet s = tableTrainingContent.getSelectionModel().getSelectedItem();
        if (t != null && s != null && s.getIndex() != 1) {
            try {
                db.getTblTrainingContent().updateIndex(t, s.getIndex(), true);
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
            updateSetsList();
            tableTrainingContent.getSelectionModel().select(s.getIndex() - 2);
        }
    }

    @FXML
    private void btnSetMoveDown() {
        Training t = listTrainings.getSelectionModel().getSelectedItem();
        IndexedSet s = tableTrainingContent.getSelectionModel().getSelectedItem();
        if (t != null && s != null && s.getIndex() != sets.size()) {
            try {
                db.getTblTrainingContent().updateIndex(t, s.getIndex(), false);
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
            updateSetsList();
            tableTrainingContent.getSelectionModel().select(s.getIndex());
        }
    }

    @FXML
    private void btnSetDelete() {
        Training t = listTrainings.getSelectionModel().getSelectedItem();
        IndexedSet s = tableTrainingContent.getSelectionModel().getSelectedItem();
        if (s == null || t == null) return;
        try {
            db.getTblTrainingContent().deleteSet(t, s.getSet(), s.getIndex());
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
        updateSetsList();
    }


    @FXML
    private void onBtnPrint() {
        Training t = listTrainings.getSelectionModel().getSelectedItem();
        PrinterNode node = new PrinterNode(t, sets);
        node.print();
    }

    @FXML
    private void onBtnPreview() {
        Training t = listTrainings.getSelectionModel().getSelectedItem();
        PrinterNode node = new PrinterNode(t, sets);
        node.show();
    }

    private void trainingSelected(Training newVal) {
        if (newVal != null) {
            try {
                sets.setAll(db.getTblTrainingContent().getSetsForTraining(newVal.getId()));
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
        } else {
            sets.clear();
        }
    }
}
