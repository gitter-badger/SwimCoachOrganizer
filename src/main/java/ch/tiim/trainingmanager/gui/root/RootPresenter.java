package ch.tiim.trainingmanager.gui.root;

import ch.tiim.javafx.View;
import ch.tiim.trainingmanager.gui.Page;
import ch.tiim.trainingmanager.gui.member.MemberView;
import ch.tiim.trainingmanager.gui.metadata.FocusView;
import ch.tiim.trainingmanager.gui.metadata.FormView;
import ch.tiim.trainingmanager.gui.sets.SetsView;
import ch.tiim.trainingmanager.gui.team.TeamView;
import ch.tiim.trainingmanager.gui.training.TrainingView;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RootPresenter {

    @FXML
    private TabPane pane;


    @FXML
    private void initialize() throws IOException {
        List<View<? extends Page>> pages = new ArrayList<>();
        pages.add(new TrainingView());
        pages.add(new SetsView());
        pages.add(new FocusView());
        pages.add(new FormView());
        pages.add(new TeamView());
        pages.add(new MemberView());

        for (final View<? extends Page> v : pages) {
            final Tab t = new Tab(v.getController().getName(), v.getParent());
            pane.getTabs().add(t);
            t.setOnSelectionChanged(event -> {
                if(t.isSelected())
                    v.getController().opened();
            });
        }
    }
}
