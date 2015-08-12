package ch.tiim.sco.gui.newtraining;

import ch.tiim.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewTrainingPresenter {

    private static final String NAME = "New Training";

    @FXML
    private Parent root;

    @FXML
    private TextField fieldName;

    @Inject(name = "main-stage")
    private Stage mainStage;

    private Stage stage;
    private boolean okPressed = false;

    @Inject
    private void injected() {
        stage = new Stage();
        Scene dialog = new Scene(root);
        stage.setScene(dialog);
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void onBtnOk() {
        okPressed = true;
        stage.close();
    }

    @FXML void onBtnCancel() {
        stage.close();
    }

    public boolean showAndWait() {
        stage.showAndWait();
        return okPressed;
    }

    public String getName() {
        return fieldName.getText();
    }
}
