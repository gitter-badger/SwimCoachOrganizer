package ch.tiim.sco.gui.lenex;

import ch.tiim.inject.Inject;
import ch.tiim.sco.gui.Page;
import ch.tiim.sco.lenex.LenexLoadTask;
import ch.tiim.sco.lenex.model.Lenex;
import ch.tiim.sco.util.FileChooserUtil;
import com.google.common.eventbus.EventBus;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.InputStream;
import java.nio.file.Path;

public class LenexPresenter extends Page {

    private static final FileChooser.ExtensionFilter LENEX_EXT = new FileChooser.ExtensionFilter("LENEX File", "*.lxf", "*.lef");

    @FXML
    private TextField path;
    @FXML
    private ProgressIndicator progress;
    @FXML
    private Label lblApp;
    @FXML
    private Label lblName;
    @FXML
    private Label lblContact;


    @Inject(name = "event-bus")
    private EventBus eventBus;
    @Inject(name = "main-stage")
    private Stage stage;

    private Lenex lenex;

    @FXML
    private void initialize() {

    }

    @FXML
    private void onBtnClose() {

    }

    @FXML
    private void onBtnOpen() {
        Path p = FileChooserUtil.openFile(stage, LENEX_EXT);
        LenexLoadTask lenexLoadTask = new LenexLoadTask(p);
        lenexLoadTask.setOnSucceeded(event -> {
            lenex = lenexLoadTask.getValue();
            lenexChanged();
            progress.setVisible(false);
        });
        eventBus.post(lenexLoadTask);
        progress.setVisible(true);
    }

    private void lenexChanged() {
        lblApp.setText(lenex.constructor.name + " " + lenex.constructor.version);
        lblName.setText(lenex.constructor.registration);
        lblContact.setText(lenex.constructor.contact.email);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public InputStream getIcon() {
        return LenexPresenter.class.getResourceAsStream("icon3.png");
    }

    @Override
    public void opened() {
    }
}
