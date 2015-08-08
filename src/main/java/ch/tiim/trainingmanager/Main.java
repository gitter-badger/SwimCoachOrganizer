package ch.tiim.trainingmanager;

import ch.tiim.inject.Injector;
import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.DatabaseController;
import ch.tiim.trainingmanager.gui.root.RootView;
import ch.tiim.trainingmanager.update.UpdatePerformer;
import ch.tiim.trainingmanager.update.VersionCheckTask;
import ch.tiim.trainingmanager.update.VersionChecker;
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

import java.util.Optional;


public class Main extends Application {
    private static final Log LOGGER = new Log(Main.class);

    private final Service<Boolean> service = new Service<Boolean>() {
        @Override
        protected Task<Boolean> createTask() {
            return new VersionCheckTask();
        }
    };

    public static void main(final String[] args) {
        launch(args);
    }

    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseController db = new DatabaseController("file.db");
        Injector.getInstance().addInjectable(db, "db-controller");
        mainStage = primaryStage;
        Injector.getInstance().addInjectable(mainStage, "main-stage");
        mainStage.setTitle("TrainingManager " + VersionChecker.getCurrentVersion());
        initRootLayout();
        initUpdateCheck();
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
