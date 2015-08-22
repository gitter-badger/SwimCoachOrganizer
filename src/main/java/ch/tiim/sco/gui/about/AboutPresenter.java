package ch.tiim.sco.gui.about;

import ch.tiim.inject.Inject;
import ch.tiim.sco.Main;
import ch.tiim.sco.update.VersionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AboutPresenter {

    @FXML
    private Parent root;
    @FXML
    private ImageView image;
    @FXML
    private Text labelVersion;
    @FXML
    private GridPane pane;
    @Inject(name = "app")
    private Main application;
    private Stage stage = null;
    private List<Credit> credits = new ArrayList<>();
    private Map<Hyperlink, String> links = new HashMap<>();

    @FXML
    private void initialize() {
        labelVersion.setText(VersionChecker.getCurrentVersion().toString());

        credits.add(new Credit("freepik.png", "Icons: flaticon.com", "http://www.flaticon.com"));
        credits.add(new Credit("sqlite.png", "Application file format: SQLite", "https://sqlite.org/"));
        credits.add(new Credit("javafx.png", "User interface framework: JavaFx", "http://www.javafx.com/"));

        int i = 0;
        for (Credit c : credits) {
            Hyperlink h = new Hyperlink(c.getText());
            h.setOnAction(this::onLink);
            ImageView v = new ImageView(getImage(c.getImage(), Integer.MAX_VALUE, 64));
            pane.addRow(i++, v, h);
            RowConstraints rc = new RowConstraints(64);
            pane.getRowConstraints().add(rc);
            links.put(h, c.getUrl());
        }
    }

    private Image getImage(String path, int maxWidth, int maxHeight) {
        return new Image(AboutPresenter.class.getResource(path).toString(), maxWidth, maxHeight, true, true, true);
    }

    public void show() {
        if (stage == null) {
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
        }
        stage.show();
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @FXML
    private void onLink(ActionEvent event) {
        application.getHostServices().showDocument(links.get(event.getSource()));
    }

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
}
