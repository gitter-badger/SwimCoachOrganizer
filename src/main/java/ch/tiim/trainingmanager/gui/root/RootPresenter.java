package ch.tiim.trainingmanager.gui.root;

import ch.tiim.javafx.View;
import ch.tiim.trainingmanager.gui.Page;
import ch.tiim.trainingmanager.gui.member.MemberView;
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
import javafx.scene.control.TabPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RootPresenter {

    @FXML
    private TabPane pane;

    private final Service<Boolean> service = new Service<Boolean>() {
        @Override
        protected Task<Boolean> createTask() {
            return new VersionCheckTask();
        }
    };

    @FXML
    private void initialize() throws IOException {
        List<View<? extends Page>> pages = new ArrayList<>();
        pages.add(new TrainingView());
        pages.add(new SetsView());
        pages.add(new FocusView());
        pages.add(new FormView());
        pages.add(new TeamView());
        pages.add(new MemberView());

        for (View<? extends Page> v : pages) {
            final Tab t = new Tab(v.getController().getName(), v.getParent());
            pane.getTabs().add(t);
            t.onSelectionChangedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == t) {
                    v.getController().opened();
                }
            });
        }
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
