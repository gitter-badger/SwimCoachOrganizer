package ch.tiim.sco;

import ch.tiim.inject.Injector;
import ch.tiim.log.Log;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.gui.root.RootView;
import ch.tiim.sco.lenex.LenexParser;
import ch.tiim.sco.update.UpdatePerformer;
import ch.tiim.sco.update.Version;
import ch.tiim.sco.update.VersionCheckTask;
import ch.tiim.sco.update.VersionChecker;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;


public class Main extends Application {
    private static final Log LOGGER = new Log(Main.class);

    private final Service<Boolean> service = new Service<Boolean>() {
        @Override
        protected Task<Boolean> createTask() {
            return new VersionCheckTask();
        }
    };
    private Stage mainStage;

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseController db = new DatabaseController("file.db");
        Injector.getInstance().addInjectable(db, "db-controller");
        mainStage = primaryStage;
        Injector.getInstance().addInjectable(mainStage, "main-stage");
        Injector.getInstance().addInjectable(this, "app");
        mainStage.setTitle("Swim Coach Organizer " + VersionChecker.getCurrentVersion());
        initRootLayout();
        parseParameters(getParameters());
        initUpdateCheck();
    }

    private void parseParameters(Parameters p) throws IOException {
        if (getParameters().getNamed().containsKey("version")) {
            VersionChecker.overrideCurrentVersion(new Version(
                    getParameters().getNamed().get("version")
            ));
        }
        if (getParameters().getNamed().containsKey("lenex")) {
            LenexParser parser = new LenexParser();
            parser.read(Paths.get(getParameters().getNamed().get("lenex")));
        }
    }


    private void initRootLayout() {
        RootView view = new RootView();
        Parent rootLayout = view.getParent();

        Scene s = new Scene(rootLayout);
        mainStage.setMinWidth(820);
        mainStage.setMinHeight(475);
        mainStage.setScene(s);
        mainStage.show();
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

    @Override
    public void stop() throws Exception {

    }
}
