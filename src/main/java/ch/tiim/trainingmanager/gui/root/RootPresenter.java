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
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RootPresenter {

    @FXML
    private ToolBar toolbar;
    @FXML
    private BorderPane root;


    @FXML
    private void initialize() throws IOException {
        List<View<? extends Page>> pages = new ArrayList<>();
        pages.addAll(Arrays.asList(
                new TrainingView(),
                new SetsView(),
                null,
                new FocusView(),
                new FormView(),
                null,
                new TeamView(),
                new MemberView()
        ));
        for (final View<? extends Page> v : pages) {
            if (v == null) {
                toolbar.getItems().add(new Separator());
            } else {
                ImageView view = new ImageView(new Image(v.getController().getIcon(), 32, 32, true, true));
                final Button t = new Button(null, view);
                t.setTooltip(new Tooltip(v.getController().getName()));
                toolbar.getItems().add(t);
                t.setOnAction(event -> {
                    root.setCenter(v.getParent());
                    v.getController().opened();
                });
            }
        }
        root.setCenter(pages.get(0).getParent());
    }
}
