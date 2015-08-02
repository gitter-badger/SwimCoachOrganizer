package ch.tiim.trainingmanager;

import ch.tiim.inject.Injector;
import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.DatabaseController;
import ch.tiim.trainingmanager.gui.root.RootView;
import ch.tiim.trainingmanager.update.UpdatePerformer;
import ch.tiim.trainingmanager.update.Version;
import ch.tiim.trainingmanager.update.VersionChecker;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;


public class Main extends Application {
    private static final Log LOGGER = new Log(Main.class);

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


    @Override
    public void stop() throws Exception {

    }
}
