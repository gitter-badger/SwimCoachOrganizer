package ch.tiim.trainingmanager.gui.root;

import ch.tiim.trainingmanager.gui.metadata.FocusView;
import ch.tiim.trainingmanager.gui.metadata.FormView;
import ch.tiim.trainingmanager.gui.sets.SetsView;
import ch.tiim.trainingmanager.gui.training.TrainingView;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

import java.io.IOException;

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
    }
}
