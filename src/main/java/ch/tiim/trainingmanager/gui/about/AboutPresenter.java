package ch.tiim.trainingmanager.gui.about;

import ch.tiim.inject.Inject;
import ch.tiim.trainingmanager.Main;
import ch.tiim.trainingmanager.update.VersionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class AboutPresenter {

    @FXML
    private Parent root;
    @FXML
    private ImageView image;
    @FXML
    private Text labelVersion;
    @FXML
    private ImageView imgFlaticon;
    @FXML
    private Hyperlink lnkFlaticon;

    @Inject(name = "app")
    private Main application;

    private Map<Hyperlink, String> links = new HashMap<>();

    @FXML
    private void initialize() {
        image.setImage(getImage("icon.png", 64, 64));
        labelVersion.setText(VersionChecker.getCurrentVersion().toString());
        imgFlaticon.setImage(getImage("freepik.png", 64, 64));

        links.put(lnkFlaticon, "http://www.flaticon.com");
    }


    public void show() {
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    private Image getImage(String path, int width, int height) {
        return new Image(AboutPresenter.class.getResourceAsStream(path));
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @FXML
    private void onLink(ActionEvent event) {
        application.getHostServices().showDocument(links.get(event.getSource()));
    }
}
