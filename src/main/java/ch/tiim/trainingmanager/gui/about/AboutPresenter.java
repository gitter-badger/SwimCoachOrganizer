package ch.tiim.trainingmanager.gui.about;

import ch.tiim.inject.Inject;
import ch.tiim.trainingmanager.Main;
import ch.tiim.trainingmanager.update.VersionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AboutPresenter {

    private static class Credit {
        private String image;
        private String text;
        private String url;

        public Credit(String image, String text, String url) {
            this.image = image;
            this.text = text;
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public String getText() {
            return text;
        }

        public String getUrl() {
            return url;
        }
    }

    @FXML
    private Parent root;
    @FXML
    private ImageView image;
    @FXML
    private Text labelVersion;
    @FXML
    private VBox vbox;

    @Inject(name = "app")
    private Main application;

    private Stage stage = null;

    private List<Credit> credits = new ArrayList<>();
    private Map<Hyperlink, String> links = new HashMap<>();

    @FXML
    private void initialize() {
        image.setImage(getImage("icon.png"));
        labelVersion.setText(VersionChecker.getCurrentVersion().toString());

        credits.add(new Credit("freepik.png","Icons from: flaticon.com", "http://www.flaticon.com"));

        for(Credit c : credits) {
            HBox hBox = new HBox();
            hBox.alignmentProperty().setValue(Pos.CENTER);
            Hyperlink h = new Hyperlink(c.getText());
            h.setOnAction(this::onLink);
            hBox.getChildren().addAll(new ImageView(getImage(c.getImage())),h);
            vbox.getChildren().add(hBox);
            links.put(h,c.getUrl());
        }
    }


    public void show() {
        if (stage == null) {
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
        }
        stage.show();
    }

    private Image getImage(String path) {
        return new Image(AboutPresenter.class.getResourceAsStream(path));
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @FXML
    private void onLink(ActionEvent event) {
        application.getHostServices().showDocument(links.get(event.getSource()));
    }
}
