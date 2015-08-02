package ch.tiim.trainingmanager.gui.root;

import ch.tiim.trainingmanager.gui.metadata.FocusView;
import ch.tiim.trainingmanager.gui.metadata.FormView;
import ch.tiim.trainingmanager.gui.sets.SetsView;
import ch.tiim.trainingmanager.gui.team.TeamView;
import ch.tiim.trainingmanager.gui.training.TrainingView;
import ch.tiim.trainingmanager.update.UpdatePerformer;
import ch.tiim.trainingmanager.update.VersionCheckTask;
import ch.tiim.trainingmanager.update.VersionChecker;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class RootPresenter {

    @FXML
    private Tab tabTrainings;
    @FXML
    private Tab tabSets;
    @FXML
    private Tab tabFocus;
    @FXML
    private Tab tabForm;
    @FXML
    private Tab tabTeam;

    private final Service<Boolean> service = new Service<Boolean>() {
        @Override
        protected Task<Boolean> createTask() {
            return new VersionCheckTask();
        }
    };

    @FXML
    private void initialize() throws IOException {
        TrainingView training = new TrainingView();
        tabTrainings.setContent(training.getParent());
        tabTrainings.setOnSelectionChanged(event -> {
            if (tabTrainings.isSelected())
                training.getController().opened();
        });


        SetsView sets = new SetsView();
        tabSets.setContent(sets.getParent());
        tabSets.setOnSelectionChanged(event -> {
            if (tabSets.isSelected())
                sets.getController().opened();
        });

        FocusView focus = new FocusView();
        tabFocus.setContent(focus.getParent());

        FormView form = new FormView();
        tabForm.setContent(form.getParent());

        TeamView team = new TeamView();
        tabTeam.setContent(team.getParent());
        tabTeam.setOnSelectionChanged(event -> {
            if (tabTeam.isSelected())
                team.getController().opened();
        });
    }

    private void initUpdateCheck() {
        service.setOnSucceeded(event -> {
            if ((Boolean) event.getSource().getValue()) {
                askForUpdate();
            }
        });
        service.start();
    }

    private void askForUpdate() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "The new version " + VersionChecker.getRemoteVersion() + " is available.\n" +
                        "Your version is " + VersionChecker.getCurrentVersion() + ".\n" +
                        "Would you like to update?", ButtonType.YES, ButtonType.NO
        );
        alert.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> t = alert.showAndWait();
        if (t.get() == ButtonType.YES) {
            Alert update = new Alert(Alert.AlertType.INFORMATION, "Update is in progress.. The app will close soon.");
            update.initStyle(StageStyle.UNDECORATED);
            update.getButtonTypes().clear();
            update.show();
            new Thread(new UpdatePerformer()).start();
        }
    }
}
