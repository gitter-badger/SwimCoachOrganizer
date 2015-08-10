package ch.tiim.trainingmanager.gui.root;

import ch.tiim.javafx.View;
import ch.tiim.trainingmanager.gui.Page;
import ch.tiim.trainingmanager.gui.about.AboutView;
import ch.tiim.trainingmanager.gui.birthday.BirthdayView;
import ch.tiim.trainingmanager.gui.member.MemberView;
import ch.tiim.trainingmanager.gui.metadata.FocusView;
import ch.tiim.trainingmanager.gui.metadata.FormView;
import ch.tiim.trainingmanager.gui.sets.SetsView;
import ch.tiim.trainingmanager.gui.team.TeamView;
import ch.tiim.trainingmanager.gui.training.TrainingView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

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
        List<View<? extends Page>> pages = getPages();
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
        toolbar.getItems().addAll(getSpacer(),getBtnAbout());
        root.setCenter(pages.get(0).getParent());
    }

    private List<View<? extends Page>> getPages() {
        List<View<? extends Page>> pages = new ArrayList<>();
        pages.addAll(Arrays.asList(
                new TrainingView(),
                new SetsView(),
                null,
                new FocusView(),
                new FormView(),
                null,
                new TeamView(),
                new MemberView(),
                null,
                new BirthdayView()
        ));
        return pages;
    }

    private Node getSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinWidth(Region.USE_PREF_SIZE);
        return spacer;
    }

    private Node getBtnAbout() {
        Button btnAbout = new Button(null,
                new ImageView(new Image(
                        RootPresenter.class.getResourceAsStream("about.png"), 32, 32, true, true
                ))
        );
        AboutView about = new AboutView();
        btnAbout.setOnAction(event -> about.getController().show());
        return btnAbout;
    }
}
